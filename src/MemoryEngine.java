

import java.util.Random;
import java.util.Scanner;

public class MemoryEngine {
    private final UI ui;
    private final Utils utils;
    private final Scanner input;
    private final Random random;

    public MemoryEngine(UI ui, Utils utils, Scanner input) {
        this.ui = ui;
        this.utils = utils;
        this.input = input;
        this.random = new Random();
    }

    public void startRound(int level) {
        int length;
        int durationSec;

        switch (level) {
            case 1: // Easy
                length = 3;
                durationSec = 6;
                break;
            case 2: // Medium
                length = 5;
                durationSec = 10;
                break;
            case 3: // Hard
                length = 10;
                durationSec = 20;
                break;
            default:
                length = 4;
                durationSec = 11;
        }

        int[] deretRahasia = generateDer(length);

        tampilkanDeret(deretRahasia, durationSec);

        String input = getUserInput();

        boolean isCorrect = evaluate(deretRahasia, input);

        ui.printSeparator();
        if (isCorrect) {
            ui.printSuccess("LUAR BIASA! Ingatan Anda sangat tajam.");
        } else {
            ui.printError("SAYANG SEKALI! Jawaban Anda salah.");
            System.out.print("Sequence yang benar adalah: " + UI.YELLOW);
            for (int num : deretRahasia) {
                System.out.print(num + " ");
            }
            System.out.println(UI.RESET);
        }
        ui.printSeparator();
    }

    public int[] generateDer(int length) {
        int[] seq = new int[length];
        for (int i = 0; i < length; i++) {
            seq[i] = random.nextInt(100); // Angka 0-99 agar tidak terlalu sulit
        }
        return seq;
    }

    // Menampilkan angka, countdown, lalu clear screen
    public void tampilkanDeret(int[] seq, int durationSec) {
        ui.clearScreen();
        ui.printInfo("Hafalkan urutan angka berikut dalam " + durationSec + " detik!");
        ui.printSeparator();

        System.out.print("\n\t" + UI.YELLOW + UI.BLUE); // Bold/Bright colors
        for (int num : seq) {
            System.out.print(num + "   ");
        }
        System.out.println(UI.RESET + "\n");

        ui.printSeparator();

        // Countdown timer visual
        System.out.print("Menutup dalam: ");
        for (int i = durationSec; i > 0; i--) {
            System.out.print(i + "... ");
            utils.sleep(1000); // Sleep 1 detik
        }

        // Sembunyikan angka!
        ui.clearScreen();
        ui.printInfo("WAKTU HABIS! Layar dibersihkan.");
    }

    // Meminta input dari user
    public String getUserInput() {
        System.out.println("Masukkan urutan angka yang Anda lihat (pisahkan dengan spasi).");
        System.out.print("Jawab: ");
        return utils.readLine(input);
    }

    // Membandingkan jawaban user dengan kunci jawaban
    public boolean evaluate(int[] seq, String userInput) {
        if (!utils.isValudFormatUrutan(userInput)) {
            ui.printWarning("Format input salah! Pastikan hanya memasukkan angka dan spasi.");
            return false;
        }

        int[] userSeq = utils.parseDeret(userInput);

        // Cek panjang array dulu
        if (seq.length != userSeq.length) {
            return false;
        }

        // Cek isi array satu per satu
        for (int i = 0; i < seq.length; i++) {
            if (seq[i] != userSeq[i]) {
                return false;
            }
        }

        return true;
    }
}