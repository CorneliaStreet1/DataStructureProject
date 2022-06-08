package HYH.MultWayMap;

import JYQ.DijkstraUtils.*;
import JYQ.DistanceGraph.*;

import java.util.Deque;



public class test {
    public static void main(String[] args) {
        MultWayMap sMap=new MultWayMap(1);
        DijkstraPathFInder finder=new DijkstraPathFInder(sMap);
        finder.FindShortestPath(0);
        Deque deque=finder.getPathTrace(0,0);
        StringBuilder s=new StringBuilder();

        //把下面这部分放回整体的模块里吧，再做个公共交通模块，再做个整体的模块，输入的节点在不同校区都能做
        int v1=-1,v2;
        while (!deque.isEmpty()){
            v2=(int)deque.removeLast();
            if(v1>=0){
                if(sMap.getKind(v1,v2)==1) s.append(":walk");
                else if(sMap.getKind(v1,v2)==2) s.append(":bike");
                //if(!deque.isEmpty())
                    s.append("=>");
            }
            s.append(v2);
            v1=v2;
        }
        System.out.println(s.toString());
    }
}
