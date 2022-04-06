package HYH.System_time;

import HYH.Model.*;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class System_time extends System_models{
    public StopStartTime stopStartTime;
    public SetRate setRate;
    public TimeRun timeRun;
    public SetZero setZero;
    public SetTime setTime;

    private Calendar calendar;
    private File time_init;
    private String path;
    private SimpleDateFormat simpleDateFormat_1;
    private SimpleDateFormat simpleDateFormat_2;
    private boolean stop_status;
    private Object stop;
    private int realSecond_to_thisSecond;

    //初始化
    public System_time(String s)  {
        super(s);
        stopStartTime=new StopStartTime("暂停/开始时间");
        setRate=new SetRate("设置时间速率");
        setZero=new SetZero("还原时间至开学");
        setTime=new SetTime("设置时间");
        timeRun=new TimeRun();
        super.add_model("1",stopStartTime);
        super.add_model("2",setRate);
        super.add_model("3",setZero);
        super.add_model("4",setTime);

        stop_status=true;
        stop=new Object();
        realSecond_to_thisSecond=60*60;
        simpleDateFormat_1=new SimpleDateFormat("yyyy/MM/dd E HH:mm");
        simpleDateFormat_2=new SimpleDateFormat("yyyy/MM/dd HH:mm");
        calendar=Calendar.getInstance();
        path="./src/main/java/HYH/System_time/Time_init.txt";
        time_init=new File(path);
        create_file();
        String words=read_file();
        try {
            calendar.setTime(simpleDateFormat_1.parse(words));
        }catch (Exception e){
            System.out.println("Time_init文件内容格式不对");
        }
        Thread time_thread=new Thread(timeRun,"时间线程");
        time_thread.start();
    }
    private void create_file(){
        if(!time_init.exists()){
            zeroTime();
            String words=simpleDateFormat_1.format(calendar.getTime());
           try {
               time_init.createNewFile();
               write_file(words);
           }catch (Exception e){
               System.out.println("文件出错了");
           }
        }
    }
    private void write_file(String words){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(time_init.getPath()));
            writer.write(words);
            writer.flush();
            writer.close();
        }catch (Exception e){
            System.out.println("文件写入出错");
        }
    }
    private String read_file(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(time_init.getPath()));
            return reader.readLine();
        }catch (Exception e){
            System.out.println("文件读出出错");
        }
        return "文件读入出错";
    }

    //基础功能
    public String returnTime() {
        return simpleDateFormat_1.format(calendar.getTime());
    }
    public void introduce_status() {
        String words=null;
        if(stop_status) words="是";
        else words="否";
        System.out.println("时间是否暂停："+words);
        System.out.println("当前时间速率（n hour/1s）:  "+(realSecond_to_thisSecond/3600.0));
        System.out.println("当前时间："+simpleDateFormat_1.format(calendar.getTime()));
    }
    public void zeroTime(){
        calendar.set(2022,1,28,0,0);
    }
    public boolean isStop(){
        return stop_status;
    }

    //子模块
    public class TimeRun implements Runnable{
        private boolean run_key;//控制时间结束的标志

        public TimeRun(){
            run_key=true;
        }
        public void run() {//时间线程运行
            while(run_key){
                try {
//                    System.out.println(simpleDateFormat_1.format(calendar.getTime()));
                    Thread.sleep(1000);
                    calendar.add(Calendar.SECOND,realSecond_to_thisSecond);
                    if(stop_status){
                        synchronized (stop){
                            stop.wait();
                        }
                    }
                }catch (Exception e){
                    System.out.println("时间运行出问题了");
                }
            }
        }
        public void end(){//最后结束时间的方法
            run_key=false;
            write_file(returnTime());
        }
    }//时间线程
    public class StopStartTime extends System_model{
        public StopStartTime(String s) {
            super(s);
        }

        public void run(){
            if(stop_status){
                synchronized (stop){
                    stop.notify();
                }
                stop_status=false;
            }
            else{
                stop_status=true;
            }
        }
    }
    public class SetRate extends System_model{
        public SetRate(String s) {
            super(s);
        }

        @Override
        public void run(){
            System.out.println("当前模块："+super.info());
            System.out.println("请输入一秒对应几小时：");
            boolean stop_key=stop_status;
            if(!stop_key) stopStartTime.run();
            Scanner scan=new Scanner(System.in);
            realSecond_to_thisSecond=scan.nextInt()*3600;
            if(!stop_key) stopStartTime.run();
        }
    }
    public class SetZero extends System_model{
        public SetZero(String s) {
            super(s);
        }

        public void run(){
            zeroTime();
        }
    }
    public class SetTime extends System_model{
        public SetTime(String s) {
            super(s);
        }

        public void run(){
            System.out.println("请按如下格式输入：yyyy/MM/dd HH:mm");
            Scanner scan=new Scanner(System.in);
            boolean key=true;
            while (key) {
                String words = scan.nextLine();
                try {
                    calendar.setTime(simpleDateFormat_2.parse(words));
                    key=false;
                } catch (ParseException e) {
                    e.printStackTrace();
                    System.out.println("输入格式不对");
                    System.out.println("请按如下格式输入：yyyy/MM/dd HH:mm");
                }
            }
        }
    }
}
