import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Graph {
    private final int V;
    private final List<Edge> edges;

    public Graph(int V) {
        this.V = V;
        edges = new ArrayList<>();
    }

    public void addEdge(int u, int v, int weight) {
        edges.add(new Edge(u, v, weight));
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public List<Edge> getAdj(int u) {
        List<Edge> adjEdges = new ArrayList<>();
        for (Edge e : edges) {
            if (e.getSourceVertex() == u) {
                adjEdges.add(e);
            }
        }
        return adjEdges;
    }

    public int getV() {
        return V;
    }

    public int Size() {
        return V;
    }

    public static Graph initialize(File file) throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        int V = sc.nextInt();
        int E = sc.nextInt();
        Graph graph = new Graph(V);
        for (int i = 0; i < E; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int weight = sc.nextInt();
            graph.addEdge(u, v, weight);
        }
        sc.close();
        return graph;
    }

    public int size() {
        return this.V;
    }
}