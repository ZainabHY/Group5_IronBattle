import java.util.Random;

public abstract class Character implements Attacker{
    private String id;
    private String name;
    private int hp;
    private boolean isAlive;

    public Character(String name, int hp) {
        this.id = generateId();
        this.name = name;
        this.hp = generateRandomHP(); // Generate random HP based on Warrior/ Wizard range
        this.isAlive = true;
    }


    // Getters and Setters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
        if (hp <= 0)
            isAlive = false;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public abstract void attack(Character character);

    // Generate a unique ID using your preferred method
    private String generateId()
    {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 10; i++)
        {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }

    protected abstract int getMinHP(); // Abstract method to retrieve minimum HP
    protected abstract int getMaxHP(); // Abstract method to retrieve maximum HP

    private int generateRandomHP() {
        Random random = new Random();
        return random.nextInt(getMaxHP() - getMinHP() + 1) + getMinHP();
    }

    public boolean isDefeated() {
        return hp <= 0;
    }
}

