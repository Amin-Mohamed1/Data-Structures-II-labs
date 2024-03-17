import java.util.ArrayList;
public class InsertionSort {
    public static ArrayList<int[]> sort(int[] arr){
        ArrayList<int[]> intermediates = new ArrayList<>();
        if(arr.length == 0){
            intermediates.add(new int[]{}) ;
            return intermediates ;
        }
        if(arr.length == 1){
            intermediates.add(arr);
            return intermediates;
        }

        for(int i = 1 ; i < arr.length ; i++){
            boolean anyChange = false;

            int key = arr[i];
            int j = i-1;

            while(j >= 0 && arr[j] > key){
                arr[j+1] = arr[j];
                j--;
            }

            if(arr[j+1] != key)
                anyChange = true;

            arr[j+1] = key;

            if(anyChange){
                intermediates.add(new int[arr.length]);
                System.arraycopy(arr, 0, intermediates.get(intermediates.size() - 1), 0, arr.length);
            }
        }
        if(intermediates.isEmpty())
            intermediates.add(arr);

        return intermediates;
    }
}
