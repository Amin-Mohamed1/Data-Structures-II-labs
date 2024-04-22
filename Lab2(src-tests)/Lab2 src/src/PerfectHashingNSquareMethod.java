import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PerfectHashingNSquareMethod<T> implements PerfectHashing<T>{
    private int n ; // #number of elements to be inserted
    private final int u = 63 ; // log2^63 ///  2^63 of integers
    private int numberOfRehashing = 0 ;  // how many times we rehash to get final hashing table
    private final double loadFactor = 0.7 ;  // = m / n^2
    private int m = 0 ; // number of elements in hashing table
    private ArrayList<T>[] hashingTable ;
    private int[][] universalHashingMatrix;
    public PerfectHashingNSquareMethod(){
        n = 4 ; // # default number to be inserted
        initialize();
    }
    public PerfectHashingNSquareMethod(int n ){
        this.n = n ;
        initialize();

    }
    private void initialize(){

        // array of hashing >> size = 4 * 4  = 16
        hashingTable = new ArrayList[n * n] ;
        // universal matrix used in hashing with size log n * u
        universalHashingMatrix = new int[(int)Math.floor( Math.log10(n*n) / Math.log10(2))][u] ;
        randomizeMatrix();

    }
    private void initializeToLoadFactor(){
        // universal matrix used in hashing with size log n * u
        universalHashingMatrix = new int[(int)Math.floor( Math.log10(n*n) / Math.log10(2))][u] ;
        randomizeMatrix();
    }

    public ArrayList<T>[] getHashingTable() {
        return hashingTable;
    }

    public int getN() {
        return n;
    }

    public int getU() {
        return u;
    }

    public int getNumberOfRehashing() {
        return numberOfRehashing;
    }

    public double getLoadFactor() {
        return loadFactor;
    }

    public int getM() {
        return m;
    }

    public int[][] getUniversalHashingMatrix() {
        return universalHashingMatrix;
    }
    public long hashing(T key){
        long temp = 0 ;
        if( key instanceof String)
            temp = Computation.convertStringToLong((String) key);
        else if(key instanceof Integer){
            temp = (int)key;
        }
        else {
            int charToInt = (char)key ;
            temp = charToInt;
        }
        return temp ;
    }
    private void randomizeMatrix(){
        int row = universalHashingMatrix.length;
        int col = universalHashingMatrix[0].length;
        universalHashingMatrix = Computation.getRandomMatrix(row,col);
    }
    public boolean insert(T key){
        long numberInLong = hashing(key);
        int[] numInBinary = Computation.decimalToBinary(numberInLong , u);
        int[] numInBinaryNew = Computation.matrixMultiplication(universalHashingMatrix , numInBinary) ;
        int index = Computation.binaryToDecimal(numInBinaryNew) ;
        if(hashingTable[index] == null){
            hashingTable[index] = new ArrayList<>() ;
            hashingTable[index].add(key);
            m++ ;
            if((double)(m*1.0)/(n*n) >= loadFactor){
                rehashDueToLoadFactor();
            }
            return true ;
        }
        if(hashing(hashingTable[index].get(0)) == numberInLong){
            return false ;
        }
        m++;
        hashingTable[index].add(key);
        rehashing();
        if((double)(m*1.0)/(n*n) >= loadFactor){
            rehashDueToLoadFactor();
        }
        return true ;
    }
    public int batchInsert(T[] ele){
        n = m + ele.length ;
        ArrayList<T> elements = getElementsToRehash() ;
        initialize();
        m = n ;
        for(T e : elements)
            insert2(e) ;
        int count = 0;
        for(T e : ele){
            if(!insert2(e))
                count++;
        }
        m = 0 ;
        for(ArrayList<T> slot : hashingTable){
            if(slot != null)
                m++;
        }

        return count ;
    }
    private ArrayList<T> getElementsToRehash(){
        ArrayList<T> temp = new ArrayList<>() ;
        for(ArrayList<T> slots : hashingTable){
            if(slots != null)
                temp.addAll(slots);
        }
        return temp ;
    }
    private void rehashDueToLoadFactor(){
        n = m ;
        initializeToLoadFactor();
        rehashing();
    }
    public boolean search(T key){
        long numberInLong = hashing(key);
        int[] numInBinary = Computation.decimalToBinary(numberInLong , u);
        int[] numInBinaryNew = Computation.matrixMultiplication(universalHashingMatrix , numInBinary) ;
        int index = Computation.binaryToDecimal(numInBinaryNew) ;
        if(hashingTable[index] == null ||hashing(hashingTable[index].get(0)) != numberInLong )
            return false;
        return true;
    }
    public boolean delete(T key){
        long numberInLong = hashing(key);
        int[] numInBinary = Computation.decimalToBinary(numberInLong , u);
        int[] numInBinaryNew = Computation.matrixMultiplication(universalHashingMatrix , numInBinary) ;
        int index = Computation.binaryToDecimal(numInBinaryNew) ;
        if(hashingTable[index] != null && hashing(hashingTable[index].get(0)) == numberInLong){
            hashingTable[index] = null ;
            m--;
            return true;
        }
        return false ;
    }
    public boolean insert2(T key){
        long numberInLong = hashing(key);
        int[] numInBinary = Computation.decimalToBinary(numberInLong , u);
        int[] numInBinaryNew = Computation.matrixMultiplication(universalHashingMatrix , numInBinary) ;
        int index = Computation.binaryToDecimal(numInBinaryNew) ;
        if(hashingTable[index] == null){
            hashingTable[index] = new ArrayList<>() ;
            hashingTable[index].add(key);
            return true ;
        }
        if(hashing(hashingTable[index].get(0)) == numberInLong){
            return false ;
        }
        hashingTable[index].add(key);
        rehashing();
        return true ;
    }
    private void rehashing(){
        ArrayList<T> elements = getElementsToRehash() ;
//        System.out.println("print elements");
//        for(T i : elements)
//            System.out.print(i +" ");
        n = m ;
        universalHashingMatrix = new int[(int)Math.floor( Math.log10(n*n) / Math.log10(2))][u] ;
        boolean collision ;
        do {
            collision = false ;
            numberOfRehashing++;
            hashingTable = new ArrayList[n * n] ;
            randomizeMatrix();
            for(T e : elements){
                long numberInLong = hashing(e);
                int[] numInBinary = Computation.decimalToBinary(numberInLong , u);
                int[] numInBinaryNew = Computation.matrixMultiplication(universalHashingMatrix , numInBinary) ;
                int index = Computation.binaryToDecimal(numInBinaryNew) ;
                if(hashingTable[index] == null){
                    hashingTable[index] = new ArrayList<>() ;
                    hashingTable[index].add(e);
                }else {
                    collision = true ;
                    break;
                }
            }
        }while (collision);
    }
    public void printHashTable(){
        int i = 0 ;
        for(ArrayList<T> slot: hashingTable){
            System.out.print("Index = " + i++ );
            if(slot == null){
                System.out.println();
                continue;
            }
            for(T x : slot)
                System.out.print( "  → element = " +x );
            System.out.println();
        }
    }
}
