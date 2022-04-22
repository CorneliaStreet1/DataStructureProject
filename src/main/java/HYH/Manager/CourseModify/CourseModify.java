package HYH.Manager.CourseModify;

import HYH.Model.*;

public class CourseModify extends System_models{
    public static int CurrentCourse;//以后函数可以修改这个达到得到当前课程的目的

    private AddCourse addCourse;
    private DelCourse delCourse;

    public CourseModify(String s) {
        super(s);
        addCourse=new AddCourse("增加课程");
        delCourse=new DelCourse("删除课程");
        add_model("1",addCourse);
        add_model("2",delCourse);
    }

    public class AddCourse extends System_model{
        Boolean_model boolean_model=new test_run();
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
        Boolean_model boolean_model=new test_run();

        public DelCourse(String s) {
            super(s);
        }

        @Override
        public void run() throws Close {
            super.run();
            boolean_model.run();
        }
    }
}
