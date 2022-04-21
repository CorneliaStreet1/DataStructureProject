package JHY.Activity;

import HYH.Information.Information;
import JHY.IrregularTable.ClassIrregularTable;

import java.util.Calendar;

public class Activity extends Information {
    String name;
    Calendar timeBegin;
    Calendar timeEnd;

    public Activity() {
    }

    public Activity(String name) {
        this.name = name;
    }

    public Activity(String name, Calendar timeBegin, Calendar timeEnd) {
        this.name = name;
        this.timeBegin = timeBegin;
        this.timeEnd = timeEnd;
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

    public boolean detectTime(Activity ac){
        if(this.timeEnd.equals(ac.timeBegin)||this.timeEnd.before(ac.timeBegin))
            return true;
        else if(this.timeBegin.equals(ac.timeEnd)||this.timeBegin.after(ac.timeEnd))
            return true;
        else
            return false;
    }
    public boolean detectTime(ClassIrregularTable c){
        if (c.getList().size()==0)
            return true;
        for(int i=0;i<c.getList().size();i++){
            if(!c.getList().get(i).detectTime(this))
                return false;
        }
        return true;
    }
    //public boolean detectTime(RegularTable)
    //public boolean detectTime()
    public boolean isRight(){
        if(this.timeBegin.before(this.timeEnd))
            return true;
        else
            return false;
    }
}
