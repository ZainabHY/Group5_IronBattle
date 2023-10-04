//import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StartBattle {
    private static List<Character> characters = new ArrayList<>();
    public static void main(String[] args) throws FileNotFoundException {

        System.out.println("\n>>>> Welcome to the Battle Simulator! <<<<\n");

        Character character1;
        Character character2;


        ///// Display Menu
        Scanner scanner = new Scanner(System.in);
        int choice ;

        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Create Characters"); // User choose characters as wanted
            System.out.println("2. Start a Battle Automatically"); // Generate random characters by system
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            while (true) {
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                    if (choice < 1 || choice > 3) {
                        throw new NumberFormatException();
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.print("You should enter 1, 2, or 3. Please try again: ");
                }
            }

            switch (choice) {
                case 1:
                    characterMenu(scanner);
                    break;
                case 2:
                    character1 = createRandomCharacter();
//        Character character2 = createRandomCharacter();

                    // To prevent repeated names
                    do {
                        character2 = createRandomCharacter();
                    } while (character1.getName().equals(character2.getName()));

                    //Adding to characters list
                    characters.add(character1);
                    characters.add(character2);

                    beforeStart(character1, character2);
//                    System.out.println("\u001B[32m===== Battle between \u001B[0m" + character1.getName() + "\u001B[32m and \u001B[0m" + character2.getName() + "\u001B[32m begins! ===== \n\n \u001B[0m");

                    break;
                case 3:
                    exit = true;
                    System.out.println("\u001B[35mThank you for using the Battle Simulator!\u001B[0m");
//                    importCharactersFromCSV();
                    // Create the CSV file at the end of the battle
                    createCSVFile();
                    break;
//            default:
//                System.out.print("You should enter 1, 2, or 3. Please try again: ");
            }
        }
    }


    //    Creating a new menu for choosing the characters
    public static void characterMenu(Scanner scanner) {
        boolean returnToMainMenu = false;
        Character character1 = null;
        Character character2 = null;

        while (!returnToMainMenu) {
            System.out.println("\nCharacter Menu");
            System.out.println("1. Warrior");
            System.out.println("2. Wizard");
            System.out.println("3. Return to the main menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.println("\u001B[33mCreating Character 1: Warrior...\u001B[0m");
                    character1 = createWarrior();
                    break;
                case 2:
                    System.out.println("\u001B[33mCreating Character 1: Wizard...\u001B[0m");
                    character1 = createWizard();
                    break;
                case 3:
                    returnToMainMenu = true;
                    break;
                default:
                    System.out.println("You should enter 1, 2, or 3. Please try again: ");
                    continue;
            }

            if (!returnToMainMenu) {
                System.out.println("\nChoose the second character:");
                System.out.println("1. Warrior");
                System.out.println("2. Wizard");
                System.out.println("3. Return to the main menu");
                System.out.print("Enter your choice: ");
                int secondChoice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (secondChoice) {
                    case 1:
                        System.out.println("\u001B[33mCreating Character 2: Warrior..\u001B[0m");
                        character2 = createWarrior();
                        break;
                    case 2:
                        System.out.println("\u001B[33mCreating Character 2: Wizard..\u001B[0m");
                        character2 = createWizard();
                        break;
                    case 3:
                        returnToMainMenu = true;
                        break;
                    default:
                        System.out.println("You should enter 1, 2, or 3. Please try again: ");
                        continue;
                }
            }

            if (character1 != null && character2 != null) {
                characters.add(character1);
                characters.add(character2);
                beforeStart(character1, character2);
            }
        }


        System.out.println("Returning to the main menu...");
    }


    private static Character createRandomCharacter()
    {
        String name = generateRandomName();
//        int hp = generateRandomValue(50, 200);

        if (Math.random() < 0.5) {
            // Create a Warrior
            int hp = generateRandomValue(100, 200);
            int stamina = generateRandomValue(10, 50);
            int strength = generateRandomValue(1, 10);
            return new Warrior(name, hp, stamina, strength);
        }
        else {
            // Create a Wizard
            int hp = generateRandomValue(50, 100);
            int mana = generateRandomValue(10, 50);
            int intelligence = generateRandomValue(1, 10);
            return new Wizard(name, hp, mana, intelligence);
        }
    }


    private static Character createWarrior()
    {
        String name = generateRandomName();
        int hp = generateRandomValue(100, 200);
        int stamina = generateRandomValue(10, 50);
        int strength = generateRandomValue(1, 10);
        return new Warrior(name, hp, stamina, strength);
    }

    // Creating Wizard
    private static Character createWizard()
    {
        String name = generateRandomName();
        int hp = generateRandomValue(50, 100);
        int mana = generateRandomValue(10, 50);
        int intelligence = generateRandomValue(1, 50);
        return new Wizard(name, hp, mana, intelligence);
    }

    // Some commands must be done before start
    private static void beforeStart(Character character1, Character character2){
        System.out.println("\u001B[32m===== Battle between \u001B[0m" + character1.getName() + "\u001B[32m and \u001B[0m" + character2.getName() + "\u001B[32m begins! ===== \n \u001B[0m");

        boolean isFirstBattle = true;
        while (true) {
            if (!isFirstBattle)
                System.out.println("\n \u001B[33m=== Restarting the battle... ===\u001B[0m");

            isFirstBattle  = false;

            Battle battle = new Battle(character1, character2);
            battle.start();

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

    private static String generateRandomName() {
        String[] names = {"Grom", "Gandalf", "Arthas", "Merlin", "Thrall", "Saruman"};
        int index = generateRandomValue(0, names.length - 1);
        return names[index];
    }

    private static int generateRandomValue(int min, int max) {
        return (int) (Math.random() * (max - min + 1)) + min;
    }


    // CSV file

    public static void createCSVFile()
    {
        String fileName = "IronBattle_Group5.csv";

        try {
            FileWriter writer = new FileWriter(fileName);

            // Writing header in our CSV file
            writer.write("Type, Name, HP, Stamina/Mana, Strength/Intelligence \n");

            // Writing characters info
            for(Character character : characters)
            {
                // To get command right for Strength / Intelligence
                String command ="";
//                String command2 ="";


                if ( character instanceof Warrior)
                    command = "Warrior, " + character.getName() + ", " + character.getName() +
                            ", " + character.getHp() + ", " + ((Warrior) character).getStamina() + ", "
                            + ((Warrior) character).getStrength() + "\n";

                else if ( character instanceof Wizard)
                    command = "Wizard, " + character.getName() + ", " + character.getName() +
                            ", " + character.getHp() + ", " + ((Wizard) character).getMana() + ", "
                            + ((Wizard) character).getIntelligence()+ "\n";



                writer.write(command);
            }

            writer.close();
            System.out.println("\nCSV of all info of the battle Created Successfully! \nFile Name: " + fileName);
        }
        catch (IOException e)
        {
            System.out.println("\nFailed to create the CSV file of the battle");
        }

    }

}
