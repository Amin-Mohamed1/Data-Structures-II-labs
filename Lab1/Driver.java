package org.example;

import java.io.File;
import java.util.*;
public class Driver {
    public static int[] read(String path) {
        File file = new File(path);
        ArrayList<Integer> arr = null;
        try {
            Scanner scanner = new Scanner(file);
            arr = new ArrayList<>();
            String input = scanner.nextLine();
            String[] numbers = input.split(", ");
            for (String number : numbers) {
                arr.add(Integer.parseInt(number));
            }
            int[] res = new int[arr.size()];
            for (int i = 0; i < arr.size(); i++) {
                res[i] = arr.get(i);
            }
            return res;
        } catch (Exception e) {
            System.out.println("File not found");
        }
        assert arr != null;
        int[] res = new int[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            res[i] = arr.get(i);
        }
        return res;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("""

                   _____            _   _            \s
                  / ____|          | | (_)           \s
                 | (___   ___  _ __| |_ _ _ __   __ _\s
                  \\___ \\ / _ \\| '__| __| | '_ \\ / _` |
                  ____) | (_) | |  | |_| | | | | (_| |
                 |_____/ \\___/|_|   \\__|_|_| |_|\\__, |
                                                 __/ |
                                                |___/\s
                """);
        System.out.println("Welcome to Lab1 Sorting Algorithms program. DS2");
        System.out.print("Please enter the path of the file you want to sort: ");
        String path = scanner.nextLine();
        int choice;
        while(true){
            System.out.println("Please select the sorting algorithm you want to use:");
            System.out.println("1. Insertion Sort");
            System.out.println("2. Merge Sort");
            System.out.println("3. Radix Sort");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            if (choice == 4) {
                break;
            }
            System.out.print("Enter 1 if you want to see the intermediate steps of the sorting algorithm. Enter 0 if you don't want to see the intermediate steps: ");
            int intermediate = scanner.nextInt();
            int[] arr = read(path);
            ArrayList<int[]> steps = null;
            switch (choice) {
                case 1 -> {
                    System.out.println("Insertion Sort");
                    long start = System.nanoTime();
                    steps = InsertionSort.sort(arr,1);
                    long end = System.nanoTime();
                    System.out.println("Time taken: " + (end - start) + " ns");
                }
                case 2 -> {
                    System.out.println("Merge Sort");
                    long start = System.nanoTime();
                    steps = MergeSort.mergeSort(arr,1);
                    long end = System.nanoTime();
                    System.out.println("Time taken: " + (end - start) + " ns");
                }
                case 3 -> {
                    System.out.println("Radix Sort");
                    long start = System.nanoTime();
                    try{
                        steps = RadixSort.sort(arr,1);
                    } catch (Exception e) {
                        System.out.println("Negative numbers are not allowed in the input array.");
                    }
                    long end = System.nanoTime();
                    System.out.println("Time taken: " + (end - start) + " ns");
                }
                default -> System.out.println("Invalid choice");
            }
            if(intermediate == 1){
                assert steps != null;
                for(int[] step: steps){
                    for(int i: step){
                        System.out.print(i + " ");
                    }
                    System.out.println();
                }
            }
            else if(intermediate == 0){
                assert steps != null;
                for(int i: steps.get(steps.size() - 1)){
                    System.out.print(i + " ");
                }
            }
            System.out.println("\n");
        }
    }
}