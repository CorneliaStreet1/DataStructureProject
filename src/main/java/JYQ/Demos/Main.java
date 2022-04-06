package JYQ.Demos;

import JYQ.Utils;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        Demo demo = new Demo("这是一个demo");
        File CWD = new File(System.getProperty("user.dir"));
        File file = Utils.join(CWD, "Demo"); //CWd/Demo;
        Demo newDemo = Utils.readObject(file, Demo.class);
        newDemo.PrintMessage();
    }
}
