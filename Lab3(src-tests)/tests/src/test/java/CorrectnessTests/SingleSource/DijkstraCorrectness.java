package CorrectnessTests.SingleSource;

import org.example.MainFunctions;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Thread.sleep;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DijkstraCorrectness {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    static MainFunctions obj;
    @Test (timeout = 5000)
    public void simpleTest() throws FileNotFoundException {
        obj = new MainFunctions();
        System.out.println(ANSI_YELLOW+"Testing simpleTest"+ANSI_RESET);
        obj.createGraph("/home/ubuntu/GitHub/CSE224-Data-Structures-2-Labs/Lab3(src-tests)/tests/src/test/simpleTest.txt");
        obj.chooseMethodOneSrc(1, 0);
        List<Integer> ans = new ArrayList<>(List.of(0, 2, 4));
        assertEquals(ans, obj.getPathFor(0, 4));
        int cost = obj.getCostFor(0, 4);
        assertEquals(6, cost);
        System.out.println("Path: " + ans);
        System.out.println("Cost: " + cost);
        System.out.println(ANSI_GREEN+"simpleTest Passed"+ANSI_RESET);
    }
    @Test(timeout = 5000)
    public void mediumTestHighDensity() throws FileNotFoundException {
        obj = new MainFunctions();
        System.out.println(ANSI_YELLOW+"Testing mediumTestHighDensity"+ANSI_RESET);
        obj.createGraph("/home/ubuntu/GitHub/CSE224-Data-Structures-2-Labs/Lab3(src-tests)/tests/src/test/mediumTestHighDensity.txt");
        obj.chooseMethodOneSrc(1, 1);
        List<Integer> ans = new ArrayList<>(List.of(1, 4, 11, 10));
        assertEquals(ans, obj.getPathFor(1, 10));
        int cost = obj.getCostFor(1, 10);
        assertEquals(39, cost);
        System.out.println("Path: " + ans);
        System.out.println("Cost: " + cost);
        System.out.println(ANSI_GREEN+"mediumTestHighDensity Passed"+ANSI_RESET);
    }
    @Test(timeout = 5000)
    public void noPathMediumTestHighDensity() throws FileNotFoundException {
        obj = new MainFunctions();
        System.out.println(ANSI_YELLOW+"Testing noPathMediumTestHighDensity"+ANSI_RESET);
        obj.createGraph("/home/ubuntu/GitHub/CSE224-Data-Structures-2-Labs/Lab3(src-tests)/tests/src/test/mediumTestHighDensity.txt");
        obj.chooseMethodOneSrc(1, 9);
        List<Integer> ans = new ArrayList<>(List.of());
        assertEquals(ans, obj.getPathFor(9, 0));
        assertEquals(Integer.MAX_VALUE, obj.getCostFor(9, 0));
        System.out.println("Path: " + ans);
        System.out.println("Cost: ∞");
        System.out.println(ANSI_GREEN+"mediumTestHighDensity Passed"+ANSI_RESET);
    }
    @Test(timeout = 5000)
    public void BigTestHighDensity1() throws FileNotFoundException {
        obj = new MainFunctions();
        System.out.println(ANSI_YELLOW+"Testing BigTestHighDensity1"+ANSI_RESET);
        obj.createGraph("/home/ubuntu/GitHub/CSE224-Data-Structures-2-Labs/Lab3(src-tests)/tests/src/test/BigTestHighDensity.txt");
        obj.chooseMethodOneSrc(1, 0);
        List<Integer> ans = new ArrayList<>();
        ans.add(0);
        for(int i=1;i<50;i+=2){
            ans.add(i);
        }
        assertEquals(ans, obj.getPathFor(0, 49));
        int cost = obj.getCostFor(0, 49);
        assertEquals(674, cost);
        System.out.println("Path: " + ans);
        System.out.println("Cost: " + cost);
        System.out.println(ANSI_GREEN+"BigTestHighDensity1 Passed"+ANSI_RESET);
    }
    @Test(timeout = 5000)
    public void BigTestHighDensity2() throws FileNotFoundException {
        obj = new MainFunctions();
        System.out.println(ANSI_YELLOW+"Testing BigTestHighDensity2"+ANSI_RESET);
        obj.createGraph("/home/ubuntu/GitHub/CSE224-Data-Structures-2-Labs/Lab3(src-tests)/tests/src/test/BigTestHighDensity.txt");
        obj.chooseMethodOneSrc(1, 0);
        List<Integer> ans = new ArrayList<>();
        for(int i=0;i<50;i+=2){
            ans.add(i);
        }
        assertEquals(ans, obj.getPathFor(0, 48));
        int cost = obj.getCostFor(0, 48);
        assertEquals(648, cost);
        System.out.println("Path: " + ans);
        System.out.println("Cost: " + cost);
        System.out.println(ANSI_GREEN+"BigTestHighDensity2 Passed"+ANSI_RESET);
    }

    @Test(timeout = 5000)
    public void testEqualShortestPathsWithNegativeValues() throws FileNotFoundException {
        obj = new MainFunctions();
        System.out.println(ANSI_YELLOW+"Testing testEqualShortestPathsWithNegativeValues"+ANSI_RESET);
        obj.createGraph("/home/ubuntu/GitHub/CSE224-Data-Structures-2-Labs/Lab3(src-tests)/tests/src/test/equalShortestPathsNegative.txt");
        obj.chooseMethodOneSrc(1, 0);
        List<Integer> ans1 = new ArrayList<>(List.of(0,1,4));
        List<Integer> ans2 = new ArrayList<>(List.of(0,2,3,4));
        List<Integer> actual = obj.getPathFor(0, 4);
        assertTrue(ans1.equals(actual) || ans2.equals(actual));
        int cost = obj.getCostFor(0, 4);
        assertEquals(-5, cost);
        System.out.println("path: " + actual);
        System.out.println("Cost: " + cost);
        System.out.println(ANSI_GREEN+"testEqualShortestPathsWithNegativeValues Passed"+ANSI_RESET);
    }

    @Test(timeout = 5000)
    public void testEqualShortestPathsWithPositiveValues() throws FileNotFoundException {
        obj = new MainFunctions();
        System.out.println(ANSI_YELLOW+"Testing testEqualShortestPathsWithPositiveValues"+ANSI_RESET);
        obj.createGraph("/home/ubuntu/GitHub/CSE224-Data-Structures-2-Labs/Lab3(src-tests)/tests/src/test/equalShortestPathsPositive.txt");
        obj.chooseMethodOneSrc(1, 0);
        List<Integer> ans1 = new ArrayList<>(List.of(0,2,3));
        List<Integer> ans2 = new ArrayList<>(List.of(0,1,3));
        List<Integer> actual = obj.getPathFor(0, 3);
        assertTrue(ans1.equals(actual) || ans2.equals(actual));
        int cost = obj.getCostFor(0, 3);
        assertEquals(5, cost);
        System.out.println("Path: " + actual);
        System.out.println("Cost: " + cost);
        System.out.println(ANSI_GREEN+"testEqualShortestPathsWithPositiveValues Passed"+ANSI_RESET);
    }

    @Test(timeout = 5000)
    public void testNegativeCycle() throws FileNotFoundException {
        obj = new MainFunctions();
        System.out.println(ANSI_YELLOW+"Testing testEqualShortestPathsWithPositiveValues"+ANSI_RESET);
        obj.createGraph("/home/ubuntu/GitHub/CSE224-Data-Structures-2-Labs/Lab3(src-tests)/tests/src/test/negativeCycle.txt");
        obj.chooseMethodOneSrc(1, 0);
        List<Integer> ans = new ArrayList<>(List.of(0,2,3,5));
        List<Integer> actual = obj.getPathFor(0, 5);
        assertEquals(ans, actual);
        int cost = obj.getCostFor(0, 5);
        assertEquals(3, cost);
        System.out.println("Path: " + actual);
        System.out.println("Cost: " + cost);
        System.out.println(ANSI_GREEN+"testNegativeCycle Passed"+ANSI_RESET);
    }

    @Test(timeout = 5000)
    public void testNegativeCycleHardVersion() throws FileNotFoundException {
        obj = new MainFunctions();
        System.out.println(ANSI_YELLOW+"Testing testEqualShortestPathsWithPositiveValues"+ANSI_RESET);
        obj.createGraph("/home/ubuntu/GitHub/CSE224-Data-Structures-2-Labs/Lab3(src-tests)/tests/src/test/negativeCycleHardVersion.txt");
        obj.chooseMethodOneSrc(1, 0);
        List<Integer> ans = new ArrayList<>(List.of(0,2,3,1,4,5));
        List<Integer> actual = obj.getPathFor(0, 5);
        assertEquals(ans, actual);
        int cost = obj.getCostFor(0, 5);
        assertEquals(3, cost);
        System.out.println("Path: " + actual);
        System.out.println("Cost: " + cost);
        System.out.println(ANSI_GREEN+"testNegativeCycle Passed"+ANSI_RESET);
    }


    @Test(timeout = 5000)
    public void oneNode() throws FileNotFoundException {
        obj = new MainFunctions();
        System.out.println(ANSI_YELLOW+"Testing oneNode"+ANSI_RESET);
        obj.createGraph("/home/ubuntu/GitHub/CSE224-Data-Structures-2-Labs/Lab3(src-tests)/tests/src/test/oneNode.txt");
        obj.chooseMethodOneSrc(1, 0);
        List<Integer> ans = new ArrayList<>(List.of());
        assertEquals(ans, obj.getPathFor(0, 0));
        int cost = obj.getCostFor(0, 0);
        assertEquals(0, cost);
        System.out.println("Path: " + ans);
        System.out.println("Cost: " + cost);
        System.out.println(ANSI_GREEN+"oneNode Passed"+ANSI_RESET);
    }

    @Test(timeout = 5000)
    public void multipleEdges() throws FileNotFoundException {
        obj = new MainFunctions();
        System.out.println(ANSI_YELLOW+"Testing multipleEdges"+ANSI_RESET);
        obj.createGraph("/home/ubuntu/GitHub/CSE224-Data-Structures-2-Labs/Lab3(src-tests)/tests/src/test/multipleEdges.txt");
        obj.chooseMethodOneSrc(1, 0);
        List<Integer> ans = new ArrayList<>(List.of(0,1));
        System.out.println("Matrix" + Arrays.deepToString(obj.getGraph().getAdjacencyMatrix()));
        assertEquals(ans, obj.getPathFor(0, 1));
        int cost = obj.getCostFor(0, 1);
        assertEquals(1, cost);
        System.out.println("Path: " + ans);
        System.out.println("Cost: " + cost);
        System.out.println(ANSI_GREEN+"multipleEdges Passed"+ANSI_RESET);
    }
    @Test(timeout = 50000)
    public void GiganticPathOneHighDensity() throws FileNotFoundException {
        obj = new MainFunctions();
        System.out.println(ANSI_YELLOW+"Testing GiganticPathOneHighDensity"+ANSI_RESET);
        obj.createGraph("/home/ubuntu/GitHub/CSE224-Data-Structures-2-Labs/Lab3(src-tests)/tests/src/test/GiganticPathOneHighDensity.txt");
        obj.chooseMethodOneSrc(1, 0);
        List<Integer> ans = new ArrayList<>(List.of(0,999));
        assertEquals(ans, obj.getPathFor(0, 999));
        int cost = obj.getCostFor(0, 999);
        assertEquals(1, cost);
        System.out.println("Path: " + ans);
        System.out.println("Cost: " + cost);
        System.out.println(ANSI_GREEN+"GiganticPathOneHighDensity Passed"+ANSI_RESET);
    }

    @Test(timeout = 5000)
    public void senario1Test() throws FileNotFoundException{
        obj = new MainFunctions();
        System.out.println(ANSI_YELLOW+"Testing senario1Test"+ANSI_RESET);
        obj.createGraph("/home/ubuntu/GitHub/CSE224-Data-Structures-2-Labs/Lab3(src-tests)/tests/src/test/mediumTestHighDensity.txt");
        obj.chooseMethodOneSrc(1, 0);
        List<Integer> ans1 = new ArrayList<>(List.of(0,4,6));
        List<Integer> ans2 = new ArrayList<>(List.of(0,1,2));
        List<Integer> ans3 = new ArrayList<>(List.of(0,12));
        List<Integer> ans4 = new ArrayList<>(List.of(0,4,8));
        assertEquals(ans1, obj.getPathFor(0, 6));
        int cost1 = obj.getCostFor(0, 6);
        assertEquals(12, cost1);
        assertEquals(ans2, obj.getPathFor(0, 2));
        assertEquals(ans3, obj.getPathFor(0, 12));
        int cost2 = obj.getCostFor(0, 2);
        assertEquals(6, cost2);
        int cost3 = obj.getCostFor(0, 12);
        assertEquals(7, cost3);
        assertEquals(ans4, obj.getPathFor(0, 8));
        int cost4 = obj.getCostFor(0, 8);
        assertEquals(20, cost4);
    }

    @Test(timeout = 5000)
    public void senario2Test() throws FileNotFoundException {
        obj = new MainFunctions();
        System.out.println(ANSI_YELLOW + "Testing senario2Test" + ANSI_RESET);
        obj.createGraph("/home/ubuntu/GitHub/CSE224-Data-Structures-2-Labs/Lab3(src-tests)/tests/src/test/simpleTest.txt");
        obj.chooseMethodOneSrc(1, 0);
        List<Integer> ans1 = new ArrayList<>(List.of(0, 2, 4));
        List<Integer> ans2 = new ArrayList<>(List.of(0, 2, 4));
        List<Integer> ans3 = new ArrayList<>(List.of(0, 1));
        assertEquals(ans1, obj.getPathFor(0, 4));
        int cost1 = obj.getCostFor(0, 4);
        assertEquals(6, cost1);
        assertEquals(ans2, obj.getPathFor(0, 4));
        int cost2 = obj.getCostFor(0, 4);
        assertEquals(6, cost2);
        assertEquals(ans3, obj.getPathFor(0, 1));
        int cost3 = obj.getCostFor(0, 1);
        assertEquals(7, cost3);
    }
}
