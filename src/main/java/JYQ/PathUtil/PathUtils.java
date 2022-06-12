package JYQ.PathUtil;

import HYH.System_main;
import JHY.Course;
import JHY.RegularTable;
import JYQ.BuptMap.ShaHeMap;
import JYQ.BuptMap.XiTuChengMap;
import JYQ.CourseManager.CourseTable;
import JYQ.CourseManager.TableInformation;
import JYQ.Directories;
import JYQ.UserLogin.Student;
import JYQ.UserLogin.UserInformation;
import JYQ.Utils;
import java.io.File;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.Scanner;

public class PathUtils {
    public static int getBuildingIndexByCourse(String CourseName) {
        if ((new ShaHeMap()).getBuildingIndex(PathUtils.getCourseBuilding(CourseName)) == null) {
            return (new XiTuChengMap()).getBuildingIndex(PathUtils.getCourseBuilding(CourseName));
        }
        return (new ShaHeMap()).getBuildingIndex(PathUtils.getCourseBuilding(CourseName));
    }
    public static String getCourseBuilding(String CourseName) {
        File CourseFile = Utils.join(Directories.CourseRepo, CourseName);
        Course course = Utils.readObject(CourseFile,Course.class);
        return course.getBuildingName();
    }
    public static int getBuildingIndexByLocation(String location) {
        if ((new ShaHeMap()).getBuildingIndex(location) == null) {
            return (new XiTuChengMap()).getBuildingIndex(location);
        }
        return (new ShaHeMap()).getBuildingIndex(location);
    }
    public static int getBuildingIndexByTime() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("你想查询周几的课?(请输入阿拉伯数字1到5，不会有人周末还上课吧，不会吧)");
        int week = scanner.nextInt();
        System.out.println("请按照HH MM的格式(24小时制)输入小时和分钟:");
        String hour,min;
        hour = scanner.next();
        if (Integer.parseInt(hour) >= 17) {
            System.out.println("你所查询的时间内没有课程");
        }
        min = scanner.next();
        if (Integer.parseInt(min) > 60) {
            System.out.println("请重新输入不大于60的分钟:");
            min = scanner.next();
        }
        int H = Integer.parseInt(hour),M = Integer.parseInt(min);
        CourseTable courseTable = new CourseTable();
        File CurrentUserFile = Utils.join(Directories.UserRepo, System_main.CurrentUserName);
        UserInformation CurrentUser = Utils.readObject(CurrentUserFile, UserInformation.class);
        Student student = (Student) CurrentUser;
        File classDir = Utils.join(Directories.UserFiles, "Class" + student.getClassNumber());
        File ClassTableFile = Utils.join(classDir, "RegularTable");
        File studentDir = Utils.join(classDir,student.getUserName());
        File studentTableFile = Utils.join(studentDir, "StudentRegularTable");
        RegularTable StudentTable = Utils.readObject(studentTableFile, RegularTable.class);
        RegularTable ClassTable = Utils.readObject(ClassTableFile, RegularTable.class);
        courseTable.getCompleteTable(ClassTable, StudentTable);
        LinkedList<TableInformation>[] table = courseTable.getCourseTable();
        int index = -1;
        for (int i = 0; i < table[week].size(); i ++) {
            TableInformation t = table[week].get(i);
            if (t.getCourse() != null && t.getStartTime().isAfter(LocalTime.of(H, M))) {
              index = PathUtils.getBuildingIndexByCourse(t.getCourse().getName());
              break;
            }
        }
        if (index == -1) {
            System.out.println("在这个时间段内课表内没有课");
            //System.exit(0);
            return -1;
        }
        return index;
    }
    public static void main(String[] args) {
       System.out.println(PathUtils.getBuildingIndexByCourse("数据库原理"));
       System.out.println(PathUtils.getBuildingIndexByLocation("教一楼"));
       System.out.println(PathUtils.getBuildingIndexByTime());
    }
}
