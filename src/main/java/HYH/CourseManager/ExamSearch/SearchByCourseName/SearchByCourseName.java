package HYH.CourseManager.ExamSearch.SearchByCourseName;

import HYH.Model.*;
public class SearchByCourseName extends System_model{
    private Search_model searchByName;

    public SearchByCourseName(String s) {
        super(s);
    }

    @Override
    public void run() throws Close {
        super.run();
        searchByName.search();
    }
}
