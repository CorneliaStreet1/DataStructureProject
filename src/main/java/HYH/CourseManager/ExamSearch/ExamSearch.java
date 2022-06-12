package HYH.CourseManager.ExamSearch;

import HYH.Model.*;
import HYH.CourseManager.ExamSearch.SearchByCourseName.*;
import HYH.CourseManager.ExamSearch.SearchByTime.*;

public class ExamSearch extends Total_models{
    public SearchByCourseName searchByCourseName;
    public SearchByTime searchByTime;

    public ExamSearch(String s) {
        super(s);
        searchByCourseName=new SearchByCourseName("按课程名称搜索");
        //searchByTime=new SearchByTime("按时间段搜索");
        add_model("1",searchByCourseName);
        //add_model("2",searchByTime);
    }
}
