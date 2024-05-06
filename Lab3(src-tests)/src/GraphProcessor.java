import java.util.*;

public class GraphProcessor {
    static Graph graph;

    public GraphProcessor(Graph graph) {
        GraphProcessor.graph = graph;
    }

    public void dijkstra(int source, int[] costs, int[] parents) {
        Arrays.fill(costs, Integer.MAX_VALUE);
        Arrays.fill(parents, -1);
        boolean[] visited = new boolean[graph.getV()];
        costs[source] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(source, 0));
        int[][] matrix = graph.getAdjacencyMatrix();

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int u = node.vertex;
            if (visited[u])
                continue;
            visited[u] = true;
            for (int v = 0; v < graph.getV(); v++) {
                if (matrix[u][v] != 0) {
                    int newCost = costs[u] + matrix[u][v];
                    if (newCost < costs[v]) {
                        costs[v] = newCost;
                        parents[v] = u;
                        pq.offer(new Node(v, newCost));
                    }
                }
            }
        }
    }


    public boolean bellmanFord(int source, int[] costs, int[] parents) {
        int n = graph.Size();
        Arrays.fill(costs, Integer.MAX_VALUE);

        costs[source] = 0;
        for (int i = 1; i < n; i++) {
            for (Edge edge : graph.getEdges()) {
                int u = edge.getSourceVertex();
                int v = edge.getDestinationVertex();
                int weight = edge.getWeight();

                if (costs[u] != Integer.MAX_VALUE && costs[u] + weight < costs[v]) {
                    costs[v] = costs[u] + weight;
                    parents[v] = u;
                }
            }
        }
        for (Edge edge : graph.getEdges()) {
            int u = edge.getSourceVertex();
            int v = edge.getDestinationVertex();
            int weight = edge.getWeight();
            if (costs[u] != Integer.MAX_VALUE && costs[u] + weight < costs[v]) {
                return false;   //negative cycle detected
            }
        }
        return true;
    }

    public boolean floydWarshall(int[][] costs, int[][] predecessor) {
        int n = graph.getV();
        boolean noCycle = true;

        initializeArrays(costs, predecessor, n);
        populateArrays(costs, predecessor);

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (costs[i][k] != Integer.MAX_VALUE && costs[k][j] != Integer.MAX_VALUE) {
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
                if(i != j) {
                    costs[i][j] = Integer.MAX_VALUE;
                }
                else{
                    costs[i][j] = 0;
                }
                parents[i][j] = -1;
            }
        }
    }

    private void populateArrays(int[][] costs, int[][] parents) {
        for (Edge e : graph.getEdges()) {
            costs[e.getSourceVertex()][e.getDestinationVertex()] = e.getWeight();
            parents[e.getSourceVertex()][e.getDestinationVertex()] = e.getSourceVertex();
        }
    }
}
