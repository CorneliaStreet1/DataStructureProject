package HYH.Manager.ActivityModify;

import HYH.Model.*;

public class ActivityModify extends System_models{
    private Demo demo;

    public ActivityModify(String s) {
        super(s);
        demo=new Demo("修改活动");
        add_model("1",demo);
    }

    @Override
    public void run() throws Close {
        super.run();
    }

    public class Demo extends System_model {
        Boolean_model boolean_model=new JHY.Activity.ActivityManager();
        public Demo(String s) {
            super(s);
        }

        @Override
        public void run() throws Close {
            super.run();
            boolean_model.run();
        }
    }
}
