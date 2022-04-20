package JHY;

import HYH.Information.Information;
import JYQ.Directories;
import JYQ.UserLogin.Student;
import JYQ.Utils;

import java.io.File;
import java.io.Serializable;

//课程名称 上课地点 开始时间 结束时间 课程群做一些getter方
/*- 上课时间、上课地点
        - 课程资料
        - 当前进度
        - 已交作业、待交作业
        - 课程群
        - 考试时间和考试地点等信息*/
public class Course extends Information implements Serializable {
    private static final long serialVersionUID=222L;

    String name;
    String address;
    String GroupInformation;
    TimePair Morning;
    TimePair Afternoon;
    //课程进度?
    //资料作业?
//    String examTime;
//    String examAddress;


    public Course() {
    }

    public Course(String name, String address) {
        this.name = name;
        this.address = address;
    }
    public Course(String name, String address, TimePair morning, TimePair afternoon) {
        this.name = name;
        this.address = address;
        this.Morning = morning;
        this.Afternoon = afternoon;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getGroupInformation() {
        return GroupInformation;
    }

    public TimePair getMorning() {
        return Morning;
    }

    public TimePair getAfternoon() {
        return Afternoon;
    }

    public void setGroupInformation(String groupInformation) {
        GroupInformation = groupInformation;
    }

    public String getWeekMorning(){

        File CurrentTable= new File(Directories.UserFiles,"Class8\\RegularTable");
        RegularTable table=Utils.readObject( CurrentTable ,RegularTable.class );

        String week;
        for(int i=0;i<7;i++){
            for(int j=0;j<4;j++){
                if(table.getTable()[i][j].name==this.name)
                    switch(i){
                        case 0 :return "星期一";
                        case 1 :return "星期二";
                        case 2 :return "星期三";
                        case 3 :return "星期四";
                        case 4 :return "星期五";
                        case 5 :return "星期六";
                        case 6 :return "星期天";
                        default:return null;
                    }
            }
        }
        return null;
    }

    public String getWeekAfternoon(){
        File CurrentTable= new File(Directories.UserFiles,"Class8\\RegularTable");
        RegularTable table=Utils.readObject( CurrentTable ,RegularTable.class );

        String week;
        for(int i=0;i<7;i++){
            for(int j=5;j<9;j++){
                if(table.getTable()[i][j].name==this.name)
                    switch(i){
                        case 0 :return "星期一";
                        case 1 :return "星期二";
                        case 2 :return "星期三";
                        case 3 :return "星期四";
                        case 4 :return "星期五";
                        case 5 :return "星期六";
                        case 6 :return "星期天";
                        default:return null;
                    }
            }
        }
        return null;
    }
}