package JHY.Guide;
import HYH.Model.Total_models;

import java.io.*;
import java.util.Calendar;
import java.util.Random;

public class GuideByTime {
    public static int[][]shahe;
    public static int[][]xitucheng;

    public int getTimeShaHe(int a, int b, int dis){
        return shahe[a][b]*dis;
    }
    public int getTimeXiTuCheng(int a,int b,int dis){
        return xitucheng[a][b]*dis;
    }

    public GuideByTime() {
        Calendar now= Total_models.system_time.returnCalendar();
        int hour=now.get(Calendar.HOUR);

        ObjectInputStream ois1=null;
        ObjectInputStream ois2=null;
        try {
            if(hour<12&&hour>6){
                ois1=new ObjectInputStream(new FileInputStream(new File("UserFiles\\MapRepo\\TimeMap","ShaheMorning")));
                ois2=new ObjectInputStream(new FileInputStream(new File("UserFiles\\MapRepo\\TimeMap","XituchengMorning")));
            }else if(hour<18&&hour>6){
                ois1=new ObjectInputStream(new FileInputStream(new File("UserFiles\\MapRepo\\TimeMap","ShaheAfternoon")));
                ois2=new ObjectInputStream(new FileInputStream(new File("UserFiles\\MapRepo\\TimeMap","XituchengAfternoon")));
            }else{
                ois1=new ObjectInputStream(new FileInputStream(new File("UserFiles\\MapRepo\\TimeMap","ShaheEvening")));
                ois2=new ObjectInputStream(new FileInputStream(new File("UserFiles\\MapRepo\\TimeMap","XituchengEvening")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            shahe= (int[][]) ois1.readObject();
            xitucheng= (int[][]) ois2.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            ois1.close();
            ois2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generateMap() {
        Random rd=new Random();
        ObjectOutputStream oos1=null;
        ObjectOutputStream oos2=null;
        ObjectOutputStream oos3=null;
        ObjectOutputStream oos4=null;
        ObjectOutputStream oos5=null;
        ObjectOutputStream oos6=null;
        try {
            oos1=new ObjectOutputStream(new FileOutputStream(new File("UserFiles\\MapRepo\\TimeMap","ShaheMorning")));
            oos2=new ObjectOutputStream(new FileOutputStream(new File("UserFiles\\MapRepo\\TimeMap","ShaheAfternoon")));
            oos3=new ObjectOutputStream(new FileOutputStream(new File("UserFiles\\MapRepo\\TimeMap","ShaheEvening")));
            oos4=new ObjectOutputStream(new FileOutputStream(new File("UserFiles\\MapRepo\\TimeMap","XituchengMorning")));
            oos5=new ObjectOutputStream(new FileOutputStream(new File("UserFiles\\MapRepo\\TimeMap","XituchengAfternoon")));
            oos6=new ObjectOutputStream(new FileOutputStream(new File("UserFiles\\MapRepo\\TimeMap","XituchengEvening")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        int[][][]shahe=new int[3][27][27];
        int[][][]xitucheng=new int[3][30][30];
        for (int i = 0; i < 3; i++) {
            for(int j=0;j<27;j++){
                for(int k=0;k<27;k++){
                    shahe[i][j][k]=rd.nextInt()%100;
                    shahe[i][j][k]=shahe[i][j][k]>0?shahe[i][j][k]:shahe[i][j][k]*(-1);
                }
            }
            for(int j=0;j<30;j++){
                for(int k=0;k<30;k++){
                    xitucheng[i][j][k]=rd.nextInt()%100;
                    xitucheng[i][j][k]=xitucheng[i][j][k]>0?xitucheng[i][j][k]:xitucheng[i][j][k]*(-1);
                }
            }
        }

        try {
            oos1.writeObject(shahe[0]);
            oos2.writeObject(shahe[1]);
            oos3.writeObject(shahe[2]);
            oos4.writeObject(xitucheng[0]);
            oos5.writeObject(xitucheng[1]);
            oos6.writeObject(xitucheng[2]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
