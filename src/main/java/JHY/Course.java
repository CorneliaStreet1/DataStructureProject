package JHY;

import HYH.Information.Information;
import java.io.Serializable;
//课程名称 上课地点 开始时间 结束时间 课程群做一些getter方
/*- 上课时间、上课地点
        - 课程资料
        - 当前进度
        - 已交作业、待交作业
        - 课程群
        - 考试时间和考试地点等信息*/
public class Course extends Information implements Serializable {
    String name;
    String address;
    String GroupInformation;
    public Course() {
    }
    public Course(String name, String address) {
        this.name = name;
        this.address = address;
    }
    public Course(String name) {
        this.name = name;
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

    public void setGroupInformation(String groupInformation) {
        GroupInformation = groupInformation;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (o instanceof Course) {
            Course course = (Course) o;
            return this.getName().equals(course.getName());
        }
        return false;
    }
}
