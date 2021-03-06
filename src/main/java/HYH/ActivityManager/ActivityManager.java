package HYH.ActivityManager;

import HYH.Model.*;
import JHY.Activity.*;

public class ActivityManager extends Total_models{
    private AddAct addAct;
    private DelAct delAct;
    private PrintAct printAct;

    private Demo demo;

    public ActivityManager(String s) {
        super(s);
        /*addAct=new AddAct("增加活动");
        delAct=new DelAct("删除活动");
        printAct=new PrintAct("打印所有活动");
        add_model("1",addAct);
        add_model("2",delAct);
        add_model("3",printAct);*/
        demo=new Demo("课外信息管理");
        add_model("1",demo);
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

    public class AddAct extends System_model{
        Boolean_model boolean_model=new test_run();
        public AddAct(String s) {
            super(s);

        }

        @Override
        public void run() throws Close {
            super.run();
            boolean_model.run();
        }
    }
    public class DelAct extends System_model{
        Boolean_model boolean_model=new test_run();
        public DelAct(String s) {
            super(s);

        }

        @Override
        public void run() throws Close {
            super.run();
            boolean_model.run();
        }
    }
    public class PrintAct extends System_model{
        Boolean_model boolean_model=new test_run();
        public PrintAct(String s) {
            super(s);
        }

        @Override
        public void run() throws Close {
            super.run();
            boolean_model.run();
        }
    }

}
