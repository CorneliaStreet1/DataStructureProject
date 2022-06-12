package HYH.CourseManager.BookSearch;

import HYH.Model.*;
import HYH.CourseManager.BookSearch.SearchByCourseName.*;
import HYH.CourseManager.BookSearch.SearchByTimeBlock.*;

public class BookSearch extends Total_models{
    public SearchByCourseName searchByCourseName;
    public  SearchByTimeBlock searchByTimeBlock;

    public BookSearch(String s) {
        super(s);
        searchByCourseName=new SearchByCourseName("按课程名称搜索");
        //searchByTimeBlock=new SearchByTimeBlock("按时间段搜索");
        add_model("1",searchByCourseName);
        //add_model("2",searchByTimeBlock);
    }
}
