import java.util.Scanner;

public class Game {
    private final UI ui;
    private final Utils utils;
    private final MemoryEngine engine;
    private final Scanner scanner;
    private boolean isRunning;

    public Game() {
        this.scanner = new Scanner(System.in);
        this.ui = new UI();
        this.utils = new Utils();
        this.engine = new MemoryEngine(ui, utils, scanner);
        this.isRunning = true;
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.run();
    }

    public void run() {
        while (isRunning) {
            showMenu();
        }
        scanner.close();
        System.out.println("Terima kasih telah bermain Memory Game!");
    }

    public void showMenu() {
        ui.printTitle();
        ui.printMenu();

        String input = utils.readLine(scanner);

        int choice = -1;
        try {
            choice = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            choice = -1;
        }

        switch (choice) {
            case 1:
                ui.printInfo("Mode: Easy");
                engine.startRound(1);
                askToPlayAgain();
                break;
            case 2:
                ui.printInfo("Mode: Medium");
                engine.startRound(2);
                askToPlayAgain();
                break;
            case 3:
                ui.printInfo("Mode: Hard");
                engine.startRound(3);
                askToPlayAgain();
                break;
            case 0:
                isRunning = false;
                break;
            default:
                ui.printError("Pilihan tidak valid. Silakan coba lagi.");
                utils.sleep(1500);
        }
    }

    private void askToPlayAgain() {
        System.out.println("\nTekan [ENTER] untuk kembali ke menu utama...");
        utils.readLine(scanner);
    }
}
