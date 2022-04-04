package JYQ;

import java.io.File;
import java.util.Arrays;

public class HasingDemo {
    public static void main(String[] args) {
        File CWD = new File(System.getProperty("user.dir"));
        System.out.println("要得到一个文件的哈希值，第一步是把它的内容读取为字节数组");
        File file = Utils.join(CWD,".gitignore");
        byte[] contents = Utils.readContents(file);
        System.out.println("第二步是调用给定的sha1函数");
        String HashValue = Utils.sha1((Object) contents);
        System.out.println("以下是.gitigore文件的哈希值：");
        System.out.println(HashValue);
    }
}
