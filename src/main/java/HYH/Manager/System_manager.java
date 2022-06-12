package HYH.Manager;

import HYH.Model.*;
import HYH.Manager.CourseModify.*;
import HYH.ActivityManager.*;

public class System_manager extends System_models{
    CourseModify courseModify;
    ActivityManager activityManager;

    public System_manager(String s) {
        super(s);
        courseModify=new CourseModify("修改课程");
        activityManager=new ActivityManager("修改活动");
        add_model("1",courseModify);
        add_model("2",activityManager);
    }

}
