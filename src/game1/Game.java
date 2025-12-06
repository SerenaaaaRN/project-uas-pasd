package game1;
public class Game {
    private Player player;
    private Story story;
    private String[] map;
    private int currentLocationIndex;

    public Game() {
        this.player = new Player();
        this.story = new Story();
        // Map Array 1D Linear
        this.map = new String[] {
                "Gerbang Kampus",
                "Fakultas Ekonomi",
                "Fakultas Hukum",
                "Fakultas Teknik",
                "Fakultas Kedokteran",
                "Fasilkom"
        };
        this.currentLocationIndex = 0;
    }

    public void start() {
        System.out.println("#############################################");
        System.out.println("#           CAMPUS ADVENTURE RPG            #");
        System.out.println("#############################################");
        System.out.println("Game Start!");

        boolean gameRunning = true;

        // Loop Game Linear
        while (gameRunning && currentLocationIndex < map.length) {
            // Tampilkan stats setiap pindah lokasi
            player.showStats();

            // Jalankan story berdasarkan lokasi saat ini
            boolean playerSurvived = story.playScene(currentLocationIndex, player);

            if (!playerSurvived) {
                // Player mati saat battle
                story.printBadEndingBattle();
                gameRunning = false;
                break;
            }

            // Pindah ke lokasi berikutnya
            currentLocationIndex++;
        }

        System.out.println("\n--- GAME OVER ---");
    }
}