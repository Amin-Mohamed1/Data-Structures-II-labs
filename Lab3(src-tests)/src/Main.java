import java.util.List;
import java.util.Scanner;

public class Main {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_CYAN = "\u001B[36m";

    public static void main(String[] args) {
        MainFunctions mainFunctions = new MainFunctions();
        Scanner scanner = new Scanner(System.in);

        boolean initialized = false;
        while (!initialized) {
            clearScreen();
            System.out.print("Enter the path of the graph file: ");
            try {
                String graphPath = scanner.nextLine();
                mainFunctions.createGraph(graphPath);
                initialized = true;
            } catch (Exception e) {
                System.out.println(ANSI_RED + "Error: Could not initialize the graph. Please try again." + ANSI_RESET);
            }
        }

        while (true) {
            clearScreen();
            System.out.println(ANSI_CYAN + "Main Menu:" + ANSI_RESET);
            System.out.println("1. " + ANSI_GREEN + "Single Source Shortest Paths" + ANSI_RESET);
            System.out.println("2. " + ANSI_GREEN + "All-Pairs Shortest Paths" + ANSI_RESET);
            System.out.println("3. " + ANSI_GREEN + "Check for Negative Cycles" + ANSI_RESET);
            System.out.println("4. " + ANSI_GREEN + "Exit" + ANSI_RESET);
            System.out.print("Select an option: ");
            String selection = scanner.nextLine();

            switch (selection) {
                case "1":
                    singleSourceShortestPathsMenu(mainFunctions, scanner);
                    break;
                case "2":
                    allPairsShortestPathsMenu(mainFunctions, scanner);
                    break;
                case "3":
                    checkNegativeCyclesMenu(mainFunctions, scanner);
                    break;
                case "4":
                    System.out.println(ANSI_CYAN + "Exiting..." + ANSI_RESET);
                    return;
                default:
                    System.out.println(ANSI_RED + "Invalid selection. Please choose again." + ANSI_RESET);
            }
        }
    }

    private static void singleSourceShortestPathsMenu(MainFunctions mainFunctions, Scanner scanner) {
        clearScreen();
        System.out.println(ANSI_CYAN + "Single Source Shortest Paths Menu:" + ANSI_RESET);
        int source = askForSourceNode(scanner);
        if (source != -1) {
            System.out.println("Select Method:");
            System.out.println("1. " + ANSI_GREEN + "Dijkstra" + ANSI_RESET);
            System.out.println("2. " + ANSI_GREEN + "Bellman-Ford" + ANSI_RESET);
            System.out.println("3. " + ANSI_GREEN + "Floyd-Warshall" + ANSI_RESET);
            System.out.print("Enter your choice: ");
            String methodChoice = scanner.nextLine();
            switch (methodChoice) {
                case "1":
                    mainFunctions.chooseMethodOneSrc(1, source);
                    singleSourceShortestPathsSubMenu(mainFunctions, scanner, source);
                    break;
                case "2":
                    mainFunctions.chooseMethodOneSrc(2, source);
                    singleSourceShortestPathsSubMenu(mainFunctions, scanner, source);
                    break;
                case "3":
                    mainFunctions.chooseMethodOneSrc(3, source);
                    singleSourceShortestPathsSubMenu(mainFunctions, scanner, source);
                    break;
                default:
                    System.out.println(ANSI_RED + "Invalid choice." + ANSI_RESET);
            }
        }
    }

    private static void singleSourceShortestPathsSubMenu(MainFunctions mainFunctions, Scanner scanner, int source) {
        int dest, cost;
        while (true) {
            clearScreen();
            System.out.println(ANSI_CYAN + "Single Source Shortest Paths Sub-Menu:" + ANSI_RESET);
            System.out.println("1. " + ANSI_GREEN + "Get cost of path to a specific node" + ANSI_RESET);
            System.out.println("2. " + ANSI_GREEN + "Get path to a specific node" + ANSI_RESET);
            System.out.println("3. " + ANSI_GREEN + "Return to main menu" + ANSI_RESET);
            System.out.print("Select an option: ");
            String selection = scanner.nextLine();

            switch (selection) {
                case "1":
                    System.out.print("Enter destination node: ");
                    dest = Integer.parseInt(scanner.nextLine());
                    cost = mainFunctions.getCostFor(source, dest);
                    if (cost != Integer.MAX_VALUE) {
                        System.out.println("Cost of path from node " + source + " to " + dest + ": " + cost);
                    } else {
                        System.out.println(ANSI_RED + "Error: Invalid choice or path not found." + ANSI_RESET);
                    }
                    promptToContinue();
                    break;
                case "2":
                    System.out.print("Enter destination node: ");
                    dest = Integer.parseInt(scanner.nextLine());
                    List<Integer> path = mainFunctions.getPathFor(source, dest);
                    cost = mainFunctions.getCostFor(source, dest);
                    if (cost != Integer.MAX_VALUE) {
                        System.out.println("Path from node " + source + " to " + dest + ": " + path);
                    } else {
                        System.out.println(ANSI_RED + "Error: Invalid choice or path not found." + ANSI_RESET);
                    }
                    promptToContinue();
                    break;
                case "3":
                    return;
                default:
                    System.out.println(ANSI_RED + "Invalid selection. Please choose again." + ANSI_RESET);
            }
        }
    }

