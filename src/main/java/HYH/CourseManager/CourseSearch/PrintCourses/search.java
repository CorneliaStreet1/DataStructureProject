package HYH.CourseManager.CourseSearch.PrintCourses;

import HYH.Model.Boolean_model;
import HYH.System_main;
import JYQ.Directories;
import JYQ.UserLogin.Student;
import JYQ.UserLogin.UserInformation;
import JYQ.Utils;
import JYQ.CourseManager.*;
import JHY.*;

import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;


public class search implements Boolean_model{
    @Override
    public boolean run() {
        Scanner scanner = new Scanner(System.in);
        File CurrentUserFile = Utils.join(Directories.UserRepo, System_main.CurrentUserName);
        UserInformation CurrentUser = Utils.readObject(CurrentUserFile, UserInformation.class);
        Student student = (Student) CurrentUser;

        File ClassDir = Utils.join(Directories.UserFiles, "Class" + student.getClassNumber());
        File ClassRegularTableFile = Utils.join(ClassDir, "RegularTable");
        RegularTable RegularTable = Utils.readObject(ClassRegularTableFile, RegularTable.class);
        File UserDir = Utils.join(ClassDir, student.getUserName());
        File tableFile = Utils.join(UserDir, "StudentRegularTable");
        RegularTable regularTable = Utils.readObject(tableFile, RegularTable.class);
        RegularTable.printTable(RegularTable, regularTable);
        System.out.println("课程的时间段信息可以从课表中查到。您要查哪一门课的具体信息?");
        /*System.out.println("请输入课程的完整名称：");
        String cn = scanner.next();*/
        CourseManager.SearchCourse();
        return false;
    }

    @Override
    public void dailyRecord() {
        return;
    }
}
