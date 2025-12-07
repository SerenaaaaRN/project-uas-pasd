package game;

public class Game {
    private boolean isRunning;

    public Game() {
        this.isRunning = true;
    }

    public static void main(String[] args) {
        Game permainan = new Game();
        permainan.run();
    }

    public void run() {
        while (isRunning) {
            showMenu();
        }
        System.out.println("Terima kasih telah bermain Memory Game!");
    }

    public void showMenu() {
        UI.printTitle();
        UI.printMenu();

        String masukan = Utils.readLine();

        int pilihan = -1;
        try {
            pilihan = Integer.parseInt(masukan);
        } catch (NumberFormatException e) {
            pilihan = -1;
        }

        switch (pilihan) {
            case 1:
                UI.printInfo("Mode: Easy");
                MemoryEngine.startRound(1);
                tanyaPlayer();
                break;
            case 2:
                UI.printInfo("Mode: Medium");
                MemoryEngine.startRound(2);
                tanyaPlayer();
                break;
            case 3:
                UI.printInfo("Mode: Hard");
                MemoryEngine.startRound(3);
                tanyaPlayer();
                break;
            case 4:
                UI.printError("Mode: Khusus Adit");
                MemoryEngine.startRound(4);
                tanyaPlayer();
                break;
            case 0:
                isRunning = false;
                break;
            default:
                UI.printError("Pilihan tidak valid. Silakan coba lagi.");
                Utils.sleep(1500);
        }
    }

    private void tanyaPlayer() {
        System.out.println("\nTekan [ENTER] untuk kembali ke menu utama...");
        Utils.readLine();
    }
}
