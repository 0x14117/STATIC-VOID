"""
Chapter 3 — Know Your Variables (primitives and references).

The chapter's big idea is that a variable holding an object does not hold the
object; it holds a remote control to it. Everything in this chapter's labs is
built on making that concrete.
"""

from .common import MAIN_ONLY, WITH_CLASS, make_checker

LABS = {

    "ch03-lab02": {
        "chapter": 3,
        "chapter_title": "Know Your Variables",
        "title": "Comparing Passwords Safely",
        "idea": "== compares the remote controls; .equals() compares the contents",

        "learn": (
            "How to compare two pieces of text correctly, and why the obvious "
            "way is wrong."
        ),

        "matters": (
            "This is a login check. If you compare a typed password to a stored "
            "password with ==, your check is not comparing the passwords at all "
            "— it is asking a different question, and the answer it gives you "
            "depends on where the text came from. Text typed by a user, read "
            "from a file, or received over a network behaves differently from "
            "text written directly in your code. That means the bug hides "
            "during your testing and appears in production."
        ),

        "explain": (
            "A variable that holds an object does not hold the object. It holds "
            "a reference to it — think of it as a remote control pointing at "
            "something sitting in memory.\n\n"
            "A String is an object. So a String variable holds a remote "
            "control, not the letters.\n\n"
            "That is why there are two different questions you can ask:\n\n"
            "    ==          Are these two remote controls pointing at the\n"
            "                SAME object?\n"
            "    .equals()   Do these two objects CONTAIN the same thing?\n\n"
            "For a password check you always mean the second one. You do not "
            "care where the text is stored. You care whether the letters match.\n\n"
            "Here is what makes this genuinely dangerous rather than merely "
            "wrong. Java saves memory by reusing identical text that you write "
            "directly in your code. Two variables both set to \"admin\" end up "
            "pointing at the same stored object, so == returns true and your "
            "broken check appears to work. You test it, it passes, you move on.\n\n"
            "Then the password arrives from a Scanner, a file, or a network "
            "request. Now it is a new object with the same letters. == returns "
            "false, and nobody can log in — or, if you wrote the test the other "
            "way round, everybody can.\n\n"
            "THE MISTAKE TO EXPECT: writing == because you tested it with text "
            "written in your own code and it worked. It was never working. It "
            "was agreeing with you by accident."
        ),

        "example": (
            "The word new forces Java to make a separate object, which is how "
            "we can see the difference on purpose:\n\n"
            "    public class Main {\n"
            "        public static void main(String[] args) {\n"
            "            String stored = new String(\"falcon\");\n"
            "            String typed  = \"falcon\";\n"
            "\n"
            "            System.out.println(stored == typed);\n"
            "            System.out.println(stored.equals(typed));\n"
            "        }\n"
            "    }\n"
            "\n"
            "prints:\n"
            "\n"
            "    false\n"
            "    true\n"
            "\n"
            "The letters are identical. Both lines are comparing the same two "
            "variables. They disagree because they are asking different "
            "questions: == asks \"same object?\" and gets no, .equals() asks "
            "\"same letters?\" and gets yes."
        ),

        "recap": [
            "A String variable holds a reference to an object, not the letters.",
            "== asks whether two references point at the same object.",
            ".equals() asks whether two objects hold the same contents.",
            "For comparing text, you always want .equals().",
            "== can return true for text written in your code, which is why the "
            "bug survives testing and reaches production.",
        ],

        "tasks": [

            {
                "id": "t1",
                "role": "warm-up",
                "brief": (
                    "A stored password and a typed password hold the same "
                    "letters, but are separate objects.\n\n"
                    "The variables are already declared. Print whether the login "
                    "should be allowed — that is, whether the two hold the same "
                    "letters.\n\n"
                    "Expected output:\n"
                    "true"
                ),
                "starter": (
                    "public class Main {\n"
                    "    public static void main(String[] args) {\n"
                    "        String savedPassword = new String(\"hunter2\");\n"
                    "        String enteredPassword = \"hunter2\";\n\n"
                    "        // print whether the login should be allowed\n"
                    "    }\n"
                    "}"
                ),
                "hints": [
                    "You want to compare the contents, not the references.",
                    "System.out.println(savedPassword.equals(enteredPassword));",
                ],
                "solution": '''public class Main {
    public static void main(String[] args) {
        String savedPassword = new String("hunter2");
        String enteredPassword = "hunter2";

        System.out.println(savedPassword.equals(enteredPassword));
    }
}''',
                "wrong": [
                    '''public class Main {
    public static void main(String[] args) {
        String savedPassword = new String("hunter2");
        String enteredPassword = "hunter2";

        System.out.println(savedPassword == enteredPassword);
    }
}''',
                ],
                "check": make_checker(
                    expected_output="true",
                    wrong_hint="I need one line: true. Compare the contents of the two variables.",
                    requires=[".equals("],
                    requires_hint="Use .equals() to compare the letters. == asks a different question.",
                ),
                "explanation": (
                    "You compared contents, so you got true — which is the "
                    "answer a login check needs.\n\n"
                    "Had you written savedPassword == enteredPassword, this would "
                    "have printed false and locked out a user who typed the "
                    "correct password. "
                    "The word new is what made that happen: it forced Java to "
                    "build a second object instead of reusing the first.\n\n"
                    "Real input is always a new object. Every password that ever "
                    "arrives from a keyboard behaves like this one."
                ),
            },

            {
                "id": "t2",
                "role": "variation",
                "brief": (
                    "Show the difference yourself, on a username this time.\n\n"
                    "Declare a String called registered holding a new String "
                    "with the letters j.smith, and a String called submitted "
                    "holding j.smith written normally.\n\n"
                    "Print the == comparison on the first line, then the "
                    ".equals() comparison on the second.\n\n"
                    "Expected output:\n"
                    "false\n"
                    "true"
                ),
                "starter": MAIN_ONLY,
                "hints": [
                    "new String(\"j.smith\") makes a separate object; \"j.smith\" on its own does not.",
                    "Two println lines: one with registered == submitted, one with registered.equals(submitted).",
                ],
                "solution": '''public class Main {
    public static void main(String[] args) {
        String registered = new String("j.smith");
        String submitted = "j.smith";

        System.out.println(registered == submitted);
        System.out.println(registered.equals(submitted));
    }
}''',
                "wrong": [
                    '''public class Main {
    public static void main(String[] args) {
        String registered = "j.smith";
        String submitted = "j.smith";

        System.out.println(registered == submitted);
        System.out.println(registered.equals(submitted));
    }
}''',
                ],
                "check": make_checker(
                    expected_output="false\ntrue",
                    wrong_hint="I need two lines: false, then true.",
                    close_hint=(
                        "If both lines say true, your two variables are pointing at the "
                        "same object — use new String(...) for the first one to force a "
                        "separate one."
                    ),
                    requires=["new String(", "==", ".equals("],
                    requires_hint="I need both comparisons written out, and the first variable built with new String(...).",
                ),
                "explanation": (
                    "Two lines, same two variables, opposite answers. Nothing "
                    "about the letters changed between them.\n\n"
                    "Notice what happens if you remove new and write both as "
                    "plain text: both lines print true, because Java reuses the "
                    "one stored copy. That version looks like proof that == "
                    "works on text. It is the exact false confidence that puts "
                    "this bug into real systems."
                ),
            },

            {
                "id": "t3",
                "role": "apply",
                "brief": (
                    "Usernames and passwords are not compared the same way in "
                    "real systems. Usernames usually ignore capital letters — "
                    "JSmith and jsmith are the same person. Passwords never do.\n\n"
                    "A user has registered as jsmith with the password Falcon9. "
                    "They have typed JSMITH and falcon9.\n\n"
                    "Print USER: followed by whether the usernames match "
                    "ignoring capitals, then PASS: followed by whether the "
                    "passwords match exactly.\n\n"
                    "Expected output:\n"
                    "USER: true\n"
                    "PASS: false"
                ),
                "starter": (
                    "public class Main {\n"
                    "    public static void main(String[] args) {\n"
                    "        String registeredUser = \"jsmith\";\n"
                    "        String registeredPass = \"Falcon9\";\n\n"
                    "        String typedUser = \"JSMITH\";\n"
                    "        String typedPass = \"falcon9\";\n\n"
                    "        // your code here\n"
                    "    }\n"
                    "}"
                ),
                "hints": [
                    "There is a second method alongside .equals() that ignores capital letters: .equalsIgnoreCase()",
                    "Use .equalsIgnoreCase() for the username and .equals() for the password.",
                ],
                "solution": '''public class Main {
    public static void main(String[] args) {
        String registeredUser = "jsmith";
        String registeredPass = "Falcon9";

        String typedUser = "JSMITH";
        String typedPass = "falcon9";

        System.out.println("USER: " + registeredUser.equalsIgnoreCase(typedUser));
        System.out.println("PASS: " + registeredPass.equals(typedPass));
    }
}''',
                "wrong": [
                    '''public class Main {
    public static void main(String[] args) {
        String registeredUser = "jsmith";
        String registeredPass = "Falcon9";

        String typedUser = "JSMITH";
        String typedPass = "falcon9";

        System.out.println("USER: " + registeredUser.equalsIgnoreCase(typedUser));
        System.out.println("PASS: " + registeredPass.equalsIgnoreCase(typedPass));
    }
}''',
                ],
                "check": make_checker(
                    expected_output="USER: true\nPASS: false",
                    wrong_hint="I need two lines, labelled USER: and PASS:.",
                    close_hint=(
                        "If PASS says true, you ignored capitals on the password. "
                        "A password comparison must be exact."
                    ),
                    requires=["equalsIgnoreCase", ".equals("],
                    requires_hint="This one needs both: equalsIgnoreCase for the username, plain equals for the password.",
                ),
                "explanation": (
                    "Two comparisons, two different rules, and the difference "
                    "is deliberate.\n\n"
                    "Being relaxed about capitals in a username is a kindness — "
                    "nobody should be locked out for pressing shift. Being "
                    "relaxed about capitals in a password destroys a large part "
                    "of its strength: it removes 26 possible characters from "
                    "every position, making the password far faster to guess.\n\n"
                    "If you had used equalsIgnoreCase on the password, this "
                    "would have printed true and let the wrong password through."
                ),
            },

            {
                "id": "t4",
                "role": "combine",
                "brief": (
                    "Now put the credentials inside an object, the way Chapter 2 "
                    "showed you.\n\n"
                    "A class called Account is started for you. Give it two "
                    "String instance variables: username and password.\n\n"
                    "In main, create one Account, set its username to admin and "
                    "its password to Tr0ub4dor, then check a login attempt where "
                    "someone has typed admin and Tr0ub4dor — and print whether "
                    "each matched.\n\n"
                    "Expected output:\n"
                    "USER: true\n"
                    "PASS: true"
                ),
                "starter": WITH_CLASS,
                "hints": [
                    "Inside class Account, declare the two fields: String username; and String password;",
                    "Account a = new Account(); then a.username = \"admin\"; and compare with a.password.equals(typedPass)",
                ],
                "solution": '''class Account {
    String username;
    String password;
}

public class Main {
    public static void main(String[] args) {
        Account account = new Account();
        account.username = "admin";
        account.password = "Tr0ub4dor";

        String typedUser = "admin";
        String typedPass = "Tr0ub4dor";

        System.out.println("USER: " + account.username.equals(typedUser));
        System.out.println("PASS: " + account.password.equals(typedPass));
    }
}''',
                "wrong": [
                    '''class Account {
    String username;
    String password;
}

public class Main {
    public static void main(String[] args) {
        Account account = new Account();
        account.username = "admin";
        account.password = "Tr0ub4dor";

        String typedUser = "admin";
        String typedPass = "Tr0ub4dor";

        System.out.println("USER: " + (account.username == typedUser));
        System.out.println("PASS: " + (account.password == typedPass));
    }
}''',
                ],
                "check": make_checker(
                    expected_output="USER: true\nPASS: true",
                    wrong_hint="I need two lines, USER: true and PASS: true.",
                    requires=["class Account", "new Account", ".equals("],
                    requires_hint="I need an Account class with the two fields, an Account object created with new, and .equals() for the comparisons.",
                ),
                "explanation": (
                    "The credentials now live in an object, which is where they "
                    "belong — one Account is one set of credentials, and you "
                    "could make a hundred of them.\n\n"
                    "Notice that the == version of this happens to print true as "
                    "well, because all the text here is written directly in the "
                    "code. That is precisely the trap. The .equals() version is "
                    "correct for a reason; the == version is right by luck, and "
                    "stops being right the moment a real user types something.\n\n"
                    "Chapter 4 fixes the other problem you can see here: "
                    "account.password is readable by anyone holding the object."
                ),
            },

            {
                "id": "t5",
                "role": "repair",
                "brief": (
                    "This login check was tested with text written directly in "
                    "the code, so it passed. Now the password arrives the way a "
                    "real one does — as a separate object — and it is broken. "
                    "The correct password is rejected.\n\n"
                    "Fix it so it prints ACCESS: true, without changing how "
                    "typedPass is created.\n\n"
                    "Expected output:\n"
                    "ACCESS: true"
                ),
                "starter": (
                    "public class Main {\n"
                    "    public static void main(String[] args) {\n"
                    "        String stored = \"Zx9!warden\";\n\n"
                    "        // this is how a real password arrives: a new object\n"
                    "        String typedPass = new String(\"Zx9!warden\");\n\n"
                    "        // this test passed during development. It is wrong.\n"
                    "        System.out.println(\"ACCESS: \" + (stored == typedPass));\n"
                    "    }\n"
                    "}"
                ),
                "hints": [
                    "The letters are identical. Only the comparison is wrong.",
                    "Replace stored == typedPass with stored.equals(typedPass).",
                ],
                "solution": '''public class Main {
    public static void main(String[] args) {
        String stored = "Zx9!warden";

        String typedPass = new String("Zx9!warden");

        System.out.println("ACCESS: " + stored.equals(typedPass));
    }
}''',
                "check": make_checker(
                    expected_output="ACCESS: true",
                    wrong_hint="I need one line: ACCESS: true.",
                    close_hint="Still false? The comparison is still asking whether they are the same object.",
                    requires=[".equals(", "new String("],
                    requires_hint="Keep typedPass as new String(...) — that is the realistic part — and fix the comparison instead.",
                ),
                "explanation": (
                    "One method call was the entire fix, and the bug was "
                    "invisible until the data stopped being convenient.\n\n"
                    "This is the shape of the real failure. The developer tests "
                    "with text typed into the source file, sees true, and ships. "
                    "The first real user types their correct password and is "
                    "refused. Nothing in the code looks wrong, there is no error "
                    "message, and nothing appears in the logs.\n\n"
                    "When a comparison behaves differently for real input than "
                    "for test data, == on objects is the first thing to check."
                ),
            },
        ],
    },
}
