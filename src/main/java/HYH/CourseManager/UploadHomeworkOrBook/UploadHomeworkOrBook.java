package HYH.CourseManager.UploadHomeworkOrBook;

import HYH.Model.*;


public class UploadHomeworkOrBook extends Total_models {
    Boolean_model boolean_model=new test_run();
    public UploadHomeworkOrBook(String s) {
        super(s);
    }
    public void run() throws Close{
//        dailyRecord();
        super.run();
        boolean_model.run();
    }
}
