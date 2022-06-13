package HYH.CourseManager.UploadHomeworkOrBook.UploadHomework;

import HYH.Model.*;
public class UploadHomework extends System_model{
    class upload implements Boolean_model{

        @Override
        public boolean run() {
            JYQ.CourseManager.CourseManager.uploadHomework();
            return false;
        }

        @Override
        public void dailyRecord() {

        }
    }

    Boolean_model boolean_model=new upload();

    public UploadHomework(String s) {
        super(s);
    }

    @Override
    public void run() throws Close {
        super.run();
        boolean_model.run();
    }
}
