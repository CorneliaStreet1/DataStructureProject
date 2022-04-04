package JYQ;

import java.io.File;

public class SerializableDemo {
    public static void main(String[] args) {
        File CWD = new File(System.getProperty("user.dir"));
        System.out.println("这是序列化的demo，运行完这个类相当于是程序运行了一次然后完全终止。这个过程做了持久化处理。");
        System.out.println("运行完这个之后，查看一下你的工作目录是不是多了一个str文件，然后去运行Des开头的那个反序列化Demo");
        String str = "这是一个序列化的demo,运行完毕之后查看工作目录有一个没有后缀名的str文件";
        File Str = new File("str");
        Utils.writeObject(Str, str);
    }
}
