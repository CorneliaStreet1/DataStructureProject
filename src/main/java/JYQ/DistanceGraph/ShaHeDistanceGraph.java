package JYQ.DistanceGraph;

import JYQ.BuptMap.ShaHeMap;
import JYQ.BuptMap.XiTuChengMap;
import JYQ.Directories;
import JYQ.Utils;

import java.io.*;
import java.util.ArrayList;

/**
 * 西土城校区，30个点，30.txt
 */
public class ShaHeDistanceGraph {
    private ArrayList<ArrayList<Character>> AdjMatrix;
    public static File MatrixFile = Utils.join(Directories.MapRepo, "27.txt");
    private ShaHeMap ShaHeMap;
    public ArrayList<ArrayList<Character>> getAdjMatrix() {
        return AdjMatrix;
    }
    public ShaHeDistanceGraph() {
        AdjMatrix = new ArrayList<>(30);
        for (int i = 0 ; i < 30; i++) {
            AdjMatrix.add(new ArrayList<>(30));
        }
        ShaHeMap = new ShaHeMap(30);
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
        for (int i = 0 ; i < 30 ; i ++) {
            for (int j = 0; j < AdjMatrix.get(i).size(); j++) {
                if (AdjMatrix.get(i).get(j) == '1') {
                    double d = Math.random() * 1000 + 101;
                    this.ShaHeMap.addEdge(i,j,(int) d);
                }
            }
        }
    }
    public int getBuildingDistance(int V1, int V2) {
        return this.ShaHeMap.getWeight(V1, V2);
    }
}

