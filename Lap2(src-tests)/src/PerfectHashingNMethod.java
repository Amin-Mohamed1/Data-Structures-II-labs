import java.util.*;
public class PerfectHashingNMethod<T> {
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
}
