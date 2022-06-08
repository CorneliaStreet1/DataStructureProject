package JYQ.ShortestDistancePathFinder;

import JYQ.BuptMap.WeigtedEgde;
import JYQ.DistanceGraph.DistanceGraph;

import java.util.*;

public class ShortestPathFinder {
       private DistanceGraph Graph;
       private int[] DistanceTo;
       private int[] EdgeTo;
       private boolean[] Removed;
       //private PriorityQueue<Integer> NearestVertices;
       public ShortestPathFinder(DistanceGraph graph) {
           Graph = graph;
           DistanceTo = new int[graph.getVertices()];
           EdgeTo = new int[graph.getVertices()];
           Removed = new boolean[graph.getVertices()];
           /*Comparator<Integer> comparator = (Integer o1, Integer o2) ->{
               //distance equaling minus one indicates distance is infinity
              if (DistanceTo[o1] == -1) {
                   return 1;
               } else if (DistanceTo[o2] == -1) {
                   return -1;
               } else {
                   return DistanceTo[o1] - DistanceTo[o2];
               }
           };
           NearestVertices = new PriorityQueue<>(graph.getVertices(), comparator);
            */
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
          /* for (int i = 0;i < Graph.getVertices(); i ++) {
               NearestVertices.add(i);
           }*/
           int p = 0;
           while (p < Graph.getVertices()) {
               p ++;
              // int bestNode =
               //NearestVertices.remove();
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
}
