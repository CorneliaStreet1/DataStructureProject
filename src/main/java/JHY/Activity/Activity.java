package JHY.Activity;

import HYH.Information.Information;
import JHY.IrregularTable;
import JHY.IrregularTable;

import java.io.Serializable;
import java.util.Calendar;
//extens
public class Activity implements Serializable {

    private static final long serialVersionUID=333L;
//可以重名
    String name;
    Calendar timeBegin;
    Calendar timeEnd;
    String address;

    public Activity() {
    }

    public Activity(String name) {
        this.name = name;
    }

    public Activity(String name,Calendar timeBegin, Calendar timeEnd,String address) {
        this.name = name;
        this.timeBegin = timeBegin;
        this.timeEnd = timeEnd;
        this.address=address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getTimeBegin() {
        return timeBegin;
    }

    public void setTimeBegin(Calendar timeBegin) {
        this.timeBegin = timeBegin;
    }

    public Calendar getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Calendar timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    //冲突检测,true无冲突,false有冲突
    //活动和活动检测
    public boolean detectTime(Activity ac){
        if(this.timeEnd.equals(ac.timeBegin)||this.timeEnd.before(ac.timeBegin))
            return true;
        else if(this.timeBegin.equals(ac.timeEnd)||this.timeBegin.after(ac.timeEnd))
            return true;
        else
            return false;
    }
    //活动和所有活动检测
    public boolean detectTime(IrregularTable c){
        if (c.getList().size()==0)
            return true;
        for(int i=0;i<c.getList().size();i++){
            if(!c.getList().get(i).detectTime(this))
                return false;
        }
        return true;
    }
    //活动和所有课程检测
    //public boolean detectTime(RegularTable)
    //添加课程,和全部活动检测
    //public static boolean detectTime()

    public void output(){//具体格式要改
        System.out.println();
        System.out.println("活动名称:"+ this.getName());
        System.out.println("活动地点:"+this.getAddress());
        System.out.println("开始时间:"+this.getTimeBegin().get(Calendar.YEAR)+"年"+(this.getTimeBegin().get(Calendar.MONTH)+1)+
                "月"+this.getTimeBegin().get(Calendar.DAY_OF_MONTH)+"日"+","+this.getTimeBegin().get(Calendar.HOUR_OF_DAY)+
                ":"+this.getTimeBegin().get(Calendar.MINUTE)+":"+this.getTimeBegin().get(Calendar.SECOND));
        System.out.println("结束时间:"+this.getTimeEnd().get(Calendar.YEAR)+"年"+(this.getTimeEnd().get(Calendar.MONTH)+1)+
                "月"+this.getTimeEnd().get(Calendar.DAY_OF_MONTH)+"日"+","+this.getTimeEnd().get(Calendar.HOUR_OF_DAY)+
                ":"+this.getTimeEnd().get(Calendar.MINUTE)+":"+this.getTimeEnd().get(Calendar.SECOND));
    }

    public void outputTime(){
        System.out.print("从 "+this.getTimeBegin().get(Calendar.YEAR)+"年"+(this.getTimeBegin().get(Calendar.MONTH)+1)+
                "月"+this.getTimeBegin().get(Calendar.DAY_OF_MONTH)+"日"+","+this.getTimeBegin().get(Calendar.HOUR_OF_DAY)+
                ":"+this.getTimeBegin().get(Calendar.MINUTE)+":"+this.getTimeBegin().get(Calendar.SECOND));
        System.out.println("到"+this.getTimeEnd().get(Calendar.YEAR)+"年"+(this.getTimeEnd().get(Calendar.MONTH)+1)+
                "月"+this.getTimeEnd().get(Calendar.DAY_OF_MONTH)+"日"+","+this.getTimeEnd().get(Calendar.HOUR_OF_DAY)+
                ":"+this.getTimeEnd().get(Calendar.MINUTE)+":"+this.getTimeEnd().get(Calendar.SECOND));
    }
}
