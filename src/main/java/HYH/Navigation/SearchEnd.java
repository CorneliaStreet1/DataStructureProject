package HYH.Navigation;

import HYH.Model.*;

import java.util.HashMap;

//选终点
public class SearchEnd extends SearchStart{
    HashMap<String,System_model> models;

    public ByCourse byCourse;
    public ByTime byTime;
    public ByPlace byPlace;

    public SearchEnd(String s) {
        super(s);
        byCourse=new ByCourse("课程名称搜索");
        byTime=new ByTime("时间点搜索");
        byPlace=new ByPlace("地点搜索");
        models=new HashMap<String, System_model>();
        AddModel("1",byCourse);
        AddModel("2",byTime);
        AddModel("3",byPlace);
    }

    @Override
    public boolean Search() throws Close{
        System.out.println("当前模块：选择终点");
        SearchStart.selectSchool.run();
        System.out.println("请选择搜索终点的方案");
        PrintModels();
        return SelectModel();
    }

    public class ByCourse extends System_model{
        public ByCourse(String s) {
            super(s);
        }

        @Override
        public void run() throws Close {
            System.out.println("课程名称搜索完成");
        }
    }
    public class ByTime extends System_model {
        public ByTime(String s) {
            super(s);
        }
        public void run() throws Close {
            System.out.println("时间点搜索完成");
        }
    }
    public class ByPlace extends System_model {
        public ByPlace(String s) {
            super(s);
        }
        public void run() throws Close {
            System.out.println("地点名称搜索完成");
        }
    }

    public void AddModel(String i,System_model model){
        models.put(i,model);
    }
    public void PrintModels(){
        System_model j;
        for(String i : models.keySet()){
            j=models.get(i);
            System.out.println("输入"+"\""+i+"\":"+j.info());
        }
    }
    public boolean SelectModel() throws Close{
        String words=super.scanner.nextLine();
        System_model temp=models.get(words);
        if(temp==null) return false;
        temp.run();
        return true;
    }
}