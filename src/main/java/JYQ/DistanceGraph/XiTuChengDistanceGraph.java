package JYQ.DistanceGraph;

import JYQ.BuptMap.WeigtedEgde;
import JYQ.BuptMap.XiTuChengMap;
import JYQ.DijkstraUtils.DijkstraGraph;
import JYQ.Directories;
import JYQ.Utils;

import java.io.*;
import java.util.ArrayList;

/**
 * 西土城校区，30个点，30.txt
 */
public class XiTuChengDistanceGraph implements DistanceGraph, Serializable, DijkstraGraph {
    static final long SerialVersionUID = 1919180114514L;
    private ArrayList<ArrayList<Character>> AdjMatrix;
    public static File MatrixFile = Utils.join(Directories.MapRepo, "30.txt");
    private XiTuChengMap xiTuChengMap;
    public ArrayList<ArrayList<Character>> getAdjMatrix() {
        return AdjMatrix;
    }

    public XiTuChengDistanceGraph() {
        AdjMatrix = new ArrayList<>(30);
        for (int i = 0 ; i < 30; i++) {
            AdjMatrix.add(new ArrayList<>(30));
        }
        xiTuChengMap = new XiTuChengMap(30);
        this.initMatrix();
        this.BuildDistanceMap();
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void BuildDistanceMap() {
        File XiTuChengDistanceGraph = Utils.join(Directories.DistanceMap, "XiTuChengDistanceGraph");
        if (!XiTuChengDistanceGraph.exists()) {
            for (int i = 0; i < 30; i++) {
                for (int j = 0; j < AdjMatrix.get(i).size(); j++) {
                    if (AdjMatrix.get(i).get(j) == '1') {
                        double d = Math.random() * 1000 + 101;
                        this.xiTuChengMap.addEdge(i, j, (int) d);
                    }
                }
            }
            Utils.writeObject(XiTuChengDistanceGraph, this);
        }
    }
    public static XiTuChengDistanceGraph ReadInGraph() {
        File XiTuChengDistanceGraph = Utils.join(Directories.DistanceMap, "XiTuChengDistanceGraph");
        return Utils.readObject(XiTuChengDistanceGraph, JYQ.DistanceGraph.XiTuChengDistanceGraph.class);
    }
    public double getBuildingDistance(int V1, int V2) {
        return this.xiTuChengMap.getWeight(V1, V2);
    }
    @Override
    public int getVertices() {
        return 30;
    }

    @Override
    public WeigtedEgde[] Adjcents(int bestNode) {
        return xiTuChengMap.Adjcents(bestNode);
    }

    public static void main(String[] args) {
        new XiTuChengDistanceGraph();
    }
}

