package HYH.MultWayMap;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import HYH.Model.*;
import org.apache.lucene.analysis.TokenStreamToAutomaton;

public class SelectBus {
    private Calendar periodStartTime,periodEndTime;
    private int periodMin;
    private int periodCost;

    private ArrayList<Calendar> specialTime;
    private int specialCost;

    SelectBus(){
        SimpleDateFormat sFormat=new SimpleDateFormat("HH:mm");
        periodStartTime=Calendar.getInstance();
        periodEndTime=Calendar.getInstance();
        periodMin=15;
        periodCost=4800;

        specialTime=new ArrayList<>();
        specialCost=3600;
        try {
            periodStartTime.setTime(sFormat.parse("7:00"));
            periodEndTime.setTime(sFormat.parse("18:00"));

            addSpecialCalendar("6:55");
            addSpecialCalendar("9:25");
            addSpecialCalendar("12:20");
            addSpecialCalendar("17:10");
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("FormatError");
        }
    }

    void addSpecialCalendar(String time) throws ParseException {
        SimpleDateFormat sFormat=new SimpleDateFormat("HH:mm");
        Calendar tempCalendar1=Calendar.getInstance();
        tempCalendar1.setTime(sFormat.parse(time));
        specialTime.add(tempCalendar1);
    }
    int run(){
        Calendar nowTime=Total_models.system_time.returnCalendar();
        int nhour=nowTime.get(Calendar.HOUR),nmin=nowTime.get(Calendar.MINUTE);
        int periodNeed=0,specialNeed=0;
        boolean pkey=false,skey=false;

        Calendar tempPeriod=(Calendar) periodStartTime.clone();
        int phour=tempPeriod.get(Calendar.HOUR),pmin=tempPeriod.get(Calendar.MINUTE);
        int endHour=periodEndTime.get(Calendar.HOUR),endMin=periodEndTime.get(Calendar.MINUTE);
        for(    ;phour<=nhour && pmin <=nmin &&
                phour<=endHour &&pmin <=endMin
                ;){
            tempPeriod.add(Calendar.MINUTE,periodMin);
            phour=tempPeriod.get(Calendar.HOUR);
            pmin=tempPeriod.get(Calendar.MINUTE);
        }
        if(phour<=endHour &&pmin <=endMin) {
            periodNeed = (phour-nhour)*3600 + (pmin-nmin)*60 + periodCost;
            pkey=true;
        }

        Calendar tempSpecial;
        int tshour,tsmin,shour=0,smin=0;
        for(int i=0;i<specialTime.size();++i){
            tempSpecial=specialTime.get(i);
            tshour=tempSpecial.get(Calendar.HOUR);
            tsmin=tempSpecial.get(Calendar.MINUTE);
            if(tshour>=nhour && tsmin>=nmin && skey==false){
                shour=tshour;
                smin=tsmin;
                skey=true;
            }
            else if(skey==true &&
                    tshour>=nhour && tsmin>=nmin &&
                    tshour<shour && tsmin<smin){
                shour=tshour;
                smin=tsmin;
            }
        }
        if(skey==true)
            specialNeed=(shour-nhour)*3600+(smin-nmin)*60+specialCost;

        int selectIt=0;
        if(pkey==true && skey==false) selectIt=1;
        else if(pkey==false && skey==true) selectIt=2;
        else if(pkey==true && skey==true){
            selectIt=(periodNeed<specialNeed?1:2);
        }
        return selectIt;
    }

    public static void main(String[] args) {
        SelectBus bus=new SelectBus();
        SimpleDateFormat s=new SimpleDateFormat("HH:mm");
        String c= Total_models.system_time.returnTime();
        System.out.println(c);
        System.out.println(bus.run());
    }
}
