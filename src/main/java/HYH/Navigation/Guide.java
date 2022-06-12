package HYH.Navigation;

import HYH.Model.*;
import HYH.MultWayMap.*;
import JYQ.BuptMap.*;
import JYQ.ShortestDistancePathFinder.*;
import JHY.Guide.*;

import java.util.HashMap;
import java.util.Scanner;


public class Guide extends System_model{
//    private static ThrowContinue Continue=new ThrowContinue("重新");

    enum SCHOOL{ShaHe,XiTuCheng};
    public SearchStart searchStart;
    public SearchEnd searchEnd;

    public static ShaHeMap shaHeMap=new ShaHeMap(20);
    public static XiTuChengMap xiTuChengMap=new XiTuChengMap(20);


    public static int startPoint=-1,endPoint=-1;


    //构造函数
    public Guide(String s) {
        super(s);
        searchStart=new SearchStart("搜索起点");
        searchEnd=new SearchEnd("搜索终点");
    }

    @Override
    public void run() throws Close {
        try {

                    //搜索起点，搜索不到introduce_models，让用户选择抛出Close还是重复该模块
                    //搜索终点,都是继承System_models，有Close
                    //导航
            searchStart.run();
            searchEnd.run();
            if(Guide.startPoint==-1||Guide.endPoint==-1) return;
            System.out.println("请选择导航策略\n输入“1”：最短路径导航\n输入“2”：最短时间导航\n输入“3”：混合最短时间策略\n");
            Scanner scanner=new Scanner(System.in);
            String select=scanner.nextLine();
            switch (select){
                case "1": JYQ.ShortestDistancePathFinder.Navigation.Navigator(Guide.startPoint,Guide.endPoint); break;
                case "2": JHY.Guide.Navigation.Navigator(Guide.startPoint,Guide.endPoint); break;
                case "3": TotalMutlWay t=new TotalMutlWay(); t.findWay(Guide.startPoint,Guide.endPoint); break;
                default:System.out.println("输入错误，退出"); return;
            }
        }catch (Close c){

        }
    }


   /* public class ThrowContinue extends System_model {
        public ThrowContinue(String s) {
            super(s);
        }

        @Override
        public void run() throws Continue {
            super.run();
            throw new Continue();
        }
    }*/








   /* public boolean SearchStart(){

        System.out.print("请输入起点名字: ");
        String name = scanner.nextLine();
        Integer i = shaHeMap.getBuildingIndex(name);
        if (i == null) return false;
        startPoint=i;
        return true;
    }
    public boolean SearchEnd(){

    }
    public SCHOOL select_school(){
        System.out.print("请输入校区：");
        int select = scanner.nextLine();
    }*/
}
