package org.example;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

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
}