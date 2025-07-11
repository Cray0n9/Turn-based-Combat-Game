import java.util.Scanner;

import Characters.ANSIColor;
import Characters.Character;
import Characters.PlayerCharacters.Mage;
import Characters.PlayerCharacters.Warmonger;
import Characters.EnemyCharacters.Goblin;
import Characters.EnemyCharacters.Orc;
import Characters.EnemyCharacters.Dragon;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;


public class Menu{
    int choice;
    Character selectedCharacter;
    Random random = new Random();

    public static void clearConsole() {  // Clear the console by printing empty lines
    for (int i = 0; i < 50; ++i) System.out.println(); 
    }

    public static int playerInput() {  // Method to handle player input
        Scanner scanner = new Scanner(System.in);
        System.out.print(ANSIColor.ORANGE+"Enter your choice: "+ANSIColor.RESET);
        return scanner.nextInt();
    }

    // Player characters
    Mage mage = new Mage("Gardjoka");
    Warmonger warmonger = new Warmonger("Glavniq");
        
    // Enemy characters
    Goblin goblin = new Goblin();
    Orc orc = new Orc();
    Dragon dragon = new Dragon();

    ArrayList<Character> enemyCharacters = new ArrayList<>(); // Array of enemy characters
    {
    enemyCharacters.add(goblin);
    enemyCharacters.add(orc);
    enemyCharacters.add(dragon);
    }

    public void startMenu() {
        clearConsole();
        System.out.println(ANSIColor.GREEN+"Welcome to the Game!"+ANSIColor.RESET);
        System.out.println();
        characterSelectMenu();
    }

    public void characterSelectMenu() {
        System.out.println(ANSIColor.YELLOW+"Choose your character:"+ANSIColor.RESET);
        System.out.println(ANSIColor.YELLOW+"1."+ANSIColor.RESET+mage.toString());
        System.out.println(ANSIColor.YELLOW+"2."+ANSIColor.RESET+warmonger.toString());

        choice = playerInput();

        if (choice == 1) {
            selectedCharacter = mage;
            clearConsole();
            System.out.println("You selected: "+ANSIColor.BLUE+selectedCharacter.getName()+ANSIColor.RESET);
        } else if (choice == 2) {
            selectedCharacter = warmonger;
            clearConsole();
            System.out.println("You selected: "+ANSIColor.BLUE+selectedCharacter.getName()+ANSIColor.RESET);
        } else {
            System.out.println(ANSIColor.RED+"Invalid choice."+ANSIColor.RESET);
            characterSelectMenu(); // Restart the menu if invalid choice
            return;
        }
        displayCombatMenu(); // Show combat menu after character selection
    }


    public void displayCombatMenu() {
        System.out.println();
        System.out.println(ANSIColor.RED+"Combat Menu:"+ANSIColor.RESET);
        System.out.println(ANSIColor.YELLOW+"1. Start Battle"+ANSIColor.RESET);
        System.out.println(ANSIColor.YELLOW+"2. View Inventory"+ANSIColor.RESET);
        System.out.println(ANSIColor.YELLOW+"3. View Character Stats"+ANSIColor.RESET);
        System.out.println(ANSIColor.YELLOW+"4. Back to Character select Menu"+ANSIColor.RESET);

        int combatChoice = playerInput();

        // Handle combat menu choices
        if(combatChoice == 1) {
            clearConsole();
            System.out.println(ANSIColor.ORANGE+"Starting battle..."+ANSIColor.RESET);
            
            if (enemyCharacters.isEmpty()) {
                System.out.println(ANSIColor.GREEN+"You have defeated all enemies!"+ANSIColor.RESET);
                return; // Exit if no enemies left
            }
            Collections.shuffle(enemyCharacters); // Shuffle the enemy characters to randomize selection
            Character enemyCharacter = enemyCharacters.get(random.nextInt(enemyCharacters.size()));
            enemyCharacters.remove(enemyCharacter); // Remove the selected enemy from the array to avoid repetition in future battles

            CombatSystem combatSystem = new CombatSystem(selectedCharacter, enemyCharacter, this); // Start combat with the selected character and a random enemy
            System.out.println("You are facing: "+ANSIColor.RED+enemyCharacter.getName()+ANSIColor.RESET);
            System.out.println(enemyCharacter.toString()); // Display enemy character stats
            combatSystem.startBattle(); // Start the battle

        } else if(combatChoice == 2) {
            clearConsole();
            System.out.println(ANSIColor.ORANGE+"Inventory is empty."+ANSIColor.RESET);

            displayCombatMenu(); // Go back to the combat menu

        } else if (combatChoice == 3) {
            clearConsole();
            System.out.println(selectedCharacter.toString());
            System.out.println("Your level is "+ANSIColor.PURPLE+selectedCharacter.getLevel()+ANSIColor.RESET);
            System.out.println(selectedCharacter.getExperience());
            
            displayCombatMenu(); // Go back to the combat menu

        } else if(combatChoice == 4) {
            clearConsole();
            characterSelectMenu(); // Go back to the select menu

        } else {
            clearConsole();
            System.out.println(ANSIColor.ORANGE+"Invalid choice. Please try again."+ANSIColor.RESET);
            displayCombatMenu(); // Restart the combat menu
        }
    }
}