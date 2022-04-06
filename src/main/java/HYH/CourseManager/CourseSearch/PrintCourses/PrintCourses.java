package HYH.CourseManager.CourseSearch.PrintCourses;

import HYH.Model.*;
public class PrintCourses extends System_model{
    private Void_model printCourses;

    public PrintCourses(String s) {
        super(s);
    }

    @Override
    public void run() throws Close {
        dailyRecord();
        printCourses.run();
    }
}
