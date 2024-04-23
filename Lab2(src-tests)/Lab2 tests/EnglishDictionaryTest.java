package org.example;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class EnglishDictionaryTest {

    @Test (timeout = 1000)
    public void testInsertIncreasesSizeForNewKeyLinear() {
        EnglishDictionary englishDictionary = new EnglishDictionary("linear");
        int[] result = englishDictionary.insert("newKey");
        assertEquals(1, result[0]);
    }

    @Test (timeout = 1000)
    public void testInsertDoesNotIncreaseSizeForExistingKeyLinear() {
        EnglishDictionary englishDictionary = new EnglishDictionary("linear");
        englishDictionary.insert("existingKey");
        int[] resultAfterSecondInsert = englishDictionary.insert("existingKey");
        assertEquals(0, resultAfterSecondInsert[0]);
    }

    @Test (timeout = 1000)
    public void testBatchDeleteReturnsCorrectCountLinear() {
        EnglishDictionary englishDictionary = new EnglishDictionary("linear");
        ArrayList<String> keys = new ArrayList<>(Arrays.asList("key1", "key2", "key3", "key4", "key5"));
        englishDictionary.batchInsertFromFile(keys.toArray(new String[0]));
        int[] result = englishDictionary.batchDeleteFromFile(keys);
        assertEquals(keys.size(), result[0]);
    }

    @Test (timeout = 1000)
    public void testInsertIncreasesSizeForNewKeyQuadratic() {
        EnglishDictionary englishDictionary = new EnglishDictionary("quadratic");
        int[] result = englishDictionary.insert("newKey");
        assertEquals(1, result[0]);
    }

    @Test (timeout = 1000)
    public void testInsertDoesNotIncreaseSizeForExistingKeyQuadratic() {
        EnglishDictionary englishDictionary = new EnglishDictionary("quadratic");
        englishDictionary.insert("existingKey");
        int[] resultAfterSecondInsert = englishDictionary.insert("existingKey");
        assertEquals(0, resultAfterSecondInsert[0]);
    }

    @Test (timeout = 1000)
    public void testBatchDeleteReturnsCorrectCountQuadratic() {
        EnglishDictionary englishDictionary = new EnglishDictionary("quadratic");
        ArrayList<String> keys = new ArrayList<>(Arrays.asList("key1", "key2", "key3", "key4", "key5"));
        englishDictionary.batchInsertFromFile(keys.toArray(new String[0]));
        int[] result = englishDictionary.batchDeleteFromFile(keys);
        assertEquals(keys.size(), result[0]);
    }

    @Test (timeout = 1000)
    public void testSimpleSearchLinear(){
        EnglishDictionary englishDictionary = new EnglishDictionary("linear");
        englishDictionary.insert("simpleText");
        assertTrue(englishDictionary.search("simpleText"));
    }

    @Test (timeout = 1000)
    public void testSearchingOnNonExistingElementLinear(){
        EnglishDictionary englishDictionary = new EnglishDictionary("linear");
        assertFalse(englishDictionary.search("notSimple"));
    }

    @Test (timeout = 1000)
    public void testSimpleDeleteLinear(){
        EnglishDictionary englishDictionary = new EnglishDictionary("linear");
        englishDictionary.insert("simpleText");
        assertEquals(1, englishDictionary.delete("simpleText")[0]);
    }

    @Test (timeout = 1000)
    public void testDeletingNonExistingElementLinear(){
        EnglishDictionary englishDictionary = new EnglishDictionary("linear");
        assertEquals(0, englishDictionary.delete("simpleText")[0]);
    }


    @Test (timeout = 1000)
    public void testSimpleSearchQuadratic(){
        EnglishDictionary englishDictionary = new EnglishDictionary("quadratic");
        englishDictionary.insert("simpleText");
        assertTrue(englishDictionary.search("simpleText"));
    }

    @Test (timeout = 1000)
    public void testSearchingOnNonExistingElementQuadratic(){
        EnglishDictionary englishDictionary = new EnglishDictionary("quadratic");
        assertFalse(englishDictionary.search("notSimple"));
    }

    @Test (timeout = 1000)
    public void testSimpleDeleteQuadratic(){
        EnglishDictionary englishDictionary = new EnglishDictionary("quadratic");
        englishDictionary.insert("simpleText");
        assertEquals(1, englishDictionary.delete("simpleText")[0]);
    }

    @Test (timeout = 1000)
    public void testDeletingNonExistingElementQuadratic(){
        EnglishDictionary englishDictionary = new EnglishDictionary("quadratic");
        assertEquals(0, englishDictionary.delete("simpleText")[0]);
    }

    @Test(timeout = 100000)
    public void testGiganticArrayOfStringsLinear(){
        EnglishDictionary englishDictionary = new EnglishDictionary("linear");
        String[] randomStringsArray = generateRandomStringsArray(10000, 10);
        assertEquals(0, englishDictionary.batchInsertFromFile(randomStringsArray)[1]);
    }

    @Test(timeout = 100000)
    public void testGiganticArrayOfStringsQuadratic(){
        EnglishDictionary englishDictionary = new EnglishDictionary("quadratic");
        String[] randomStringsArray = generateRandomStringsArray(10000, 10);
        assertEquals(0, englishDictionary.batchInsertFromFile(randomStringsArray)[1]);
    }

    @Test (timeout = 100000)
    public void testBatchDeleteLinear(){
        EnglishDictionary englishDictionary = new EnglishDictionary("linear");
        String[] randomStringsArray = generateRandomStringsArray(5000, 10);
        englishDictionary.batchInsertFromFile(randomStringsArray);
        ArrayList<String> str = new ArrayList<>();
        Collections.addAll(str, randomStringsArray);
        assertEquals(5000, englishDictionary.batchDeleteFromFile(str)[0]);
    }

    @Test (timeout = 100000)
    public void testBatchDeleteQuadratic(){
        EnglishDictionary englishDictionary = new EnglishDictionary("quadratic");
        String[] randomStringsArray = generateRandomStringsArray(5000, 10);
        englishDictionary.batchInsertFromFile(randomStringsArray);
        ArrayList<String> str = new ArrayList<>();
        Collections.addAll(str, randomStringsArray);
        assertEquals(5000, englishDictionary.batchDeleteFromFile(str)[0]);
    }




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