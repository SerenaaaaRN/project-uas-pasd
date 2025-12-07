import java.util.Scanner;

public class Utils {
    static Scanner input = new Scanner(System.in);

    // Validasi apakah input string hanya berisi angka dan spasi
    public static boolean isValudFormatUrutan(String masukan) {
        if (masukan == null || masukan.trim().isEmpty()) {
            return false;
        }
        return masukan.matches("[0-9 ]+");
    }

    // Mengubah string input menjadi array int
    public static int[] parseDeret(String masukan) {
        try {
            // Memisahkan berdasarkan satu atau lebih spasi
            String[] bagian = masukan.trim().split("\\s+");
            int[] deret = new int[bagian.length];

            for (int i = 0; i < bagian.length; i++) {
                deret[i] = Integer.parseInt(bagian[i]);
            }
            return deret;
        } catch (NumberFormatException e) {
            return new int[0]; // Return array kosong jika gagal parsing
        }
    }

    // Helper untuk menghentikan program sejenak (Sleep)
    public static void sleep(int milidetik) {
        try {
            Thread.sleep(milidetik);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Timer interrupted.");
        }
    }

    // Helper untuk membaca input baris dari user
    public static String readLine() {
        if (input.hasNextLine()) {
            return input.nextLine();
        }
        return "";
    }
}
