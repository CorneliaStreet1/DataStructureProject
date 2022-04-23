package HYH.System_time;

import HYH.Model.*;
import HYH.System_main;
import JYQ.Utils;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class Alarms extends System_models implements Serializable {
    private static final long serialVersionUID = 1029742992;
    //闹钟类
    public enum TYPE{ONCE,DAY,WEEK;}
    public class Alarm implements Serializable{
        private Calendar calendar;
        private boolean isOn;
        private String name;
        private TYPE type;

        public Alarm(Calendar calendar, TYPE type,String name) {
            this.calendar = calendar;
            isOn=false;
            this.type = type;
            this.name=name;
        }

        public Calendar returnCalendar(){
            return calendar;
        }
        public boolean returnIsOn(){
            return isOn;
        }
        public TYPE returnType(){
            return type;
        }


        public void PrintTime(){
            SimpleDateFormat sFormat_temp=null;
            String words_type=null;
            String words_time;
            sFormat_temp=switchFomat(type);
            switch(type){
                case ONCE:words_type="一次  "; break;
                case DAY:words_type="每天  "; break;
                case WEEK:words_type="每周  "; break;
            }
            words_time=sFormat_temp.format(calendar.getTime());
            System.out.println(name+"  "+words_type+"  "+words_time);
        }
        public boolean equal(final Calendar calendar1){
            boolean isEqual=true;
            int hour=calendar.get(Calendar.HOUR_OF_DAY);
            int min=calendar.get(Calendar.MINUTE);
            int dayOfweek=calendar.get(Calendar.DAY_OF_WEEK);
            int hour1=calendar1.get(Calendar.HOUR_OF_DAY);
            int min1=calendar1.get(Calendar.MINUTE);
            int dayOfweek1=calendar1.get(Calendar.DAY_OF_WEEK);
            if(hour!=hour1||min!=min1) isEqual=false;
            if(type==TYPE.WEEK&&dayOfweek!=dayOfweek1) isEqual=false;
            return isEqual;
        }
        public void invertOn(){
            isOn=isOn==true?false:true;
        }
    }

    //闹钟管理属性
    private Alarm[] alarm_clock;
    private int alarm_num,alarm_num_max;

    //格式
    private SimpleDateFormat sFormat1,sFormat2,sFormat3;

    //子模块声明
    private Add_select add_select;
    private Del del;
    private Print print;
    private Invert invert;

    //构造函数
    public Alarms(String s){
        super(s);
        alarm_num=0;
        alarm_num_max=10;
        alarm_clock=new Alarm[alarm_num_max];
        sFormat1=new SimpleDateFormat("HH:mm");//具体某一天的闹钟格式//没计划好，先这样吧
        sFormat2=new SimpleDateFormat("HH:mm");//每天
        sFormat3=new SimpleDateFormat("E HH:mm");//每周
        add_select=new Add_select("增加闹钟");
        del=new Del("删除闹钟");
        print=new Print("打印所有闹钟");
        invert=new Invert("打开/关闭闹钟");
        add_model("1",add_select);
        add_model("2",del);
        add_model("3",print);
        add_model("4",invert);
    }

    @Override
    public void introduce_status() {
        System.out.println("当前时间："+Total_models.system_time.returnTime());
    }

    //子模块
    public class Add_select extends System_models implements Serializable{
        private Add add1,add2,add3;

        public Add_select(String s) {
            super(s);
            add1=new Add("增加一次性闹钟",TYPE.ONCE);
            add2=new Add("增加每天闹钟",TYPE.DAY);
            add3=new Add("增加每周闹钟",TYPE.WEEK);
            add_model("1",add1);
            add_model("2",add2);
            add_model("3",add3);
        }
    }

    public class Add extends System_model implements Serializable{
        TYPE type;
        public Add(String s,TYPE t) {
            super(s);
            type=t;
        }

        @Override
        public void run() throws Close {
            super.run();
            System.out.println("闹钟增加：");
            AddAlarm(type);

        }
    }

    public class Del extends System_model implements Serializable{
        public Del(String s) {
            super(s);
        }

        @Override
        public void run() throws Close {
            super.run();
            System.out.println("删除闹钟：");
            DelAlarm();
            System.out.println("\n");
        }
    }

    public class Print extends System_model implements Serializable{
        public Print(String s) {
            super(s);
        }

        @Override
        public void run() throws Close {
            super.run();
            System.out.println("闹钟列表如下：");
            PrintAlarms();
            System.out.println("\n");
        }
    }

    public class Invert extends System_model implements Serializable{
        public Invert(String s) {
            super(s);
        }

        @Override
        public void run() throws Close {
            super.run();
            System.out.println("调整闹钟开关\n闹钟列表：");
            InvertOnAlarms();
            System.out.println("已调整闹钟开关，闹钟状态如下：\n闹钟列表");
            PrintAlarms();
            System.out.println("\n");
        }
    }



    //挑选格式
    public SimpleDateFormat switchFomat(TYPE type){
        SimpleDateFormat sFormat_temp=null;
        switch (type){
            case ONCE:sFormat_temp=sFormat1; break;
            case DAY:sFormat_temp=sFormat2; break;
            case WEEK:sFormat_temp=sFormat3; break;
        }
        return sFormat_temp;
    }



    //子模块实现
    public void AddAlarm(TYPE type){
        if(alarm_num>=alarm_num_max){
            System.out.println("闹钟已经满了");
            return;
        }
        SimpleDateFormat sFormat_temp = null;
        System.out.print("请设置闹钟时间，时间格式：");
        sFormat_temp=switchFomat(type);
        System.out.println(sFormat_temp.toPattern());
        Scanner scanner=new Scanner(System.in);
        String words=scanner.nextLine();
        Calendar calendar=Calendar.getInstance();
        try {
            calendar.setTime(sFormat_temp.parse(words));
        }catch (Exception e){
            System.out.println("输入格式不对");
            return;
        }/*
        if(type!=TYPE.ONCE){

        }*/
        System.out.print("请输入闹钟名称：");
        words=scanner.nextLine();
        alarm_clock[alarm_num]=new Alarm(calendar,type,words);
        alarm_num++;
        System.out.println("已增加闹钟\n\n");
    }

    public void DelAlarm(){
        PrintAlarms();
        if(alarm_num==0){
            System.out.println("没有闹钟，不能删除");
            return;
        }
        System.out.println("请输入序号删除对应序号的闹钟");
        Scanner scanner=new Scanner(System.in);
        int i=scanner.nextInt();
        DelAlarm(i);

        return;
    }

    public void DelAlarm(int i){
        if(i>=0&&i<alarm_num){
            if(i==alarm_num) alarm_num--;
            else{
                alarm_num--;
                alarm_clock[i]=alarm_clock[alarm_num];
            }
        }
        else
            System.out.println("序号不对");
        return;
    }

    public void PrintAlarms(){
        if(alarm_num==0){
            System.out.println("当前没有闹钟");
            return;
        }
        for(int i=0;i<alarm_num;++i){
            String words=alarm_clock[i].isOn?"on   ":"off  ";
            System.out.print(i+"  "+words);
            alarm_clock[i].PrintTime();
        }
    }

    public void InvertOnAlarms(){
        PrintAlarms();
        Scanner scanner=new Scanner(System.in);
        System.out.print("请输入你想开关的闹钟的序号：");
        int i=scanner.nextInt();
        if(i>=0&&i<alarm_num) alarm_clock[i].invertOn();
        else System.out.println("序号不对");
    }

    //响铃
    public void Ring(Calendar calendar){
        for(int i=0;i<alarm_num;++i){
            if(alarm_clock[i].isOn){
                if(alarm_clock[i].equal(Total_models.system_time.returnCalendar())){
                    System.out.print("闹钟提醒： ");
                    alarm_clock[i].PrintTime();
                    System.out.println("\n");
                    if(alarm_clock[i].type==TYPE.ONCE) alarm_clock[i].invertOn();
                }
            }
        }
    }

}
