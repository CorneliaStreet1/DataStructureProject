package JYQ.DijkstraUtils;

import JYQ.BuptMap.WeigtedEgde;

public interface DijkstraGraph {
    int getVertices();

    WeigtedEgde[] Adjcents(int bestNode);

}
