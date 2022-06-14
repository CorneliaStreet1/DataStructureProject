package JHY.Activity;

import JHY.IrregularTable;
import JHY.RegularTable;
import java.io.Serializable;
import java.util.Calendar;

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
    public boolean detectTime(IrregularTable c) {
        if (c.getList().size() == 0)
            return true;
        int seq = c.getSeq(this.timeBegin);
        if (seq != 0 && c.getList().get(seq - 1).timeBegin.equals(this.timeBegin))
            return false;
        int date = this.timeBegin.get(Calendar.DATE);
        int month = this.timeBegin.get(Calendar.MONTH);
        int year = this.timeBegin.get(Calendar.YEAR);

            for (int i = seq; i < c.getList().size() && c.getList().get(i).getTimeBegin().get(Calendar.DATE) == date
                    && c.getList().get(i).getTimeBegin().get(Calendar.MONTH) == month
                    && c.getList().get(i).getTimeBegin().get(Calendar.YEAR) == year; i++) {
                if (!c.getList().get(i).detectTime(this))
                    return false;
            }

            for (int i = seq - 1; i > -1 && c.getList().get(i).getTimeBegin().get(Calendar.DATE) == date
                    && c.getList().get(i).getTimeBegin().get(Calendar.MONTH) == month
                    && c.getList().get(i).getTimeBegin().get(Calendar.YEAR) == year; i--) {
                if (!c.getList().get(i).detectTime(this))
                    return false;
            }

        return true;
    }
    //活动和所有课程检测
    public boolean detectTime(RegularTable table){
        int dayOfWeek=(timeBegin.get(Calendar.DAY_OF_WEEK)+5)%7;
        for(int i=0;i<11;i++){
            if(table.getTable()[dayOfWeek][i]!=null){
                if(!detectTime(i))
                    return false;
            }
        }
        return true;
    }

    public boolean detectTime(int seq){
        Calendar tb=Calendar.getInstance();
        Calendar te=Calendar.getInstance();
        switch(seq){
            case 0: {
                tb.set(timeBegin.get(Calendar.YEAR), timeBegin.get(Calendar.MONTH),
                        timeBegin.get(Calendar.DATE), 8, 00, 0);
                te.set(timeBegin.get(Calendar.YEAR), timeBegin.get(Calendar.MONTH),
                        timeBegin.get(Calendar.DATE), 8, 45, 0);
            }
            break;
            case 1: {
                tb.set(timeBegin.get(Calendar.YEAR), timeBegin.get(Calendar.MONTH),
                        timeBegin.get(Calendar.DATE), 8, 50, 0);
                te.set(timeBegin.get(Calendar.YEAR), timeBegin.get(Calendar.MONTH),
                        timeBegin.get(Calendar.DATE), 9, 35, 0);
            }
            break;
            case 2: {
                tb.set(timeBegin.get(Calendar.YEAR), timeBegin.get(Calendar.MONTH),
                        timeBegin.get(Calendar.DATE), 9, 50, 0);
                te.set(timeBegin.get(Calendar.YEAR), timeBegin.get(Calendar.MONTH),
                        timeBegin.get(Calendar.DATE), 10, 35, 0);
            }
            break;
            case 3: {
                tb.set(timeBegin.get(Calendar.YEAR), timeBegin.get(Calendar.MONTH),
                        timeBegin.get(Calendar.DATE), 10, 40, 0);
                te.set(timeBegin.get(Calendar.YEAR), timeBegin.get(Calendar.MONTH),
                        timeBegin.get(Calendar.DATE), 11, 25, 0);
            }
            break;
            case 4: {
                tb.set(timeBegin.get(Calendar.YEAR), timeBegin.get(Calendar.MONTH),
                        timeBegin.get(Calendar.DATE), 11, 30, 0);
                te.set(timeBegin.get(Calendar.YEAR), timeBegin.get(Calendar.MONTH),
                        timeBegin.get(Calendar.DATE), 12, 15, 0);
            }
            break;
            case 5: {
                tb.set(timeBegin.get(Calendar.YEAR), timeBegin.get(Calendar.MONTH),
                        timeBegin.get(Calendar.DATE), 13, 00, 0);
                te.set(timeBegin.get(Calendar.YEAR), timeBegin.get(Calendar.MONTH),
                        timeBegin.get(Calendar.DATE), 13, 45, 0);
            }
            break;
            case 6: {
                tb.set(timeBegin.get(Calendar.YEAR), timeBegin.get(Calendar.MONTH),
                        timeBegin.get(Calendar.DATE), 13, 50, 0);
                te.set(timeBegin.get(Calendar.YEAR), timeBegin.get(Calendar.MONTH),
                        timeBegin.get(Calendar.DATE), 14, 35, 0);
            }
            break;
            case 7: {
                tb.set(timeBegin.get(Calendar.YEAR), timeBegin.get(Calendar.MONTH),
                        timeBegin.get(Calendar.DATE), 14, 45, 0);
                te.set(timeBegin.get(Calendar.YEAR), timeBegin.get(Calendar.MONTH),
                        timeBegin.get(Calendar.DATE), 15, 30, 0);
            }
            break;
            case 8: {
                tb.set(timeBegin.get(Calendar.YEAR), timeBegin.get(Calendar.MONTH),
                        timeBegin.get(Calendar.DATE), 15, 40, 0);
                te.set(timeBegin.get(Calendar.YEAR), timeBegin.get(Calendar.MONTH),
                        timeBegin.get(Calendar.DATE), 16, 25, 0);
            }
            break;
            case 9: {
                tb.set(timeBegin.get(Calendar.YEAR), timeBegin.get(Calendar.MONTH),
                        timeBegin.get(Calendar.DATE), 16, 35, 0);
                te.set(timeBegin.get(Calendar.YEAR), timeBegin.get(Calendar.MONTH),
                        timeBegin.get(Calendar.DATE), 17, 20, 0);
            }
            break;
            case 10: {
                tb.set(timeBegin.get(Calendar.YEAR), timeBegin.get(Calendar.MONTH),
                        timeBegin.get(Calendar.DATE), 17, 25, 0);
                te.set(timeBegin.get(Calendar.YEAR), timeBegin.get(Calendar.MONTH),
                        timeBegin.get(Calendar.DATE), 18, 10, 0);
            }
            break;
        }
        if(timeEnd.equals(tb)||timeEnd.before(tb))
            return true;
        else if(timeBegin.equals(te)||this.timeBegin.after(te))
            return true;
        else
            return false;
    }

    public void output(){
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
