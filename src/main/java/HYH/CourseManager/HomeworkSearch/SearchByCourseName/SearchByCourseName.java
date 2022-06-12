package HYH.CourseManager.HomeworkSearch.SearchByCourseName;

import HYH.Model.*;
public class SearchByCourseName extends System_model{
    class searchHomework implements Boolean_model{
        @Override
        public boolean run() {
            JYQ.CourseManager.CourseManager.SearchHomework();
            return false;
        }

        @Override
        public void dailyRecord() {

        }
    }


    Boolean_model boolean_model=new searchHomework();

    public SearchByCourseName(String s) {
        super(s);
    }

    public void run() throws Close {
        super.run();
        boolean_model.run();
    }
}
