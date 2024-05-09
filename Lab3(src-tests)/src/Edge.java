package org.example;

public class Edge {
    private final int u; // Source vertex
    private final int v; // Destination vertex
    private final int weight; // Weight of the edge

    public Edge(int u, int v, int weight) {
        this.u = u;
        this.v = v;
        this.weight = weight;
    }

    public int getSourceVertex() {
        return u;
    }

    public int getDestinationVertex() {
        return v;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "(" + u + " -> " + v + ", weight: " + weight + ")";
    }
}
