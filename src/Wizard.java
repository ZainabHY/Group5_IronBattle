import java.util.Random;



public class Wizard extends Character{
    private int mana;
    private int intelligence;
    Random random = new Random();

    public Wizard(String name, int hp, int mana, int intelligence) {
        super(name, hp);
        this.mana = mana;
        this.intelligence = intelligence;
//        this.mana = generateMana(); // Generate random MANA in this range (10-50)
//        this.intelligence = generateIntelligence(); // Generate random INTELLIGENCE in this range (1-50)
    }

    // Getters and Setters
    public int getMana() {
        return mana;
    }

    // Set for mana by Generating random number between 10-50
//    private int generateMana()
//    {
//        return random.nextInt(10,51);
//    }

    public int getIntelligence() {
        return intelligence;
    }

    // Set for mana by Generating random number between 1-50
//    private int generateIntelligence()
//    {
//        return random.nextInt(1, 51);
//    }


    // Minimum HP for a Wizard
    @Override
    protected int getMinHP() {
        return 50;
    }

    // Maximum HP for a Wizard
    @Override
    protected int getMaxHP() {
        return 100;
    }


    // Overloaded attack() method for Wizards
    @Override
    public void attack(Character character) {
        int attackType = random.nextInt(2); // 0 for Fireball, 1 for Staff hit

        if (attackType == 0) {
            if (mana >= 5)
            {
                int fireballDamage = intelligence; // Damage based on the wizard's intelligence
                character.setHp(character.getHp() - fireballDamage); // Reduce target's health
                mana -= 5; // Reduce mana by 5 for the Fireball attack
                System.out.println(getName() + " casts a Fireball on " + character.getName() + " causing " + fireballDamage + " damage");
            }

            else
            {
                System.out.println(getName() + " doesn't have enough mana to cast a Fireball");
                attack(character); // Retry with a Staff hit
            }
        }

        else
        {
            if (mana >= 1)
            {
                int staffHitDamage = 2; // Fixed damage by 2 for a Staff hit
                character.setHp(character.getHp() - staffHitDamage); // Reduce target's health
                mana += 1; // Recover mana by +1 for the Staff hit
                System.out.println(getName() + " hits " + character.getName() + " with a Staff causing " + staffHitDamage + " damage");
            }
            else
            {
                System.out.println(getName() + " doesn't have enough mana to perform a Staff hit");
                mana += 2; // Recover mana by 2
            }
        }
    }
}