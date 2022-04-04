package JYQ.Demos;

import JYQ.Utils;

import java.io.File;
public class DuplicateCheckingDemo {
    public static void main(String[] args) {
        File CWD = new File(System.getProperty("user.dir"));
        File file1 = Utils.join(CWD, "Test1.txt");
        File file2 = Utils.join(CWD, "Test2.txt");
        File result = Utils.join(CWD, "Result.txt");

    }
}
