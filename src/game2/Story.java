package game2;

import java.util.Random;

public class Story {

    Game game;
    UI ui;
    VisibilityManager vm;
    Player player;

    int monsterHP;

    public Story(Game g, UI userInterface, VisibilityManager vManager, Player p) {
        game = g;
        ui = userInterface;
        vm = vManager;
        player = p;
    }

    public void defaultSetup() {
        player.hp = 15;
        player.currentWeapon = "Pisau";
        player.hasKTM = 0;
        monsterHP = 20;

        ui.hpLabelNumber.setText("" + player.hp);
        ui.weaponLabelName.setText(player.currentWeapon);

        // Menghubungkan tombol start di UI dengan Handler di Game
        ui.startButton.addActionListener(game.new TitleScreenHandler());
    }

    public void selectPosition(String nextPosition) {

        switch (nextPosition) {
            case "townGate":
                townGate();
                break;
            case "talkGuard":
                talkGuard();
                break;
            case "attackGuard":
                attackGuard();
                break;
            case "crossRoad":
                crossRoad();
                break;
            case "north":
                north();
                break;
            case "east":
                east();
                break;
            case "west":
                west();
                break;
            case "fight":
                fight();
                break;
            case "playerAttack":
                playerAttack();
                break;
            case "monsterAttack":
                monsterAttack();
                break;
            case "win":
                win();
                break;
            case "lose":
                lose();
                break;
            case "ending":
                ending();
                break;
        }
    }

    public void townGate() {
        ui.mainTextArea.setText(
                "Kamu berada di depan gerbang FASILKOM (Ilmu Komputer). \nSatpam Fasilkom menghadangmu di depan. \n\nApa yang kamu lakukan?");

        ui.choice1.setText("Bicara pada Satpam");
        ui.choice2.setText("Terobos Masuk");
        ui.choice3.setText("Keluar gedung");
        ui.choice4.setText("");

        game.nextPosition1 = "talkGuard";
        game.nextPosition2 = "attackGuard";
        game.nextPosition3 = "crossRoad";
        game.nextPosition4 = "";
    }

    public void talkGuard() {
        if (player.hasKTM == 0) {
            ui.mainTextArea.setText(
                    "Satpam: Dek, mana KTM (Kartu Tanda Mahasiswa)-nya? \nKalau tidak ada KTM gak boleh masuk.");
            ui.choice1.setText(">");
            ui.choice2.setText("");
            ui.choice3.setText("");
            ui.choice4.setText("");
            game.nextPosition1 = "townGate";
            game.nextPosition2 = "";
            game.nextPosition3 = "";
            game.nextPosition4 = "";
        } else {
            ending();
        }
    }

    public void attackGuard() {
        ui.mainTextArea.setText(
                "Satpam: Woi, jangan rusuh di kampus!\n\nSatpam menepis seranganmu dengan pentungan.\n(Kamu menerima 3 damage)");
        player.hp -= 3;
        ui.hpLabelNumber.setText("" + player.hp);

        ui.choice1.setText(">");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPosition1 = "townGate";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";
    }

    public void crossRoad() {
        ui.mainTextArea.setText("Kamu berada di Bundaran Kampus.\nSelatan adalah jalan kembali ke FASILKOM.");

        ui.choice1.setText("Ke Utara (FKIP)");
        ui.choice2.setText("Ke Timur (FE)");
        ui.choice3.setText("Ke Selatan (FASILKOM)");
        ui.choice4.setText("Ke Barat (FISIP/FH)");

        game.nextPosition1 = "north";
        game.nextPosition2 = "east";
        game.nextPosition3 = "townGate";
        game.nextPosition4 = "west";
    }

    public void north() {
        ui.mainTextArea.setText(
                "Ini adalah Kantin FKIP (Keguruan). \nKamu membeli es teh dan gorengan. Segar sekali! \n\n(HP kamu pulih 2 poin)");
        player.hp = player.hp + 2;
        ui.hpLabelNumber.setText("" + player.hp);

        ui.choice1.setText("Kembali ke Bundaran");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPosition1 = "crossRoad";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";
    }

