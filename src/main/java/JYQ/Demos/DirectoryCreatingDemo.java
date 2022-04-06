package JYQ.Demos;

import JYQ.Utils;

import java.io.File;

public class DirectoryCreatingDemo {
    public static final File CWD = new File(System.getProperty("user.dir"));
    public static final File UserFiles = Utils.join(CWD, "UserFiles");

    public static void main(String[] args) {
        File User2 = Utils.join(UserFiles, "User2");
        User2.mkdir();
        File file = Utils.join(User2, "Data");
        file.mkdir();
        Demo demo = new Demo("114514");
        File Demo = Utils.join(file, "Demo");
        Utils.writeObject(Demo, demo);
        Demo read = Utils.readObject(Demo, JYQ.Demos.Demo.class);
        read.PrintMessage();
    }
}
