import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;

/**
 * Runs a learner's program so that its output looks like a real screen.
 *
 * When a person types at a terminal, what they type appears on the screen.
 * When a test feeds input through a pipe, it does not - so a program that
 * prints "Host: " and then reads WEB-01 would produce "Host: " followed
 * directly by its next line of output, and nobody could match that against
 * the sample run in the brief.
 *
 * This class stands in front of the learner's Main. It replaces System.in
 * with a stream that hands over input one line at a time and writes each
 * line to the screen at the moment the program reads it, exactly as a
 * terminal would. Then it calls the learner's main method.
 *
 *   java -cp <build>:<game> LabEcho Main
 */
public class LabEcho {

    public static void main(String[] args) {
        System.setIn(new EchoingInput(System.in));

        // printf("%.1f") writes 7,5 on a machine set up for, say, Germany.
        // Tests are written with a decimal point, so every test run uses UK
        // number formatting, whatever the machine's own setting is.
        java.util.Locale.setDefault(java.util.Locale.UK);

        Method main;
        try {
            Class<?> program = Class.forName(args[0]);
            main = program.getMethod("main", String[].class);
        } catch (ClassNotFoundException e) {
            System.out.println("[lab] No class called " + args[0] + " was found. The "
                    + "file must contain: public class " + args[0]);
            System.exit(3);
            return;
        } catch (NoSuchMethodException e) {
            System.out.println("[lab] " + args[0] + " has no main method. It needs: "
                    + "public static void main(String[] args)");
            System.exit(3);
            return;
        }

        try {
            main.invoke(null, (Object) new String[0]);
        } catch (InvocationTargetException e) {
            System.out.flush();
            describeCrash(e.getCause());
            System.exit(1);
        } catch (IllegalAccessException e) {
            System.out.println("[lab] main must be public static void main(String[] args)");
            System.exit(3);
        }
        System.out.flush();
    }

    /**
     * The same report the JVM would print, minus the frames that belong to
     * this harness, so the learner sees only their own code.
     */
    private static void describeCrash(Throwable crash) {
        System.out.println("Exception in thread \"main\" " + crash);
        for (StackTraceElement frame : crash.getStackTrace()) {
            String where = frame.getClassName();
            if (where.startsWith("LabEcho") || where.startsWith("java.lang.reflect")
                    || where.startsWith("jdk.internal") || where.startsWith("sun.reflect")) {
                continue;
            }
            System.out.println("\tat " + frame);
        }
    }

    /** Hands over one line per read, echoing it as it goes. */
    private static class EchoingInput extends InputStream {

        private final InputStream source;
        private byte[] pending = new byte[0];
        private int position = 0;

        EchoingInput(InputStream source) {
            this.source = source;
        }

        @Override
        public int read() throws IOException {
            byte[] one = new byte[1];
            int count = read(one, 0, 1);
            return count <= 0 ? -1 : one[0] & 0xff;
        }

        @Override
        public int read(byte[] buffer, int offset, int length) throws IOException {
            if (length == 0) {
                return 0;
            }
            if (position >= pending.length && !nextLine()) {
                return -1;
            }
            int count = Math.min(length, pending.length - position);
            System.arraycopy(pending, position, buffer, offset, count);
            position = position + count;
            return count;
        }

        @Override
        public int available() {
            return pending.length - position;
        }

        private boolean nextLine() throws IOException {
            ByteArrayOutputStream line = new ByteArrayOutputStream();
            int c = source.read();
            while (c != -1) {
                line.write(c);
                if (c == '\n') {
                    break;
                }
                c = source.read();
            }
            if (line.size() == 0) {
                return false;
            }
            pending = line.toByteArray();
            position = 0;

            String typed = new String(pending, StandardCharsets.UTF_8);
            System.out.print(typed.endsWith("\n") ? typed : typed + "\n");
            System.out.flush();
            return true;
        }
    }
}
