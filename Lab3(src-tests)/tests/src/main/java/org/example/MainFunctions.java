package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class MainFunctions {
    // Constants for ANSI escape codes
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_CYAN = "\u001B[36m";

    private Graph graph = null;
    private GraphProcessor gp;
    private int[] costOneD;
    private int[] parentsOneD;
    private int[][] costTwoD;
    private int[][] parentsTwoD;
    private boolean cycle;
    private int method = 0;
    private boolean forAll = false;

    public void createGraph(String path) throws FileNotFoundException {
        File file = new File(path);
        graph = Graph.initialize(file);
        gp = new GraphProcessor(graph);
        costOneD = new int[graph.size()];
        parentsOneD = new int[graph.size()];
        costTwoD = new int[graph.size()][graph.size()]; // Initialize costTwoD array
        parentsTwoD = new int[graph.size()][graph.size()];
    }

    public void chooseMethodOneSrc(int method, int source) {

        this.method = method;
        switch (method) {
            case 1:
                gp.dijkstra(source, costOneD, parentsOneD);
                break;
            case 2:
                cycle = gp.bellmanFord(source, costOneD, parentsOneD);
                break;
            case 3:
                cycle = gp.floydWarshall(costTwoD, parentsTwoD);
                break;
        }
    }

    public void chooseMethodForAll(int method) {
        this.method = method;
        forAll = true;
        switch (method) {
            case 1:
                for (int i = 0; i < graph.size(); i++) {
                    chooseMethodOneSrc(method, i);
                    for (int j = 0; j < graph.size(); j++) {
                        costTwoD[i][j] = costOneD[j];
                        parentsTwoD[i][j] = parentsOneD[j];
                    }
                }
                break;
            case 2:
                for (int i = 0; i < graph.size(); i++) {
                    chooseMethodOneSrc(method, i);
                    if (!cycle) return;
                    for (int j = 0; j < graph.size(); j++) {
                        costTwoD[i][j] = costOneD[j];
                        parentsTwoD[i][j] = parentsOneD[j];
                    }
                }
                break;
            case 3:
                System.out.println("Working in Floyd...");
                chooseMethodOneSrc(method, 0);
                break;
        }
    }

    public int getCostFor(int src, int dest) {
        return switch (method) {
            case 1, 2 -> {
                if (forAll) yield costTwoD[src][dest];
                yield costOneD[dest];
            }
            case 3 -> costTwoD[src][dest];
            default -> Integer.MAX_VALUE; // Indicates an error
        };
    }

    public List<Integer> getPathFor(int src, int dest) {
        List<Integer> path = new ArrayList<>();
        switch (method) {
            case 1, 2:
                if (forAll) {
                    if (parentsTwoD[src][dest] != -1) {
                        int cur = dest;
                        path.addFirst(cur);
                        while (cur != src && cur != -1) {
                            cur = parentsTwoD[src][cur];
                            path.addFirst(cur);
                        }
                    }
                } else {
                    if (parentsOneD[dest] != -1) {
                        int cur = dest;
                        path.addFirst(cur);
                        while (cur != src && cur != -1) {
                            cur = parentsOneD[cur];
                            path.addFirst(cur);
                        }
                    }
                }
                break;
            case 3:
                if (parentsTwoD[src][dest] != -1) {
                    int cur = dest;
                    path.addFirst(cur);
                    while (cur != src) {
                        cur = parentsTwoD[src][cur];
                        path.addFirst(cur);
                    }
                }
                break;
        }
        return path;
    }

    public boolean detectNegativeCycles() {
        return !cycle;
    }


}


