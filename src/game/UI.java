package game;
public class UI {
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String CYAN = "\u001B[36m";
    public static final String PURPLE = "\u001B[35m";

    public static void printTitle() {
        clearScreen();
        System.out.println(CYAN + "========================================");
        System.out.println("      M E M O R Y   G A M E   C L I     ");
        System.out.println("========================================" + RESET);
    }

    public static void printInfo(String pesan) {
        System.out.println(BLUE + "[INFO] " + RESET + pesan);
    }

    public static void printSuccess(String pesan) {
        System.out.println(GREEN + "[SUCCESS] " + RESET + pesan);
    }

    public static void printError(String pesan) {
        System.out.println(RED + "[ERROR] " + RESET + pesan);
    }

    public static void printWarning(String pesan) {
        System.out.println(YELLOW + "[!] " + RESET + pesan);
    }

    public static void printMenu() {
        System.out.println("\nPilih Tingkat Kesulitan:");
        System.out.println("[1] Easy (3 Angka)");
        System.out.println("[2] Medium (5 Angka)");
        System.out.println("[3] Hard (10 Angka)");
        System.out.println("[4] Khusus Adit (50 Angka)");
        System.out.println("[0] Keluar");
        System.out.print("Masukkan Pilihan: ");
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void printSeparator() {
        System.out.println(PURPLE + "----------------------------------------" + RESET);
    }
}