    public void east() {
        ui.mainTextArea.setText(
                "Kamu masuk ke Gedung FE (Ekonomi). \nKamu menemukan Pedang tertinggal di meja!\n\n(Kamu mendapatkan Pedang)");
        player.currentWeapon = "Pedang";
        ui.weaponLabelName.setText(player.currentWeapon);

        ui.choice1.setText("Kembali ke Bundaran");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPosition1 = "crossRoad";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";
    }

    public void west() {
        ui.mainTextArea.setText("Kamu berjalan ke area FISIP dan FH (Hukum).\nTiba-tiba Senior melabrakmu!");

        ui.choice1.setText("Lawan");
        ui.choice2.setText("Lari");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPosition1 = "fight";
        game.nextPosition2 = "crossRoad";
        game.nextPosition3 = "";
        game.nextPosition4 = "";
    }

    public void fight() {
        ui.mainTextArea.setText("HP Senior: " + monsterHP + "\n\nApa yang kamu lakukan?");

        ui.choice1.setText("Debat / Serang");
        ui.choice2.setText("Lari");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPosition1 = "playerAttack";
        game.nextPosition2 = "crossRoad";
        game.nextPosition3 = "";
        game.nextPosition4 = "";
    }

    public void playerAttack() {
        int playerDamage = 0;

        if (player.currentWeapon.equals("Pisau")) {
            playerDamage = new Random().nextInt(3);
        } else if (player.currentWeapon.equals("Pedang")) {
            playerDamage = new Random().nextInt(12);
        }

        ui.mainTextArea.setText(
                "Kamu menyerang Senior dengan " + player.currentWeapon + " dan memberi " + playerDamage + " damage!");
        monsterHP -= playerDamage;

        ui.choice1.setText(">");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        if (monsterHP > 0) {
            game.nextPosition1 = "monsterAttack";
            game.nextPosition2 = "";
            game.nextPosition3 = "";
            game.nextPosition4 = "";
        } else {
            game.nextPosition1 = "win";
            game.nextPosition2 = "";
            game.nextPosition3 = "";
            game.nextPosition4 = "";
        }
    }

    public void monsterAttack() {
        int monsterDamage = new Random().nextInt(6);

        ui.mainTextArea.setText("Senior memarahi kamu habis-habisan! \nMentalmu kena " + monsterDamage + " damage!");
        player.hp -= monsterDamage;
        ui.hpLabelNumber.setText("" + player.hp);

        ui.choice1.setText(">");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        if (player.hp > 0) {
            game.nextPosition1 = "fight";
            game.nextPosition2 = "";
            game.nextPosition3 = "";
            game.nextPosition4 = "";
        } else {
            game.nextPosition1 = "lose";
            game.nextPosition2 = "";
            game.nextPosition3 = "";
            game.nextPosition4 = "";
        }
    }

    public void win() {
        ui.mainTextArea.setText(
                "Kamu berhasil membuat Senior diam seribu bahasa!\nDia menjatuhkan dompet yang berisi KTM kamu!\n\n(Kamu mendapatkan KTM)");
        player.hasKTM = 1;

        ui.choice1.setText("Kembali ke Bundaran");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPosition1 = "crossRoad";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";
    }

    public void lose() {
        ui.mainTextArea.setText("Kamu pingsan karena tekanan mental!\nKamu DO (Drop Out).\n\nGAME OVER");

        ui.choice1.setText("");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");
        ui.choice1.setVisible(false);
        ui.choice2.setVisible(false);
        ui.choice3.setVisible(false);
        ui.choice4.setVisible(false);
    }

    public void ending() {
        ui.mainTextArea
                .setText("Satpam: Ah, itu KTM kamu. Silakan masuk.\nKamu akhirnya bisa masuk ke Fasilkom.\n\nTHE END");

        ui.choice1.setVisible(false);
        ui.choice2.setVisible(false);
        ui.choice3.setVisible(false);
        ui.choice4.setVisible(false);
    }
}
