package org.example;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Random;

public class Generator {
    public static void main(String[] args) {
//        try {
//            PrintWriter writer = new PrintWriter("output.txt", "UTF-8");
//            writer.println(1000 + " " + 1000001);
//            for (int i = 0; i < 1000; i++) {
//                for (int j = 0; j < 1000; j++) {
//                    writer.println(i + " " + j + " " + 500);
//                }
//            }
//            writer.println(0 + " " + 9999 + " " + 1);
//            writer.close();
//            System.out.println("done");
//        } catch (IOException e) {
//            System.out.println("An error occurred while writing to the file.");
//            e.printStackTrace();
//        }
        Random random = new Random();
        int source = random.nextInt(0,9);
        System.out.println(source);
    }
}
