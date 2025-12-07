import java.util.Random;

public class MemoryEngine {
    private static Random random = new Random();

    public static void startRound(int level) {
        int length;
        int durationSec;

        switch (level) {
            case 1: // Easy
                length = 3;
                durationSec = 5;
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

        boolean jikaBenar = bandingkan(deretRahasia, input);

        UI.printSeparator();
        if (jikaBenar) {
            UI.printSuccess("LUAR BIASA! Ingatan Anda sangat tajam.");
        } else {
            UI.printError("SAYANG SEKALI! Jawaban Anda salah.");
            System.out.print("Sequence yang benar adalah: " + UI.YELLOW);
            for (int num : deretRahasia) {
                System.out.print(num + " ");
            }
            System.out.println(UI.RESET);
        }
        UI.printSeparator();
    }

    public static int[] generateDer(int length) {
        int[] seq = new int[length];
        for (int i = 0; i < length; i++) {
            seq[i] = random.nextInt(100); // Angka 0-99 
        }
        return seq;
    }

    // Menampilkan angka, countdown, lalu clear screen
    public static void tampilkanDeret(int[] seq, int durationSec) {
        UI.clearScreen();
        UI.printInfo("Hafalkan urutan angka berikut dalam " + durationSec + " detik!");
        UI.printSeparator();

        System.out.print("\n\t" + UI.YELLOW + UI.BLUE); // Bold/Bright colors
        for (int num : seq) {
            System.out.print(num + "   ");
        }
        System.out.println(UI.RESET + "\n");

        UI.printSeparator();

        // Countdown timer visual
        System.out.print("Menutup dalam: ");
        for (int i = durationSec; i > 0; i--) {
            System.out.print(i + "... ");
            Utils.sleep(1000); // Sleep 1 detik
        }

        // Sembunyikan angka
        UI.clearScreen();
        UI.printInfo("WAKTU HABIS! Layar dibersihkan.");
    }

    // Meminta input dari user
    public static String getUserInput() {
        System.out.println("Masukkan urutan angka yang Anda lihat (pisahkan dengan spasi).");
        System.out.print("Jawab: ");
        return Utils.readLine();
    }

    // Membandingkan jawaban user dengan kunci jawaban
    public static boolean bandingkan(int[] seq, String userInput) {
        if (!Utils.isValidFormatUrutan(userInput)) {
            UI.printWarning("Format input salah! Pastikan hanya memasukkan angka dan spasi.");
            return false;
        }

        int[] userSeq = Utils.parseDeret(userInput);

        // Cek panjang array
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