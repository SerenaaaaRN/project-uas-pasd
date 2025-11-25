import java.util.Scanner;

public class Battle {
    private Scanner scanner;

    // Definisi Warna
    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String RED_BOLD = "\033[1;31m";

    public Battle() {
        this.scanner = new Scanner(System.in);
    }

    public boolean fight(Player player, String enemyName, int enemyHp, int enemyAtk, int enemyDef) {
        System.out.println(RED + "\n==========================================" + RESET);
        System.out.println(RED_BOLD + "⚠️ BATTLE START! Musuh: " + enemyName + " ⚠️" + RESET);
        System.out.println(RED + "HP Musuh: " + enemyHp + " | ATK: " + enemyAtk + RESET);
        System.out.println(RED + "==========================================" + RESET);

        while (player.isAlive() && enemyHp > 0) {
            System.out.println("\nGiliranmu! Tekan ENTER untuk menyerang...");
            scanner.nextLine();

            // 1. Player Attack
            int damageToEnemy = Math.max(0, player.getAttack() - enemyDef);
            enemyHp -= damageToEnemy;
            System.out.println(
                    BLUE + "⚔️ Kamu menyerang " + enemyName + " sebesar " + damageToEnemy + " damage!" + RESET);

            if (enemyHp <= 0) {
                System.out.println(GREEN + "✅ " + enemyName + " telah dikalahkan!" + RESET);
                return true;
            }

            // 2. Enemy Attack
            int damageToPlayer = Math.max(0, enemyAtk - player.getDefense());
            player.setHp(player.getHp() - damageToPlayer);
            System.out.println(
                    RED + "🛡️ " + enemyName + " membalas! Kamu terkena " + damageToPlayer + " damage." + RESET);
            System.out.println(GREEN + "❤️ Sisa HP-mu: " + player.getHp() + RESET);

            if (!player.isAlive()) {
                System.out.println(RED_BOLD + "💀 Kamu pingsan dalam pertarungan..." + RESET);
                return false;
            }
        }
        return player.isAlive();
    }
}