public class UI {
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String CYAN = "\u001B[36m";
    public static final String PURPLE = "\u001B[35m";

    public void printTitle() {
        clearScreen();
        System.out.println(CYAN + "========================================");
        System.out.println("      M E M O R Y   G A M E   C L I     ");
        System.out.println("========================================" + RESET);
    }

    public void printInfo(String pesan) {
        System.out.println(BLUE + "[INFO] " + RESET + pesan);
    }

    public void printSuccess(String pesan) {
        System.out.println(GREEN + "[SUCCESS] " + RESET + pesan);
    }

    public void printError(String pesan) {
        System.out.println(RED + "[ERROR] " + RESET + pesan);
    }

    public void printWarning(String pesan) {
        System.out.println(YELLOW + "[!] " + RESET + pesan);
    }

    public void printMenu() {
        System.out.println("\nPilih Tingkat Kesulitan:");
        System.out.println("1. Easy (3 Angka)");
        System.out.println("2. Medium (5 Angka)");
        System.out.println("3. Hard (10 Angka)");
        System.out.println("0. Keluar");
        System.out.print("Pilihan Anda: ");
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void printSeparator() {
        System.out.println(PURPLE + "----------------------------------------" + RESET);
    }
}
