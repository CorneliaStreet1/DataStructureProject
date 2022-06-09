package JHY.Guide;

import JYQ.BuptMap.ShaHeMap;
import JYQ.BuptMap.XiTuChengMap;
import JYQ.DistanceGraph.ShaHeDistanceGraph;
import JYQ.DistanceGraph.XiTuChengDistanceGraph;

import java.util.Deque;

public class Navigation {
    public static void Navigator(int Source, int Dest) {
        //如果起点的编号小于27，则说明起点在沙河，反之在西土城
        System.out.println("按最短时间策略寻找到的路径如下:");
        boolean SourceIsInShaHe = (Source < 27);
        boolean DestIsInShaHe = (Dest < 27);
        //起点和终点都在沙河
        if (SourceIsInShaHe && DestIsInShaHe) {
            Deque<Integer> PathTrace = Navigation.FindPathInShaHe(Source, Dest);
            StringBuilder stringBuilder = new StringBuilder();
            while (!PathTrace.isEmpty()) {
                String building = (new ShaHeMap()).getBuildingName(PathTrace.removeLast());
                stringBuilder.append(building);
                if (PathTrace.isEmpty()) {
                    break;
                }
                stringBuilder.append("=>");
            }
            System.out.println(stringBuilder);
        }
        //起点在沙河，终点在西土城
        else if (SourceIsInShaHe && !DestIsInShaHe){
            Deque<Integer> ShaHeTrace = Navigation.FindPathInShaHe(Source, 0);
            Deque<Integer> XiTuChengTrace = Navigation.FindPathInXiTuCheng(0,Dest - 30);
            StringBuilder ShaHe = new StringBuilder("在沙河：");
            while (!ShaHeTrace.isEmpty()) {
                String building = (new ShaHeMap()).getBuildingName(ShaHeTrace.removeLast());
                ShaHe.append(building);
                if (ShaHeTrace.isEmpty()) {
                    break;
                }
                ShaHe.append("=>");
            }
            StringBuilder BenBu = new StringBuilder("在本部：");
            while (!XiTuChengTrace.isEmpty()) {
                String building = (new XiTuChengMap()).getBuildingName(XiTuChengTrace.removeLast() + 30);
                BenBu.append(building);
                if (XiTuChengTrace.isEmpty()) {
                    break;
                }
                BenBu.append("=>");
            }
            System.out.println(ShaHe+"=>公共交通=>" + BenBu);
        }
        //起点在西土城，终点在沙河
        else if (!SourceIsInShaHe && DestIsInShaHe) {
            Deque<Integer> ShaHeTrace = FindPathInShaHe(0, Dest);
            Deque<Integer> XiTuChengTrace = FindPathInXiTuCheng(Source - 30,0);
            StringBuilder ShaHe = new StringBuilder("在沙河：");
            while (!ShaHeTrace.isEmpty()) {
                String building = (new ShaHeMap()).getBuildingName(ShaHeTrace.removeLast());
                ShaHe.append(building);
                if (ShaHeTrace.isEmpty()) {
                    break;
                }
                ShaHe.append("=>");
            }
            StringBuilder BenBu = new StringBuilder("在本部：");
            while (!XiTuChengTrace.isEmpty()) {
                String building = (new XiTuChengMap()).getBuildingName(XiTuChengTrace.removeLast() + 30);
                BenBu.append(building);
                if (XiTuChengTrace.isEmpty()) {
                    break;
                }
                BenBu.append("=>");
            }
            System.out.println( BenBu + "=>公共交通=>" +  ShaHe);
        }//起点和终点都在西土城
        else {
            Deque<Integer> XiTuChengTrace = Navigation.FindPathInXiTuCheng(Source - 30,Dest - 30);
            StringBuilder BenBu = new StringBuilder();
            while (!XiTuChengTrace.isEmpty()) {
                String building = (new XiTuChengMap()).getBuildingName(XiTuChengTrace.removeLast() + 30);
                BenBu.append(building);
                if (XiTuChengTrace.isEmpty()) {
                    break;
                }
                BenBu.append("=>");
            }
            System.out.println(BenBu);
        }
    }
    private static Deque<Integer> FindPathInShaHe(int Source, int Dest) {
        //ShortestPathFinder shortestPathFinder = new ShortestPathFinder(ShaHeDistanceGraph.ReadInGraph());
        //shortestPathFinder.FindShortestPath(Source);
        PathFInderShaHe pathFInderShaHe = new PathFInderShaHe(ShaHeDistanceGraph.ReadInGraph());
        pathFInderShaHe.FindShortestPath(Source);
        return pathFInderShaHe.getPathTrace(Source, Dest);
    }
    private static Deque<Integer> FindPathInXiTuCheng(int Source, int Dest) {
        //ShortestPathFinder shortestPathFinder = new ShortestPathFinder(XiTuChengDistanceGraph.ReadInGraph());
        //shortestPathFinder.FindShortestPath(Source);
        PathFinderXiTuCheng pathFinderXiTuCheng = new PathFinderXiTuCheng(XiTuChengDistanceGraph.ReadInGraph());
        pathFinderXiTuCheng.FindShortestPath(Source);
        return pathFinderXiTuCheng.getPathTrace(Source, Dest);
    }

    public static void main(String[] args) {
        Navigation.Navigator(37,1);
    }
}
