package JYQ.Demos;

import JYQ.Utils;

import java.io.File;
import java.io.IOException;

public class CWDDemo {
    public static void main(String[] args) throws IOException {
        System.out.println(System.getProperty("user.dir"));
        File f = new File("dummy.txt");
        f.createNewFile();
        if (f.exists()) {
            Utils.writeContents(f, "Hello World");
        }
        File d = new File("dummy");
        d.mkdir();
    }
}
