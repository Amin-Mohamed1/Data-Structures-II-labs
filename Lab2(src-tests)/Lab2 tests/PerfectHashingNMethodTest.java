package org.example;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class PerfectHashingNMethodTest {

    @Test(timeout = 1000)
    public void testInsertingInteger(){
        PerfectHashingNMethod<Integer> testObject = new PerfectHashingNMethod<>();
        assertTrue(testObject.insert(1));
        System.out.println("resultSize:" +" "+testObject.getUtilizedSpace());
    }

    @Test(timeout = 1000)
    public void testInsertingString(){
        PerfectHashingNMethod<String> testObject = new PerfectHashingNMethod<>();
        assertTrue(testObject.insert("AmenMohamedAmen&ArmiaJoseph"));
        System.out.println("resultSize:" +" "+testObject.getUtilizedSpace());
    }

    @Test(timeout = 100000)
    public void testGiganticArrayOfStrings(){
        PerfectHashingNMethod<String> testObject = new PerfectHashingNMethod<>();
        //gets random strings of size 10 in a 5000 string array
        String[] randomStringsArray = generateRandomStringsArray(100000, 10);
        assertEquals(0, testObject.batchInsert(randomStringsArray));
        System.out.println("resultSize:" +" "+testObject.getUtilizedSpace());
        System.out.println("factor:" + " " + testObject.getUtilizedSpace()/(double)100000);
    }

    @Test(timeout = 1000)
    public void testIdenticalNNumbersWithInsertBatch(){
        // get array of random size and with random Identical number across all array, it should
        // return the size of the array.size-1
        // (only inserted the first element but could not insert the other array.size-1)
        PerfectHashingNMethod<Integer> testObject = new PerfectHashingNMethod<>();
        int randomSize = generateRandomNumber(1, 1000);
        int randomNumber = generateRandomNumber(1, 10000);
        Integer[] arr = new Integer[randomSize];
        Arrays.fill(arr, randomNumber);
        // try to put print :)
         System.out.println(randomSize);
        assertEquals(randomSize-1, testObject.batchInsert(arr));
        System.out.println("resultSize:" +" "+testObject.getUtilizedSpace());
        System.out.println("factor:" + " " + testObject.getUtilizedSpace()/(double)randomSize);
    }

    @Test(timeout = 1000)
    public void testIdenticalNNumbersWithInsert(){
        // get array of random size and with random Identical number across all array, it should
        // return only 1 true and array.size-1 false
        // (only inserted the first element but could not insert the other array.size-1)
        PerfectHashingNMethod<Integer> testObject = new PerfectHashingNMethod<>();
        int randomSize = generateRandomNumber(1, 1000);
        int randomNumber = generateRandomNumber(1, 10000);
        Integer[] arr = new Integer[randomSize];
        Arrays.fill(arr, randomNumber);
        // try to put print :)
         System.out.println(randomSize);
        int count = 0;
        for(int i = 0; i < arr.length; i++){
            if(!testObject.insert(arr[i]))
                count++;
        }
        assertEquals(randomSize-1, count);
        System.out.println("resultSize:" +" "+testObject.getUtilizedSpace());
        System.out.println("factor:" + " " + testObject.getUtilizedSpace()/(double)randomSize);
    }

    @Test(timeout = 1000)
    public void testIdenticalNCharactersWithBatchInsert(){
        // get array of random size and with random Identical chars across all array, it should
        // return only 1 true and array.size-1 false
        // (only inserted the first element but could not insert the other array.size-1)
        PerfectHashingNMethod<Character> testObject = new PerfectHashingNMethod<>();
        int randomSize = generateRandomNumber(1, 1000);
        Character randomCharacter = generateRandomCharacter();
        Character[] arr = new Character[randomSize];
        Arrays.fill(arr, randomCharacter);
        // try to put print :)
         System.out.println(randomSize);
        assertEquals(randomSize-1, testObject.batchInsert(arr));
        System.out.println("resultSize:" +" "+testObject.getUtilizedSpace());
        System.out.println("factor:" + " " + testObject.getUtilizedSpace()/(double)randomSize);
    }

    @Test(timeout = 1000)
    public void testIdenticalNCharactersWithInsert(){
        // get array of random size and with random Identical chars across all array, it should
        // return only 1 true and array.size-1 false
        // (only inserted the first element but could not insert the other array.size-1)
        PerfectHashingNMethod<Character> testObject = new PerfectHashingNMethod<>();
        int randomSize = generateRandomNumber(1, 1000);
        Character randomCharacter = generateRandomCharacter();
        Character[] arr = new Character[randomSize];
        Arrays.fill(arr, randomCharacter);
        // try to put print :)
         System.out.println(randomSize);
        int count = 0;
        for(int i = 0; i < arr.length; i++){
            if(!testObject.insert(arr[i]))
                count++;
        }
        assertEquals(randomSize-1, count);
        System.out.println("resultSize:" +" "+testObject.getUtilizedSpace());
        System.out.println("factor:" + " " + testObject.getUtilizedSpace()/(double)randomSize);
    }

    @Test(timeout = 1000)
    public void testIdenticalNStringsWithBatchInsert(){
        // get array of random size and with random Identical strings across all array, it should
        // return only 1 true and array.size-1 false
        // (only inserted the first element but could not insert the other array.size-1)
        PerfectHashingNMethod<String> testObject = new PerfectHashingNMethod<>();
        int randomSize = generateRandomNumber(1, 1000);
        int randomWordLength = generateRandomNumber(1, 10);
        String randomString = generateRandomString(randomWordLength);
        String[] arr = new String[randomSize];
        Arrays.fill(arr, randomString);
        // try to put print :)
         System.out.println(randomSize);
        assertEquals(randomSize-1, testObject.batchInsert(arr));
        System.out.println("resultSize:" +" "+testObject.getUtilizedSpace());
        System.out.println("factor:" + " " + testObject.getUtilizedSpace()/(double)randomSize);
    }

    @Test(timeout = 1000)
    public void testIdenticalNStringsWithInsert(){
        // get array of random size and with random Identical strings across all array, it should
        // return only 1 true and array.size-1 false
        // (only inserted the first element but could not insert the other array.size-1)
        PerfectHashingNMethod<String> testObject = new PerfectHashingNMethod<>();
        int randomSize = generateRandomNumber(1, 1000);
        int randomWordLength = generateRandomNumber(1, 10);
        String randomString = generateRandomString(randomWordLength);
        String[] arr = new String[randomSize];
        Arrays.fill(arr, randomString);
        // try to put print :)
         System.out.println(randomSize);
        int count = 0;
        for(int i = 0; i < arr.length; i++){
            if(!testObject.insert(arr[i]))
                count++;
        }
        assertEquals(randomSize-1, count);
        System.out.println("resultSize:" +" "+testObject.getUtilizedSpace());
        System.out.println("factor:" + " " + testObject.getUtilizedSpace()/(double)randomSize);
    }

    @Test(timeout = 1000)
    public void testLinearNNumbersWithInsertBatch(){
        // get array of random size and with random numbers across all array, it should
        // return the size of the array.size-1
        // (only inserted the first element but could not insert the other array.size-1)
        PerfectHashingNMethod<Integer> testObject = new PerfectHashingNMethod<>();
        int randomSize = generateRandomNumber(1, 1000);
        Integer[] arr = new Integer[randomSize];
        for(int i = 0 ; i < arr.length; i++){
            arr[i] = i;
        }
        // try to put print :)
         System.out.println("originalSize:" +" "+ randomSize);
        assertEquals(0, testObject.batchInsert(arr));
        System.out.println("resultSize:" +" "+testObject.getUtilizedSpace());
        System.out.println("factor:" + " " + testObject.getUtilizedSpace()/(double)randomSize);
    }

    @Test(timeout = 1000000)
    public void testAverageNNumbersWithInsertBatch(){
        // get array of random size and with random numbers across all array, it should
        // return the size of the array.size-1
        // (only inserted the first element but could not insert the other array.size-1)
        int numberOfTrials = 50;
        int avgOfAvgMemorySpaces = 0;
        long avgOfAvgTimes = 0;
        int avgOfAvgRehashing = 0;
        for(int i = 1 ; i <= numberOfTrials; i++){
            int size = 300*i;
            Integer[] arr = new Integer[size];
            int FillingTimes = 30;
            int avgMemorySpace = 0;
            long avgTime = 0;
            int avgRehashingTimes = 0;
            for(int j = 0 ; j < FillingTimes; j++){
                PerfectHashingNMethod<Integer> testObject = new PerfectHashingNMethod<>();
                for(int k = 0 ; k < size; k++){
                    arr[k] = generateRandomNumber(1, 10000);
                }
                long start = System.nanoTime();
                testObject.batchInsert(arr);
                long end = System.nanoTime();
                int rehashingTimes = testObject.getNumberOfRehashing();
                avgTime+= (end-start)/1000000;
                avgMemorySpace+= testObject.getUtilizedSpace();
                avgRehashingTimes+= rehashingTimes;
            }
            avgMemorySpace/=FillingTimes;
            avgTime/=FillingTimes;
            avgRehashingTimes/=FillingTimes;
            System.out.println("Batched numbers = " + size + "-> " + "Average Memory Space " + i + " : " + avgMemorySpace);
//            System.out.println("Batched numbers = " + size + "-> " + "Average time in " + i + " : " + avgTime + " ms");
//            System.out.println("Batched numbers = " + size + "-> " + "Average Rehashing times in " + i + " : " + avgRehashingTimes + " rehashes");

            avgOfAvgMemorySpaces += avgMemorySpace;
            avgOfAvgTimes+=avgTime;
            avgOfAvgRehashing+= avgRehashingTimes;
        }
        avgOfAvgMemorySpaces/=numberOfTrials;
        avgOfAvgTimes/=numberOfTrials;
        avgOfAvgRehashing/=numberOfTrials;
        System.out.println("Average of Average space : " + avgOfAvgMemorySpaces);
//        System.out.println("Average of Average Time : " + avgOfAvgTimes + " ms");
//        System.out.println("Average number of Rehashing : " + avgOfAvgRehashing + " rehashes");
    }

    @Test
    public void testAverageNNumbersWithInsert(){
        // get array of random size and with random numbers across all array, it should
        // return the size of the array.size-1
        // (only inserted the first element but could not insert the other array.size-1)
        int numberOfTrials = 50;
        int avgOfAvgMemorySpaces = 0;
        long avgOfAvgTimes = 0;
        int avgOfAvgRehashing = 0;
        for(int i = 1 ; i <= numberOfTrials; i++){
            int size = 300*i;
            Integer[] arr = new Integer[size];
            int FillingTimes = 30;
            int avgMemorySpace = 0;
            long avgTime = 0;
            int avgRehashingTimes = 0;
            for(int j = 0 ; j < FillingTimes; j++){
                PerfectHashingNMethod<Integer> testObject = new PerfectHashingNMethod<>();
                long start = System.nanoTime();
                for(int k = 0 ; k < size; k++){
                    arr[k] = generateRandomNumber(1, 10000);
                    testObject.insert(arr[k]);
                }
                long end = System.nanoTime();
                int rehashingTimes = testObject.getNumberOfRehashing();
                avgTime+= (end-start)/1000000;
                avgMemorySpace+= testObject.getUtilizedSpace();
                avgRehashingTimes+= rehashingTimes;
            }
            avgMemorySpace/=FillingTimes;
            avgTime/=FillingTimes;
            avgRehashingTimes/=FillingTimes;
//            System.out.println("Batched numbers = " + size + "-> " + "Average Memory Space " + i + " : " + avgMemorySpace);
            System.out.println("Inserted numbers = " + size + "-> " + "Average time in " + i + " : " + avgTime + " ms");
            System.out.println("Inserted numbers = " + size + "-> " + "Average Rehashing times in " + i + " : " + avgRehashingTimes + " rehashes");

            avgOfAvgMemorySpaces += avgMemorySpace;
            avgOfAvgTimes+=avgTime;
            avgOfAvgRehashing+= avgRehashingTimes;
        }
        avgOfAvgMemorySpaces/=numberOfTrials;
        avgOfAvgTimes/=numberOfTrials;
        avgOfAvgRehashing/=numberOfTrials;
//        System.out.println("Average of Average space : " + avgOfAvgMemorySpaces);
        System.out.println("Average of Average Time : " + avgOfAvgTimes + " ms");
        System.out.println("Average number of Rehashing : " + avgOfAvgRehashing + " rehashes");
    }

    @Test (timeout = 1000)
    public void testSimpleSearch(){
        PerfectHashingNMethod<String> testObject = new PerfectHashingNMethod<>();
        testObject.insert("simpleText");
        int count = 0;
        for(int i = 0 ; i < 1000; i++)
            if(testObject.search("simpleText"))
                count++;
        assertEquals(1000, count);
    }

    @Test (timeout = 1000)
    public void testSearchingOnNonExistingElement(){
        PerfectHashingNMethod<String> testObject = new PerfectHashingNMethod<>();
        int count = 0;
        for(int i = 0 ; i < 1000; i++)
            if(!testObject.search("notSimple"))
                count++;
        assertEquals(1000, count);
    }

    @Test (timeout = 1000)
    public void testSimpleDelete(){
        PerfectHashingNMethod<String> testObject = new PerfectHashingNMethod<>();
        testObject.insert("simpleText");
        assertTrue(testObject.delete("simpleText"));
    }

    @Test (timeout = 1000)
    public void testDeletingNonExistingElement(){
        PerfectHashingNMethod<String> testObject = new PerfectHashingNMethod<>();
        testObject.insert("notSimple");
        testObject.delete("notSimple");
        int count = 0;
        for(int i = 0 ; i < 1000; i++)
            if(!testObject.delete("notSimple"))
                count++;
        assertEquals(1000, count);
    }


    // Function to generate a random string of length n
    public static String generateRandomString(int n) {
        // Define the characters allowed in the random string
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        // Initialize a random object
        Random random = new Random();

        // Initialize a StringBuilder to store the generated string
        StringBuilder sb = new StringBuilder(n);

        // Generate n random characters
        for (int i = 0; i < n; i++) {
            // Generate a random index within the length of the characters string
            int index = random.nextInt(characters.length());

            // Append the character at the random index to the StringBuilder
            sb.append(characters.charAt(index));
        }

        // Convert StringBuilder to String and return
        return sb.toString();
    }

    // Function to generate an array of random strings of given length and size
    public static String[] generateRandomStringsArray(int size, int length) {
        // Initialize an array to store the random strings
        String[] randomStrings = new String[size];

        // Generate random strings and store them in the array
        for (int i = 0; i < size; i++) {
            randomStrings[i] = generateRandomString(length);
        }

        // Return the array of random strings
        return randomStrings;
    }

    public static int generateRandomNumber(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }

    public static char generateRandomCharacter() {
        // Define the characters allowed in the random string
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        // Initialize a random object
        Random random = new Random();

        // Generate a random index within the length of the characters string
        int index = random.nextInt(characters.length());

        // Return the character at the random index
        return characters.charAt(index);
    }

}