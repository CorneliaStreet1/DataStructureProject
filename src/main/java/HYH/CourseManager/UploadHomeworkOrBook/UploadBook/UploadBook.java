package HYH.CourseManager.UploadHomeworkOrBook.UploadBook;

import HYH.Model.*;
public class UploadBook extends System_model{
    class upload implements Boolean_model{

        @Override
        public boolean run() {
            JYQ.CourseManager.CourseManager.UploadMaterialAsStudent();
            return false;
        }

        @Override
        public void dailyRecord() {

        }
    }

    Boolean_model boolean_model=new upload();

    public UploadBook(String s) {
        super(s);
    }

    @Override
    public void run() throws Close {
        super.run();
        boolean_model.run();
    }
}
