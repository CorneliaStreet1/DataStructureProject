package HYH.Model;

import java.util.HashMap;
import java.util.Scanner;
import HYH.System_time.*;

public class System_models extends System_model{//选择模块
    private HashMap<String,System_model> models;//该模块下的子模块映射
    private static final System_close close=new System_close("关闭");//搭载了关闭模块


    public System_models(String s) {
        super(s);
        models=new HashMap<String, System_model>();
        add_model("x",close);
    }
    public void add_model(String i,System_model m){//增加子模块
        models.put(i,m);
    }
    public void introduce_model(){//介绍子模块和输入的映射关系
        System_model j;
        System.out.println("当前模块："+super.info());
        introduce_status();
        for(String i : models.keySet()){
            j=models.get(i);
            System.out.println("输入"+"\""+i+"\":"+j.info());
        }

    }
    public void introduce_status(){

    }
    public void select_model(String s) throws Close {//选择子模块
        System.out.println('\n');
        String words =s;
        System_model model = models.get(words);
        if(model!=null) model.run();
        else System.out.println("输入指令不对");
    }
    public String scan() throws Close {//这里还没加输入时暂停的模块
        Scanner scan = new Scanner(System.in);
        return scan.next();
    }


    public void run() throws Close {
        try{
            while(true){
                dailyRecord();
                introduce_model();
                select_model(scan());
            }
        }catch (Close a){//选择子模块中扣x就启动System_close模块关闭当前模块，回到上一模块

        }
    }
}
