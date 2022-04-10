package HYH.CourseManager;

import HYH.Model.*;
import HYH.CourseManager.CourseSearch.*;
import HYH.CourseManager.ExamSearch.*;
import HYH.CourseManager.UploadHomeworkOrBook.*;
import HYH.CourseManager.HomeworkSearch.*;
import HYH.CourseManager.BookSearch.*;

public class CourseManager extends Total_models{
    public CourseSearch courseSearch;
    public UploadHomeworkOrBook uploadHomeworkOrBook;
    public ExamSearch examSearch;
    public BookSearch bookSearch;
    public HomeworkSearch homeworkSearch;

    public CourseManager(String s) {
        super(s);
        courseSearch=new CourseSearch("课程查询");
        uploadHomeworkOrBook=new UploadHomeworkOrBook("上传作业或资料");
        examSearch=new ExamSearch("考试查询");
        bookSearch=new BookSearch("资料查询");
        homeworkSearch=new HomeworkSearch("作业查询");
        add_model("1",courseSearch);
        add_model("2",uploadHomeworkOrBook);
        add_model("3",examSearch);
        add_model("4",bookSearch);
        add_model("5",homeworkSearch);
    }


    public void run() throws Close {
        super.run();
    }
}
