package HYH;
import HYH.Model.*;
import HYH.CourseManager.*;
import HYH.System_log.*;
import HYH.System_time.*;
import JYQ.UserLogin.*;
import JYQ.Utils;

import java.io.File;

public class System_main extends Total_models{
    public static String CurrentUserName = "manager";
    public static int CurrentUserClass=-1;

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

                if(!CurrentUserName.equals("manager")){
                    String userPath="./UserFiles/UserRepo/"+CurrentUserName;
                    File user=new File(userPath);
                    Student student_user=Utils.readObject(user,Student.class);
                    CurrentUserClass=student_user.getClassNumber();
                    System.out.println("班级："+CurrentUserClass);
                }
                else CurrentUserClass=-1;


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
