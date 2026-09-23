/**
 * NORTHSTAR SYSTEMS - the fictional organisation the missions happen in.
 *
 * Held in one place so later missions can refer back to the same hosts and
 * accounts, and the world stays consistent as it grows.
 */
public class World {

    public static final String ORGANISATION = "NORTHSTAR SYSTEMS";

    /** name | department | ip | role */
    public static final String[][] HOSTS = {
        {"DC-01",   "Security",    "10.0.1.5",   "Domain controller"},
        {"WEB-01",  "Development", "10.0.2.20",  "Public web server"},
        {"DB-01",   "Development", "10.0.2.21",  "Customer database"},
        {"FILE-01", "Operations",  "10.0.4.11",  "File server"},
        {"WS-001",  "Finance",     "10.0.8.31",  "Workstation"},
        {"WS-002",  "HR",          "10.0.8.32",  "Workstation"},
    };

    /** username | department | role | clearance */
    public static final String[][] ACCOUNTS = {
        {"jsmith",     "Finance",     "Employee",       "1"},
        {"a.okafor",   "Security",    "Analyst",        "5"},
        {"m.reyes",    "IT",          "Administrator",  "8"},
        {"contractor", "Development", "Contractor",     "1"},
        {"SVC-BACKUP", "Operations",  "Service account","3"},
    };

    public static void showInventory() {
        Terminal.newScreen();
        Terminal.rule('+', '=');
        Terminal.centred(ORGANISATION + " - INVENTORY");
        Terminal.rule('+', '=');
        Terminal.blank();

        Terminal.heading("HOSTS");
        Terminal.blank();
        Terminal.line("  " + Terminal.pad("HOST", 10) + Terminal.pad("DEPARTMENT", 14)
                + Terminal.pad("ADDRESS", 13) + "ROLE");
        for (String[] host : HOSTS) {
            Terminal.line("  " + Terminal.pad(host[0], 10) + Terminal.pad(host[1], 14)
                    + Terminal.pad(host[2], 13) + host[3]);
        }
        Terminal.blank();

        Terminal.heading("ACCOUNTS");
        Terminal.blank();
        Terminal.line("  " + Terminal.pad("USERNAME", 13) + Terminal.pad("DEPARTMENT", 14)
                + Terminal.pad("ROLE", 17) + "CLEARANCE");
        for (String[] account : ACCOUNTS) {
            Terminal.line("  " + Terminal.pad(account[0], 13) + Terminal.pad(account[1], 14)
                    + Terminal.pad(account[2], 17) + account[3]);
        }
        Terminal.blank();
        Terminal.wrapped("Missions refer to these systems and accounts. "
                + "Everything here is simulated. None of it is real and none of "
                + "it is reachable.", "  ");
        Terminal.blank();
        Terminal.pause();
    }
}
