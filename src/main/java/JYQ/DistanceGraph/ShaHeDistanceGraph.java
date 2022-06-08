package JYQ.DistanceGraph;

import JYQ.BuptMap.ShaHeMap;
import JYQ.BuptMap.WeigtedEgde;
import JYQ.DijkstraUtils.DijkstraGraph;
import JYQ.Directories;
import JYQ.Utils;
import java.io.*;
import java.util.ArrayList;

public class ShaHeDistanceGraph implements DistanceGraph ,Serializable, DijkstraGraph {
    private static final long SerialVersionUID = 1145141919180L;
    private ArrayList<ArrayList<Character>> AdjMatrix;
    public static File MatrixFile = Utils.join(Directories.MapRepo, "27.txt");
    private ShaHeMap ShaHeMap;
    public ArrayList<ArrayList<Character>> getAdjMatrix() {
        return AdjMatrix;
    }
    public ShaHeDistanceGraph() {
        AdjMatrix = new ArrayList<>(27);
        for (int i = 0 ; i < 27; i++) {
            AdjMatrix.add(new ArrayList<>(27));
        }
        ShaHeMap = new ShaHeMap(27);
        initMatrix();
        BuildDistanceMap();
    }
    public void initMatrix() {
        try {
            int LineNum = 0;
            BufferedReader bufferedReader = new BufferedReader(new FileReader(MatrixFile));
            int end = 1;
            while (end != -1) {
                end = bufferedReader.read();
                if (end == -1) {
                    break;
                }
                char c = (char) end;
                if (((char)end) == '\n') {
                    LineNum += 1;
                    continue;
                }
                if (((char)end) != '\r') {
                    AdjMatrix.get(LineNum).add((char) end);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void BuildDistanceMap() {
        File ShaHeDistanceGraph = Utils.join(Directories.DistanceMap, "ShaHeDistanceGraph");
        if (!ShaHeDistanceGraph.exists()) {
            for (int i = 0; i < 27; i++) {
                for (int j = 0; j < AdjMatrix.get(i).size(); j++) {
                    if (AdjMatrix.get(i).get(j) == '1') {
                        double d = Math.random() * 1000 + 101;
                        this.ShaHeMap.addEdge(i, j, (int) d);
                    }
                }
            }
            Utils.writeObject(ShaHeDistanceGraph,this);
        }
    }
    public static ShaHeDistanceGraph ReadInGraph() {
        File ShaHeDistanceGraph = Utils.join(Directories.DistanceMap, "ShaHeDistanceGraph");
        return Utils.readObject(ShaHeDistanceGraph, JYQ.DistanceGraph.ShaHeDistanceGraph.class);
    }
    public int getBuildingDistance(int V1, int V2) {
        return this.ShaHeMap.getWeight(V1, V2);
    }

    @Override
    public int getVertices() {
        return 27;
    }

    @Override
    public WeigtedEgde[] Adjcents(int bestNode) {
        return ShaHeMap.Adjcents(bestNode);
    }

    public static void main(String[] args) {
        new ShaHeDistanceGraph();
    }
}

