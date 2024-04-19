import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
public class PerfectHashingNMethod<T> implements PerfectHashing<T> {
    private int inputSize;
    private int numberOfRehashing = 0;
    public final double loadFactor = 0.7;
    private int[][] universalHashingMatrix;
    private HashMap<T, T> elements; // To keep track of elements
    private ArrayList<T>[] firstLevelTable;
    private ArrayList<T>[] secondLevelTables;

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
        secondLevelTables = new ArrayList[inputSize * 5];
        for (int i = 0; i < secondLevelTables.length; i++) {
            secondLevelTables[i] = new ArrayList<>();
        }
        universalHashingMatrix = new int[(int) Math.floor(Math.log10(inputSize * 5) / Math.log10(2))][63];
        elements = new HashMap<>();
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
    private int secondLevelHash(int index, T key, int size) {
        PerfectHashingNSquareMethod<T> nSquare = new PerfectHashingNSquareMethod<>(size);
        firstLevelTable[index].add(key);
        System.out.println(firstLevelTable[index]);
        for(T entry : firstLevelTable[index]){
            nSquare.insert(entry);
            System.out.println("Second level at slot " + index);
            System.out.println(Arrays.toString(nSquare.getHashingTable()));
        }
        return 0;
    }



    // Insertion
    @Override
    public boolean insert(T key) {
        int index = computeFirstLevelHash(key);
        if (firstLevelTable[index] == null) {
            firstLevelTable[index] = new ArrayList<>();
            firstLevelTable[index].add(key);
            elements.put(key, key);
            System.out.println("elements" + elements);
            printFirstLevelTable();
            printSecondLevelTables();
            return true;
        }
        int size = firstLevelTable[index].size();
        // Rehash each element into the appropriate second-level table
        elements.put(key, key);
        int secondIndex = secondLevelHash(index, key, size + 1);
        secondLevelTables[secondIndex].add(key);
        printFirstLevelTable();
        printSecondLevelTables();
        // Check load factor and perform rehashing if necessary
        /*
        if ((double) (elements.size()) / (double) (inputSize * 5) >= loadFactor) {
            rehashDueToLoadFactor();
        }
        */
        return true;
    }

    // Search
    @Override
    public boolean search(T key) {
        int index = computeFirstLevelHash(key);
        if (firstLevelTable[index] == null) {
            return false;
        }
        // Find the element in the appropriate second-level table
        int size = firstLevelTable[index].size();
        int secondIndex = secondLevelHash(index, key, size + 1);
        return secondLevelTables[secondIndex].contains(key);
    }

    // Deletion
    @Override
    public boolean delete(T key) {
        int index = computeFirstLevelHash(key);
        if (firstLevelTable[index] == null) {
            return false;
        }
        // Delete the element from the appropriate second-level table
        int size = firstLevelTable[index].size();
        int secondIndex = secondLevelHash(index, key, size + 1);
        boolean deleted = secondLevelTables[secondIndex].remove(key);
        if (deleted) {
            elements.remove(key);
        }
        return deleted;
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

    // Rehash due to load factor
    private void rehashDueToLoadFactor() {
        inputSize *= 2;
        initialize();
        rehashingByLoadFactor();
    }

    public int getNumberOfRehashing() {
        return numberOfRehashing;
    }

    public void setNumberOfRehashing(int numberOfRehashing) {
        this.numberOfRehashing = numberOfRehashing;
    }

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
    }

    // Print the first-level table
    public void printFirstLevelTable() {
        System.out.println("First Level Table:");
        for (int i = 0; i < firstLevelTable.length; i++) {
            if (firstLevelTable[i] != null) {
                System.out.print(i + ": ");
                for (T key : firstLevelTable[i]) {
                    System.out.print(key + " ");
                }
                System.out.println();
            }
        }
    }

    // Print the second-level tables
    public void printSecondLevelTables() {
        System.out.println("Second Level Tables:");
        for (int i = 0; i < secondLevelTables.length; i++) {
            System.out.print(i + ": ");
            for (T key : secondLevelTables[i]) {
                System.out.print(key + " ");
            }
            System.out.println();
        }
    }
}
