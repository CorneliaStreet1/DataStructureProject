package JYQ.DistanceGraph;

import JYQ.BuptMap.BuptGraph;
import JYQ.BuptMap.XiTuChengMap;
import JYQ.Directories;
import JYQ.Utils;

import java.io.*;
import java.util.ArrayList;

/**
 * 西土城校区，30个点，30.txt
 */
public class XiTuChengDistanceGraph {
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
        for (int i = 0 ; i < 30 ; i ++) {
            for (int j = 0; j < AdjMatrix.get(i).size(); j++) {
                if (AdjMatrix.get(i).get(j) == '1') {
                    double d = Math.random() * 1000 + 101;
                    this.xiTuChengMap.addEdge(i,j,(int) d);
                }
            }
        }
    }
    public int getBuildingDistance(int V1, int V2) {
        return this.xiTuChengMap.getWeight(V1, V2);
    }
}
class Test {
    public static void main(String[] args) {
        XiTuChengDistanceGraph xiTuChengDistanceGraph = new XiTuChengDistanceGraph();
        xiTuChengDistanceGraph.initMatrix();
        System.out.println(xiTuChengDistanceGraph.getAdjMatrix());
    }
}

