package JYQ.Demos;

import java.io.Serializable;

public class Demo implements Serializable {
    public String meassage;
    public Demo(String st) {
        meassage = st;
    }
    public void PrintMessage() {
        System.out.println(meassage);
    }
}
