import java.util.Scanner;
import java.util.Random;

public class Story {
    private Battle battleSystem;
    private Scanner scanner;
    private Random random;

    // Warna
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

    // --- SCENE 1: GERBANG (Easter Egg Added) ---
    private boolean sceneGerbang(Player p) {
        printDivider();
        System.out.println(PURPLE + "📍 LOKASI: GERBANG KAMPUS" + RESET);
        System.out.println("Di pojok gerbang, kamu melihat seekor kucing kampus (Kucing Oren) sedang tidur.");
        System.out.println(YELLOW + "1. Elus Kucingnya dulu (Easter Egg?)" + RESET);
        System.out.println(YELLOW + "2. Langsung cari info ke Kating" + RESET);
        System.out.print("Pilihanmu: ");

        if (scanner.nextLine().equals("1")) {
            System.out.println(GREEN + "\n🐈 Kucing itu mengeong senang! 'Meong!'" + RESET);
            System.out.println("Kamu merasa diberkati dewa kucing. (Defense +5 Permanen!)");
            // Disini kita akali defense naik lewat HP buff atau sekedar flavor text kalau
            // di Player ga ada setter defense
            // Asumsi sederhana: HP full heal + semangat
            p.setHp(100);
        }

        System.out.println("\nKamu bertemu Kating.");
        System.out.println("[Kating]: 'Ke Fasilkom? Lewat Ekonomi dulu sana, nih bekal.'");
        System.out.println(GREEN + "Dapat Roti! (HP +10)" + RESET);
        p.setHp(p.getHp() + 10);

        promptEnter();
        return true;
    }

    // --- SCENE 2: EKONOMI (Branch: Fight vs Bayar) ---
    private boolean sceneEkonomi(Player p) {
        printDivider();
        System.out.println(PURPLE + "📍 LOKASI: FAKULTAS EKONOMI" + RESET);
        System.out.println("Koor Divisi Danus mencegatmu. Dia memegang kotak sumbangan.");
        System.out.println(CYAN + "[Danus]: 'Wajib beli merchandise stiker angkatan! 50 Ribu!'" + RESET);

        System.out.println("\nOpsi:");
        System.out.println("1. 'Gak punya duit bang!' (Ajak Berantem)");
        System.out.println("2. Bayar aja biar cepet (Skip Battle, HP -15 karena nggak makan siang)");

        if (scanner.nextLine().equals("2")) {
            System.out.println(YELLOW + "\nKamu memberikan uang saku terakhirmu..." + RESET);
            System.out.println("Koor Danus minggir sambil tersenyum licik.");
            System.out.println(RED + "Kamu kelaparan. HP berkurang 15." + RESET);
            p.setHp(p.getHp() - 15);
            promptEnter();
            return true;
        }

        // Kalau pilih 1, Battle
        System.out.println(CYAN + "\n[Danus]: 'Pelit amat lu! Sini ribut!'" + RESET);
        return battleSystem.fight(p, "Koor Danus", 45, 10, 2);
    }

    // --- SCENE 3: HUKUM (Branch: Fight vs Kabur/Diplomasi) ---
    private boolean sceneHukum(Player p) {
        printDivider();
        System.out.println(PURPLE + "📍 LOKASI: FAKULTAS HUKUM" + RESET);
        System.out.println("Dosen Bajupink sedang orasi di lorong.");
        System.out.println("Dia melihatmu lewat.");
        System.out.println(CYAN + "[Dosen]: 'Kamu! Mahasiswa mana? Tahu pasal 33 tentang sopan santun?!'" + RESET);

        System.out.println("\nOpsi:");
        System.out.println("1. Debat Balik (Battle)");
        System.out.println("2. Pura-pura kesurupan (Skip Battle, tapi malu)");

        if (scanner.nextLine().equals("2")) {
            System.out.println(YELLOW + "\nKamu kejang-kejang di lantai..." + RESET);
            System.out.println("Dosen bingung: 'Waduh, bawa ke klinik! Jangan di sini!'");
            System.out.println("Kamu berhasil lolos, tapi harga dirimu hancur.");
            System.out.println(
                    "(Tidak ada penalti HP, tapi Good Ending mungkin lebih susah dicapai kalau HP tidak full)");
            promptEnter();
            return true;
        }

        return battleSystem.fight(p, "Dosen Bajupink", 50, 12, 3);
    }

