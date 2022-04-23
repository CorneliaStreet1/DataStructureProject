package HYH.Navigation;

import HYH.Model.*;
import JYQ.BuptMap.*;

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
