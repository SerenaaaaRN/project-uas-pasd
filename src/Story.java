import java.util.Scanner;
import java.util.Random;

public class Story {
    private Battle battleSystem;
    private Scanner scanner;
    private Random random;

    // Definisi Warna
    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String PURPLE = "\u001B[35m";
    private static final String CYAN = "\u001B[36m";

    public Story() {
        this.battleSystem = new Battle();
        this.scanner = new Scanner(System.in);
        this.random = new Random();
    }

    // Mengembalikan true jika player masih hidup untuk lanjut ke next stage
    public boolean playScene(int locationIndex, Player player) {
        switch (locationIndex) {
            case 0:
                return sceneGerbang(player);
            case 1:
                return sceneEkonomi(player);
            case 2:
                return sceneHukum(player);
            case 3:
                return sceneTeknik(player);
            case 4:
                return sceneKedokteran(player);
            case 5:
                return sceneFasilkom(player);
            default:
                return true;
        }
    }

    private void printDivider() {
        System.out.println(PURPLE + "\n-------------------------------------------------------------" + RESET);
    }

    private boolean sceneGerbang(Player p) {
        printDivider();
        System.out.println(PURPLE + "📍 LOKASI: GERBANG KAMPUS" + RESET);
        System.out.println("Kamu berdiri di depan gerbang kampus yang megah.");
        System.out.println("Kamu bingung arah ke Fasilkom. Ada dua orang di sana.");
        System.out.println(YELLOW + "1. Tanya Satpam yang lagi ngopi.");
        System.out.println("2. Tanya Kakak Tingkat yang kelihatan sibuk." + RESET);
        System.out.print("Pilihanmu (1/2): ");

        String choice = scanner.nextLine();

        if (choice.equals("1")) {
            System.out.println(CYAN
                    + "\n[Satpam]: 'Waduh dek, saya baru hari pertama kerja. Coba ke gedung sebelah sana, kayaknya Ekonomi deh.'"
                    + RESET);
            System.out.println("(Satpam ngasih info ngaco, kamu diarahkan ke Ekonomi. Tidak ada efek status.)");
        } else {
            System.out.println(CYAN + "\n[Kakak Tingkat]: 'Mau ke Fasilkom? Wah jauh dek.'" + RESET);
            System.out.println(CYAN + "[Kakak Tingkat]: 'Nih minum dulu biar semangat.'" + RESET);
            System.out.println(GREEN + "Kamu diberi air mineral! (HP +10)" + RESET);
            p.setHp(p.getHp() + 10);
        }

        promptEnter();
        return true;
    }

    private boolean sceneEkonomi(Player p) {
        printDivider();
        System.out.println(PURPLE + "📍 LOKASI: FAKULTAS EKONOMI" + RESET);
        System.out.println("Suasana ramai. Banyak mahasiswa rapat jam 11 malam.");
        System.out.println("Seorang Koor Divisi menghalangimu dengan tatapan tajam.");
        System.out.println(CYAN + "\n[Koor Divisi]: 'Eh maba! Mau kemana? Ikut rapat proker dulu sini!'" + RESET);

        System.out.println("\nRespon kamu?");
        System.out.println(YELLOW + "1. 'Maaf kak, saya buru-buru mau ke Fasilkom.' (Sopan)");
        System.out.println("2. 'Apaan sih, minggir lu!' (Ngegas)" + RESET);
        System.out.print("Pilihanmu (1/2): ");
        String choice = scanner.nextLine();

        int enemyAtk = 8;
        int enemyHp = 50;

        if (choice.equals("2")) {
            System.out.println(CYAN + "\n[Koor Divisi]: 'Wah nyolot! Rasain ini!'" + RESET);
            System.out.println(RED + "Koor Divisi marah! Attack dia meningkat drastis!" + RESET);
            enemyAtk += 5; // Musuh jadi lebih kuat
        } else {
            System.out.println(CYAN + "\n[Koor Divisi]: 'Gak ada alasan! Anak baru harus ditatar!'" + RESET);
            System.out.println("Dia tetap menyerang, tapi setidaknya tidak mengamuk.");
        }

        // Battle Start
        boolean win = battleSystem.fight(p, "Koor Divisi", enemyHp, enemyAtk, 2);

        if (win) {
            System.out.println(CYAN + "\n[Koor Divisi]: 'Oke oke, kamu boleh lewat. Galak amat.'" + RESET);
            promptEnter();
        }
        return win;
    }

