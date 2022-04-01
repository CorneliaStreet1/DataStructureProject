package HYH.CourseManager;

import HYH.Model.*;
import HYH.CourseManager.CourseSearch.*;
import HYH.CourseManager.ExamSearch.*;
import HYH.CourseManager.UploadHomeworkOrBook.*;

public class CourseManager extends Total_models{
    public CourseSearch courseSearch;
    public UploadHomeworkOrBook uploadHomeworkOrBook;
    public ExamSearch examSearch;

    public CourseManager(String s) {
        super(s);
        courseSearch=new CourseSearch("课程查询");
        uploadHomeworkOrBook=new UploadHomeworkOrBook("上传作业或资料");
        examSearch=new ExamSearch("考试查询");
        super.add_model("1",courseSearch);
        super.add_model("2",uploadHomeworkOrBook);
        super.add_model("3",examSearch);
    }


    public void run() throws Close {
        super.run();
    }
}
