package JYQ.ShortestDistancePathFinder;

import JYQ.BuptMap.WeigtedEgde;
import JYQ.DistanceGraph.DistanceGraph;

import java.util.ArrayList;

//a simple test map
public class TestMap implements DistanceGraph {
    private ArrayList<WeigtedEgde[]> AdjacentList;
    public TestMap() {
        AdjacentList = new ArrayList<>(7);
        for (int i = 0; i <7; i ++) {
            AdjacentList.add(i,new WeigtedEgde[7]);
        }
        this.addEdge(0,1,2);
        this.addEdge(0,2,1);
        this.addEdge(1,2,5);
        this.addEdge(1,4,3);
        this.addEdge(2,4,1);
        this.addEdge(2,5,15);
        this.addEdge(1,3,11);
        this.addEdge(4,3,2);
        this.addEdge(4,5,4);
        this.addEdge(4,6,5);
        this.addEdge(3,6,1);
        this.addEdge(5,6,1);
    }
    @Override
    public int getVertices() {
        return 7;
    }
    @Override
    public WeigtedEgde[] Adjcents(int bestNode) {
        return AdjacentList.get(bestNode);
    }
    public void addEdge(int V1, int V2, int W) {
        AdjacentList.get(V1)[V2] = new WeigtedEgde(V2, W);
        AdjacentList.get(V2)[V1] = new WeigtedEgde(V1, W);
    }

    public static void main(String[] args) {
        ShortestPathFinder shortestPathFinder = new ShortestPathFinder(new TestMap());
        shortestPathFinder.FindShortestPath(0);
        System.out.println(shortestPathFinder.PrintPathTrace(0,3));
    }
}
