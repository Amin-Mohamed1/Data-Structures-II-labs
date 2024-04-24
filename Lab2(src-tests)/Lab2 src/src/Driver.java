import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) throws IOException {
        String RESET = "\u001B[0m";
        String RED = "\u001B[31m";
        String GREEN = "\u001B[32m";
        String YELLOW = "\u001B[33m";
        System.out.println(RED+"\n" +
                "  _    _           _     _             \n" +
                " | |  | |         | |   (_)            \n" +
                " | |__| | __ _ ___| |__  _ _ __   __ _ \n" +
                " |  __  |/ _` / __| '_ \\| | '_ \\ / _` |\n" +
                " | |  | | (_| \\__ \\ | | | | | | | (_| |\n" +
                " |_|  |_|\\__,_|___/_| |_|_|_| |_|\\__, |\n" +
                "                                  __/ |\n" +
                "                                 |___/ \n"+RESET);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the type of the backend perfect hashing (e.g., quadratic/linear):");
        String backendType = scanner.nextLine();
        while(!backendType.equals("quadratic") && !backendType.equals("linear")) {
            System.out.println(RED+"Invalid backend type."+RESET);
            System.out.print("Enter the type of the backend perfect hashing (e.g., quadratic/linear):");
            backendType = scanner.nextLine();
        }
        EnglishDictionary dictionary = new EnglishDictionary(backendType);
        while(true) {
            System.out.println("I. Insert a string");
            System.out.println("D. Delete a string");
            System.out.println("S. Search for a string");
            System.out.println("BI. Batch insert strings from a file");
            System.out.println("BD. Batch delete strings from a file");
            System.out.println("Q. Quit");
            System.out.print("Choose an operation:");
            String choice = scanner.nextLine();
            choice = choice.toLowerCase();
            int[] result;
            switch (choice) {
                case "i":
                    System.out.print("Enter the string to insert:");
                    String insertString = scanner.nextLine();
                    if(insertString.isEmpty() || insertString.isBlank()) {
                        System.out.println(RED+"String cannot be empty."+RESET);
                        break;
                    }
                    result = dictionary.insert(insertString);
                    if (result[0]==1) {
                        System.out.println(GREEN+"String inserted successfully."+RESET);
                        System.out.println(YELLOW+"Number of rehashings: " + result[1]+RESET);
                    }
                    else {
                        System.out.println(RED+"String already exists in the dictionary."+RESET);
                    }
                    break;
                case "d":
                    System.out.print("Enter the string to delete:");
                    String deleteString = scanner.nextLine();
                    result = dictionary.delete(deleteString);
                    if (result[0]==1) {
                        System.out.println(GREEN+"String deleted successfully."+RESET);
                        System.out.println(YELLOW+"Number of rehashings: " + result[1]+RESET);
                    }
                    else
                        System.out.println(RED+"String doesn't exist in the dictionary."+RESET);
                    break;
                case "s":
                    System.out.print("Enter the string to search:");
                    String searchString = scanner.nextLine();
                    if (dictionary.search(searchString)) {
                        System.out.println(GREEN+"String exists in the dictionary."+RESET);
                    }
                    else {
                        System.out.println(RED+"String doesn't exist in the dictionary."+RESET);
                    }
                    break;
                case "bi":
                    System.out.print("Enter the path of the file containing strings to insert:");
                    String insertFilePath = scanner.nextLine();
                    ArrayList<String> insertStrings = Parser.parseCSVFromFile(insertFilePath);
                    System.out.println(insertStrings);
                    result = dictionary.batchInsertFromFile(Arrays.copyOf(
                        insertStrings.toArray(), insertStrings.size(), String[].class));
                    System.out.println(GREEN+"Number of newly added strings: " + result[0]+RESET);
                    System.out.println(RED+"Number of already existing strings: " + result[1]+RESET);
                    System.out.println(YELLOW+"Number of rehashings: " + result[2]+RESET);
                    break;
                case "bd":
                    System.out.println("Enter the path of the file containing strings to delete:");
                    String deleteFilePath = scanner.nextLine();
                    ArrayList<String> deleteStrings = Parser.parseCSVFromFile(deleteFilePath);
                    result = dictionary.batchDeleteFromFile(deleteStrings);
                    System.out.println("Number of deleted strings: " + result[0]);
                    System.out.println("Number of non-existing strings: " + result[1]);
                    System.out.println("Number of rehashings: " + result[2]);
                    break;
                case "q":
                    System.out.println("Quitting...");
                    return;
                default:
                    System.out.println(RED +"Invalid choice."+ RESET);
            }
        }
    }
}