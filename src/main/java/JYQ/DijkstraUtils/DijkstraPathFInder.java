package JYQ.DijkstraUtils;

import JYQ.BuptMap.WeigtedEgde;
import JYQ.DistanceGraph.DistanceGraph;
import JYQ.DistanceGraph.ShaHeDistanceGraph;

import java.util.ArrayDeque;
import java.util.Deque;

public class DijkstraPathFInder {

    private DijkstraGraph Graph;
    private int[] DistanceTo;
    private int[] EdgeTo;
    private boolean[] Removed;
    public DijkstraPathFInder(DijkstraGraph graph) {
        Graph = graph;
        DistanceTo = new int[graph.getVertices()];
        EdgeTo = new int[graph.getVertices()];
        Removed = new boolean[graph.getVertices()];
    }
    public void FindShortestPath(int Source) {
        for (int i = 0 ; i < Graph.getVertices(); i ++) {
            if (i == Source) {
                DistanceTo[i] = 0;
                EdgeTo[i] = i;
            }
            else {
                DistanceTo[i] = -1;
                EdgeTo[i] = -1;
            }
        }
        int p = 0;
        while (p < Graph.getVertices()) {
            p ++;
            int bestNode = this.FindNearest();
            Removed[bestNode] = true;
            WeigtedEgde[] Adjacent = Graph.Adjcents(bestNode);
            for (int i = 0 ; i < Adjacent.length;i ++) {
                WeigtedEgde weigtedEgde = Adjacent[i];
                if (weigtedEgde != null) {
                    int NewDistance = weigtedEgde.getWeight() + DistanceTo[bestNode];
                    int CurrentAdjacent = weigtedEgde.getAdjacentVertexNumber();
                    if (DistanceTo[CurrentAdjacent] == -1 || DistanceTo[CurrentAdjacent] > NewDistance) {
                        DistanceTo[CurrentAdjacent] = NewDistance;
                        EdgeTo[CurrentAdjacent] = bestNode;
                    }
                }
            }
        }
    }
    private int FindNearest() {
        int smallest = 0;
        for (int i = 0 ; i < DistanceTo.length; i ++) {
            if (DistanceTo[i] != -1 && !Removed[i]) {
                smallest = i;
                break;
            }
        }
        for (int i = 0;i < DistanceTo.length; i ++) {
            if (DistanceTo[i] != -1 && !Removed[i]&& DistanceTo[i] < DistanceTo[smallest]) {
                smallest = i;
            }
        }
        return smallest;
    }
    public Deque<Integer> getPathTrace(int Source, int Dest) {
        int p = Dest;
        ArrayDeque<Integer> Trace = new ArrayDeque<>();
        while (EdgeTo[p] != Source) {
            Trace.add(p);
            p = EdgeTo[p];
        }
        Trace.add(p);
        Trace.add(Source);
        return Trace;
    }
    public String PrintPathTrace(int Source, int Dest) {
        Deque<Integer> Path = this.getPathTrace(Source, Dest);
        StringBuilder stringBuilder = new StringBuilder();
        while (!Path.isEmpty()) {
            stringBuilder.append(Path.removeLast());
            if (Path.isEmpty()) {
                break;
            }
            stringBuilder.append("=>");
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {

        DijkstraGraph dijkstraGraph = ShaHeDistanceGraph.ReadInGraph();
        DijkstraPathFInder dijkstraPathFInder = new DijkstraPathFInder(dijkstraGraph);
        dijkstraPathFInder.FindShortestPath(0);
        System.out.println(dijkstraPathFInder.PrintPathTrace(0,26));
        System.out.println(dijkstraPathFInder.getPathTrace(0,26));
    }
}
