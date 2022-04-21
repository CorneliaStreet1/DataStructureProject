package JHY.Activity;

import java.util.Calendar;

public class PersonalActivity extends Activity{
    String master;
    int clAss;

    public PersonalActivity() {
    }

    public PersonalActivity(String name, Calendar timeBegin, Calendar timeEnd, String master, int clAss) {
        super(name, timeBegin, timeEnd);
        this.master = master;
        this.clAss = clAss;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public int getClAss() {
        return clAss;
    }

    public void setClAss(int clAss) {
        this.clAss = clAss;
    }
}
