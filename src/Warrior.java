import java.util.Random;


public class Warrior extends Character {
    private int stamina;
    private int strength;
    Random random = new Random();

    public Warrior(String name, int hp, int stamina, int strength) {
        super(name, hp);
        this.stamina = stamina;
        this.strength = strength;

//        this.stamina = generateStamina(); // Generate random STAMINA in this range (10-50)
//        this.strength = generateStrength(); // Generate random STRENGTH in this range (1-10)
    }

    // Getters and Setters

    public int getStamina() {
        return stamina;
    }

    // Set for STAMINA by Generating random number between 10-50
//    private int generateStamina()
//    {
//        return random.nextInt(10,51);
//    }

    public int getStrength() {
        return strength;
    }

    // Set for STRENGTH by Generating random number between 10-50
//    private int generateStrength()
//    {
//        return random.nextInt(1, 11);
//    }

    // Minimum HP for a Warrior
    @Override
    protected int getMinHP() {
        return 100;
    }

    // Maximum HP for a Warrior
    @Override
    protected int getMaxHP() {
        return 200;
    }


    // Overloaded attack() method for Warriors
    @Override
    public void attack(Character character) {
        int attackType = random.nextInt(2); // 0 for Heavy attack, 1 for Weak attack

        if (attackType == 0) {
            if (stamina >= 5) {
                int heavyAttackDamage = strength; // Damage based on the warrior's strength
                character.setHp(character.getHp() - heavyAttackDamage); // Reduce target's health
                stamina -= 5; // Consume stamina for the heavy attack
                System.out.println(getName() + " performs a Heavy attack on " + character.getName() + " causing " + heavyAttackDamage + " damage");
            } else {
                System.out.println(getName() + " doesn't have enough stamina to perform a Heavy attack");
                attack(character); // Retry with a Weak attack
            }
        } else {
            if (stamina >= 1) {
                int weakAttackDamage = strength / 2; // Damage is half of the warrior's strength
                character.setHp(character.getHp() - weakAttackDamage); // Reduce target's health
                stamina += 1; // Recover stamina for the weak attack
                System.out.println(getName() + " performs a Weak attack on " + character.getName() + " causing " + weakAttackDamage + " damage");
            } else {
                System.out.println(getName() + " doesn't have enough stamina to perform a Weak attack");
                stamina += 2; // Recover stamina by 2
            }
        }
    }
}