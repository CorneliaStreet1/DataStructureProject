package JYQ.BuptMap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
public class BuptGraph implements Serializable{
    private ArrayList<LinkedList<WeigtedEgde>> Buildings;
    private int Vertices;
    private int Edges;
    public BuptGraph() {

    }
    public BuptGraph(int VertexNumber) {
        Buildings = new ArrayList<LinkedList<WeigtedEgde>>(VertexNumber);
        for (int i = 0 ; i < VertexNumber; i ++) {
            Buildings.add(i, new LinkedList<WeigtedEgde>());
        }
        Vertices = VertexNumber;
        Edges = 0;
    }
    public int getVertices() {
        return  Vertices;
    }
    public int getEdges() {
        return Edges;
    }

    /**
     *
     * @param V1
     * @param V2
     * @param W
     */
    public void addEdge(int V1, int V2, int W) {
        if ((Buildings.get(V1).contains(new WeigtedEgde(V2,W))|| Buildings.get(V2).contains(new WeigtedEgde(V1, W))) || V1 == V2) {
            return;
        }
        else {
            Buildings.get(V1).add(new WeigtedEgde(V2, W));
            Edges += 1;
        }
    }
    public LinkedList<WeigtedEgde> Adjcents(int V) {
        return this.Buildings.get(V);
    }
    public static void main(String[] args) {
        BuptGraph buptGraph = new BuptGraph(10);
        double EdgeNum = Math.random() * 20 + 1;
        for (int i = 0 ; i < (int) EdgeNum ; i ++) {
            double V1 = Math.random() * 10;
            double V2 = Math.random() * 10;
            buptGraph.addEdge((int) V1, (int) V2, (int) (V1 + V2));
        }
    }

    public Integer getBuildingIndex(String BuildingName) {
        return null;
    }
}
