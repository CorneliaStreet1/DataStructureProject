package HYH.Model;

import HYH.System_time.*;
public class Total_models extends System_models{//搭载了时间系统的选择模块
    public static final System_time system_time=new System_time("时间系统");

    public Total_models(String s) {
        super(s);
        super.add_model("t",system_time);
    }

    @Override
    public String scan() throws Close{
//        system_time.stopStartTime.run();
        String words;
        words=super.scan();
//        system_time.stopStartTime.run();
        return words;
    }
}
