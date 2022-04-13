package JYQ.BuptMap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
public class BuptGraph implements Serializable{
    private ArrayList<LinkedList<Integer>> Buildings;
    private int Vertices;
    private int Edges;
    public BuptGraph() {

    }
    public BuptGraph(int VerticeNumber) {
        Buildings = new ArrayList<>(VerticeNumber);
        for (int i = 0 ; i < VerticeNumber; i ++) {
            Buildings.add(i, new LinkedList<>());
        }
        Vertices = VerticeNumber;
        Edges = 0;
    }
    public int getVertices() {
        return  Vertices;
    }
    public int getEdges() {
        return Edges;
    }
    public void addEdge(int V1, int V2) {
        if ((Buildings.get(V1).contains(V2) || Buildings.get(V2).contains(V1)) || V1 == V2) {
            return;
        }
        else {
            Buildings.get(V1).add(V2);
            Buildings.get(V2).add(V1);
            Edges += 1;
        }
    }
    public LinkedList<Integer> Adjcents(int V) {
        return this.Buildings.get(V);
    }
    public static void main(String[] args) {
        BuptGraph buptGraph = new BuptGraph(10);
        double EdgeNum = Math.random() * 20 + 1;
        for (int i = 0 ; i < (int) EdgeNum ; i ++) {
            double V1 = Math.random() * 10;
            double V2 = Math.random() * 10;
            buptGraph.addEdge((int) V1, (int) V2);
        }
    }
}
