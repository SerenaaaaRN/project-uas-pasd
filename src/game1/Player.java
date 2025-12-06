package game1;
public class Player {
    // Definisi Warna
    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m";
    private static final String BLUE = "\u001B[34m";
    private static final String YELLOW = "\u001B[33m";

    private int hp;
    private int maxHp;
    private int attack;
    private int defense;
    private String[] inventory;

    public Player() {
        this.maxHp = 100;
        this.hp = 100;
        this.attack = 15;
        this.defense = 5;
        this.inventory = new String[3];
        // Dummy items
        inventory[0] = "KTM";
        inventory[1] = "Botol Minum";
        inventory[2] = "Laptop Kentang";
    }

    // Getters & Setters
    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
        if (this.hp > maxHp)
            this.hp = maxHp;
        if (this.hp < 0)
            this.hp = 0;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public void showStats() {
        System.out.println(BLUE + "\n--- STATUS PLAYER ---" + RESET);
        System.out.println("HP      : " + GREEN + hp + "/" + maxHp + RESET);
        System.out.println("Attack  : " + attack);
        System.out.println("Defense : " + defense);
        System.out.print("Inventory: " + YELLOW);
        for (String item : inventory) {
            if (item != null)
                System.out.print("[" + item + "] ");
        }
        System.out.println(RESET);
        System.out.println(BLUE + "---------------------" + RESET);
    }
}