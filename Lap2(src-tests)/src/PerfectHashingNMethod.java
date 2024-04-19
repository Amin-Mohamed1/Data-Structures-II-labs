/*
import java.util.*;
public class PerfectHashingNMethod <T> implements PerfectHashing<T>{
    private int n ; // #number of elements to be inserted
    private final int u = 63 ; // log2^63 ///  2^63 of integers
    private int numberOfRehashing = 0 ;  // how many times we rehash to get final hashing table
    private final double loadFactor = 0.7 ;  // = m / n^2
    private int m = 0 ; // number of elements in hashing array
    private ArrayList<T>[] hashingTable ;
    private int[][] universalHashingMatrix;
    private Map<T,T> elements = new HashMap<>() ;;
    public PerfectHashingNMethod(){
        n = 64 ; // # default number to be inserted
        initialize();
    }
    public PerfectHashingNMethod(int n ){
        this.n = n ;
        initialize();
    }
    private void initialize(){
        hashingTable = new ArrayList[n * n] ;
        universalHashingMatrix = new int[(int)Math.floor( Math.log10(n*n) / Math.log10(2))][u] ;
        randomizeMatrix();
    }
    private void randomizeMatrix(){
        int row = universalHashingMatrix.length;
        int col = universalHashingMatrix[0].length;
        universalHashingMatrix = Computation.getRandomMatrix(row,col);
    }
    @Override
    public boolean insert(T key) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }
    @Override
    public boolean search(T key) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'search'");
    }
    @Override
    public boolean delete(T key) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
}
*/

import java.util.ArrayList;
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
    private int secondLevelHash(int index, T key) {
        //JUST THIS DONNEE!!!!
        return 0;
    }

    // Insertion
    @Override
    public boolean insert(T key) {
        int index = computeFirstLevelHash(key);
        if (firstLevelTable[index] == null) {
            firstLevelTable[index] = new ArrayList<>();
        }
        firstLevelTable[index].add(key);
        elements.put(key, key);
        // Rehash each element into the appropriate second-level table
        int secondIndex = secondLevelHash(index, key);
        secondLevelTables[secondIndex].add(key);
        // Check load factor and perform rehashing if necessary
        if ((double) (elements.size()) / (double) (inputSize * 5) >= loadFactor) {
            rehashDueToLoadFactor();
        }
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
        int secondIndex = secondLevelHash(index, key);
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
        int secondIndex = secondLevelHash(index, key);
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
