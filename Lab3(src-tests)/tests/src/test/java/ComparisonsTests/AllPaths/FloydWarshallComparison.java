package ComparisonsTests.AllPaths;

import org.example.MainFunctions;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Random;

import static java.lang.Thread.sleep;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FloydWarshallComparison {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    static MainFunctions obj;
    public void graphGenerator(int nodes, int edges){
        Random random = new Random();
        try {
            PrintWriter writer = new PrintWriter("temp.txt", "UTF-8");
            writer.println(nodes + " " + edges);
            int source;
            int destination;
            int weight;
            for (int i = 0; i < edges; i++) {
                source = random.nextInt(0, nodes);
                destination = random.nextInt(0, nodes);
                weight = random.nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE);
                writer.println(source + " " + destination + " " + weight);
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(ANSI_RED+"An error occurred while writing to the file."+ANSI_RESET);
            e.printStackTrace();
        }
    }
                                                         /*10 nodes*/
    /*===============================================================================================================*/
    @Test
    public void test_10_10() throws FileNotFoundException {
        obj = new MainFunctions();
        Random random = new Random();
        long avg = 0;
        for(int i=0;i<10;i++){
            int source = random.nextInt(0,10);
            int destination = random.nextInt(0,10);
            graphGenerator(10, 10);
            long start = System.nanoTime();
            obj.createGraph("/home/ubuntu/GitHub/CSE224-Data-Structures-2-Labs/Lab3(src-tests)/tests/temp.txt");
            obj.chooseMethodForAll(3);
            List<Integer> ans = obj.getPathFor(source, destination);
            int cost = obj.getCostFor(source, destination);
            long end = System.nanoTime();
            avg += (end-start)/1000;
        }
        System.out.println("Average time for 10 nodes and 10 edges: " + avg/10 + " microseconds");
    }
    @Test
    public void test_10_100() throws FileNotFoundException {
        obj = new MainFunctions();
        Random random = new Random();
        long avg = 0;
        for(int i=0;i<10;i++){
            int source = random.nextInt(0,10);
            int destination = random.nextInt(0,10);
            graphGenerator(10, 100);
            long start = System.nanoTime();
            obj.createGraph("/home/ubuntu/GitHub/CSE224-Data-Structures-2-Labs/Lab3(src-tests)/tests/temp.txt");
            obj.chooseMethodForAll(3);
            List<Integer> ans = obj.getPathFor(source, destination);
            int cost = obj.getCostFor(source, destination);
            long end = System.nanoTime();
            avg += (end-start)/1000;
        }
        System.out.println("Average time for 10 nodes and 100 edges: " + avg/10 + " microseconds");
    }
    @Test
    public void test_10_1000() throws FileNotFoundException {
        obj = new MainFunctions();
        Random random = new Random();
        long avg = 0;
        for(int i=0;i<10;i++){
            int source = random.nextInt(0,10);
            int destination = random.nextInt(0,10);
            graphGenerator(10, 1000);
            long start = System.nanoTime();
            obj.createGraph("/home/ubuntu/GitHub/CSE224-Data-Structures-2-Labs/Lab3(src-tests)/tests/temp.txt");
            obj.chooseMethodForAll(3);
            List<Integer> ans = obj.getPathFor(source, destination);
            int cost = obj.getCostFor(source, destination);
            long end = System.nanoTime();
            avg += (end-start)/1000;
        }
        System.out.println("Average time for 10 nodes and 1,000 edges: " + avg/10 + " microseconds");
    }
    @Test
    public void test_10_10000() throws FileNotFoundException {
        obj = new MainFunctions();
        Random random = new Random();
        long avg = 0;
        for(int i=0;i<10;i++){
            int source = random.nextInt(0,10);
            int destination = random.nextInt(0,10);
            graphGenerator(10, 10000);
            long start = System.nanoTime();
            obj.createGraph("/home/ubuntu/GitHub/CSE224-Data-Structures-2-Labs/Lab3(src-tests)/tests/temp.txt");
            obj.chooseMethodForAll(3);
            List<Integer> ans = obj.getPathFor(source, destination);
            int cost = obj.getCostFor(source, destination);
            long end = System.nanoTime();
            avg += (end-start)/1000;
        }
        System.out.println("Average time for 10 nodes and 10,000 edges: " + avg/10 + " microseconds");
    }
    @Test
    public void test_10_100000() throws FileNotFoundException {
        obj = new MainFunctions();
        Random random = new Random();
        long avg = 0;
        for(int i=0;i<10;i++){
            int source = random.nextInt(0,10);
            int destination = random.nextInt(0,10);
            graphGenerator(10, 100000);
            long start = System.nanoTime();
            obj.createGraph("/home/ubuntu/GitHub/CSE224-Data-Structures-2-Labs/Lab3(src-tests)/tests/temp.txt");
            obj.chooseMethodForAll(3);
            List<Integer> ans = obj.getPathFor(source, destination);
            int cost = obj.getCostFor(source, destination);
            long end = System.nanoTime();
            avg += (end-start)/1000;
        }
        System.out.println("Average time for 10 nodes and 100,000 edges: " + avg/10 + " microseconds");
    }
    @Test
    public void test_10_1000000() throws FileNotFoundException {
        obj = new MainFunctions();
        Random random = new Random();
        long avg = 0;
        for(int i=0;i<10;i++){
            int source = random.nextInt(0,10);
            int destination = random.nextInt(0,10);
            graphGenerator(10, 1000000);
            long start = System.nanoTime();
            obj.createGraph("/home/ubuntu/GitHub/CSE224-Data-Structures-2-Labs/Lab3(src-tests)/tests/temp.txt");
            obj.chooseMethodForAll(3);
            List<Integer> ans = obj.getPathFor(source, destination);
            int cost = obj.getCostFor(source, destination);
            long end = System.nanoTime();
            avg += (end-start)/1000;
        }
        System.out.println("Average time for 10 nodes and 1,000,000 edges: " + avg/10 + " microseconds");
    }
                                                            /*100 nodes*/
    /*===============================================================================================================*/
    @Test
    public void test_100_10() throws FileNotFoundException {
        obj = new MainFunctions();
        Random random = new Random();
        long avg = 0;
        for(int i=0;i<10;i++){
            int source = random.nextInt(0,100);
            int destination = random.nextInt(0,100);
            graphGenerator(100, 10);
            long start = System.nanoTime();
            obj.createGraph("/home/ubuntu/GitHub/CSE224-Data-Structures-2-Labs/Lab3(src-tests)/tests/temp.txt");
            obj.chooseMethodForAll(3);
            List<Integer> ans = obj.getPathFor(source, destination);
            int cost = obj.getCostFor(source, destination);
            long end = System.nanoTime();
            avg += (end-start)/1000;
        }
        System.out.println("Average time for 100 nodes and 10 edges: " + avg/10 + " microseconds");
    }
    @Test
    public void test_100_100() throws FileNotFoundException {
        obj = new MainFunctions();
        Random random = new Random();
        long avg = 0;
        for(int i=0;i<10;i++){
            int source = random.nextInt(0,100);
            int destination = random.nextInt(0,100);
            graphGenerator(100, 100);
            long start = System.nanoTime();
            obj.createGraph("/home/ubuntu/GitHub/CSE224-Data-Structures-2-Labs/Lab3(src-tests)/tests/temp.txt");
            obj.chooseMethodForAll(3);
            List<Integer> ans = obj.getPathFor(source, destination);
            int cost = obj.getCostFor(source, destination);
            long end = System.nanoTime();
            avg += (end-start)/1000;
        }
        System.out.println("Average time for 100 nodes and 100 edges: " + avg/10 + " microseconds");
    }
    @Test
    public void test_100_1000() throws FileNotFoundException {
        obj = new MainFunctions();
        Random random = new Random();
        long avg = 0;
        for(int i=0;i<10;i++){
            int source = random.nextInt(0,100);
            int destination = random.nextInt(0,100);
            graphGenerator(100, 1000);
            long start = System.nanoTime();
            obj.createGraph("/home/ubuntu/GitHub/CSE224-Data-Structures-2-Labs/Lab3(src-tests)/tests/temp.txt");
            obj.chooseMethodForAll(3);
            List<Integer> ans = obj.getPathFor(source, destination);
            int cost = obj.getCostFor(source, destination);
            long end = System.nanoTime();
            avg += (end-start)/1000;
        }
        System.out.println("Average time for 100 nodes and 1,000 edges: " + avg/10 + " microseconds");
    }
    @Test
    public void test_100_10000() throws FileNotFoundException {
        obj = new MainFunctions();
        Random random = new Random();
        long avg = 0;
        for(int i=0;i<10;i++){
            int source = random.nextInt(0,100);
            int destination = random.nextInt(0,100);
            graphGenerator(100, 10000);
            long start = System.nanoTime();
            obj.createGraph("/home/ubuntu/GitHub/CSE224-Data-Structures-2-Labs/Lab3(src-tests)/tests/temp.txt");
            obj.chooseMethodForAll(3);
            List<Integer> ans = obj.getPathFor(source, destination);
            int cost = obj.getCostFor(source, destination);
            long end = System.nanoTime();
            avg += (end-start)/1000;
        }
        System.out.println("Average time for 100 nodes and 10,000 edges: " + avg/10 + " microseconds");
    }
    @Test
    public void test_100_100000() throws FileNotFoundException {
        obj = new MainFunctions();
        Random random = new Random();
        long avg = 0;
        for(int i=0;i<10;i++){
            int source = random.nextInt(0,100);
            int destination = random.nextInt(0,100);
            graphGenerator(100, 100000);
            long start = System.nanoTime();
            obj.createGraph("/home/ubuntu/GitHub/CSE224-Data-Structures-2-Labs/Lab3(src-tests)/tests/temp.txt");
            obj.chooseMethodForAll(3);
            List<Integer> ans = obj.getPathFor(source, destination);
            int cost = obj.getCostFor(source, destination);
            long end = System.nanoTime();
            avg += (end-start)/1000;
        }
        System.out.println("Average time for 100 nodes and 100,000 edges: " + avg/10 + " microseconds");
    }
    @Test
    public void test_100_1000000() throws FileNotFoundException {
        obj = new MainFunctions();
        Random random = new Random();
        long avg = 0;
        for(int i=0;i<10;i++){
            int source = random.nextInt(0,100);
            int destination = random.nextInt(0,100);
            graphGenerator(100, 1000000);
            long start = System.nanoTime();
            obj.createGraph("/home/ubuntu/GitHub/CSE224-Data-Structures-2-Labs/Lab3(src-tests)/tests/temp.txt");
            obj.chooseMethodForAll(3);
            List<Integer> ans = obj.getPathFor(source, destination);
            int cost = obj.getCostFor(source, destination);
            long end = System.nanoTime();
            avg += (end-start)/1000;
        }
        System.out.println("Average time for 100 nodes and 1,000,000 edges: " + avg/10 + " microseconds");
    }
                                                        /*1,000 nodes*/
    /*===============================================================================================================*/
    @Test
    public void test_1000_10() throws FileNotFoundException {
        obj = new MainFunctions();
        Random random = new Random();
        long avg = 0;
        for(int i=0;i<10;i++){
            int source = random.nextInt(0,1000);
            int destination = random.nextInt(0,1000);
            graphGenerator(1000, 10);
            long start = System.nanoTime();
            obj.createGraph("/home/ubuntu/GitHub/CSE224-Data-Structures-2-Labs/Lab3(src-tests)/tests/temp.txt");
            obj.chooseMethodForAll(3);
            List<Integer> ans = obj.getPathFor(source, destination);
            int cost = obj.getCostFor(source, destination);
            long end = System.nanoTime();
            avg += (end-start)/1000;
        }
        System.out.println("Average time for 1,000 nodes and 10 edges: " + avg/10 + " microseconds");
    }
    @Test
    public void test_1000_100() throws FileNotFoundException {
        obj = new MainFunctions();
        Random random = new Random();
        long avg = 0;
        for(int i=0;i<10;i++){
            int source = random.nextInt(0,1000);
            int destination = random.nextInt(0,1000);
            graphGenerator(1000, 100);
            long start = System.nanoTime();
            obj.createGraph("/home/ubuntu/GitHub/CSE224-Data-Structures-2-Labs/Lab3(src-tests)/tests/temp.txt");
            obj.chooseMethodForAll(3);
            List<Integer> ans = obj.getPathFor(source, destination);
            int cost = obj.getCostFor(source, destination);
            long end = System.nanoTime();
            avg += (end-start)/1000;
        }
        System.out.println("Average time for 1,000 nodes and 100 edges: " + avg/10 + " microseconds");
    }
    @Test
    public void test_1000_1000() throws FileNotFoundException {
        obj = new MainFunctions();
        Random random = new Random();
        long avg = 0;
        for(int i=0;i<10;i++){
            int source = random.nextInt(0,1000);
            int destination = random.nextInt(0,1000);
            graphGenerator(1000, 1000);
            long start = System.nanoTime();
            obj.createGraph("/home/ubuntu/GitHub/CSE224-Data-Structures-2-Labs/Lab3(src-tests)/tests/temp.txt");
            obj.chooseMethodForAll(3);
            List<Integer> ans = obj.getPathFor(source, destination);
            int cost = obj.getCostFor(source, destination);
            long end = System.nanoTime();
            avg += (end-start)/1000;
        }
        System.out.println("Average time for 1,000 nodes and 1,000 edges: " + avg/10 + " microseconds");
    }
    @Test
    public void test_1000_10000() throws FileNotFoundException {
        obj = new MainFunctions();
        Random random = new Random();
        long avg = 0;
        for(int i=0;i<10;i++){
            int source = random.nextInt(0,1000);
            int destination = random.nextInt(0,1000);
            graphGenerator(1000, 10000);
            long start = System.nanoTime();
            obj.createGraph("/home/ubuntu/GitHub/CSE224-Data-Structures-2-Labs/Lab3(src-tests)/tests/temp.txt");
            obj.chooseMethodForAll(3);
            List<Integer> ans = obj.getPathFor(source, destination);
            int cost = obj.getCostFor(source, destination);
            long end = System.nanoTime();
            avg += (end-start)/1000;
        }
        System.out.println("Average time for 1,000 nodes and 10,000 edges: " + avg/10 + " microseconds");
    }
    @Test
    public void test_1000_100000() throws FileNotFoundException {
        obj = new MainFunctions();
        Random random = new Random();
        long avg = 0;
        for(int i=0;i<10;i++){
            int source = random.nextInt(0,1000);
            int destination = random.nextInt(0,1000);
            graphGenerator(1000, 100000);
            long start = System.nanoTime();
            obj.createGraph("/home/ubuntu/GitHub/CSE224-Data-Structures-2-Labs/Lab3(src-tests)/tests/temp.txt");
            obj.chooseMethodForAll(3);
            List<Integer> ans = obj.getPathFor(source, destination);
            int cost = obj.getCostFor(source, destination);
            long end = System.nanoTime();
            avg += (end-start)/1000;
        }
        System.out.println("Average time for 1,000 nodes and 100,000 edges: " + avg/10 + " microseconds");
    }
    @Test
    public void test_1000_1000000() throws FileNotFoundException {
        obj = new MainFunctions();
        Random random = new Random();
        long avg = 0;
        for(int i=0;i<10;i++){
            int source = random.nextInt(0,1000);
            int destination = random.nextInt(0,1000);
            graphGenerator(1000, 1000000);
            long start = System.nanoTime();
            obj.createGraph("/home/ubuntu/GitHub/CSE224-Data-Structures-2-Labs/Lab3(src-tests)/tests/temp.txt");
            obj.chooseMethodForAll(3);
            List<Integer> ans = obj.getPathFor(source, destination);
            int cost = obj.getCostFor(source, destination);
            long end = System.nanoTime();
            avg += (end-start)/1000;
        }
        System.out.println("Average time for 1,000 nodes and 1,000,000 edges: " + avg/10 + " microseconds");
    }
                                                        /*2,000 nodes*/
    /*===============================================================================================================*/
    @Test
    public void test_2000_10() throws FileNotFoundException {
        obj = new MainFunctions();
        Random random = new Random();
        long avg = 0;
        for(int i=0;i<10;i++){
            int source = random.nextInt(0,2000);
            int destination = random.nextInt(0,2000);
            graphGenerator(2000, 10);
            long start = System.nanoTime();
            obj.createGraph("/home/ubuntu/GitHub/CSE224-Data-Structures-2-Labs/Lab3(src-tests)/tests/temp.txt");
            obj.chooseMethodForAll(3);
            List<Integer> ans = obj.getPathFor(source, destination);
            int cost = obj.getCostFor(source, destination);
            long end = System.nanoTime();
            avg += (end-start)/1000;
        }
        System.out.println("Average time for 2,000 nodes and 10 edges: " + avg/10 + " microseconds");
    }
    @Test
    public void test_2000_100() throws FileNotFoundException {
        obj = new MainFunctions();
        Random random = new Random();
        long avg = 0;
        for(int i=0;i<10;i++){
            int source = random.nextInt(0,2000);
            int destination = random.nextInt(0,2000);
            graphGenerator(2000, 100);
            long start = System.nanoTime();
            obj.createGraph("/home/ubuntu/GitHub/CSE224-Data-Structures-2-Labs/Lab3(src-tests)/tests/temp.txt");
            obj.chooseMethodForAll(3);
            List<Integer> ans = obj.getPathFor(source, destination);
            int cost = obj.getCostFor(source, destination);
            long end = System.nanoTime();
            avg += (end-start)/1000;
        }
        System.out.println("Average time for 2,000 nodes and 100 edges: " + avg/10 + " microseconds");
    }
    @Test
    public void test_2000_1000() throws FileNotFoundException {
        obj = new MainFunctions();
        Random random = new Random();
        long avg = 0;
        for(int i=0;i<10;i++){
            int source = random.nextInt(0,2000);
            int destination = random.nextInt(0,2000);
            graphGenerator(2000, 1000);
            long start = System.nanoTime();
            obj.createGraph("/home/ubuntu/GitHub/CSE224-Data-Structures-2-Labs/Lab3(src-tests)/tests/temp.txt");
            obj.chooseMethodForAll(3);
            List<Integer> ans = obj.getPathFor(source, destination);
            int cost = obj.getCostFor(source, destination);
            long end = System.nanoTime();
            avg += (end-start)/1000;
        }
        System.out.println("Average time for 2,000 nodes and 1,000 edges: " + avg/10 + " microseconds");
    }
    @Test
    public void test_2000_10000() throws FileNotFoundException {
        obj = new MainFunctions();
        Random random = new Random();
        long avg = 0;
        for(int i=0;i<10;i++){
            int source = random.nextInt(0,2000);
            int destination = random.nextInt(0,2000);
            graphGenerator(2000, 10000);
            long start = System.nanoTime();
            obj.createGraph("/home/ubuntu/GitHub/CSE224-Data-Structures-2-Labs/Lab3(src-tests)/tests/temp.txt");
            obj.chooseMethodForAll(3);
            List<Integer> ans = obj.getPathFor(source, destination);
            int cost = obj.getCostFor(source, destination);
            long end = System.nanoTime();
            avg += (end-start)/1000;
        }
        System.out.println("Average time for 2,000 nodes and 10,000 edges: " + avg/10 + " microseconds");
    }
    @Test
    public void test_2000_100000() throws FileNotFoundException {
        obj = new MainFunctions();
        Random random = new Random();
        long avg = 0;
        for(int i=0;i<10;i++){
            int source = random.nextInt(0,2000);
            int destination = random.nextInt(0,2000);
            graphGenerator(2000, 100000);
            long start = System.nanoTime();
            obj.createGraph("/home/ubuntu/GitHub/CSE224-Data-Structures-2-Labs/Lab3(src-tests)/tests/temp.txt");
            obj.chooseMethodForAll(3);
            List<Integer> ans = obj.getPathFor(source, destination);
            int cost = obj.getCostFor(source, destination);
            long end = System.nanoTime();
            avg += (end-start)/1000;
        }
        System.out.println("Average time for 2,000 nodes and 100,000 edges: " + avg/10 + " microseconds");
    }
    @Test
    public void test_2000_1000000() throws FileNotFoundException {
        obj = new MainFunctions();
        Random random = new Random();
        long avg = 0;
        for(int i=0;i<10;i++){
            int source = random.nextInt(0,2000);
            int destination = random.nextInt(0,2000);
            graphGenerator(2000, 1000000);
            long start = System.nanoTime();
            obj.createGraph("/home/ubuntu/GitHub/CSE224-Data-Structures-2-Labs/Lab3(src-tests)/tests/temp.txt");
            obj.chooseMethodForAll(3);
            List<Integer> ans = obj.getPathFor(source, destination);
            int cost = obj.getCostFor(source, destination);
            long end = System.nanoTime();
            avg += (end-start)/1000;
        }
        System.out.println("Average time for 2,000 nodes and 1,000,000 edges: " + avg/10 + " microseconds");
    }
                                                        /*5,000 nodes*/
    /*===============================================================================================================*/
    @Test
    public void test_5000_10() throws FileNotFoundException {
        obj = new MainFunctions();
        Random random = new Random();
        long avg = 0;
        for(int i=0;i<10;i++){
            int source = random.nextInt(0,5000);
            int destination = random.nextInt(0,5000);
            graphGenerator(5000, 10);
            long start = System.nanoTime();
            obj.createGraph("/home/ubuntu/GitHub/CSE224-Data-Structures-2-Labs/Lab3(src-tests)/tests/temp.txt");
            obj.chooseMethodForAll(3);
            List<Integer> ans = obj.getPathFor(source, destination);
            int cost = obj.getCostFor(source, destination);
            long end = System.nanoTime();
            avg += (end-start)/1000;
        }
        System.out.println("Average time for 5,000 nodes and 10 edges: " + avg/10 + " microseconds");
    }
    @Test
    public void test_5000_100() throws FileNotFoundException {
        obj = new MainFunctions();
        Random random = new Random();
        long avg = 0;
        for(int i=0;i<10;i++){
            int source = random.nextInt(0,5000);
            int destination = random.nextInt(0,5000);
            graphGenerator(5000, 100);
            long start = System.nanoTime();
            obj.createGraph("/home/ubuntu/GitHub/CSE224-Data-Structures-2-Labs/Lab3(src-tests)/tests/temp.txt");
            obj.chooseMethodForAll(3);
            List<Integer> ans = obj.getPathFor(source, destination);
            int cost = obj.getCostFor(source, destination);
            long end = System.nanoTime();
            avg += (end-start)/1000;
        }
        System.out.println("Average time for 5,000 nodes and 100 edges: " + avg/10 + " microseconds");
    }
    @Test
    public void test_5000_1000() throws FileNotFoundException {
        obj = new MainFunctions();
        Random random = new Random();
        long avg = 0;
        for(int i=0;i<10;i++){
            int source = random.nextInt(0,5000);
            int destination = random.nextInt(0,5000);
            graphGenerator(5000, 1000);
            long start = System.nanoTime();
            obj.createGraph("/home/ubuntu/GitHub/CSE224-Data-Structures-2-Labs/Lab3(src-tests)/tests/temp.txt");
            obj.chooseMethodForAll(3);
            List<Integer> ans = obj.getPathFor(source, destination);
            int cost = obj.getCostFor(source, destination);
            long end = System.nanoTime();
            avg += (end-start)/1000;
        }
        System.out.println("Average time for 5,000 nodes and 1,000 edges: " + avg/10 + " microseconds");
    }
    @Test
    public void test_5000_10000() throws FileNotFoundException {
        obj = new MainFunctions();
        Random random = new Random();
        long avg = 0;
        for(int i=0;i<10;i++){
            int source = random.nextInt(0,5000);
            int destination = random.nextInt(0,5000);
            graphGenerator(5000, 10000);
            long start = System.nanoTime();
            obj.createGraph("/home/ubuntu/GitHub/CSE224-Data-Structures-2-Labs/Lab3(src-tests)/tests/temp.txt");
            obj.chooseMethodForAll(3);
            List<Integer> ans = obj.getPathFor(source, destination);
            int cost = obj.getCostFor(source, destination);
            long end = System.nanoTime();
            avg += (end-start)/1000;
        }
        System.out.println("Average time for 5,000 nodes and 10,000 edges: " + avg/10 + " microseconds");
    }
    @Test
    public void test_5000_100000() throws FileNotFoundException {
        obj = new MainFunctions();
        Random random = new Random();
        long avg = 0;
        for(int i=0;i<10;i++){
            int source = random.nextInt(0,5000);
            int destination = random.nextInt(0,5000);
            graphGenerator(5000, 100000);
            long start = System.nanoTime();
            obj.createGraph("/home/ubuntu/GitHub/CSE224-Data-Structures-2-Labs/Lab3(src-tests)/tests/temp.txt");
            obj.chooseMethodForAll(3);
            List<Integer> ans = obj.getPathFor(source, destination);
            int cost = obj.getCostFor(source, destination);
            long end = System.nanoTime();
            avg += (end-start)/1000;
        }
        System.out.println("Average time for 5,000 nodes and 100,000 edges: " + avg/10 + " microseconds");
    }
    @Test
    public void test_5000_1000000() throws FileNotFoundException {
        obj = new MainFunctions();
        Random random = new Random();
        long avg = 0;
        for(int i=0;i<10;i++){
            int source = random.nextInt(0,5000);
            int destination = random.nextInt(0,5000);
            graphGenerator(5000, 1000000);
            long start = System.nanoTime();
            obj.createGraph("/home/ubuntu/GitHub/CSE224-Data-Structures-2-Labs/Lab3(src-tests)/tests/temp.txt");
            obj.chooseMethodForAll(3);
            List<Integer> ans = obj.getPathFor(source, destination);
            int cost = obj.getCostFor(source, destination);
            long end = System.nanoTime();
            avg += (end-start)/1000;
        }
        System.out.println("Average time for 5,000 nodes and 1,000,000 edges: " + avg/10 + " microseconds");
    }
                                                        /*10,000 nodes*/
    /*===============================================================================================================*/
    @Test
    public void test_10000_10() throws FileNotFoundException {
        obj = new MainFunctions();
        Random random = new Random();
        long avg = 0;
        for(int i=0;i<10;i++){
            int source = random.nextInt(0,10000);
            int destination = random.nextInt(0,10000);
            graphGenerator(10000, 10);
            long start = System.nanoTime();
            obj.createGraph("/home/ubuntu/GitHub/CSE224-Data-Structures-2-Labs/Lab3(src-tests)/tests/temp.txt");
            obj.chooseMethodForAll(3);
            List<Integer> ans = obj.getPathFor(source, destination);
            int cost = obj.getCostFor(source, destination);
            long end = System.nanoTime();
            avg += (end-start)/1000;
        }
        System.out.println("Average time for 10,000 nodes and 10 edges: " + avg/10 + " microseconds");
    }
    @Test
    public void test_10000_100() throws FileNotFoundException {
        obj = new MainFunctions();
        Random random = new Random();
        long avg = 0;
        for(int i=0;i<10;i++){
            int source = random.nextInt(0,10000);
            int destination = random.nextInt(0,10000);
            graphGenerator(10000, 100);
            long start = System.nanoTime();
            obj.createGraph("/home/ubuntu/GitHub/CSE224-Data-Structures-2-Labs/Lab3(src-tests)/tests/temp.txt");
            obj.chooseMethodForAll(3);
            List<Integer> ans = obj.getPathFor(source, destination);
            int cost = obj.getCostFor(source, destination);
            long end = System.nanoTime();
            avg += (end-start)/1000;
        }
        System.out.println("Average time for 10,000 nodes and 100 edges: " + avg/10 + " microseconds");
    }
    @Test
    public void test_10000_1000() throws FileNotFoundException {
        obj = new MainFunctions();
        Random random = new Random();
        long avg = 0;
        for(int i=0;i<10;i++){
            int source = random.nextInt(0,10000);
            int destination = random.nextInt(0,10000);
            graphGenerator(10000, 1000);
            long start = System.nanoTime();
            obj.createGraph("/home/ubuntu/GitHub/CSE224-Data-Structures-2-Labs/Lab3(src-tests)/tests/temp.txt");
            obj.chooseMethodForAll(3);
            List<Integer> ans = obj.getPathFor(source, destination);
            int cost = obj.getCostFor(source, destination);
            long end = System.nanoTime();
            avg += (end-start)/1000;
        }
        System.out.println("Average time for 10,000 nodes and 1,000 edges: " + avg/10 + " microseconds");
    }
    @Test
    public void test_10000_10000() throws FileNotFoundException {
        obj = new MainFunctions();
        Random random = new Random();
        long avg = 0;
        for(int i=0;i<10;i++){
            int source = random.nextInt(0,10000);
            int destination = random.nextInt(0,10000);
            graphGenerator(10000, 10000);
            long start = System.nanoTime();
            obj.createGraph("/home/ubuntu/GitHub/CSE224-Data-Structures-2-Labs/Lab3(src-tests)/tests/temp.txt");
            obj.chooseMethodForAll(3);
            List<Integer> ans = obj.getPathFor(source, destination);
            int cost = obj.getCostFor(source, destination);
            long end = System.nanoTime();
            avg += (end-start)/1000;
        }
        System.out.println("Average time for 10,000 nodes and 10,000 edges: " + avg/10 + " microseconds");
    }
    @Test
    public void test_10000_100000() throws FileNotFoundException {
        obj = new MainFunctions();
        Random random = new Random();
        long avg = 0;
        for(int i=0;i<10;i++){
            int source = random.nextInt(0,10000);
            int destination = random.nextInt(0,10000);
            graphGenerator(10000, 100000);
            long start = System.nanoTime();
            obj.createGraph("/home/ubuntu/GitHub/CSE224-Data-Structures-2-Labs/Lab3(src-tests)/tests/temp.txt");
            obj.chooseMethodForAll(3);
            List<Integer> ans = obj.getPathFor(source, destination);
            int cost = obj.getCostFor(source, destination);
            long end = System.nanoTime();
            avg += (end-start)/1000;
        }
        System.out.println("Average time for 10,000 nodes and 100,000 edges: " + avg/10 + " microseconds");
    }
    @Test
    public void test_10000_1000000() throws FileNotFoundException {
        obj = new MainFunctions();
        Random random = new Random();
        long avg = 0;
        for(int i=0;i<10;i++){
            int source = random.nextInt(0,10000);
            int destination = random.nextInt(0,10000);
            graphGenerator(10000, 1000000);
            long start = System.nanoTime();
            obj.createGraph("/home/ubuntu/GitHub/CSE224-Data-Structures-2-Labs/Lab3(src-tests)/tests/temp.txt");
            obj.chooseMethodForAll(3);
            List<Integer> ans = obj.getPathFor(source, destination);
            int cost = obj.getCostFor(source, destination);
            long end = System.nanoTime();
            avg += (end-start)/1000;
        }
        System.out.println("Average time for 10,000 nodes and 1,000,000 edges: " + avg/10 + " microseconds");
    }
                                                        /*100,000 nodes*/
    /*===============================================================================================================*/
    @Test
    public void test_100000_10() throws FileNotFoundException {
        obj = new MainFunctions();
        Random random = new Random();
        long avg = 0;
        for(int i=0;i<10;i++){
            int source = random.nextInt(0,100000);
            int destination = random.nextInt(0,100000);
            graphGenerator(100000, 10);
            long start = System.nanoTime();
            obj.createGraph("/home/ubuntu/GitHub/CSE224-Data-Structures-2-Labs/Lab3(src-tests)/tests/temp.txt");
            obj.chooseMethodForAll(3);
            List<Integer> ans = obj.getPathFor(source, destination);
            int cost = obj.getCostFor(source, destination);
            long end = System.nanoTime();
            avg += (end-start)/1000;
        }
        System.out.println("Average time for 100,000 nodes and 10 edges: " + avg/10 + " microseconds");
    }
    @Test
    public void test_100000_100() throws FileNotFoundException {
        obj = new MainFunctions();
        Random random = new Random();
        long avg = 0;
        for(int i=0;i<10;i++){
            int source = random.nextInt(0,100000);
            int destination = random.nextInt(0,100000);
            graphGenerator(100000, 100);
            long start = System.nanoTime();
            obj.createGraph("/home/ubuntu/GitHub/CSE224-Data-Structures-2-Labs/Lab3(src-tests)/tests/temp.txt");
            obj.chooseMethodForAll(3);
            List<Integer> ans = obj.getPathFor(source, destination);
            int cost = obj.getCostFor(source, destination);
            long end = System.nanoTime();
            avg += (end-start)/1000;
        }
        System.out.println("Average time for 100,000 nodes and 100 edges: " + avg/10 + " microseconds");
    }
    @Test
    public void test_100000_1000() throws FileNotFoundException {
        obj = new MainFunctions();
        Random random = new Random();
        long avg = 0;
        for(int i=0;i<10;i++){
            int source = random.nextInt(0,100000);
            int destination = random.nextInt(0,100000);
            graphGenerator(100000, 1000);
            long start = System.nanoTime();
            obj.createGraph("/home/ubuntu/GitHub/CSE224-Data-Structures-2-Labs/Lab3(src-tests)/tests/temp.txt");
            obj.chooseMethodForAll(3);
            List<Integer> ans = obj.getPathFor(source, destination);
            int cost = obj.getCostFor(source, destination);
            long end = System.nanoTime();
            avg += (end-start)/1000;
        }
        System.out.println("Average time for 100,000 nodes and 1,000 edges: " + avg/10 + " microseconds");
    }
    @Test
    public void test_100000_10000() throws FileNotFoundException {
        obj = new MainFunctions();
        Random random = new Random();
        long avg = 0;
        for(int i=0;i<10;i++){
            int source = random.nextInt(0,100000);
            int destination = random.nextInt(0,100000);
            graphGenerator(100000, 10000);
            long start = System.nanoTime();
            obj.createGraph("/home/ubuntu/GitHub/CSE224-Data-Structures-2-Labs/Lab3(src-tests)/tests/temp.txt");
            obj.chooseMethodForAll(3);
            List<Integer> ans = obj.getPathFor(source, destination);
            int cost = obj.getCostFor(source, destination);
            long end = System.nanoTime();
            avg += (end-start)/1000;
        }
        System.out.println("Average time for 100,000 nodes and 10,000 edges: " + avg/10 + " microseconds");
    }
    @Test
    public void test_100000_100000() throws FileNotFoundException {
        obj = new MainFunctions();
        Random random = new Random();
        long avg = 0;
        for(int i=0;i<10;i++){
            int source = random.nextInt(0,100000);
            int destination = random.nextInt(0,100000);
            graphGenerator(100000, 100000);
            long start = System.nanoTime();
            obj.createGraph("/home/ubuntu/GitHub/CSE224-Data-Structures-2-Labs/Lab3(src-tests)/tests/temp.txt");
            obj.chooseMethodForAll(3);
            List<Integer> ans = obj.getPathFor(source, destination);
            int cost = obj.getCostFor(source, destination);
            long end = System.nanoTime();
            avg += (end-start)/1000;
        }
        System.out.println("Average time for 100,000 nodes and 100,000 edges: " + avg/10 + " microseconds");
    }
    @Test
    public void test_100000_1000000() throws FileNotFoundException {
        obj = new MainFunctions();
        Random random = new Random();
        long avg = 0;
        for(int i=0;i<10;i++){
            int source = random.nextInt(0,100000);
            int destination = random.nextInt(0,100000);
            graphGenerator(100000, 1000000);
            long start = System.nanoTime();
            obj.createGraph("/home/ubuntu/GitHub/CSE224-Data-Structures-2-Labs/Lab3(src-tests)/tests/temp.txt");
            obj.chooseMethodForAll(3);
            List<Integer> ans = obj.getPathFor(source, destination);
            int cost = obj.getCostFor(source, destination);
            long end = System.nanoTime();
            avg += (end-start)/1000;
        }
        System.out.println("Average time for 100,000 nodes and 1,000,000 edges: " + avg/10 + " microseconds");
    }
                                                        /*1,000,000 nodes*/
    /*===============================================================================================================*/
    @Test
    public void test_1000000_10() throws FileNotFoundException {
        obj = new MainFunctions();
        Random random = new Random();
        long avg = 0;
        for(int i=0;i<10;i++){
            int source = random.nextInt(0,1000000);
            int destination = random.nextInt(0,1000000);
            graphGenerator(1000000, 10);
            long start = System.nanoTime();
            obj.createGraph("/home/ubuntu/GitHub/CSE224-Data-Structures-2-Labs/Lab3(src-tests)/tests/temp.txt");
            obj.chooseMethodForAll(3);
            List<Integer> ans = obj.getPathFor(source, destination);
            int cost = obj.getCostFor(source, destination);
            long end = System.nanoTime();
            avg += (end-start)/1000;
        }
        System.out.println("Average time for 1,000,000 nodes and 10 edges: " + avg/10 + " microseconds");
    }
    @Test
    public void test_1000000_100() throws FileNotFoundException {
        obj = new MainFunctions();
        Random random = new Random();
        long avg = 0;
        for(int i=0;i<10;i++){
            int source = random.nextInt(0,1000000);
            int destination = random.nextInt(0,1000000);
            graphGenerator(1000000, 100);
            long start = System.nanoTime();
            obj.createGraph("/home/ubuntu/GitHub/CSE224-Data-Structures-2-Labs/Lab3(src-tests)/tests/temp.txt");
            obj.chooseMethodForAll(3);
            List<Integer> ans = obj.getPathFor(source, destination);
            int cost = obj.getCostFor(source, destination);
            long end = System.nanoTime();
            avg += (end-start)/1000;
        }
        System.out.println("Average time for 1,000,000 nodes and 100 edges: " + avg/10 + " microseconds");
    }
    @Test
    public void test_1000000_1000() throws FileNotFoundException {
        obj = new MainFunctions();
        Random random = new Random();
        long avg = 0;
        for(int i=0;i<10;i++){
            int source = random.nextInt(0,1000000);
            int destination = random.nextInt(0,1000000);
            graphGenerator(1000000, 1000);
            long start = System.nanoTime();
            obj.createGraph("/home/ubuntu/GitHub/CSE224-Data-Structures-2-Labs/Lab3(src-tests)/tests/temp.txt");
            obj.chooseMethodForAll(3);
            List<Integer> ans = obj.getPathFor(source, destination);
            int cost = obj.getCostFor(source, destination);
            long end = System.nanoTime();
            avg += (end-start)/1000;
        }
        System.out.println("Average time for 1,000,000 nodes and 1,000 edges: " + avg/10 + " microseconds");
    }
    @Test
    public void test_1000000_10000() throws FileNotFoundException {
        obj = new MainFunctions();
        Random random = new Random();
        long avg = 0;
        for(int i=0;i<10;i++){
            int source = random.nextInt(0,1000000);
            int destination = random.nextInt(0,1000000);
            graphGenerator(1000000, 10000);
            long start = System.nanoTime();
            obj.createGraph("/home/ubuntu/GitHub/CSE224-Data-Structures-2-Labs/Lab3(src-tests)/tests/temp.txt");
            obj.chooseMethodForAll(3);
            List<Integer> ans = obj.getPathFor(source, destination);
            int cost = obj.getCostFor(source, destination);
            long end = System.nanoTime();
            avg += (end-start)/1000;
        }
        System.out.println("Average time for 1,000,000 nodes and 10,000 edges: " + avg/10 + " microseconds");
    }
    @Test
    public void test_1000000_100000() throws FileNotFoundException {
        obj = new MainFunctions();
        Random random = new Random();
        long avg = 0;
        for(int i=0;i<10;i++){
            int source = random.nextInt(0,1000000);
            int destination = random.nextInt(0,1000000);
            graphGenerator(1000000, 100000);
            long start = System.nanoTime();
            obj.createGraph("/home/ubuntu/GitHub/CSE224-Data-Structures-2-Labs/Lab3(src-tests)/tests/temp.txt");
            obj.chooseMethodForAll(3);
            List<Integer> ans = obj.getPathFor(source, destination);
            int cost = obj.getCostFor(source, destination);
            long end = System.nanoTime();
            avg += (end-start)/1000;
        }
        System.out.println("Average time for 1,000,000 nodes and 100,000 edges: " + avg/10 + " microseconds");
    }
    @Test
    public void test_1000000_1000000() throws FileNotFoundException {
        obj = new MainFunctions();
        Random random = new Random();
        long avg = 0;
        for(int i=0;i<10;i++){
            int source = random.nextInt(0,1000000);
            int destination = random.nextInt(0,1000000);
            graphGenerator(1000000, 1000000);
            long start = System.nanoTime();
            obj.createGraph("/home/ubuntu/GitHub/CSE224-Data-Structures-2-Labs/Lab3(src-tests)/tests/temp.txt");
            obj.chooseMethodForAll(3);
            List<Integer> ans = obj.getPathFor(source, destination);
            int cost = obj.getCostFor(source, destination);
            long end = System.nanoTime();
            avg += (end-start)/1000;
        }
        System.out.println("Average time for 1,000,000 nodes and 1,000,000 edges: " + avg/10 + " microseconds");
    }
}
