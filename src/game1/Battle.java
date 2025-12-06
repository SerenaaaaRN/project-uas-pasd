package game1;
import java.util.Scanner;
import java.util.Random;

public class Battle {
    private Scanner scanner;
    private Random random;

    // Warna
    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String CYAN = "\u001B[36m";

    public Battle() {
        this.scanner = new Scanner(System.in);
        this.random = new Random();
    }

    public boolean fight(Player player, String enemyName, int enemyHp, int enemyAtk, int enemyDef) {
        System.out.println(RED + "\n⚔️  BATTLE START! Musuh: " + enemyName + " ⚔️" + RESET);
        System.out.println(RED + "HP Musuh: " + enemyHp + " | ATK: " + enemyAtk + RESET);

        while (player.isAlive() && enemyHp > 0) {
            System.out.println("\n" + BLUE + "--- Giliranmu ---" + RESET);
            System.out.println("HP Kamu: " + player.getHp());
            System.out.println("1. 👊 Serang Biasa (Akurasi 100%)");
            System.out.println("2. ✨ Skill: Jurus Deadline (Dmg Besar, Akurasi 60%)");
            System.out.println("3. 🍞 Makan Gorengan (Heal 20 HP, Skip Turn)");
            System.out.print("Pilih aksi (1-3): ");

            String choice = scanner.nextLine();
            int damageToEnemy = 0;
            boolean playerTurnSuccess = true;

            // Logika Player
            if (choice.equals("1")) {
                damageToEnemy = Math.max(1, player.getAttack() - enemyDef);
                System.out.println("Kamu memukul " + enemyName + "!");
            } else if (choice.equals("2")) {
                if (random.nextInt(100) < 60) { // 60% chance
                    damageToEnemy = Math.max(1, (player.getAttack() * 2) - enemyDef);
                    System.out.println(CYAN + "🔥 CRITICAL! Kamu melempar tumpukan tugas! Masuk Pak Eko!" + RESET);
                } else {
                    System.out.println(YELLOW + "Meleset! Kamu malah stress sendiri." + RESET);
                    damageToEnemy = 0;
                }
            } else if (choice.equals("3")) {
                System.out.println(GREEN + "Kamu makan gorengan sisa rapat. HP bertambah!" + RESET);
                player.setHp(player.getHp() + 20);
                damageToEnemy = 0;
                playerTurnSuccess = false; // Tidak nyerang
            } else {
                System.out.println("Bingung mau ngapain? Kamu diam saja.");
            }

            // Apply Damage ke Musuh
            if (damageToEnemy > 0) {
                enemyHp -= damageToEnemy;
                System.out.println(BLUE + "Musuh terkena " + damageToEnemy + " damage!" + RESET);
            }

            if (enemyHp <= 0) {
                System.out.println(GREEN + "\n✅ " + enemyName + " Kabur/Kalah!" + RESET);
                return true;
            }

            // Giliran Musuh
            System.out.println(RED + "\n--- Giliran Musuh ---" + RESET);
            int damageToPlayer = Math.max(0, enemyAtk - player.getDefense());

            // Randomize enemy behaviour dikit
            if (random.nextInt(10) < 2) {
                System.out.println(enemyName + " meleset saat menyerang!");
            } else {
                player.setHp(player.getHp() - damageToPlayer);
                System.out.println("🛡️ " + enemyName + " menyerangmu! Terluka " + damageToPlayer + " poin.");
            }

            if (!player.isAlive()) {
                System.out.println(RED + "💀 Pandanganmu gelap... Kamu pingsan." + RESET);
                return false;
            }
        }
        return player.isAlive();
    }
}