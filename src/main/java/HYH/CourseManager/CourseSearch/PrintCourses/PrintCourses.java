package HYH.CourseManager.CourseSearch.PrintCourses;

import HYH.Model.*;
public class PrintCourses extends System_model{
    Boolean_model boolean_model=new search();

    public PrintCourses(String s) {
        super(s);
    }

    public void run() throws Close {
        super.run();
        boolean_model.run();
    }
}
