package HYH.Manager.CourseModify;

import HYH.Model.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CourseModify extends System_models{
    public static int CurrentCourse;//以后函数可以修改这个达到得到当前课程的目的

    private AddCourse addCourse;
    private DelCourse delCourse;
    private AddHomeWork addHomeWork;
    private AddBook addBook;
    private AddExam addExam;
    private AddClassCourse addClassCourse;
    private DelClassCourse delClassCourse;

    public CourseModify(String s) {
        super(s);
        addCourse=new AddCourse("增加课程");
        delCourse=new DelCourse("删除课程");
        addHomeWork=new AddHomeWork("增加作业");
        addBook=new AddBook("增加资料");
        addExam=new AddExam("增加考试");
        addClassCourse=new AddClassCourse("为班级增加课程");
        delClassCourse=new DelClassCourse("为班级删除课程");

        add_model("1",addCourse);
        add_model("2",delCourse);
        add_model("3",addHomeWork);
        add_model("4",addBook);
        add_model("5",addExam);
        add_model("6",addClassCourse);
        add_model("7",delClassCourse);
    }

    public class AddCourse extends System_model{
        class addCourse implements Boolean_model{
            @Override
            public boolean run() {
                /**/
                JYQ.CourseManager.CourseManager.addNewCourse();
                return false;
            }

            @Override
            public void dailyRecord() {

            }
        }
        Boolean_model boolean_model=new addCourse();
        public AddCourse(String s) {
            super(s);
        }

        @Override
        public void run() throws Close {
            super.run();
            boolean_model.run();
        }
    }
    public class DelCourse extends System_model{
        class delCourse implements Boolean_model{
            @Override
            public boolean run() {
                /**/
                JYQ.CourseManager.CourseManager.deleteCourse();
                return false;
            }

            @Override
            public void dailyRecord() {

            }
        }
        Boolean_model boolean_model=new delCourse();

        public DelCourse(String s) {
            super(s);
        }

        @Override
        public void run() throws Close {
            super.run();
            boolean_model.run();
        }
    }
    public class AddExam extends System_model{
        class addExam implements Boolean_model{
            @Override
            public boolean run() {
                JYQ.CourseManager.CourseManager.addExamForCourse();
                return false;
            }

            @Override
            public void dailyRecord() {

            }
        }
        Boolean_model boolean_model=new addExam();

        public AddExam(String s) {
            super(s);
        }

        @Override
        public void run() throws Close {
            super.run();
            boolean_model.run();
        }
    }
    public class AddHomeWork extends  System_model {
        class addHomeWork implements Boolean_model{
            @Override
            public boolean run() {
                JYQ.CourseManager.CourseManager.addHomeworkForCourse();
                return false;
            }

            @Override
            public void dailyRecord() {

            }
        }
        Boolean_model boolean_model=new addHomeWork();

        public AddHomeWork(String s) {
            super(s);
        }

        @Override
        public void run() throws Close {
            super.run();
            boolean_model.run();
        }
    }
    public class AddBook extends System_model {
        class addBook implements Boolean_model{
            @Override
            public boolean run() {
                System.out.println("加书等待接");
                return false;
            }

            @Override
            public void dailyRecord() {

            }
        }
        Boolean_model boolean_model=new addBook();


        public AddBook(String s) {
            super(s);
        }

        @Override
        public void run() throws Close {
            super.run();
            boolean_model.run();
        }
    }

    public class AddClassCourse extends  System_model {
        class addClassCourse implements Boolean_model{
            @Override
            public boolean run() {
                Scanner scanner=new Scanner(System.in);
                System.out.println("请输入您想添加课程的班级的序号(阿拉伯数字，不要奇怪的东西)");
                int num;
                try {
                    num = scanner.nextInt();
                }
                catch (InputMismatchException e) {
                    System.out.println("输入错误，退出");
                    return false;
                }
                JYQ.CourseManager.CourseManager.addCourseForClass(num);
                return false;
            }

            @Override
            public void dailyRecord() {

            }
        }
        Boolean_model boolean_model=new addClassCourse();

        public AddClassCourse(String s) {
            super(s);
        }

        @Override
        public void run() throws Close {
            super.run();
            boolean_model.run();
        }
    }
    public class DelClassCourse extends  System_model {
        class delClassCourse implements Boolean_model{
            @Override
            public boolean run() {
                Scanner scanner=new Scanner(System.in);
                System.out.println("请输入您想删除课程的班级的序号(阿拉伯数字，不要奇怪的东西)");
                int num;
                try {
                    num = scanner.nextInt();
                }
                catch (InputMismatchException e) {
                    System.out.println("输入错误，退出");
                    return false;
                }
                JYQ.CourseManager.CourseManager.deleteClassForClass(num);
                return false;
            }

            @Override
            public void dailyRecord() {

            }
        }
        Boolean_model boolean_model=new delClassCourse();

        public DelClassCourse(String s) {
            super(s);
        }

        @Override
        public void run() throws Close {
            super.run();
            boolean_model.run();
        }
    }


}
