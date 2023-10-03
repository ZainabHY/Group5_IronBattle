public class Battle {
    private Character character1;
    private Character character2;
    private Character winner;

    public Battle(Character character1, Character character2) {
        this.character1 = character1;
        this.character2 = character2;
    }

    public void start() {
        System.out.println("-------- Battle Start --------\n");

        while (true) {
            System.out.println("\u001B[32m** Round begins! **\u001B[0m");

            // Simultaneous attacks
            character1.attack(character2);
            character2.attack(character1);

            // To get command right for Strength / Intelligence
            String command1 ="";
            String command2 ="";
            if ( character1 instanceof Warrior)
                command1 = " Strength: " + ((Warrior) character1).getStrength();
            else if ( character1 instanceof Wizard)
                command1 = " Intelligence: " + ((Wizard) character1).getIntelligence();

            if ( character2 instanceof Warrior)
                command2 = " Strength: " + ((Warrior) character2).getStrength();
            else if ( character2 instanceof Wizard)
                command2 = " Intelligence: " + ((Wizard) character2).getIntelligence();



            System.out.println(character1.getName() + " attacks " + character2.getName() + "! | " +
                    character2.getName() + " HP: " + character2.getHp() + command1);
            System.out.println(character2.getName() + " attacks " + character1.getName() + "! | " +
                    character1.getName() + " HP: " + character1.getHp() + command2);

            if (character1.isDefeated() && character2.isDefeated()) {
                System.out.println("\u001B[33m === Both combatants are defeated. The battle ends in a tie! ===\u001B[0m");
                break;
            }
            else if (character1.isDefeated()) {
                winner = character2;
                break;
            }
            else if (character2.isDefeated()) {
                winner = character1;
                break;
            }

            System.out.println("\u001B[33m** Round ends! **\u001B[0m");
            System.out.println();
        }

        System.out.println("\n \u001B[35m----- Battle Over -----\u001B[0m");
    }

    public Character getWinner() {
        return winner;
    }
}