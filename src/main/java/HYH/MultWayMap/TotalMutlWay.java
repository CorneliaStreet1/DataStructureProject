package HYH.MultWayMap;

import java.util.Deque;
import JYQ.DijkstraUtils.*;
import JYQ.BuptMap.*;
import HYH.Model.*;

public class TotalMutlWay {

    String schoolFindWay(int source,int dest,int school){
        ShaHeMap shaMap=new ShaHeMap();
        XiTuChengMap xiMap=new XiTuChengMap();
        MultWayMap sMap=new MultWayMap(school);
        DijkstraPathFInder finder=new DijkstraPathFInder(sMap);
        finder.FindShortestPath(source);
        Deque deque=finder.getPathTrace(source,dest);
        StringBuilder s=new StringBuilder();


        //把下面这部分放回整体的模块里吧，再做个公共交通模块，再做个整体的模块，输入的节点在不同校区都能做
        int v1=-1,v2;
        while (!deque.isEmpty()){
            v2=(int)deque.removeLast();
            String name=null;
            if(school==1) name=shaMap.getBuildingName(v2);
            else if(school==2){

                name=xiMap.getBuildingName(v2+30);//getBuildingName接收的还是真实的序号
            }

            if(v1>=0){
                if(sMap.getKind(v1,v2)==1) s.append("-walk");
                else if(sMap.getKind(v1,v2)==2) s.append("-bike");
                //if(!deque.isEmpty())
                s.append("=>");
            }
            s.append(name);
            v1=v2;
        }
        return s.toString();
    }


    public void findWay(int v1,int v2){
        int school1,school2;
        school1=(v1>29?2:1);
        school2=(v2>29?2:1);
        if(school1==school2){
            if(school1==2){
                v1-=30;
                v2-=30;
            }
            System.out.println(schoolFindWay(v1,v2,school1));
        }
        else{
            if(school1==2) v1-=30;
            if(school2==2) v2-=30;
            System.out.print(schoolFindWay(v1,0,school1));

            int bus=new SelectBus().run();
            if(bus==1) System.out.print("=>周期公交=>");
            else if(bus==2) System.out.print("=>定点公交=>");
            else System.out.print("=>暂无公交=>");
            System.out.println(schoolFindWay(0,v2,school2));
        }
    }

    public static void main(String[] args) {
        TotalMutlWay t=new TotalMutlWay();
        t.findWay(7,47);
    }
}