    private boolean sceneHukum(Player p) {
        printDivider();
        System.out.println(PURPLE + "📍 LOKASI: FAKULTAS HUKUM" + RESET);
        System.out.println("Dinding penuh poster lomba debat konstitusi.");
        System.out.println("Dosen Bajupink menatapmu dari balik kacamata tebalnya.");
        System.out.println(CYAN + "\n[Dosen Bajupink]: 'Pasal 33 ayat 1... kamu melanggar zona dosen!'" + RESET);

        System.out.println("\nApa yang kamu lakukan?");
        System.out.println(YELLOW + "1. Ajak debat pasal UUD. (Intellect)");
        System.out.println("2. Pasrah minta maaf. (Submissive)" + RESET);
        System.out.print("Pilihanmu (1/2): ");
        String choice = scanner.nextLine();

        int enemyDef = 3;

        if (choice.equals("1")) {
            System.out.println("\nKamu mengeluarkan argumen hukum yang brilian!");
            System.out.println(CYAN + "[Dosen Bajupink]: 'Hah?! Ternyata kamu paham hukum?!'" + RESET);
            System.out.println(GREEN + "Pertahanan mental dosen goyah! (Enemy Defense Turun)" + RESET);
            enemyDef = 0;
        } else {
            System.out.println("\nKamu hanya menunduk takut. Dosen semakin percaya diri.");
        }

        // Enemy: HP (40), ATK (10)
        boolean win = battleSystem.fight(p, "Dosen Bajupink, SH., MH.", 40, 10, enemyDef);

        if (win) {
            System.out.println(CYAN + "\n[Dosen Bajupink]: 'Argumentasimu valid. Silakan lanjut.'" + RESET);
            promptEnter();
        }
        return win;
    }

    private boolean sceneTeknik(Player p) {
        printDivider();
        System.out.println(PURPLE + "📍 LOKASI: FAKULTAS TEKNIK" + RESET);
        System.out.println("Bau solder dimana-mana. Sebuah Robot Skripsi terlihat korsleting!");
        System.out.println(CYAN + "\n[Robot Proyek]: 'ERROR 404... TARGET LOCKED...'" + RESET);

        System.out.println("\nRobot itu bersiap menembakkan laser solder!");
        System.out.println(YELLOW + "1. Cari tombol OFF di punggungnya. (Risky)");
        System.out.println("2. Lempar pakai obeng di lantai. (Safe)" + RESET);
        System.out.print("Pilihanmu (1/2): ");
        String choice = scanner.nextLine();

        int enemyHp = 60;

        if (choice.equals("1")) {
            if (random.nextBoolean()) { // 50% Chance
                System.out.println(GREEN + "\nBERHASIL! Kamu menekan tombol reset!" + RESET);
                System.out.println("HP Robot berkurang drastis sebelum battle!");
                enemyHp = 30;
            } else {
                System.out.println(RED + "\nGAGAL! Kamu malah kesetrum!" + RESET);
                System.out.println("HP kamu berkurang 15!");
                p.setHp(p.getHp() - 15);
            }
        } else {
            System.out.println("\nKamu melempar obeng. 'TANG!' Robot itu sedikit penyok.");
            enemyHp -= 10;
        }

        if (!p.isAlive())
            return false; // Mati konyol kesetrum

        boolean win = battleSystem.fight(p, "Robot Proyek Akhir", enemyHp, 12, 5);

        if (win) {
            System.out.println("\nRobot itu meledak kecil dan berhenti bergerak.");
            promptEnter();
        }
        return win;
    }

