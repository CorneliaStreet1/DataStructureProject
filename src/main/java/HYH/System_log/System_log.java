package HYH.System_log;
import HYH.Model.*;
public class System_log extends System_models{
    private boolean run_key;

    public System_log(String s) {
        super(s);
        run_key=true;
        super.add_model("e",new enter("登录"));
    }

    public class enter extends System_model{
        public enter(String s) {
            super(s);
        }
        public void run() throws Close {
            run_key=false;
        }
    }
    public void run() throws Close {
        run_key=true;
        while(run_key){
            super.introduce_model();
            super.select_model(super.scan());
        }
    }
}
