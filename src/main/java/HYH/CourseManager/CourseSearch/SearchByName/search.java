package HYH.CourseManager.CourseSearch.SearchByName;

import HYH.Model.*;
import JYQ.CourseManager.*;

import java.util.Scanner;

public class search implements Boolean_model{

    @Override
    public boolean run() {
        System.out.println("请输入要查询的课程的完整名称:");
        Scanner scanner=new Scanner(System.in);
        String CourseName = scanner.next();
        CourseManager.SearchCourse(CourseName);
        return false;
    }

    @Override
    public void dailyRecord() {
        return;
    }
}
