package HYH;

import HYH.Model.*;
import HYH.DailyRecord.*;
import HYH.CourseManager.*;
import HYH.System_log.*;
import HYH.System_time.*;
import HYH.ActivityManager.*;
import HYH.Manager.*;
import HYH.Navigation.*;

import JYQ.Directories;
import JYQ.UserLogin.*;
import JYQ.Utils;

import java.io.File;

public class System_main extends Total_models{
    public static String CurrentUserName = "manager";
    public static int CurrentUserClass=-1;

    CourseManager courseManager;
    ActivityManager activityManager;
    Navigation navigation;

    public System_main(String s){
        super(s);
        courseManager=new CourseManager("课程查询管理");
        activityManager=new ActivityManager("活动查询管理");
        navigation=new Navigation("导航系统");
        add_model("1",courseManager);
        add_model("2",activityManager);
        add_model("3",navigation);
    }

    public static void main(String[] arg){
        System_main total=new System_main("主页面");
        System_manager system_manager=new System_manager("管理员");
        System_log log=new System_log("登录");
//        System_model.recordClean();
        RecordOperate.recordClean();
        Thread time_thread=new Thread(Total_models.system_time.timeRun,"时间线程");
        time_thread.start();
        try{
            while(true){
                log.run();

                //获取当前用户班级
                UserInformation userInformation = Utils.readObject(Utils.join(Directories.UserRepo, CurrentUserName),UserInformation.class);
                String userPath="./UserFiles/UserRepo/"+CurrentUserName;
                File user=new File(userPath);
                if(userInformation.isStudent()){
                    Student student_user=Utils.readObject(user,Student.class);
                    CurrentUserClass=student_user.getClassNumber();
                    System.out.println("班级："+CurrentUserClass);
                }
                else{
                    CurrentUserClass=-1;
                    //Manager manager=Utils.readObject(user,Manager.class);
                }
                //写用户信息进日志
                RecordOperate.WriteRecord("用户登录\n用户名："+CurrentUserName+"\n");
                if(CurrentUserClass!=-1) RecordOperate.WriteRecord("用户班级："+CurrentUserClass+"\n");

                //时间系统的闹钟初始化
                Total_models.system_time.Init();



                //时间继续
                if(Total_models.system_time.isStop())
                    Total_models.system_time.stopStartTime.run();
                //选择模块
                if(CurrentUserClass!=-1) total.run();
                else system_manager.run();
                //时间暂停
                if(!Total_models.system_time.isStop())
                    Total_models.system_time.stopStartTime.run();

                //时间系统的闹钟持久化
                Total_models.system_time.Store();
                RecordOperate.WriteRecord("用户："+CurrentUserName+" 退出程序\n");
            }
        }catch (Close a){
            Total_models.system_time.timeRun.end();
            Total_models.system_time.stopStartTime.run();//暂停了标志就没用了
            Total_models.system_time.ClearStopLayer();
        }
    }
}
