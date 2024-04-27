
import java.util.Arrays;


public class GraphProcessor {
    static Graph graph;

    public GraphProcessor(Graph graph) {
        GraphProcessor.graph = graph;
    }

    public void dijkstra(int source, int[] costs, int[] parents) {
        // Implemented by Rafi
    }
    public boolean bellmanFord(int source, int[] costs, int[] parents) {
        // Implemented By Habiba 
        int n = graph.Size();
        boolean noCycle = true;
        int[][] edges = new int[n][n];

        initializeArrays(edges,costs ,n,parents);
        populateArrays(edges);

        costs[source] = 0;
        for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i!= j && edges[i][j]!= Integer.MAX_VALUE && costs[i] + edges[i][j] < costs[j]) {
                        costs[j] = costs[i] + edges[i][j];
                        parents[j] = i;
                    }
                }
        }
        int []prev = Arrays.copyOf(costs, n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i!= j) {
                    prev[j] = Math.min(prev[i] + edges[i][j],prev[j]);
                    if(prev[j] != costs[j]) 
                         return true;
            }
        }
    }
    
        return false;
    }

    public boolean floydWarshall(int[][] costs, int[][] predecessor) {
        int n = graph.getV();
        boolean noCycle = true;

        initializeArrays(costs, predecessor, n);
        populateArrays(costs, predecessor);

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i != j && costs[i][k] != Integer.MAX_VALUE && costs[k][j] != Integer.MAX_VALUE) {
                        if (costs[i][k] + costs[k][j] < costs[i][j]) {
                            costs[i][j] = costs[i][k] + costs[k][j];
                            predecessor[i][j] = predecessor[k][j];
                        }
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (costs[i][i] < 0) {
                noCycle = false;
                break;
            }
        }
        return noCycle;
    }

    private void initializeArrays(int[][] costs, int[][] parents, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                costs[i][j] = Integer.MAX_VALUE;
                parents[i][j] = -1;
            }
        }
    }

    private void populateArrays(int[][] costs, int[][] parents) {
        for (Edge e: graph.getEdges()) {
            costs[e.getSourceVertex()][e.getDestinationVertex()] = e.getWeight();
            parents[e.getSourceVertex()][e.getDestinationVertex()] = e.getSourceVertex();
        }
    }
    private void initializeArrays(int [][]edges,int[] costs, int n,int [] parents) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
               edges[i][j]=Integer.MAX_VALUE;
            }
            costs[i] = Integer.MAX_VALUE;
            parents[i] = -1;
        }
    }
    private void populateArrays(int[][] costs) {
        for (Edge e: graph.getEdges()) {
            costs[e.getSourceVertex()][e.getDestinationVertex()] = e.getWeight();
        }
    }
}
