import java.util.*;
public class RadixSort {
    public static ArrayList<int[]> sort(int[] arr) throws IllegalArgumentException {
        ArrayList<int[]> steps = new ArrayList<>();

        if (arr.length == 0) {
            steps.add(new int[]{});
            return steps;
        }

        int maxDigits = getMaxNumberOfDigits(getMax(arr, arr.length));

        /* Initialize 20 buckets: 10 for positive digits (0-9) and 10 for negative digits (-9 to 0) */
        ArrayList<ArrayList<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            buckets.add(new ArrayList<>());
        }

        // Perform radix sort
        for (int i = 1; i <= maxDigits; i++) {

            /* this loop is to initialize slots in the corresponding bucket index */
            for (int j = 0; j < arr.length; j++) {
                int digit = getDigit(arr[j], i);
                buckets.get(digit + 9).add(arr[j]); // Shift negative digits to the end of buckets
            }

            /* bring back the elements from the buckets to the array */
            int index = 0;
            for (int j = 0; j < 20; j++) {
                for (int k = 0; k < buckets.get(j).size(); k++) {
                    arr[index] = buckets.get(j).get(k);
                    index++;
                }
                buckets.get(j).clear();
            }

            /* adding the array after the i-th iteration for 1 <= i <= maxDigits */
            steps.add(new int[arr.length]);
            for (int j = 0; j < arr.length; j++) {
                steps.get(steps.size() - 1)[j] = arr[j];
            }
        }
        return steps;
    }


    /* get the maximum number in the array */
    private static int getMax(int[] arr, int n) {
        int maxNumber = Math.abs(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            if (Math.abs(arr[i]) > maxNumber) {
                maxNumber = Math.abs(arr[i]);
            }
        }
        return maxNumber;
    }

    /* get the number of digits in the maximum number in the array */
    private static int getMaxNumberOfDigits(int maxNumber) {
        int maxDigits = 0;
        while (maxNumber > 0) {
            maxNumber /= 10;
            maxDigits++;
        }
        return maxDigits;
    }

    /* get digit in certain place within a number */
    private static int getDigit(int number, int digit) {
        return (number / (int) Math.pow(10, digit - 1)) % 10;
    }
}