    private static void allPairsShortestPathsMenu(MainFunctions mainFunctions, Scanner scanner) {
        clearScreen();
        System.out.println(ANSI_CYAN + "All-Pairs Shortest Paths Menu:" + ANSI_RESET);
        System.out.println("Select Method:");
        System.out.println("1. " + ANSI_GREEN + "Dijkstra" + ANSI_RESET);
        System.out.println("2. " + ANSI_GREEN + "Bellman-Ford" + ANSI_RESET);
        System.out.println("3. " + ANSI_GREEN + "Floyd-Warshall" + ANSI_RESET);
        System.out.print("Enter your choice: ");
        String methodChoice = scanner.nextLine();
        switch (methodChoice) {
            case "1":
                mainFunctions.chooseMethodForAll(1);
                allPairsShortestPathsSubMenu(mainFunctions, scanner);
                break;
            case "2":
                mainFunctions.chooseMethodForAll(2);
                allPairsShortestPathsSubMenu(mainFunctions, scanner);
                break;
            case "3":
                mainFunctions.chooseMethodForAll(3);
                allPairsShortestPathsSubMenu(mainFunctions, scanner);
                break;
            default:
                System.out.println(ANSI_RED + "Invalid choice." + ANSI_RESET);
        }
    }

    private static void allPairsShortestPathsSubMenu(MainFunctions mainFunctions, Scanner scanner) {
        int src, dest, cost;
        while (true) {
            clearScreen();
            System.out.println(ANSI_CYAN + "All-Pairs Shortest Paths Sub-Menu:" + ANSI_RESET);
            System.out.println("1. " + ANSI_GREEN + "Get cost of path from a specific node to another" + ANSI_RESET);
            System.out.println("2. " + ANSI_GREEN + "Get path from a specific node to another" + ANSI_RESET);
            System.out.println("3. " + ANSI_GREEN + "Return to main menu" + ANSI_RESET);
            System.out.print("Select an option: ");
            String selection = scanner.nextLine();

            switch (selection) {
                case "1":
                    System.out.print("Enter source node: ");
                    src = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter destination node: ");
                    dest = Integer.parseInt(scanner.nextLine());
                    cost = mainFunctions.getCostFor(src, dest);
                    if (cost != Integer.MAX_VALUE) {
                        System.out.println("Cost of path from node " + src + " to " + dest + ": " + cost);
                    } else {
                        System.out.println(ANSI_RED + "Error: Invalid choice or path not found." + ANSI_RESET);
                    }
                    promptToContinue();
                    break;
                case "2":
                    System.out.print("Enter source node: ");
                    src = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter destination node: ");
                    dest = Integer.parseInt(scanner.nextLine());
                    List<Integer> path = mainFunctions.getPathFor(src, dest);
                    cost = mainFunctions.getCostFor(src, dest);
                    if (cost != Integer.MAX_VALUE) {
                        System.out.println("Path from node " + src + " to " + dest + ": " + path);
                    } else {
                        System.out.println(ANSI_RED + "Error: Invalid choice or path not found." + ANSI_RESET);
                    }
                    promptToContinue();
                    break;
                case "3":
                    return;
                default:
                    System.out.println(ANSI_RED + "Invalid selection. Please choose again." + ANSI_RESET);
            }
        }
    }

    private static void checkNegativeCyclesMenu(MainFunctions mainFunctions, Scanner scanner) {
        clearScreen();
        System.out.println(ANSI_CYAN + "Check for Negative Cycles Menu:" + ANSI_RESET);
        System.out.println("Select Method:");
        System.out.println("1. " + ANSI_GREEN + "Bellman-Ford" + ANSI_RESET);
        System.out.println("2. " + ANSI_GREEN + "Floyd-Warshall" + ANSI_RESET);
        System.out.print("Enter your choice: ");
        String methodChoice = scanner.nextLine();
        switch (methodChoice) {
            case "1":
                checkNegativeCycles(mainFunctions, true);
                break;
            case "2":
                checkNegativeCycles(mainFunctions, false);
                break;
            default:
                System.out.println(ANSI_RED + "Invalid choice." + ANSI_RESET);
        }
        promptToContinue();
    }

    private static void checkNegativeCycles(MainFunctions mainFunctions, boolean useBellmanFord) {
        clearScreen();
        if (useBellmanFord) {
            mainFunctions.chooseMethodForAll(2);
        } else {
            mainFunctions.chooseMethodForAll(3);
        }
        System.out.println(ANSI_CYAN + "Checking for Negative Cycles..." + ANSI_RESET);
        if (mainFunctions.detectNegativeCycles()) {
            System.out.println(ANSI_RED + "Negative cycles detected." + ANSI_RESET);
        } else {
            System.out.println(ANSI_GREEN + "No negative cycles found." + ANSI_RESET);
        }
    }

    private static int askForSourceNode(Scanner scanner) {
        System.out.print("Enter the source node: ");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println(ANSI_RED + "Invalid input. Please enter a valid integer." + ANSI_RESET);
            return -1;
        }
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void promptToContinue() {
        System.out.println("Press Enter to continue...");
        new java.util.Scanner(System.in).nextLine();
    }
}
