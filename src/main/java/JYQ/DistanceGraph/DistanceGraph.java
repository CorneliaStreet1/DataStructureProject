package JYQ.DistanceGraph;

import JYQ.BuptMap.WeigtedEgde;

public interface DistanceGraph {
    int getVertices();

    WeigtedEgde[] Adjcents(int bestNode);
}
