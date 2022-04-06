package HYH.CourseManager.CourseSearch.SearchByName;

import HYH.Model.*;
public class SearchByName extends System_model{
    private Search_model search_model;

    public SearchByName(String s) {
        super(s);
    }

    @Override
    public void run() throws Close {
        dailyRecord();
        search_model.search();
    }
}
