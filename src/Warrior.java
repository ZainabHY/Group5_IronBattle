import java.util.Random;
public class Warrior extends Character {
    private int stamina;
    private int strength;

    public static int generateRandomNumber(int min, int max) {
        // Create an instance of the Random class
        Random random = new Random();

        // Generate a random number between min and max (inclusive)
        return random.nextInt((max - min) + 1) + min;
    }

    //
    public Warrior(String name, int hp, int stamina,int strength) {
        super(name, hp);
        this.strength = strength;
        this.stamina= stamina;
    }




    //get methods for stamina. however, the stamina value already sat randomly on parent class
    public int getStamina() {
        return stamina;
    }

    //get methods for strength. however, the stamina value already sat randomly on parent class
    public int getStrength() {
        return strength;
    }




    public static String generateRandomHitType() {
        Random random = new Random();
        int randomNumber = random.nextInt(2); // Generates either 0 or 1

        // Use the random number to select the hit type
        if (randomNumber == 0) {
            return "weak";
        } else {
            return "heavy";
        }

    }
    @Override
    public void attack(Character c) {
        String hitType = generateRandomHitType();
        if (!isAlive()) {
            System.out.println(getName() + " is not alive and cannot attack.");
            return;
        }

        if (stamina >= 5) {
            if (hitType == "heavy")
            {
                int damage = strength;
                c.setHp(c.getHp() - damage);
                stamina = stamina -5;
                System.out.println(getName() +" performs a Heavy attack on " + c.getName() + " for " + damage + " damage." );

            }

            //hitType is equal to weak
            else {
                int damage = strength / 2; // Weak attack deals half of the strength damage
                c.setHp(c.getHp() - damage);
                stamina += 1; // Recover 1 stamina point with a Weak attack
                System.out.println(getName() + " performs a Weak attack on " + c.getName() + " for " + damage + " damage.");
            }

        }
        //when warrior have less stamina
        else if (stamina>=0){
            if (hitType == "heavy")//perform a weak attack
            {
                int damage = strength/2;
                c.setHp(c.getHp() - damage);
                stamina -= 5;
                System.out.println(getName() + " performs a Weak attack on " + c.getName() + " for " + damage + " damage since the stamina is not enough for a heavy attack");


            }
            //hitType is equal to weak
            else
            {
                int damage = 0;
                c.setHp(c.getHp()-damage);
                stamina += 2;
                System.out.println(getName() + " performs a Weak attack on " + c.getName() + " for " + damage + " damage");

            }

        }
    }

    @Override
    protected int getMinHP() {
        return 100;
    }

    @Override
    protected int getMaxHP() {
        return 200;
    }
}