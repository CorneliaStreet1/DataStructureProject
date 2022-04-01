package HYH;
import HYH.Model.*;
import HYH.CourseManager.*;
import HYH.System_log.*;
public class System_main extends Total_models{
    public System_main(String s){
        super(s);
        add_model("1",new CourseManager("课程查询管理"));
        //如上把各模块加到主模块上
    }


    public void add_model(String i, System_model m) {
        super.add_model(i, m);
    }


    public void run() throws Close {
        super.run();
    }

    public static void main(String[] arg){
        System_main total=new System_main("主页面");
        System_log log=new System_log("登录");//只写了个框架
        try{
            while(true){
                log.run();
                total.run();
            }
        }catch (Close a){

        }
    }
}
