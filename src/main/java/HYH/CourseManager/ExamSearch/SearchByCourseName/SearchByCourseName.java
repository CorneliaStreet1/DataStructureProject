package HYH.CourseManager.ExamSearch.SearchByCourseName;

import HYH.Model.*;
public class SearchByCourseName extends System_model{
    class searchExam implements Boolean_model{
        @Override
        public boolean run() {
            JYQ.CourseManager.CourseManager.SearchExam();
            return false;
        }

        @Override
        public void dailyRecord() {

        }
    }

    Boolean_model boolean_model=new searchExam();
    public SearchByCourseName(String s) {
        super(s);
    }

    public void run() throws Close {
        super.run();
        boolean_model.run();
    }
}
