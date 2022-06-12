package HYH.CourseManager.HomeworkSearch;

import HYH.Model.*;
import HYH.CourseManager.HomeworkSearch.SearchByCourseName.*;
import HYH.CourseManager.HomeworkSearch.SearchByTimeBlock.*;
public class HomeworkSearch extends Total_models{
    SearchByCourseName searchByCourseName;
    SearchByTimeBlock searchByTimeBlock;

    public HomeworkSearch(String s) {
        super(s);
        searchByCourseName=new SearchByCourseName("按课程名称查询");
        //searchByTimeBlock=new SearchByTimeBlock("按时间段查询");
        add_model("1",searchByCourseName);
        //add_model("2",searchByTimeBlock);
    }
}
