package JYQ.CourseManager;

import HYH.Model.Boolean_model;
import HYH.System_main;
import JYQ.Directories;
import JYQ.UserLogin.Student;
import JYQ.UserLogin.UserInformation;
import JYQ.Utils;
import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

import JHY.*;
public class CourseManager implements Boolean_model {
    public static void addNewCourse(Course course) {
        Scanner scanner = new Scanner(System.in);
       if (CourseManager.hasCourse(course)) {
            System.out.println("您所添加的课程已经存在于课程系统中，如果继续添加将会覆盖原有的课程，继续吗?(y继续，n终止)");
            String y = scanner.next();
            if (y.compareToIgnoreCase("n") == 0) {
                return;
            }
        }
        File CourseRepo = Directories.CourseRepo;
        if (!CourseRepo.exists()) {
            CourseRepo.mkdir();
        }
        File courseFile = Utils.join(CourseRepo, course.getName());
        Utils.writeObject(courseFile, course);
    }
    public static void deleteCourse(Course course) {
        File CourseRepo = Directories.CourseRepo;
        File courseFile = Utils.join(CourseRepo, course.getName());
        if (!CourseManager.hasCourse(course)) {
            System.out.println("您所要删除的课程不存在！");
            return;
        }
        else {
            Utils.restrictedDelete(courseFile);
        }
    }
    public static boolean hasCourse(Course course) {
        if (course == null) {
            return false;
        }
        else {
            File courseFile = Utils.join(Directories.CourseRepo, course.getName());
            return courseFile.exists();
        }
    }
    public static void Interface() {
        Scanner scanner = new Scanner(System.in);
        File CurrentUserFile = Utils.join(Directories.UserRepo, System_main.CurrentUserName);
        UserInformation CurrentUser = Utils.readObject(CurrentUserFile, UserInformation.class);
        if (CurrentUser.isStudent()) {
            Student student = (Student) CurrentUser;
            int option;
            System.out.println("欢迎进入课程管理模块。您现在处于学生端");
            System.out.println("您在本模块中可以做的事有:");
            System.out.println("1.查询课程信息");
            System.out.println("2.上传作业和资料");
            System.out.println("3.查询作业和资料");
            System.out.println("4.查询考试相关信息");
            System.out.println("请输入您想要的功能的序号:");
            try {
                option = scanner.nextInt();
            }
            catch (InputMismatchException e) {
                System.out.println("您输入的不是整数，请重新输入:");
                option = scanner.nextInt();
            }
            if (option == 1) {
                int MethodOption;
                System.out.println("您选择的是查询课程信息功能，在本功能中您可以选择两种查询方式:");
                System.out.println("1.按照课程名称查询");
                System.out.println("2.通过课表查询");
                System.out.println("请输入您想要的查询方式的序号:");
                try {
                    MethodOption = scanner.nextInt();
                }
                catch (InputMismatchException e) {
                    System.out.println("您输入的不是整数序号，请重新输入整数序号:");
                    MethodOption = scanner.nextInt();
                }
                if (MethodOption == 1) {
                    System.out.println("请输入要查询的课程的完整名称:");
                    String CourseName = scanner.next();
                    CourseManager.SearchCourse(CourseName);
                }
                else {
                    File ClassDir = Utils.join(Directories.UserFiles, "Class" + student.getClassNumber());
                    File ClassRegularTableFile = Utils.join(ClassDir, "RegularTable");
                    RegularTable RegularTable = Utils.readObject(ClassRegularTableFile, RegularTable.class);
                    File UserDir = Utils.join(ClassDir, student.getUserName());
                    File tableFile = Utils.join(UserDir, "StudentRegularTable");
                    RegularTable regularTable = Utils.readObject(tableFile, RegularTable.class);
                    JHY.RegularTable.printTable(RegularTable, regularTable);
                    System.out.println("课程的时间段信息可以从课表中查到。您要查哪一门课的具体信息?");
                    System.out.println("请输入课程的完整名称：");
                    String cn = scanner.next();
                    CourseManager.SearchCourse(cn);
                }
            }
        }
        else {
            System.out.println("欢迎进入课程管理模块。您现在处于管理员端");
            System.out.println("您可以做的事情有：");
            System.out.println("1.为某个班级添加课程");
            System.out.println("2.为某个班级删除课程");
            System.out.println("请输入您想使用的功能的序号:");
            int option;
            try {
                option = scanner.nextInt();
            }
            catch (InputMismatchException e) {
                System.out.println("不要输入奇怪的东西啊喂，输个数字好不好，拜托，你真是太逊了。");
                option = scanner.nextInt();
            }
            if (option == 1) {
                System.out.println("请输入您想添加课程的班级的序号(阿拉伯数字，不要奇怪的东西)");
                int num;
                try {
                    num = scanner.nextInt();
                }
                catch (InputMismatchException e) {
                    System.out.println("都说了不要奇怪的东西，你真的太逊了，重新输入吧。");
                    num = scanner.nextInt();
                }
                CourseManager.addCourseForClass(num);
            }
            else {
                System.out.println("请输入您想删除课程的班级的序号(阿拉伯数字，不要奇怪的东西)");
                int num;
                try {
                    num = scanner.nextInt();
                }
                catch (InputMismatchException e) {
                    System.out.println("都说了不要奇怪的东西，你真的太逊了，重新输入吧。");
                    num = scanner.nextInt();
                }
                CourseManager.deleteClassForClass(num);
            }
        }
    }
    public static void SearchCourse(String CourseName) {
        File CourseFile = Utils.join(Directories.CourseRepo, CourseName);
        Course course = Utils.readObject(CourseFile, Course.class);
        System.out.println("查询到的课程信息如下:");
        System.out.println("课程名称: " + course.getName());
        System.out.println("上课地点: "+course.getAddress());
        System.out.println("上午的上课时间："
                + course.getMorning().getStartTimeHour() + ":" + course.getMorning().getStartTimeMin()
                +"到" + course.getMorning().getEndTimeHour() + ":" + course.getMorning().getEndTimeMin()
        );
        System.out.println("下午的上课时间: "
                + course.getAfternoon().getStartTimeHour() + ":" + course.getAfternoon().getStartTimeMin()
                +"到" + course.getAfternoon().getEndTimeHour() + ":" + course.getAfternoon().getEndTimeMin()
        );
        System.out.println("有关的课程群信息: " + course.getGroupInformation());
    }
    public static void addCourseForClass(int ClassNum) {
        Scanner scanner = new Scanner(System.in);
        File ClassDir = Utils.join(Directories.UserFiles, "Class" + ClassNum);
        File ClassRegularTable = Utils.join(ClassDir, "RegularTable");
        RegularTable regularTable = Utils.readObject(ClassRegularTable, RegularTable.class);
        System.out.println("请输入您想为" + ClassNum +"班添加的课程(输入exit结束): ");
        System.out.println("请按照课程名称 周几(一个阿拉伯数字即可) 第几节(一个阿拉伯数字即可)的格式来输入。");
        while (scanner.hasNext()) {
            String name,Day,Seq;
            int day;
            int seq;
            try {
                name = scanner.next();
                if (name.equals("exit")) {
                    break;
                }
                Day = scanner.next();
                if (Day.equals("exit")) {
                    break;
                }
                Seq = scanner.next();
                if (Seq.equals("exit")) {
                    break;
                }
                day = Integer.parseInt(Day);
                seq = Integer.parseInt(Seq);

            }
            catch (InputMismatchException e) {
                System.out.println("您输入的格式有误，请重新输入:");
                name = scanner.next();
                if (name.equals("exit")) {
                    break;
                }
                Day = scanner.next();
                if (Day.equals("exit")) {
                    break;
                }
                Seq = scanner.next();
                if (Seq.equals("exit")) {
                    break;
                }
                day = Integer.parseInt(Day);
                seq = Integer.parseInt(Seq);
            }
            File courseFile = Utils.join(Directories.CourseRepo, name);
            if (!courseFile.exists()) {
                System.out.println("您输入的课程在课程库中不存在，请重新输入:");
            }
            else {
                Course course = Utils.readObject(courseFile, Course.class);
                regularTable.addLesson(day, seq, course);
            }
        }
        Utils.writeObject(ClassRegularTable,regularTable);
        System.out.println("添加课程成功");
    }
    public static void deleteClassForClass(int ClassNum) {
        Scanner scanner = new Scanner(System.in);
        File ClassDir = Utils.join(Directories.UserFiles, "Class" + ClassNum);
        File ClassRegularTable = Utils.join(ClassDir, "RegularTable");
        RegularTable regularTable = Utils.readObject(ClassRegularTable, RegularTable.class);
        System.out.println("请依次输入两个阿拉伯数字，代表周几和第几节课(输入exit结束)");
        while (scanner.hasNext()) {
            String day = scanner.next();
            if (day.equals("exit")) {
                break;
            }
            String seq = scanner.next();
            if (seq.equals("exit")) {
                break;
            }
            regularTable.deleteLesson(Integer.parseInt(day), Integer.parseInt(seq));
        }
        Utils.writeObject(ClassRegularTable, regularTable);
        System.out.println("删除课程成功");
    }
    @Override
    public boolean run() {
        CourseManager.Interface();
        return true;
    }
    @Override
    public void dailyRecord() {
        return;
    }
    public static void main(String[] args) {
       Course[] courses= {new Course("计算机组成原理","S208",
                new TimePair(9, 50, 12, 15),//3-5
                new TimePair(1,0,14, 35)),//6-7
        new Course("计算机网络", "N516",
                new TimePair(8,0,9,35),//1-2
                new TimePair(13,0,14,35)),//6-7
        new Course("毛泽东思想和中国特色社会主义理论体系概论","E431",
                new TimePair(9,50,11,25),//3-4
                new TimePair(14,45,16,25)),//8-9
        new Course("体育基础（男篮）","运动场",
                new TimePair(10,40,11,25),//4-5
                new TimePair(13,0,14,25)),//6-7
        new Course("形式语言与自动机", "S510",
                new TimePair(9,50,11,25),//3-4
                new TimePair(14,45,16,25)),//8-9
        new Course("面向对象程序设计Java","N201",
                new TimePair(9,50,12,15),//3-5
                new TimePair(13,0,15,30)),//6-8
        new Course("数据结构课程设计","N207",
                new TimePair(8,0,9,35),//1-2
                new TimePair(13,0,14,35)),//6-7
        new Course("面向对象程序设计C++","S301",
                new TimePair(8,0,9,35),//1-2
                new TimePair(13,0,14,35)),//6-7
        new Course("离散数学","S201",
                new TimePair(8,0,10,35),//1-3
                new TimePair(13,0,15,30)),//6-8
        new Course("数学建模","N201",
                new TimePair(9,50,12,15),//3-5
                new TimePair(13,0,15,30)),//6-8
        };
        /*for (int i = 0 ; i< courses.length ; i ++) {
            CourseManager.addNewCourse(courses[i]);
        }*/
       // deleteCourse(courses[1]);
        CourseManager.Interface();
        return;
    }
}
