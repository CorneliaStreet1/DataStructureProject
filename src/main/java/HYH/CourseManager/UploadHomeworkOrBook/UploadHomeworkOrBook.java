package HYH.CourseManager.UploadHomeworkOrBook;

import HYH.Model.*;
import HYH.CourseManager.UploadHomeworkOrBook.UploadHomework.*;
import HYH.CourseManager.UploadHomeworkOrBook.UploadBook.*;


public class UploadHomeworkOrBook extends Total_models {
   // Boolean_model boolean_model=new test_run();
    private UploadHomework uploadHomework;
    private UploadBook uploadBook;


    public UploadHomeworkOrBook(String s) {
        super(s);
        uploadHomework=new UploadHomework("上传作业");
        uploadBook=new UploadBook("上传资料");
        add_model("1",uploadHomework);
        add_model("2",uploadBook);
    }
    public void run() throws Close{
//        dailyRecord();
        super.run();
       // boolean_model.run();
    }
}