    private boolean sceneKedokteran(Player p) {
        printDivider();
        System.out.println(PURPLE + "📍 LOKASI: FAKULTAS KEDOKTERAN" + RESET);
        System.out.println("Lorong dingin dan wangi formalin.");
        System.out.println("Tiba-tiba Anatomi Skeleton melompat ke arahmu!");

        System.out.println("\nDia sangat cepat!");
        System.out.println(YELLOW + "1. Bertahan (Defensive Stance)." + RESET);
        System.out.println(YELLOW + "2. Serang duluan! (Aggressive)" + RESET);
        System.out.print("Pilihanmu (1/2): ");
        String choice = scanner.nextLine();

        int playerStartDef = p.getDefense();
        int playerStartAtk = p.getAttack();

        // Temporary Buff/Debuff for this battle only (simplification logic)
        // Kita modif stat musuh aja biar gampang handle return stat player
        int enemyAtk = 14;

        if (choice.equals("1")) {
            System.out.println(GREEN + "\nKamu pasang kuda-kuda bertahan." + RESET);
            System.out.println("Serangan Skeleton tidak akan terlalu sakit.");
            enemyAtk = 10; // Attack musuh turun
        } else {
            System.out.println(RED + "\nKamu nekat menyerang duluan!" + RESET);
            System.out.println("Skeleton kaget, tapi dia juga siap memukulmu keras!");
            // High Risk High Reward logic handled in battle naturally
        }

        boolean win = battleSystem.fight(p, "Anatomi Skeleton", 55, enemyAtk, 4);

        if (win) {
            System.out.println("\nTulang-tulang itu berserakan kembali ke posisi semula.");
            promptEnter();
        }
        return win;
    }

    private boolean sceneFasilkom(Player p) {
        printDivider();
        System.out.println(PURPLE + "📍 LOKASI: FASILKOM (TUJUAN AKHIR)" + RESET);
        System.out.println("Akhirnya sampai di gedung penuh cahaya LED RGB.");

        int currentHp = p.getHp();
        System.out.println(GREEN + "Sisa HP kamu: " + currentHp + RESET);

        System.out.println("\n--- ENDING ---");

        if (currentHp > 70) {
            System.out.println(GREEN + "🌟 GOOD ENDING 🌟" + RESET);
            System.out.println("“Selamat! Kamu diterima di Fasilkom sebagai mahasiswa baru penuh harapan.”");
            System.out.println("Karena HP-mu masih banyak, kamu langsung ikut kepanitiaan!");
        } else if (currentHp > 20) {
            System.out.println(YELLOW + "😐 NEUTRAL ENDING 😐" + RESET);
            System.out.println("“Salah ruangan… kamu malah jadi anak magang Fakultas Ekonomi.”");
            System.out.println("Kamu bingung tapi ya sudahlah, yang penting lulus.");
        } else {
            // HP Rendah (<= 20)
            System.out.println(RED + "💀 BAD ENDING 💀" + RESET);
            System.out.println("“Kamu drop out setelah diserang dosen killer di sidang awal.”");
            System.out.println("Kesehatanmu terlalu rendah untuk bertahan di kerasnya Fasilkom.");
        }

        return true; // Game selesai
    }

    public void printBadEndingBattle() {
        System.out.println(RED + "\n💀 BAD ENDING 💀" + RESET);
        System.out.println("“Kamu drop out setelah kalah bertarung di tengah jalan.”");
        System.out.println("Perjalanan kampusmu berakhir di sini.");
    }

    private void promptEnter() {
        System.out.println(YELLOW + "Tekan ENTER untuk lanjut berjalan..." + RESET);
        scanner.nextLine();
    }
}