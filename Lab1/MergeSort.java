import java.util.ArrayList;
public class MergeSort {
    public static  ArrayList<int[]> mergeSort(int[] arr , int getInter) {

        //arraylist for intermediates arrays
        ArrayList<int[]> intermediates = new ArrayList<>();

        //add original array
        intermediates.add(new int[arr.length]);
        int last = intermediates.size();
        for (int i = 0; i < arr.length; i++) {
            intermediates.get(last - 1)[i] = arr[i];
        }
        int[] temp = new int[arr.length];
        mergeSort(arr, temp, 0, arr.length - 1, intermediates ,getInter);
        if(getInter == 0 )
            intermediates.add(arr) ;
        return intermediates;
    }

    private static void mergeSort(int[] arr, int[] temp, int left, int right, ArrayList<int[]> intermediates , int getInter) {
        if (left < right) {
            int middle = (left + right) / 2;
            mergeSort(arr, temp, left, middle, intermediates ,getInter);
            mergeSort(arr, temp, middle + 1, right, intermediates,getInter);
            merge(arr, temp, left, middle, right, intermediates, getInter);
        }
    }

    private static void merge(int[] arr, int[] temp, int left, int middle, int right, ArrayList<int[]> intermediates , int getInter) {
        System.arraycopy(arr, left, temp, left, right - left + 1);

        int i = left;
        int j = middle + 1;
        int k = left;

        while (i <= middle && j <= right) {
            if (temp[i] <= temp[j]) {
                arr[k++] = temp[i++];
            } else {
                arr[k++] = temp[j++];
            }
        }

        while (i <= middle) {
            arr[k++] = temp[i++];
        }
        while (j <= right) {
            arr[k++] = temp[j++];
        }
        if(getInter == 1){

            intermediates.add(new int[arr.length]);
            int last = intermediates.size();
            for (int m = 0; m < arr.length; m++) {
                intermediates.get(last - 1)[m] = arr[m];
            }
        }

    }
}
