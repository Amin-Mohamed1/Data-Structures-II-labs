import java.io.IOException;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the type of the backend perfect hashing (e.g., quadratic/linear):");
        String backendType = scanner.nextLine();
        
        EnglishDictionary dictionary = new EnglishDictionary(backendType);
        
        while (true) {
            System.out.println("Choose an operation:");
            System.out.println("1. Insert a string");
            System.out.println("2. Delete a string");
            System.out.println("3. Search for a string");
            System.out.println("4. Batch insert strings from a file");
            System.out.println("5. Batch delete strings from a file");
            System.out.println("6. Exit");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    System.out.println("Enter the string to insert:");
                    String insertString = scanner.nextLine();
                    if (dictionary.insert(insertString)) {
                        System.out.println("String inserted successfully.");
                    } else {
                        System.out.println("String already exists in the dictionary.");
                    }
                    break;
                case 2:
                    System.out.println("Enter the string to delete:");
                    String deleteString = scanner.nextLine();
                    if (dictionary.delete(deleteString)) {
                        System.out.println("String deleted successfully.");
                    } else {
                        System.out.println("String doesn't exist in the dictionary.");
                    }
                    break;
                case 3:
                    System.out.println("Enter the string to search:");
                    String searchString = scanner.nextLine();
                    if (dictionary.search(searchString)) {
                        System.out.println("String exists in the dictionary.");
                    } else {
                        System.out.println("String doesn't exist in the dictionary.");
                    }
                    break;
                case 4:
                    System.out.println("Enter the path of the file containing strings to insert:");
                    String insertFilePath = scanner.nextLine();
                    try {
                        int[] result = dictionary.batchInsertFromFile(insertFilePath);
                        System.out.println("Number of newly added strings: " + result[0]);
                        System.out.println("Number of already existing strings: " + result[1]);
                    } catch (IOException e) {
                        System.out.println("Error reading from file: " + e.getMessage());
                    }
                    break;
                case 5:
                    System.out.println("Enter the path of the file containing strings to delete:");
                    String deleteFilePath = scanner.nextLine();
                    try {
                        int[] result = dictionary.batchDeleteFromFile(deleteFilePath);
                        System.out.println("Number of deleted strings: " + result[0]);
                        System.out.println("Number of non-existing strings: " + result[1]);
                    } catch (IOException e) {
                        System.out.println("Error reading from file: " + e.getMessage());
                    }
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
            }
        }
    }
}
