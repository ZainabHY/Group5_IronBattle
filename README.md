Brief Description:
RPG battle simulator that consists of having 2 characters fighting to determine one winner 

We have 6 files .. let us describe them 

1. Attacker interface  --> By Waleed Alqahtani
2. Character abstract class --> By Waleed Alqahtani
   - Character class implements Attacker
   - It has 2 subclasses: Warrior and Wizard

3. Warrior class  --> By Jumana Alhabsi
    - Warrior extends Character

4. Wizard class  --> By Joud Alturki
    - Wizard extends Character

5. Battle class --> By Joud Alturki
    - Battle has start() method to start the battle rounds when called
    - Also contains Detailed information about each character for each round
   
6. StartBattle  -->  By Zainab AlYousif
   - StartBattle contains main method
   - Display 2 types of Menus for the user  
     - Main menu
     - Submenu (Choosing Characters)
   - Battle could be start automatically by generating random characters 
   or the user choose each character type first
   - Includes methods for:
     - Creating random characters
     - Creating Wizard character
     - Creating Warrior character
     - Creating CSV file contains characters info at the end of the battle  