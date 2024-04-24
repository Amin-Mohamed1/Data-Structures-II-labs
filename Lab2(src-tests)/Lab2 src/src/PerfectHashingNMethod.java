package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class PerfectHashingNMethod<T> implements PerfectHashing<T> {
    private int inputSize;
    private int numberOfRehashing = 0;
    public final double loadFactor = 0.7;
    private int[][] universalHashingMatrix;
    int m = 0;
    private ArrayList<T> elements = new ArrayList<>(); // To keep track of elements
    private ArrayList<T>[] firstLevelTable;
    private PerfectHashingNSquareMethod<T>[] secondLevelTables;

    // Default constructor
    public PerfectHashingNMethod() {
        inputSize = 64; // Default number to be inserted
        initialize();
    }

    public int getUtilizedSpace() {
        int utilizedSpace = 0;
        // Count elements in the first-level table
        // for (ArrayList<T> list : firstLevelTable) {
        // if (list != null) {
        // utilizedSpace += list.size();
        // }
        // }
        // Count elements in the second-level tables
        for (PerfectHashingNSquareMethod<T> table : secondLevelTables) {
            if (table != null) {
                utilizedSpace += table.getN() * table.getN();
            }
        }
        return utilizedSpace + firstLevelTable.length;
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
        for (T entry : firstLevelTable[index]) {
            nSquare.insert(entry);
        }
        secondLevelTables[index] = nSquare;
    }

    // Insertion
    @Override
    public boolean insert(T key) {
        int index = computeFirstLevelHash(key);
        if (firstLevelTable[index] == null || firstLevelTable[index].isEmpty()) {
            firstLevelTable[index] = new ArrayList<>();
            firstLevelTable[index].add(key);
            elements.add(key);
            m++;
            // printFirstLevelTable();
            // printSecondLevelTables();
            return true;
        }
        int size = firstLevelTable[index].size();
        // Rehash each element into the appropriate second-level table
        if (elements.contains(key))
            return false;
        elements.add(key);
        m++;
        secondLevelHash(index, key, size + 1);
        // printFirstLevelTable();
        // printSecondLevelTables();
        return true;
    }

    // Search
    @Override
    public boolean search(T key) {
        int index = computeFirstLevelHash(key);
        if (firstLevelTable[index] == null)
            return false;
        if (secondLevelTables[index] != null) {
            // printFirstLevelTable();
            // printSecondLevelTables();
            return secondLevelTables[index].search(key);
        }
        // printFirstLevelTable();
        // printSecondLevelTables();
        return true;
    }

    // Deletion
    @Override
    public boolean delete(T key) {
        int index = computeFirstLevelHash(key);
        if (firstLevelTable[index] == null || !firstLevelTable[index].contains(key))
            return false;
        // Delete the element from the appropriate second-level table
        if (secondLevelTables[index] != null) {
            firstLevelTable[index].remove(key);
            if (secondLevelTables[index].delete(key)) {
                elements.remove(key);
                m--;
                // printFirstLevelTable();
                // printSecondLevelTables();
                return true;
            }
            // printFirstLevelTable();
            // printSecondLevelTables();
            return false;
        }
        firstLevelTable[index].remove(key);
        elements.remove(key);
        m--;
        // printFirstLevelTable();
        // printSecondLevelTables();
        return true;
    }

    @Override
    public int batchInsert(T[] ele) {
        this.inputSize = ele.length + m;
        ArrayList<T> old = new ArrayList<>(this.elements);
        old.addAll(Arrays.asList(ele));
        initialize();
        m = 0;
        int failed = 0;
        for (T e : old) {
            if (!insert(e))
                failed++;
        }
        return failed;
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
        numberOfRehashing = 0;
        for (PerfectHashingNSquareMethod<T> table : secondLevelTables)
            if (table != null)
                numberOfRehashing += table.getNumberOfRehashing();
        return numberOfRehashing;
    }

    // Print the first-level table
    public void printFirstLevelTable() {
        String RESET = "\u001B[0m";
        String CYAN = "\u001B[36m";
        System.out.println(CYAN + "\nFirst Level Table:" + RESET);
        for (int i = 0; i < firstLevelTable.length; i++) {
            System.out.print("Index = " + i);
            if (firstLevelTable[i] != null) {
                if (!firstLevelTable[i].isEmpty())
                    System.out.print("  → elements = ");
                int s = 0;
                for (T key : firstLevelTable[i]) {
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
                if (count == 0) {
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