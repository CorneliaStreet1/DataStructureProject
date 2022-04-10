package HYH.CourseManager.BookSearch;

import HYH.Model.*;
import HYH.CourseManager.BookSearch.SearchByUploadTime.*;
import HYH.CourseManager.BookSearch.SearchByCourseName.*;

public class BookSearch extends Total_models{
    public SearchByUploadTime searchByUploadTime;
    public SearchByCourseName searchByCourseName;

    public BookSearch(String s) {
        super(s);
        searchByCourseName=new SearchByCourseName("按课程名称搜索");
        searchByUploadTime=new SearchByUploadTime("按上传时间搜索");
        add_model("1",searchByCourseName);
        add_model("2",searchByUploadTime);
    }
}
