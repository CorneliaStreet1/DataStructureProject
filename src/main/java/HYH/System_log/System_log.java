package HYH.System_log;

import HYH.Model.*;
import HYH.DailyRecord.*;
import JYQ.UserLogin.*;

public class System_log extends System_models{
    private boolean run_key;

    public System_log(String s) {
        super(s);
        run_key=true;
        add_model("e",new enter("跳过登录"));
        add_model("1",new user_log("登录"));
    }

    public class enter extends System_model{
        public enter(String s) {
            super(s);
        }
        public void run() throws Close {
            run_key=false;
        }
    }
    public class user_log extends System_model{
        private Boolean_model log=new WelcomeInterface();

        public user_log(String s) {
            super(s);
        }
        @Override
        public void run() throws Close {
            super.run();
            boolean log_status=log.run();
            if(log_status) run_key=false;
        }
    }
    public void run() throws Close {
//        dailyRecord();
        RecordOperate.WriteRecord("系统：进入“"+"登录"+"”模块\n");
        run_key=true;
        while(run_key){
            super.introduce_model();
            super.select_model(super.scan());
        }
    }
}
