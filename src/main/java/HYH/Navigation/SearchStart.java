package HYH.Navigation;

import HYH.Model.*;
import JYQ.BuptMap.*;

//选起点
public class SearchStart extends SearchModel {

    public static SelectSchool selectSchool=new SelectSchool("选择校区");
    public SearchStart(String s) {
        super(s);
    }

    @Override
    public boolean Search() throws Close{
        System.out.println("当前模块：选择起点");
        selectSchool.run();


        System.out.println(super.readSchool());
        BuptGraph graph=(super.readSchool()==SCHOOL.ShaHe?new ShaHeMap():new XiTuChengMap());
        System.out.print("请输入起点名字：");
        String name=super.scanner.nextLine();
        Integer i=graph.getBuildingIndex(name);
        if(i==null) return false;
        Guide.startPoint=i;
        return true;
    }
}
