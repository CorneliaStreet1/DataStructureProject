package HYH.System_log;
import HYH.Model.*;
public class System_log extends System_models{


    public System_log(String s) {
        super(s);
    }


    public void run() throws Close {
        while(true){
            super.introduce_model();
            super.select_model(super.scan());
        }
    }
}
