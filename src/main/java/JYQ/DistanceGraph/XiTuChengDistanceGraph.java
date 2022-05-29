package JYQ.DistanceGraph;

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

    public ArrayList<ArrayList<Character>> getAdjMatrix() {
        return AdjMatrix;
    }

    public XiTuChengDistanceGraph() {
        AdjMatrix = new ArrayList<>(30);
        for (int i = 0 ; i < 30; i++) {
            AdjMatrix.add(new ArrayList<>(30));
        }
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
                if (((char)end) == '\n') {
                    LineNum += 1;
                }
                AdjMatrix.get(LineNum).add((char)end);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
class Test {
    public static void main(String[] args) {
        XiTuChengDistanceGraph xiTuChengDistanceGraph = new XiTuChengDistanceGraph();
        xiTuChengDistanceGraph.initMatrix();
        System.out.println(xiTuChengDistanceGraph.getAdjMatrix());
    }
}

