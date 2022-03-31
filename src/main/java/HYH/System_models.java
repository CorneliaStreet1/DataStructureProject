package HYH;

import java.util.HashMap;
import java.util.Scanner;

public class System_models extends System_model{
    private HashMap<String,System_model> models;//该模块下的子模块映射

    public System_models(String s) {
        super(s);
        models=new HashMap<String, System_model>();
        add_model("x",new System_close("close"));
    }
    public void add_model(String i,System_model m){//增加子模块
        models.put(i,m);
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


    public void run() throws Close {
        try{//没有子模块的模块重载一下就好了
            while(true){
                introduce_model();
                select_model();
            }
        }catch (Close a){//选择子模块中扣x就启动System_close模块关闭当前模块，回到上一模块

        }
    }
}
