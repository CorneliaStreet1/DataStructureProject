package HYH.Model;

import HYH.DailyRecord.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Scanner;
import HYH.System_time.*;

public class System_models extends System_model implements Serializable {//选择模块
    private HashMap<String,System_model> models;//该模块下的子模块映射
    private static final System_close close=new System_close("关闭");//搭载了关闭模块
    private static Scanner scan = new Scanner(System.in);

    public System_models(String s) {
        super(s);
        models=new HashMap<String, System_model>();
        add_model("x",close);
    }
    public void add_model(String i,System_model m){//增加子模块
        models.put(i,m);
    }

    public void replace_model(String i,System_model m){
        models.replace(i,m);
    }

    public void introduce_model(){//介绍子模块和输入的映射关系
        System.out.println("当前模块："+super.info());
        introduce_status();
        print_model();
    }
    public void print_model(){
        System_model j;
        for(String i : models.keySet()){
            j=models.get(i);
            System.out.println("输入"+"\""+i+"\":"+j.info());
        }
    }
    public void introduce_status(){

    }
    public boolean select_model(String s) throws Close {//选择子模块
        System.out.println('\n');
        String words =s;
        RecordOperate.WriteRecord("用户输入："+words+"\n");
        System_model model = models.get(words);
        if(model!=null){
            model.run();
            return true;
        }
        else {
            RecordOperate.WriteRecord("用户输入格式错误\n");
            System.out.println("输入指令不对");
            return false;
        }
    }
    public String scan() throws Close {
        return scan.next();
    }


    public void run() throws Close {
        try{
            while(true){
//                dailyRecord();
                super.run();
                introduce_model();
                select_model(scan());
            }
        }catch (Close a){//选择子模块中输入x就启动System_close模块关闭当前模块，回到上一模块

        }
    }
}
