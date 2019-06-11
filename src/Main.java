import phonebook.PhoneBook;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter full path to phonebook file: ");
        String path = in.nextLine();
        PhoneBook phoneBook = new PhoneBook();

        while (true) {
            System.out.println();
            System.out.println("For read from file:              Press 1.");
            System.out.println("For creating new record:         Press 2.");
            System.out.println("For delete record:               Press 3.");
            System.out.println("For access to number:            Press 4.");
            System.out.println("For print all numbers:           Press 5.");
            System.out.println("For print top 5 outgoing calls:  Press 6.");
            System.out.println("For exit:                        Press 0.");
            System.out.println("----------------------");
            int choice = Integer.parseInt(in.nextLine());

            switch (choice) {
                case 1:
                    phoneBook.readFromFile(path);
                    if (phoneBook.readFromFile(path)) {

                    }
                    break;
                case 2:
                    System.out.println("Enter name: ");
                    String name = in.nextLine();
                    System.out.println("Enter number: ");
                    String number = in.nextLine();
                    if (phoneBook.addNewPair(name, number)) {
                        System.out.println("Creating new record - Successful!");
                        System.out.println("----------------------");
                    } else {
                        System.out.println("The record has failed. The number is incorrect");
                        System.out.println("----------------------");
                    }
                    break;
                case 3:
                    System.out.println("Enter name you want to delete: ");
                    String deletedName = in.nextLine();
                    if (phoneBook.deletePair(deletedName)) {
                        System.out.println("Deleting record - successful!");
                        System.out.println("----------------------");
                    } else {
                        System.out.println("The name you want to delete is not in the phonebook.");
                        System.out.println("----------------------");
                    }
                    break;
                case 4:
                    System.out.println("Enter name you want to access: ");
                    String accessName = in.nextLine();
                    System.out.println(phoneBook.accessPhoneNumber(accessName));
                    System.out.println("----------------------");
                    break;
                case 5:
                    phoneBook.printAllSorted();
                    System.out.println("----------------------");
                    break;
                case 6:
                    phoneBook.getTopFive();
                    System.out.println("----------------------");
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid input");
            }
        }
    }
}

