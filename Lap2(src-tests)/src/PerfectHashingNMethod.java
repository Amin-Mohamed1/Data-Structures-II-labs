import java.util.ArrayList;
import java.util.HashMap;

public class PerfectHashingNMethod<T> implements PerfectHashing<T> {
    private int inputSize;
    private int numberOfRehashing = 0;
    public final double loadFactor = 0.7;
    private int[][] universalHashingMatrix;
    private HashMap<T, T> elements = new HashMap<>(); // To keep track of elements
    private ArrayList<T>[] firstLevelTable;
    private PerfectHashingNSquareMethod<T>[] secondLevelTables;
    private int m; // number of elements in hashing array

    // Default constructor
    public PerfectHashingNMethod() {
        inputSize = 64; // Default number to be inserted
        initialize();
    }

    // Parameterized constructor
    public PerfectHashingNMethod(int inputSize) {
        this.inputSize = inputSize;
        initialize();
    }

    // Initialize the hashing tables and matrix
    private void initialize() {
        firstLevelTable = new ArrayList[inputSize * 5];
        secondLevelTables = new PerfectHashingNSquareMethod[inputSize * 5];
        universalHashingMatrix = new int[(int) Math.floor(Math.log10(inputSize * 5) / Math.log10(2))][63];
        m=0;
        randomizeMatrix();
    }

    // Universal hash function for first-level hashing
    private int computeFirstLevelHash(T key) {
        long hashedKey = computeHash(key);
        int[] binaryRepresentation = Computation.decimalToBinary(hashedKey, 63);
        int[] transformedBinary = Computation.matrixMultiplication(universalHashingMatrix, binaryRepresentation);
        return Computation.binaryToDecimal(transformedBinary);
    }

    // Second-level hash functions
    private void secondLevelHash(int index, T key, int size) {
        PerfectHashingNSquareMethod<T> nSquare = new PerfectHashingNSquareMethod<>(size);
        if (firstLevelTable[index] == null)
            return;
        if (firstLevelTable[index].contains(key))
            return;
        firstLevelTable[index].add(key);
        for(T entry : firstLevelTable[index]){
            nSquare.insert(entry);
        }
        secondLevelTables[index] =  nSquare;
    }

    // Insertion
    @Override
    public boolean insert(T key) {
        int index = computeFirstLevelHash(key);
        if (firstLevelTable[index] == null || firstLevelTable[index].isEmpty()){
            firstLevelTable[index] = new ArrayList<>();
            firstLevelTable[index].add(key);
            elements.put(key, key);
            m++;
            if((double)(m*1.0)/(inputSize * 5) >= 0.7){
                rehashDueToLoadFactor();
            }
            printFirstLevelTable();
            printSecondLevelTables();
            return true;
        }
        int size = firstLevelTable[index].size();
        // Rehash each element into the appropriate second-level table
        if (elements.containsKey(key))
            return false;
        elements.put(key, key);
        m++;
        if((double)(m*1.0)/(inputSize * 5) >= 0.7){
            rehashDueToLoadFactor();
            return true;
        }
        secondLevelHash(index, key, size + 1);
        printFirstLevelTable();
        printSecondLevelTables();
        return true;
    }

    // Search
    @Override
    public boolean search(T key) {
        int index = computeFirstLevelHash(key);
        if (firstLevelTable[index] == null)
            return false;
        if (secondLevelTables[index] != null) {
            printFirstLevelTable();
            printSecondLevelTables();
            return secondLevelTables[index].search(key);
        }
        printFirstLevelTable();
        printSecondLevelTables();
        return true;
    }

    // Deletion
    @Override
    public boolean delete(T key) {
        int index = computeFirstLevelHash(key);
        if (firstLevelTable[index] == null)
            return false;
        // Delete the element from the appropriate second-level table
        if (secondLevelTables[index] != null){
            firstLevelTable[index].remove(key);
            if(secondLevelTables[index].delete(key)){
                elements.remove(key);
                m--;
                printFirstLevelTable();
                printSecondLevelTables();
                return true;
            }
            printFirstLevelTable();
            printSecondLevelTables();
            return false;
        }
        firstLevelTable[index].remove(key);
        elements.remove(key);
        m--;
        printFirstLevelTable();
        printSecondLevelTables();
        return true;
    }
    // Hashing function
    private long computeHash(T key) {
        // Hashing logic based on key type
        return switch (key.getClass().getSimpleName()) {
            case "String" -> Computation.convertStringToLong((String) key);
            case "Integer" -> (Integer) key;
            case "Character" -> (long) (Character) key;
            default -> throw new IllegalArgumentException("Unsupported key type: " + key.getClass().getName());
        };
    }
    // Randomize the universal hashing matrix
    private void randomizeMatrix() {
        int row = universalHashingMatrix.length;
        int col = universalHashingMatrix[0].length;
        universalHashingMatrix = Computation.getRandomMatrix(row, col);
    }
    public int getNumberOfRehashing() {
        return numberOfRehashing;
    }
    private void rehashDueToLoadFactor() {
        inputSize *= 2;
        initialize();
        rehashingByLoadFactor();
    }
    private void rehashingByLoadFactor(){
        numberOfRehashing++;
        for(T key : elements.keySet()){
            insert(key);
        }
    }
    /*// Rehash due to load factor

    // Rehashing based on load factor
    private void rehashingByLoadFactor() {
        boolean collision;
        do {
            collision = false;
            numberOfRehashing++;
            firstLevelTable = new ArrayList[inputSize * 5];
            randomizeMatrix();
            for (Map.Entry<T, T> entry : elements.entrySet()) {
                long hashCode = computeHash(entry.getKey());
                int[] binaryHashCode = Computation.decimalToBinary(hashCode, 63);
                int[] newBinaryHashCode = Computation.matrixMultiplication(universalHashingMatrix, binaryHashCode);
                int index = Computation.binaryToDecimal(newBinaryHashCode);
                if (firstLevelTable[index] == null) {
                    firstLevelTable[index] = new ArrayList<>();
                    firstLevelTable[index].add(entry.getKey());
                    firstLevelTable[index].add(entry.getValue());
                } else {
                    collision = true;
                    break;
                }
            }
        } while (collision);
    }*/

    // Print the first-level table
    public void printFirstLevelTable() {
        String RESET = "\u001B[0m";
        String CYAN = "\u001B[36m";
        System.out.println(CYAN + "\nFirst Level Table:" + RESET);
        for (int i = 0; i < firstLevelTable.length; i++) {
            System.out.print("Index = " + i );
            if (firstLevelTable[i] != null) {
                if(!firstLevelTable[i].isEmpty())
                    System.out.print("  → elements = ");
                int s = 0;
                for (T key : firstLevelTable[i]){
                    s++;
                    System.out.print(key + " ");
                    if (s != firstLevelTable[i].size())
                        System.out.print(", ");
                }
            }
            System.out.println();
        }
    }

    // Print the second-level tables
    public void printSecondLevelTables() {
        String RESET = "\u001B[0m";
        String YELLOW = "\u001B[33m";
        String CYAN = "\u001B[36m";
        int count = 0;
        for (int i = 0; i < secondLevelTables.length; i++) {
            if (secondLevelTables[i] != null) {
                if(count == 0){
                    System.out.println(CYAN + "\nSecond Level Tables:" + RESET);
                    count++;
                }
                System.out.print(YELLOW);
                System.out.println("Slot " + i + ":");
                System.out.print(RESET);
                secondLevelTables[i].printHashTable();
            }
        }
    }
}
