package HYH.System_time;

import HYH.Model.*;
import HYH.System_main;
import JYQ.Utils;

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
    public Alarms alarms;

    //时间系统时间属性
    private Calendar calendar;
    //时间系统文件及路径
    private File time_init;
    private String path;
    //时间格式
    private SimpleDateFormat simpleDateFormat_1;
    private SimpleDateFormat simpleDateFormat_2;
    //暂停状态
    private boolean stop_status;
    private int stop_layerNum;
    //暂停锁
    private Object stop;
    //流速倍率
    private float realSecond_to_thisSecond;
    //闹钟文件及路径
    String userPath;
    File alarmsFile;


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
        add_model("5",alarms);

        stop_status=true;
        stop_layerNum=0;
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
    //闹钟初始化
    public void Init(){
        if(System_main.CurrentUserClass==-1){
            userPath=null;
            alarmsFile=null;
            alarms=null;
            replace_model("5",alarms);
            return;
        }
        userPath="./UserFiles/Class"+System_main.CurrentUserClass+"/"+System_main.CurrentUserName;
        alarmsFile=new File(userPath+"/alarms");
        if(!alarmsFile.exists()){
            try {
                alarmsFile.createNewFile();
                alarms=new Alarms("闹钟设置");
                replace_model("5",alarms);
                Utils.writeObject(alarmsFile,alarms);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("闹钟文件创建失败");
            }
        }
        else{
            alarms=Utils.readObject(alarmsFile,Alarms.class);
            replace_model("5",alarms);
        }
    }
    //闹钟持久化
    public void Store(){
        if(System_main.CurrentUserClass==-1) return;
        Utils.writeObject(alarmsFile,alarms);
    }

    //基础功能
    public String returnTime() {
        return simpleDateFormat_1.format(calendar.getTime());
    }
    public Calendar returnCalendar(){
        return calendar;
    }
    //介绍状态
    public void introduce_status() {
        String words=null;
        if(stop_status) words="是";
        else words="否";
        System.out.println("时间的暂停状态是否为暂停："+words);
        words=isRealStop()?"是":"否";
        System.out.println("当前时间是否暂停："+words);
        System.out.println("当前时间速率（n hour/1s）:  "+(realSecond_to_thisSecond/3600.0));
        System.out.println("当前时间："+simpleDateFormat_1.format(calendar.getTime()));
    }
    //基础功能
    public void zeroTime(){
        calendar.set(2022,1,28,0,0);
    }
    public boolean isStop(){
        return stop_status;
    }
    public boolean isRealStop(){
        if(stop_status||stop_layerNum>0) return true;
        else return false;
    }

    //线程主模块
    public class TimeRun implements Runnable{
        private boolean run_key;//控制时间结束的标志

        public TimeRun(){
            run_key=true;
        }
        public void run() {//时间线程运行
            int add=0;
            float second=0;
            while(run_key){
                try {
//                    System.out.println(simpleDateFormat_1.format(calendar.getTime()));

                    Thread.sleep(1000);
                    //---------
                    if(stop_status||stop_layerNum>0){
                        synchronized (stop){
                            stop.wait();
                        }
                    }
                    //--------
                    second+=realSecond_to_thisSecond;
                    add=(int)second;
                    second-=add;
                    for(int i=0;i<add;++i) {
                        calendar.add(Calendar.SECOND, 1);
                        if(calendar.get(Calendar.SECOND)==0){
                            if(alarms!=null) alarms.Ring(calendar);
                        }
                    }
//                    if(stop_status||stop_layerNum>0){
//                        synchronized (stop){
//                            stop.wait();
//                        }
//                    }
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
    //暂停开始时间状态
    public class StopStartTime extends System_model{
        public StopStartTime(String s) {
            super(s);
        }

        public void run(){
            stop_status=stop_status==true?false:true;
            if(stop_layerNum==0&&stop_status==false){
                synchronized (stop){
                    stop.notify();
                }
            }
        }
    }

    public void AddStopLayer(){//add和dec一定要连着用
        stop_layerNum++;
    }
    public void DecStopLayer(){//同上
        if(stop_layerNum==0){
            System.out.println("已经不能释放停止层了");
            return;
        }
        stop_layerNum--;
        if(stop_layerNum==0&&stop_status==false){
            synchronized (stop){
                stop.notify();
            }
        }
    }
    public void ClearStopLayer(){//慎用
        if(stop_layerNum!=0){
            stop_layerNum=0;
            if(stop_status==false){
                synchronized (stop){
                    stop.notify();
                }
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
//            boolean stop_key=stop_status;
//            if(!stop_key) stopStartTime.run();
            Scanner scan=new Scanner(System.in);
            realSecond_to_thisSecond=scan.nextFloat()*3600;
//            if(!stop_key) stopStartTime.run();
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

    @Override
    public void run() throws Close {
        AddStopLayer();
        super.run();
        DecStopLayer();
    }
}
