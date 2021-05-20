/* 
   Author: Amanda Perrine
   Date: 05/17/2021
   Assignment Name: PetDatabase.java
   Program is design to manage an array of Pet objects and read file
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PetDatabase {

    // main
    public static void main(String[] args) {
        System.out.println("Pet Database Program.");
        ArrayList<Pet> pets;
        // create array for pets
        try ( Scanner s = new Scanner(System.in)) {
            // create array for pets
            pets = new ArrayList<>();
            // load data from a file
            loadData(pets, "pet.txt");
            OUTER:
            while (true) {
                System.out.println();
                printMenu();
                int option = getUserInput(s);
                // switch statement
                switch (option) {
                    case 1:
                        displayPets(pets);
                        break;
                    case 2:
                        addPet(pets, s);
                        break;
                    case 3:
                        remove(pets, s);
                        break;
                    default:
                        break OUTER;
                }
            }
            // close the scanner object
        }

        // save database of pets
        saveData(pets, "pet.txt");
        // print message and end program
        System.out.println();
        System.out.println("Goodbye!");
    }

    private static void saveData(ArrayList<Pet> pets, String filename) {
        // write pets data to given file
        try {
            try ( // open a file to write
                     FileWriter file = new FileWriter(filename)) {
                for (int i = 0; i < pets.size(); i++) {
                    file.write(String.format("%s %d\n", pets.get(i).getName(), pets.get(i).getAge()));
                }
                // close the file
            }
        } catch (IOException e) {
            // print error message
            System.err.println("Can not save Data!");
        }

    }

    private static void remove(ArrayList<Pet> pets, Scanner s) {
        System.out.println();
        // ask user for id to remove
        System.out.print("Enter the pet ID to remove: ");
        // check for valid input
        try {
            int ID = Integer.parseInt(s.nextLine());
            if (ID < 0 || ID > pets.size() - 1) {
                // print error message
                System.out.print("Error: ID ");
                System.out.print(ID);
                System.out.println(" does not exist.");
            } else {
                // remove pet with given id
                // print message removal is success
                System.out.print(pets.get(ID).getName() + " " + pets.get(ID).getAge());
                System.out.println(" is removed.");
                pets.remove(ID);
            }
        } catch (NumberFormatException e) {
            // print error message
            System.out.println("Invalid Input!");
        }
    }

    private static void addPet(ArrayList<Pet> pets, Scanner s) {
        System.out.println();
        // prompt user to add pets till database is full
        while (true) {
            System.out.print("Add pet (name, age): ");
            // get user input
            String input;
            input = s.nextLine();
            String[] data = input.split(" ");
            // check input data
            try {
                if (data.length != 2) {
                    throw new IllegalArgumentException();
                }
                int age = Integer.parseInt(data[1]);
                // check for age
                if (age < 1 || age > 20) {
                    // print error message
                    System.out.print("Error: ");
                    System.out.print(age);
                    System.out.println(" is not a valid age");
                } else {
                    // create a pet object
                    Pet p = new Pet(data[0], age);
                    // check if database have space
                    if (pets.size() < 5) {
                        // add pet to database
                        pets.add(p);
                    } else  {
                        // print error message
                        System.out.print("Error: ");
                        System.out.println("Database is full.");
                        break;
                    }
                }
            } catch (IllegalArgumentException e) {
                // print error message
                System.out.print("Error: ");
                System.out.print(input);
                System.out.println(" is not a valid input");
            }
        }
    }

    private static void displayPets(ArrayList<Pet> pets) {
        // print header
        System.out.println("+-------------------------+");
        System.out.println(String.format("| %2s | %-12s | %3s |", "ID", "NAME", "AGE"));
        System.out.println("+-------------------------+");
        // print pets
        for (int i = 0; i < pets.size(); i++) {
            System.out.println(String.format("| %2d | %-12s | %3d |", i, pets.get(i).getName(), pets.get(i).getAge()));
        }
        System.out.println("+-------------------------+");
        System.out.print(pets.size());
        System.out.println(" rows in set.");

    }

    private static int getUserInput(Scanner s) {
        // get input from user 
        int option = 0;
        // prompt user till a valid input is entered
        while (true) {
            System.out.print("\nYour choice: ");
            try {
                option = Integer.parseInt(s.nextLine());
                if (option > 0 || option < 5) {
                    // return the correct option
                    break;
                }
            } catch (NumberFormatException e) {
                // print error message
                System.out.println("Invalid Input!");
            }
        }
        return option;
    }

    private static void printMenu() {
        // print menu
        System.out.println("What would you like to do?");
        System.out.println("1) View all pets");
        System.out.println("2) Add new pets");
        System.out.println("3) Remove a pet");
        System.out.println("4) Exit program");
    }

    private static void loadData(ArrayList<Pet> pets, String filename) {
        // read data from file if file exist
        // create a scanner object to read data
        Scanner file;
        try {
            file = new Scanner(new File(filename));
            while (file.hasNextLine()) {
                // get input data from file
                String input = file.nextLine();
                String[] data = input.split(" ");
                // create a pet object
                Pet p = new Pet(data[0], Integer.parseInt(data[1]));
                // add pet to database
                pets.add(p);
            }
            // close the scanner
            file.close();
        } catch (FileNotFoundException e) {
            // file does not exist
        }
    }

}
