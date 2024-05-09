package org.example;

class Node implements Comparable<Node> {
    int vertex;
    int cost;
    public Node(int vertex, int cost) {
        this.vertex = vertex;
        this.cost = cost;
    }
    public int compareTo(Node other) {
        return Integer.compare(cost, other.cost);
    }
}
