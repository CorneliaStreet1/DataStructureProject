package HYH.CourseManager.BookSearch.SearchByCourseName;

import HYH.Model.*;
public class SearchByCourseName extends System_model{
//    Search_model search_model;

    class searchBook implements Boolean_model{
        @Override
        public boolean run() {
            JYQ.CourseManager.CourseManager.CheckoutMaterialAsStudent();
            return false;
        }

        @Override
        public void dailyRecord() {

        }
    }
    Boolean_model boolean_model=new searchBook();

    public SearchByCourseName(String s) {
        super(s);
    }

    public void run() throws Close {
        super.run();
//        search_model.run();
        boolean_model.run();
    }
}
