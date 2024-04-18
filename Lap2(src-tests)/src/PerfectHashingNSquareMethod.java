import java.util.ArrayList;

public class PerfectHashingNSquareMethod<T> {
    private int n ; // #number of elements to be inserted
    private final int u = 63 ; // log2^63 ///  2^63 of integers
    private int numberOfRehashing ;  // how many times we rehash to get final hashing table
    private int loadFactor ;  // = m / n^2
    private int m  ; // number of elements in hashing array
    private ArrayList<T>[] hashingTable ;
    private int[][] universalHashingMatrix;
    private ArrayList<T> elements;
    public PerfectHashingNSquareMethod(){
        n = 64 ; // # default number to be inserted
        initialize();
    }
    public PerfectHashingNSquareMethod(int n ){
        this.n = n ;
        initialize();

    }
    private void initialize(){
        m = 0 ;
        numberOfRehashing = 0 ;
        loadFactor = 0 ;
        // array of hashing >> size = 64*64 = 4096
        hashingTable = new ArrayList[n * n] ;
        // universal matrix used in hashing with size log n * u
        universalHashingMatrix = new int[(int)Math.floor( Math.log10(n*n) / Math.log10(2))][u] ;
        randomizeMatrix();
        elements = new ArrayList<>() ;
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

    public int getLoadFactor() {
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
           elements.add(key);
           m++ ;
           return true ;
       }
        if(hashing(hashingTable[index].get(0)) == numberInLong)
            return false ;
        elements.add(key);
        rehashing();
       return true ;
    }
    private void rehashing(){
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
                System.out.println( "  >> element = " +x );

        }
    }
}
