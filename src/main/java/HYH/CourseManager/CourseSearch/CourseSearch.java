package HYH.CourseManager.CourseSearch;

import HYH.Model.*;
import HYH.CourseManager.CourseSearch.PrintCourses.*;
import HYH.CourseManager.CourseSearch.SearchByName.*;

public class CourseSearch extends Total_models{
    public PrintCourses printCourses;
    public SearchByName searchByName;

    public CourseSearch(String s) {
        super(s);
        printCourses=new PrintCourses("打印课表");
        searchByName=new SearchByName("按课程名称查询");
        add_model("1",printCourses);
        add_model("2",searchByName);
    }
}
