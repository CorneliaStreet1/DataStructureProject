package HYH.Model;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class System_model implements Serializable {//所有模块的父类或者没有子模块的功能模块继承
    private String info_of_model;//这个模块是功能信息，用于提示用户选择
    private static String record_path="./src/main/java/HYH/DailyRecord";
    private static File record=new File(record_path+"/DailyRecord.txt");

    public System_model(String s){//每个模块都有一个关闭选项回到上一个层级
        info_of_model=s;
    }

    public String info(){//返回功能信息
        return info_of_model;
    }

    public void run() throws Close{//运行该模块
        dailyRecord();
    }
    public void dailyRecord(){
        if(!record.exists()){
            try {
                record.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("日志文件创建出错");
            }
        }
        try {
            BufferedWriter writer=new BufferedWriter(new FileWriter(record.getPath(),true));
            writer.write("系统：进入“"+info_of_model+"”模块\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("日志写入出错");
        }
    }
    public static void recordClean(){
        if(record.exists()){
            BufferedWriter writer= null;
            try {
                writer = new BufferedWriter(new FileWriter(record.getPath()));
                writer.write("");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("日志写入出错");
            }

        }
    }
}
