import java.util.Scanner;

public class Utils {

    // Validasi apakah input string hanya berisi angka dan spasi
    public boolean isValidSequenceFormat(String input) {
        if (input == null || input.trim().isEmpty()) {
            return false;
        }
        return input.matches("[0-9 ]+");
    }

    // Mengubah string input (misal: "1 5 3") menjadi array int
    public int[] parseSequence(String input) {
        try {
            // Memisahkan berdasarkan satu atau lebih spasi
            String[] parts = input.trim().split("\\s+");
            int[] sequence = new int[parts.length];

            for (int i = 0; i < parts.length; i++) {
                sequence[i] = Integer.parseInt(parts[i]);
            }
            return sequence;
        } catch (NumberFormatException e) {
            return new int[0]; // Return array kosong jika gagal parsing
        }
    }

    // Helper untuk menghentikan program sejenak (Sleep)
    public void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Timer interrupted.");
        }
    }

    // Helper untuk membaca input baris dari user
    public String readLine(Scanner scanner) {
        if (scanner.hasNextLine()) {
            return scanner.nextLine();
        }
        return "";
    }
}
