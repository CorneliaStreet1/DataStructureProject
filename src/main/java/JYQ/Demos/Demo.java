package JYQ.Demos;

import java.io.Serializable;

public class Demo implements Serializable {
    public String message;
    public Demo(String st) {
        message = st;
    }
    public void PrintMessage() {
        System.out.println(message);
    }
}
