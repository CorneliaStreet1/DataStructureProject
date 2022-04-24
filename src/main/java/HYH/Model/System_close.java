package HYH.Model;

public class System_close extends System_model{
    public System_close(String s) {
        super(s);
    }

    public void run() throws Close{
//        dailyRecord();
        super.run();
        throw new Close();
    }
}
