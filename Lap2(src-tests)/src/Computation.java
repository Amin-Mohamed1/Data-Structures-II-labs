import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Computation {
    public static int[] decimalToBinary(long num , int size){
        int[] binary = new int[size];
        if(num < 0){
            binary[0] = 1 ;
            num *= -1 ;
        }
        int i = size - 1;
        while(num != 0){
            binary[i--] = (int) (num % 2);
            num /= 2 ;
        }
        return binary ;
    }
    public static long binaryToDecimal(int[] num){
        long decimal = 0 ;
        for(int i = num.length - 1  ,  j = 0; i >=0 ; --i , j++ ){
            if(num[i] == 0)
                continue;
            decimal += (long) (Math.pow(2,j) * num[i]);
        }
        return decimal;
    }
    public static long convertStringToLong(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes());
            BigInteger bigInt = new BigInteger(1, hashBytes);
            return bigInt.longValue();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return 0;
        }
    }
    public static int[] matrixMultiplication(int[][] matrixOfHashing , int[] binaryNum){
        int[] res = new int[matrixOfHashing.length] ;
        for (int i = 0; i < matrixOfHashing.length; i++) {
            int sum = 0;
            for (int j = 0; j < binaryNum.length; j++) {
                sum += matrixOfHashing[i][j] * binaryNum[j];
            }
            res[i] = sum % 2;
        }
        return res;
    }

}
