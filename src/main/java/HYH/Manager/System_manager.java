package HYH.Manager;

import HYH.Model.*;
import HYH.Manager.CourseModify.*;

public class System_manager extends System_models{
    CourseModify courseModify;

    public System_manager(String s) {
        super(s);
        courseModify=new CourseModify("修改课程");
        add_model("1",courseModify);
    }

}
