package HYH;

import java.util.HashMap;
import java.util.Scanner;

public class System_model {
    private String info_of_model;//这个模块是功能信息，用于提示用户选择

    public System_model(String s){//每个模块都有一个关闭选项回到上一个层级
        info_of_model=s;
    }

    public String info(){//返回功能信息
        return info_of_model;
    }

    public void run() throws Close{//运行该模块

    }
}
