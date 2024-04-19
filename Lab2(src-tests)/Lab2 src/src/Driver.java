import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.awt.Color;

public class Driver {
    public static void main(String[] args) throws IOException {
        String RESET = "\u001B[0m";
        String RED = "\u001B[31m";
        String GREEN = "\u001B[32m";
        String YELLOW = "\u001B[33m";
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the type of the backend perfect hashing (e.g., quadratic/linear):");
        String backendType = scanner.nextLine();
        EnglishDictionary dictionary = new EnglishDictionary(backendType);
        while(true) {
            System.out.println("1. Insert a string");
            System.out.println("2. Delete a string");
            System.out.println("3. Search for a string");
            System.out.println("4. Batch insert strings from a file");
            System.out.println("5. Batch delete strings from a file");
            System.out.println("6. Exit");
            System.out.print("Choose an operation:");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            int[] result;
            switch (choice) {
                case 1:
                    System.out.print("Enter the string to insert:");
                    String insertString = scanner.nextLine();
                    result = dictionary.insert(insertString);
                    if (result[0]==1) {
                        System.out.println(GREEN+"String inserted successfully."+RESET);
                        System.out.println("Number of rehashings: " + result[1]);
                    }
                    else {
                        System.out.println(YELLOW+"String already exists in the dictionary."+RESET);
                    }
                    break;
                case 2:
                    System.out.print("Enter the string to delete:");
                    String deleteString = scanner.nextLine();
                    result = dictionary.delete(deleteString);
                    if (result[0]==1) {
                        System.out.println(GREEN+"String deleted successfully."+RESET);
                        System.out.println("Number of rehashings: " + result[1]);
                    }
                    else
                        System.out.println(RED+"String doesn't exist in the dictionary."+RESET);
                    break;
                case 3:
                    System.out.print("Enter the string to search:");
                    String searchString = scanner.nextLine();
                    if (dictionary.search(searchString)) {
                        System.out.println(GREEN+"String exists in the dictionary."+RESET);
                    }
                    else {
                        System.out.println(RED+"String doesn't exist in the dictionary."+RESET);
                    }
                    break;
                case 4:
                    System.out.print("Enter the path of the file containing strings to insert:");
                    String insertFilePath = scanner.nextLine();
                    ArrayList<String> insertStrings = CSVParser.parseCSVFromFile(insertFilePath);
                    System.out.println(insertStrings);
                    result = dictionary.batchInsertFromFile(insertStrings);
                    System.out.println("Number of newly added strings: " + result[0]);
                    System.out.println("Number of already existing strings: " + result[1]);
                    System.out.println("Number of rehashings: " + result[2]);
                    break;
                case 5:
                    System.out.println("Enter the path of the file containing strings to delete:");
                    String deleteFilePath = scanner.nextLine();
                    ArrayList<String> deleteStrings = CSVParser.parseCSVFromFile(deleteFilePath);
                    result = dictionary.batchDeleteFromFile(deleteStrings);
                    System.out.println("Number of deleted strings: " + result[0]);
                    System.out.println("Number of non-existing strings: " + result[1]);
                    System.out.println("Number of rehashings: " + result[2]);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println(RED +"Invalid choice. Please enter a number between 1 and 6."+ RESET);
            }
        }
    }
}