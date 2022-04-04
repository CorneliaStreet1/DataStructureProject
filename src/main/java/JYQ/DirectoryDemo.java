package JYQ;

import java.io.File;
import java.util.List;

public class DirectoryDemo {
    public static void main(String[] args) {
        File CWD = new File(System.getProperty("user.dir"));
        List<String> files = Utils.plainFilenamesIn(CWD);
        System.out.println("处于工作目录的非文件夹文件有：" + files);
        List<String> files2 = Utils.plainFilenamesIn(CWD.toString() + "\\src\\main\\java\\JYQ");
        System.out.println("处于JYQ文件夹中的文件有：" + files2);
    }
}
