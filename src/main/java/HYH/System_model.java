package HYH;

import java.util.HashMap;
import java.util.Scanner;

public class System_model {
    private HashMap<String,System_model> models;//该模块下的子模块映射
    private String info_of_model;//这个模块是功能信息，用于提示用户选择

    public System_model(String s){//每个模块都有一个关闭选项回到上一个层级
        models.put("x",new System_close());
        info_of_model=s;
    }
    public void add_model(String i,System_model m){//增加子模块
        models.put(i,m);
    }
    public String info(){//返回功能信息
        return info_of_model;
    }
    public void introduce_model(){//介绍子模块和输入的映射关系
        System_model j;
        for(String i : models.keySet()){
            j=models.get(i);
            System.out.println("输入"+i+j.info());
        }
    }
    public void select_model() throws Close {//选择子模块
        Scanner scan = new Scanner(System.in);
        String words = scan.next();
        System_model model = models.get(words);
        model.run();
    }
    public void run() throws Close{//运行该模块
        try{//没有子模块的模块重载一下就好了
            while(1){
                introduce_model();
                select_model();
            }
        }catch (Close a){//选择子模块中扣x就启动System_close模块关闭当前模块，回到上一模块

        }
    }
}
