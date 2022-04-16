package JHY;

import HYH.Information.Information;

//课程名称 上课地点 开始时间 结束时间 课程群做一些getter方
/*- 上课时间、上课地点
        - 课程资料
        - 当前进度
        - 已交作业、待交作业
        - 课程群
        - 考试时间和考试地点等信息*/
public class Course extends Information {

    String name;
    String address;
    int startTimeAM;
    int endTimeAM;
    int startTimePM;
    int endTimePM;
    String GroupInformation;

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

    public int getStartTimeAM() {
        return startTimeAM;
    }

    public void setStartTimeAM(int startTimeAM) {
        this.startTimeAM = startTimeAM;
    }

    public int getEndTimeAM() {
        return endTimeAM;
    }

    public void setEndTimeAM(int endTimeAM) {
        this.endTimeAM = endTimeAM;
    }

    public int getStartTimePM() {
        return startTimePM;
    }

    public void setStartTimePM(int startTimePM) {
        this.startTimePM = startTimePM;
    }

    public int getEndTimePM() {
        return endTimePM;
    }

    public void setEndTimePM(int endTimePM) {
        this.endTimePM = endTimePM;
    }

    public String getGroupInformation() {
        return GroupInformation;
    }

    public void setGroupInformation(String groupInformation) {
        GroupInformation = groupInformation;
    }
}
