package game1;
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

    public int playScene(int locationIndex, Player player) {
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
                return -1; // End game if location is unknown
        }
    }

    private void printDivider() {
        System.out.println(PURPLE + "\n-------------------------------------------------------------" + RESET);
    }

    // --- SCENE 1: GERBANG (Easter Egg Added) ---
    private int sceneGerbang(Player p) {
        printDivider();
        System.out.println(PURPLE + "📍 LOKASI: GERBANG KAMPUS" + RESET);
        System.out.println("Di pojok gerbang, kamu melihat seekor kucing kampus (Kucing Oren) sedang tidur.");
        System.out.println(YELLOW + "1. Elus Kucingnya dulu (Easter Egg?)" + RESET);
        System.out.println(YELLOW + "2. Langsung cari info ke Kating" + RESET);
        System.out.print("Pilihanmu: ");

        if (scanner.nextLine().equals("1")) {
            System.out.println(GREEN + "\n🐈 Kucing itu mengeong senang! 'Meong!'" + RESET);
            System.out.println("Kamu merasa diberkati dewa kucing. (Defense +5 Permanen!)");
            p.setDefense(p.getDefense() + 5);
        }

        System.out.println("\nKamu bertemu Kating.");
        System.out.println("[Kating]: 'Ke Fasilkom? Lewat Ekonomi dulu sana, atau mau coba jalan pintas lewat Teknik?'");
        System.out.println(GREEN + "Dapat Roti! (HP +10)" + RESET);
        p.setHp(p.getHp() + 10);

        System.out.println("\nOpsi Rute:");
        System.out.println("1. Lewat Ekonomi (Jalan biasa)");
        System.out.println("2. Lewat Teknik (Jalan pintas, lebih berbahaya!)");
        System.out.print("Pilihanmu: ");
        String choice = scanner.nextLine();

        promptEnter();

        if (choice.equals("2")) {
            System.out.println("Kamu memilih jalan pintas...");
            return 3; // Next scene: Teknik
        }
        System.out.println("Kamu memilih jalan biasa...");
        return 1; // Next scene: Ekonomi
    }

    // --- SCENE 2: EKONOMI (Branch: Fight vs Bayar) ---
    private int sceneEkonomi(Player p) {
        printDivider();
        System.out.println(PURPLE + "📍 LOKASI: FAKULTAS EKONOMI" + RESET);
        System.out.println("Koor Divisi Danus mencegatmu. Dia memegang kotak sumbangan.");
        System.out.println(CYAN + "[Danus]: 'Wajib beli merchandise stiker angkatan! 50 Ribu!'" + RESET);

        System.out.println("\nOpsi:");
        System.out.println("1. 'Gak punya duit bang!' (Ajak Berantem)");
        System.out.println("2. Bayar Rp50 (Skip Battle)");

        String choice = scanner.nextLine();

        if (choice.equals("2")) {
            if (p.getMoney() >= 50) {
                System.out.println(YELLOW + "\nKamu membayar Rp50 dan Koor Danus pun minggir." + RESET);
                p.setMoney(p.getMoney() - 50);
                promptEnter();
                return 2; // Next scene: Hukum
            } else {
                System.out.println(RED + "\nUangmu tidak cukup! Koor Danus marah." + RESET);
            }
        }

        // Kalau pilih 1 atau uang tidak cukup, Battle
        System.out.println(CYAN + "\n[Danus]: 'Pelit amat lu! Sini ribut!'" + RESET);
        if (battleSystem.fight(p, "Koor Danus", 45, 10, 2)) {
            return 2; // Win battle, next scene: Hukum
        } else {
            return -1; // Lose battle, game over
        }
    }

    // --- SCENE 3: HUKUM (Branch: Fight vs Kabur/Diplomasi) ---
    private int sceneHukum(Player p) {
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
            promptEnter();
            return 3; // Next scene: Teknik
        }

        if (battleSystem.fight(p, "Dosen Bajupink", 50, 12, 3)) {
            return 3; // Win battle, next scene: Teknik
        } else {
            return -1; // Lose battle, game over
        }
    }

    // --- SCENE 4: TEKNIK (Puzzle Branching) ---
    private int sceneTeknik(Player p) {
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
                p.addItem("Obeng Sakti");
                System.out.println(GREEN + "HP Recovery +20" + RESET);
                p.setHp(p.getHp() + 20);
                promptEnter();
                return 4; // Skip battle & Win, next scene: Kedokteran
            } else {
                System.out.println(RED + "GAGAL! PC Meledak!" + RESET);
                System.out.println("HP -20. Dan Robotnya sadar kamu penyusup!");
                p.setHp(p.getHp() - 20);
                if (!p.isAlive())
                    return -1; // Player died from explosion
            }
        }

        System.out.println(CYAN + "\n[Robot]: 'EXTERMINATE!'" + RESET);
        if (battleSystem.fight(p, "Robot Proyek Gagal", 65, 14, 5)) {
            return 4; // Win battle, next scene: Kedokteran
        } else {
            return -1; // Lose battle, game over
        }
    }

    // --- SCENE 5: KEDOKTERAN (Wajib Battle tapi ada gimmick) ---
    private int sceneKedokteran(Player p) {
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
            return -1; // Player died from formalin

        if (battleSystem.fight(p, "Anatomi Skeleton", enemyHp, enemyAtk, 4)) {
            return 5; // Win battle, next scene: Fasilkom
        } else {
            return -1; // Lose battle, game over
        }
    }

    // --- ENDING ---
    private int sceneFasilkom(Player p) {
        printDivider();
        System.out.println(PURPLE + "📍 LOKASI: FASILKOM" + RESET);
        int finalHp = p.getHp();
        boolean hasObeng = p.hasItem("Obeng Sakti");

        System.out.println("HP Akhir: " + finalHp);
        if (hasObeng) {
            System.out.println(CYAN + "Kamu membawa 'Obeng Sakti' milik anak Teknik..." + RESET);
        }


        if (finalHp > 70) {
            System.out.println(GREEN + "🏆 GOOD ENDING: MAHASISWA TELADAN 🏆" + RESET);
            System.out.println("Kamu masuk Fasilkom dengan sehat walafiat.");
            System.out.println("Langsung dapet IP 4.0 di semester satu!");
            if (hasObeng) {
                System.out.println("Berkat 'Obeng Sakti', kamu juga jadi jago ngoprek dan buka jasa servis laptop.");
            }
        } else if (finalHp > 20) {
            System.out.println(YELLOW + "😐 NEUTRAL ENDING: MAHASISWA KUPU-KUPU 😐" + RESET);
            System.out.println("Kamu lolos, tapi capek banget.");
            if (hasObeng) {
                System.out.println("Tapi berkat 'Obeng Sakti', kamu jadi teknisi andalan di kosan dan dapet banyak teman!");
            } else {
                System.out.println("Kuliah, Pulang, Kuliah, Pulang. Yang penting lulus.");
            }
        } else {
            System.out.println(RED + "☠️ BAD ENDING: DROP OUT DINI ☠️" + RESET);
            System.out.println("Baru masuk gerbang Fasilkom, kamu pingsan dan dilarikan ke RS.");
            if (hasObeng) {
                System.out.println("Saat di RS, kamu iseng memperbaiki mesin EKG rusak dengan 'Obeng Sakti'. Kamu direkrut jadi teknisi rumah sakit dan kaya raya. Gagal jadi programmer, sukses jadi tukang servis.");
            } else {
                System.out.println("Orang tua menyuruhmu pindah jurusan ke Tata Boga.");
            }
        }
        return -1; // End the game
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