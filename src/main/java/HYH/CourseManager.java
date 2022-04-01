package HYH;

import HYH.Model.Close;
import HYH.Model.System_models;

public class CourseManager extends System_models{
    public CourseManager(String s) {
        super(s);
        super.add_model("1",new CourseSearch("课程查询"));
        super.add_model("2",new UploadHomeworkOrBook("上传作业或资料"));
        super.add_model("3",new ExamSearch("考试查询"));
    }


    public void run() throws Close {
        super.run();
    }
}
