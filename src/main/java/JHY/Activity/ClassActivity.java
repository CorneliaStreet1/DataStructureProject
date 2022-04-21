package JHY.Activity;

import java.util.Calendar;

public class ClassActivity extends Activity {
    int clAss;

    public ClassActivity() {
    }

    public ClassActivity(String name, Calendar timeBegin, Calendar timeEnd, int clAss) {
        super(name, timeBegin, timeEnd);
        this.clAss = clAss;
    }

    public int getClAss() {
        return clAss;
    }

    public void setClAss(int clAss) {
        this.clAss = clAss;
    }
}
