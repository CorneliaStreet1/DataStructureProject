package HYH.DailyRecord;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class RecordOperate {
    private static String record_path="./src/main/java/HYH/DailyRecord";
    private static File record=new File(record_path+"/DailyRecord.txt");

    public static void WriteRecord(String words){
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
            writer.write(words);
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
                System.out.println("日志清空出错");
            }

        }
    }
}
