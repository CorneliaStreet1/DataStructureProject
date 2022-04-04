package JYQ;

import java.io.File;

public class DeserializeDemo {
    public static void main(String[] args) {
        File CWD = new File(System.getProperty("user.dir"));
        File str = new File("str");
        String string = Utils.readObject(str, String.class);
        System.out.println("这是反序列化的demo，在这里运行一次，相当于是做了持久化工作之后的第一次运行。");
        System.out.println("下一行是从str文件中读取到的内容：");
        System.out.println(string);
    }
}
