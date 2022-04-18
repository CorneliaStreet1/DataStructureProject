package HYH;
import HYH.Model.*;
import HYH.CourseManager.*;
import HYH.System_log.*;
import HYH.System_time.*;
import JYQ.UserLogin.UserInformation;

public class System_main extends Total_models{
    public static String CurrentUserName = "jyq";
    public System_main(String s){
        super(s);
        add_model("1",new CourseManager("课程查询管理"));
        //如上把各模块加到主模块上
    }

    public static void main(String[] arg){
        System_main total=new System_main("主页面");
        System_log log=new System_log("登录");//只写了个框架
        System_model.recordClean();
        try{
            while(true){
                log.run();
                if(Total_models.system_time.isStop())
                    Total_models.system_time.stopStartTime.run();
                total.run();
                if(!Total_models.system_time.isStop())
                    Total_models.system_time.stopStartTime.run();
            }
        }catch (Close a){
            Total_models.system_time.timeRun.end();
            Total_models.system_time.stopStartTime.run();//暂停了标志就没用了
        }
    }
}
