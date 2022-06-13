package JHY;


import JHY.Activity.Activity;

import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Demo {
    public static void main(String[] args){
        Calendar ca=Calendar.getInstance();
        /*System.out.print(ca.get(Calendar.YEAR)+"年"+(ca.get(Calendar.MONTH)+1)+
                "月"+ca.get(Calendar.DAY_OF_MONTH)+"日"+","+ca.get(Calendar.HOUR_OF_DAY)+
                ":"+ca.get(Calendar.MINUTE)+":"+ca.get(Calendar.SECOND));
        ca.set(ca.get(Calendar.YEAR),ca.get(Calendar.MONTH),ca.get(Calendar.DATE),0,0,0);

        *//*ca.add(Calendar.DAY_OF_YEAR,5);*//*

        System.out.println();
        System.out.print(ca.get(Calendar.YEAR)+"年"+(ca.get(Calendar.MONTH)+1)+
                "月"+ca.get(Calendar.DAY_OF_WEEK)+"日"+","+ca.get(Calendar.HOUR)+
                ":"+ca.get(Calendar.MINUTE)+":"+ca.get(Calendar.SECOND));

        ca.set(ca.get(Calendar.YEAR),ca.get(Calendar.MONTH),ca.get(Calendar.DATE));

        System.out.println();
        System.out.print(ca.get(Calendar.YEAR)+"年"+(ca.get(Calendar.MONTH)+1)+
                "月"+ca.get(Calendar.DAY_OF_WEEK)+"日"+","+ca.get(Calendar.HOUR)+
                ":"+ca.get(Calendar.MINUTE)+":"+ca.get(Calendar.SECOND));*/
        ca.add(Calendar.DATE,5);
        System.out.println(ca.get(Calendar.DAY_OF_WEEK));

    }

}
