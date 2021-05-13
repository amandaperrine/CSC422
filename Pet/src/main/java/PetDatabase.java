/* 
   Author: Amanda Perrine
   Date: 05/11/2021
   Assignment Name: PetDatabase.java
   Program is design to manage an array of Pet objects. 
 */

import java.util.Scanner;

//PetDatabase class
public class PetDatabase {

    public static Pet[] pets = new Pet[100];
    public static int petCount = 0;
    public static Scanner s = new Scanner(System.in);
    public static Scanner input = new Scanner(System.in);

    //Main method
    public static void main(String args[]) {

        System.out.print("Pet Database Program.");

        //While loop
        while (true) {
            int choice = getUserChoice();
            //Swich statements
            switch (choice) {
                case 1:
                    showAllPets();
                    break;
                case 2:
                    addPets();
                    break;
                case 5:
                    searchPetsByName();
                    break;
                case 6:
                    searchPetsByAge();
                    break;
            }//End of switch statements

        } //End of while loop
    }

    //Get user choice from method
    private static int getUserChoice() {
        System.out.print("\nWhat would you like to do?\n"
                + "\n1. View all pets"
                + "\n2. Add more pets"
                + "\n3. Update an existing pet"
                + "\n4. Remove an existing pet"
                + "\n5. Search pets by name"
                + "\n6. Search pets by age"
                + "\n7. Exit program\n"
                + "\nYour Choice: ");
        int choiceMenu = s.nextInt();

        return choiceMenu;
    }

    //Case 1: view all pets
    private static void showAllPets() {
        //For loop
        for (int m = 0; m < petCount; m++) {
            System.out.printf("\n%3s %10s %4s\n", "ID", "Name", "Age");
            System.out.print(" " + "|" + m + "|" + "     " + pets[m].getName() + "  " + "|" + pets[m].getAge() + "|");
        }//End of for loop
        System.out.println("\n" + petCount + " rows in set.");
    }

    //Case 2: add more pets
    private static void addPets() {
        String addPetsinput = "";

        //While loop
        while (!addPetsinput.equalsIgnoreCase("done")) {
            System.out.print("add pet (name age): ");
            addPetsinput = input.nextLine();
            if (addPetsinput.equalsIgnoreCase("done")) {
                System.out.println(petCount + " pets added.");
                break;
            }

            String name;
            int m = 0;
            while (addPetsinput.charAt(m) != ' ') {
                m++;
            }
            name = addPetsinput.substring(0, m);
            int age = Integer.parseInt(addPetsinput.substring(m + 1));
            Pet addingNewPet = new Pet(name, age);
            pets[petCount] = addingNewPet;
            petCount++;
        }//End of while loop
    }

    //Case 5: search pets by name
    private static void searchPetsByName() {
        System.out.print("\nEnter a name to search: ");
        String nameSearch = input.next();

        //For loop
        for (int j = 0; j < petCount; j++) {
            if (pets[j].getName().equalsIgnoreCase(nameSearch)) {
                System.out.printf("\n%3s %10s %4s\n", "ID", "Name", "Age");
                System.out.print(" " + "|" + j + "|" + "     " + pets[j].getName() + "  " + "|" + pets[j].getAge() + "|");
            }
        }//End of for loop
    }

    //Case 6: search pets by age
    private static void searchPetsByAge() {
        System.out.print("\nEnter age to search: ");
        int ageSearch = s.nextInt();

        //For loop
        for (int j = 0; j < petCount; j++) {
            if (pets[j].getAge() == ageSearch) {
                System.out.printf("\n%3s %10s %4s\n", "ID", "Name", "Age");
                System.out.print(" " + "|" + j + "|" + "     " + pets[j].getName() + "  " + "|" + pets[j].getAge() + "|");
            }
        }//End of for loop
    }

}
