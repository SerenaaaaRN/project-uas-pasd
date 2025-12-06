import java.util.Scanner;

public class Game {
    private final UI tampilan;
    private final Utils utils;
    private final MemoryEngine engine;
    private final Scanner input;
    private boolean isRunning;

    public Game() {
        this.input = new Scanner(System.in);
        this.tampilan = new UI();
        this.utils = new Utils();
        this.engine = new MemoryEngine(tampilan, utils, input);
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
        input.close();
        System.out.println("Terima kasih telah bermain Memory Game!");
    }

    public void showMenu() {
        tampilan.printTitle();
        tampilan.printMenu();

        String masukan = utils.readLine(input);

        int pilihan = -1;
        try {
            pilihan = Integer.parseInt(masukan);
        } catch (NumberFormatException e) {
            pilihan = -1;
        }

        switch (pilihan) {
            case 1:
                tampilan.printInfo("Mode: Easy");
                engine.startRound(1);
                tanyaPlayer();
                break;
            case 2:
                tampilan.printInfo("Mode: Medium");
                engine.startRound(2);
                tanyaPlayer();
                break;
            case 3:
                tampilan.printInfo("Mode: Hard");
                engine.startRound(3);
                tanyaPlayer();
                break;
            case 0:
                isRunning = false;
                break;
            default:
                tampilan.printError("Pilihan tidak valid. Silakan coba lagi.");
                utils.sleep(1500);
        }
    }

    private void tanyaPlayer() {
        System.out.println("\nTekan [ENTER] untuk kembali ke menu utama...");
        utils.readLine(input);
    }
}