    // --- SCENE 4: TEKNIK (Puzzle Branching) ---
    private boolean sceneTeknik(Player p) {
        printDivider();
        System.out.println(PURPLE + "📍 LOKASI: FAKULTAS TEKNIK" + RESET);
        System.out.println("Ada Robot Penjaga. Di sebelahnya ada PC rusak.");

        System.out.println("\nOpsi:");
        System.out.println("1. Hancurkan Robotnya! (Battle Keras)");
        System.out.println("2. Coba perbaiki PC rusak itu (Hack Sistem)");

        String choice = scanner.nextLine();

        if (choice.equals("2")) {
            System.out.println("\nKamu mencoba mengotak-atik kabel PC...");
            // Gacha keberuntungan
            if (random.nextBoolean()) {
                System.out.println(GREEN + "BERHASIL! Kamu meretas sistem robot!" + RESET);
                System.out.println("Robot itu mati sendiri. Kamu dapat item 'Obeng Sakti'.");
                System.out.println(GREEN + "HP Recovery +20" + RESET);
                p.setHp(p.getHp() + 20);
                promptEnter();
                return true; // Skip battle & Win
            } else {
                System.out.println(RED + "GAGAL! PC Meledak!" + RESET);
                System.out.println("HP -20. Dan Robotnya sadar kamu penyusup!");
                p.setHp(p.getHp() - 20);
                if (!p.isAlive())
                    return false;
            }
        }

        System.out.println(CYAN + "\n[Robot]: 'EXTERMINATE!'" + RESET);
        return battleSystem.fight(p, "Robot Proyek Gagal", 65, 14, 5);
    }

    // --- SCENE 5: KEDOKTERAN (Wajib Battle tapi ada gimmick) ---
    private boolean sceneKedokteran(Player p) {
        printDivider();
        System.out.println(PURPLE + "📍 LOKASI: FAKULTAS KEDOKTERAN" + RESET);
        System.out.println("Hantu Skeleton menghadang. Ini rintangan terakhir sebelum Fasilkom!");

        System.out.println("Kamu menemukan botol cairan aneh di meja lab.");
        System.out.println("1. Minum (Siapa tau jamu kuat)");
        System.out.println("2. Lempar ke Skeleton");
        System.out.println("3. Biarin aja");

        String choice = scanner.nextLine();
        int enemyHp = 60;
        int enemyAtk = 15;

        if (choice.equals("1")) {
            System.out.println(RED + "ITU FORMALIN BODOH! HP -10" + RESET);
            p.setHp(p.getHp() - 10);
        } else if (choice.equals("2")) {
            System.out.println(GREEN + "CAIRAN ASAM! Tulang musuh rapuh! (HP Musuh tinggal separuh)" + RESET);
            enemyHp = 30;
        }

        if (!p.isAlive())
            return false;

        return battleSystem.fight(p, "Anatomi Skeleton", enemyHp, enemyAtk, 4);
    }

    // --- ENDING ---
    private boolean sceneFasilkom(Player p) {
        printDivider();
        System.out.println(PURPLE + "📍 LOKASI: FASILKOM" + RESET);
        int finalHp = p.getHp();
        System.out.println("HP Akhir: " + finalHp);

        if (finalHp > 70) {
            System.out.println(GREEN + "🏆 GOOD ENDING: MAHASISWA TELADAN 🏆" + RESET);
            System.out.println("Kamu masuk Fasilkom dengan sehat walafiat.");
            System.out.println("Langsung dapet IP 4.0 di semester satu!");
        } else if (finalHp > 20) {
            System.out.println(YELLOW + "😐 NEUTRAL ENDING: MAHASISWA KUPU-KUPU 😐" + RESET);
            System.out.println("Kamu lolos, tapi capek banget.");
            System.out.println("Kuliah, Pulang, Kuliah, Pulang. Yang penting lulus.");
        } else {
            System.out.println(RED + "☠️ BAD ENDING: DROP OUT DINI ☠️" + RESET);
            System.out.println("Baru masuk gerbang Fasilkom, kamu pingsan dan dilarikan ke RS.");
            System.out.println("Orang tua menyuruhmu pindah jurusan ke Tata Boga.");
        }
        return true;
    }

    public void printBadEndingBattle() {
        System.out.println(RED + "\n💀 GAME OVER 💀" + RESET);
        System.out.println("Perjuanganmu terhenti di tengah jalan.");
    }

    private void promptEnter() {
        System.out.println(YELLOW + "Tekan ENTER untuk lanjut..." + RESET);
        scanner.nextLine();
    }
}