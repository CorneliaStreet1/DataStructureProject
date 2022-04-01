package HYH.System_time;

import HYH.Model.*;
public class System_time extends System_models{
    public ReturnTime returnTime;
    public StopStartTime stopStartTime;
    public SetRate setRate;
    public PrintTime printTime;

    public System_time(String s) {
        super(s);
        returnTime=new ReturnTime("返回时间");
        stopStartTime=new StopStartTime("暂停/开始时间");
        setRate=new SetRate("设置时间速率");
        printTime=new PrintTime("显示当前时间");
        super.add_model("1",stopStartTime);
        super.add_model("2",setRate);
        super.add_model("3",printTime);

    }
    public String returnTime() throws Close{
        String words=new String();
        returnTime.run(words);
        return words;
    }

    @Override
    public String scan() throws Close{
        String words;
        stopStartTime.run();
        words=super.scan();
        stopStartTime.run();
        return words;
    }
}
