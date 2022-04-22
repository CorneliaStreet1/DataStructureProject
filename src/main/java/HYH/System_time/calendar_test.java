package HYH.System_time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class calendar_test {
    public Calendar calendar;

    public calendar_test() {
        calendar=Calendar.getInstance();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("HH:mm");
        try {
            calendar.setTime(simpleDateFormat.parse("12:13"));
            calendar.set(Calendar.MONTH,1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public static void main(String arg[]){
        calendar_test c=new calendar_test();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMdd HH:mm");
        System.out.println(simpleDateFormat.format(c.calendar.getTime()));
    }
}
