package JYQ.UserLogin;

import JHY.Course;
import JYQ.Utils;

import java.io.File;

public class Demo {

    public static void main(String[] args) {
        Student A = Utils.readObject(new File("D:\\DataStructureProject\\UserFiles\\UserRepo\\学生A"), Student.class);
        Student C = Utils.readObject(new File("D:\\DataStructureProject\\UserFiles\\UserRepo\\学生C"), Student.class);
        Student B = Utils.readObject(new File("D:\\DataStructureProject\\UserFiles\\UserRepo\\学生B"), Student.class);
        A.addCourse( new Course("面向对象程序设计C++","S301", "综合实验教学楼"),3,6);
        B.addCourse( new Course("面向对象程序设计C++","S301", "综合实验教学楼"),3,6);
        C.addCourse( new Course("面向对象程序设计C++","S301", "综合实验教学楼"),3,6);
        A.addCourse( new Course("面向对象程序设计C++","S301", "综合实验教学楼"),3,7);
        B.addCourse( new Course("面向对象程序设计C++","S301", "综合实验教学楼"),3,7);
        C.addCourse( new Course("面向对象程序设计C++","S301", "综合实验教学楼"),3,7);
        A.addCourse( new Course("数据库原理","N309","教一楼"),3,8);
        B.addCourse( new Course("数据库原理","N309","教一楼"),3,8);
        C.addCourse( new Course("数据库原理","N309","教一楼"),3,8);
        A.addCourse( new Course("数据库原理","N309","教一楼"),3,9);
        B.addCourse( new Course("数据库原理","N309","教一楼"),3,9);
        C.addCourse( new Course("数据库原理","N309","教一楼"),3,9);
        A.addCourse(new Course("离散数学","S201","综合实验教学楼"),5,1);
        B.addCourse(new Course("离散数学","S201","综合实验教学楼"),5,1);
        C.addCourse(new Course("离散数学","S201","综合实验教学楼"),5,1);
        A.addCourse(new Course("离散数学","S201","综合实验教学楼"),5,2);
        B.addCourse(new Course("离散数学","S201","综合实验教学楼"),5,2);
        C.addCourse(new Course("离散数学","S201","综合实验教学楼"),5,2);
    }
}
