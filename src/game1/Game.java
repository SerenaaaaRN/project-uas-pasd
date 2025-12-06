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

        // Loop Game
        while (gameRunning && currentLocationIndex < map.length) {
            // Tampilkan stats setiap pindah lokasi
            player.showStats();

            // Jalankan story, yang akan return index lokasi berikutnya
            int nextLocationIndex = story.playScene(currentLocationIndex, player);

            if (nextLocationIndex == -1) {
                // Player mati atau game selesai
                gameRunning = false;
                break;
            }

            // Pindah ke lokasi berikutnya
            currentLocationIndex = nextLocationIndex;
        }

        System.out.println("\n--- GAME OVER ---");
    }
}