package JHY;


import JHY.Activity.Activity;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Demo {
    public static void main(String[] args) {
      /*  Date date=new Date();
        Date date1=new Date(1145141919L);
        Activity ac=new Activity("打个郊县");
        ac.setTimeBegin(date);ac.setTimeEnd(date1);*/
        int option;
        Scanner sc=new Scanner(System.in);
        while(true){
            try {
                if (sc.hasNextInt()) {
                    option = sc.nextInt();
                    if (option < 4 && option > 0)
                        break;
                    else
                        throw new InputMismatchException();


                } else{
                    String s=sc.next();
                    throw new InputMismatchException();
                }

            }catch(InputMismatchException e){
                System.out.println("您当前的输入有误,请重新输入");
            }

        }
    }
}
