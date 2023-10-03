import java.util.Scanner;

public class StartBattle_Menu {
    public static void main(String[] args) {
        Character character1 = createRandomCharacter();
        Character character2;
//        Character character2 = createRandomCharacter();

        // To prevent repeated names
        do {
            character2 = createRandomCharacter();
        } while (character1.getName().equals(character2.getName()));

        Battle battle = new Battle(character1, character2);

        System.out.println("\n-------------- Welcome to the Battle Simulator! --------------");
        System.out.println("\n----------------------------------------------------\n");

        // Creating a Scanner object to take user input for Menu
        Scanner scanner = new Scanner(System.in);
        boolean exit = false; // if user want to exit the battle

        while (!exit) {
            System.out.println("\n--- MENU ---");
//            System.out.println("1. Creating Characters");
            System.out.println("1. Start Battle");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");

            /* To handle the case when the user enters an incorrect input
             from the menu, and Retake user's input */
            int choice;
            while (true)
            {
                try
                {
                    choice = Integer.parseInt(scanner.nextLine());
                    if (choice < 1 || choice > 2)
                        throw new NumberFormatException();
                    break;
                }
                catch (NumberFormatException e)
                {
                    System.out.print("Invalid input. Please enter 1 or 2: ");
                }
            }

            switch (choice)
            {
//                case 1:
//                    createWarrior(scanner);
//                    break;
                case 1:
                    battle.start();
                    break;
                case 2:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        System.out.println("Thank you for using the Battle Simulator!");


        System.out.println("\u001B[32m===== Battle between \u001B[0m" + character1.getName() + "\u001B[32m and \u001B[0m" + character2.getName() + "\u001B[32m begins! ===== \n \u001B[0m");

        boolean isFirstBattle = true;
        while (true) {
            if (!isFirstBattle)
                System.out.println("\n \u001B[33m=== Restarting the battle... ===\u001B[0m");

            isFirstBattle  = false;

//            Battle battle = new Battle(character1, character2);
//            battle.start();

            Character winner = battle.getWinner();
            if (winner != null) {
                System.out.println("\n----------------------------------------------------\n");
                System.out.println("\u001B[32m****\u001B[0m " + winner.getName() + " \u001B[32mwins the battle! ****\n\n\u001B[0m");
                break;
            }
            else {
                System.out.println("\u001B[33m === The battle ended in a tie ===\u001B[0m ");
            }
        }
    }

    private static Character createRandomCharacter() {
        String name = generateRandomName();
        int hp = generateRandomValue(50, 200);

        if (Math.random() < 0.5) {
            // Create a Warrior
            int stamina = generateRandomValue(10, 50);
            int strength = generateRandomValue(1, 10);
            return new Warrior(name, hp, stamina, strength);
        } else {
            // Create a Wizard
            int mana = generateRandomValue(10, 50);
            int intelligence = generateRandomValue(1, 50);
            return new Wizard(name, hp, mana, intelligence);
        }
    }

    private static String generateRandomName() {
        String[] names = {"Grom", "Gandalf", "Arthas", "Merlin", "Thrall", "Saruman"};
        int index = generateRandomValue(0, names.length - 1);
        return names[index];
    }

    private static int generateRandomValue(int min, int max) {
        return (int) (Math.random() * (max - min + 1)) + min;
    }
}