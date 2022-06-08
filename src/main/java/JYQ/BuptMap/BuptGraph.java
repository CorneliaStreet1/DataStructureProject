package JYQ.BuptMap;

import java.io.Serializable;
import java.util.ArrayList;

public class BuptGraph implements Serializable{
    private ArrayList<WeigtedEgde[]> Buildings;
    private int Vertices;
    private int Edges;
    public BuptGraph() {

    }
    public BuptGraph(int VertexNumber) {
        Buildings = new ArrayList<WeigtedEgde[]>(VertexNumber);
        for (int i = 0 ; i < VertexNumber; i ++) {
            Buildings.add(i, new WeigtedEgde[VertexNumber]);
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
        if (((new WeigtedEgde(V2,W)).equals(Buildings.get(V1)[V2]) && (new WeigtedEgde(V1, W)).equals(Buildings.get(V2)[V1])) || V1 == V2) {
            return;
        }
        else {
            Buildings.get(V1)[V2] = new WeigtedEgde(V2, W);
            Buildings.get(V2)[V1] = new WeigtedEgde(V1,W);
            Edges += 1;
        }
    }
    public WeigtedEgde[] Adjcents(int V) {
        return this.Buildings.get(V);
    }
    public int getWeight(int V1, int V2) {
        if (Buildings.get(V1)[V2] == null) {
            throw new RuntimeException("There is no edge between V1 and V2");
        }
        return Buildings.get(V1)[V2].getWeight();
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
