package HYH.Model;

public class test_run implements Boolean_model{
    @Override
    public boolean run() {
        System.out.println("已运行该模块");
        return false;
    }

    @Override
    public void dailyRecord() {
        return;
    }
}
