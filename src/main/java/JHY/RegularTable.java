package JHY;

import java.io.Serializable;
import java.util.ArrayList;

public class RegularTable implements Serializable {

    private static final long serialVersionUID=111L;

    public static String[] dayTime={"早上8:00到8:50","早上9:00到9:50","早上10:00到10:50","早上11:00到11:50","下午13:00到13:50"
            ,"下午14:00到14:50","下午15:00到15:50","下午16:00到16:50","下午17:00到17:50"};
    public static String[] weekTime={"星期一","星期二","星期三","星期四","星期五","星期六","星期日"};


    private Course[][] table;

    public Course[][] getTable() {
        return table;
    }

    //创建对象同时初始化
    public RegularTable() {
        table=new Course[7][9];
    }
    public Course get(int day, int seq) {
        return this.table[day][seq];
    }
    //添加课
    public boolean addLesson(int day, int seq, Course lesson){
        //异常?
        if(table[day-1][seq-1]!=null)
            return false;
        else{
            table[day-1][seq-1]=lesson;
            return true;
        }
    }

    //删除课
    public boolean deleteLesson(int day,int seq){
        //
        if(table[day-1][seq-1]==null)
            return false;
        else{
            table[day-1][seq-1]=null;
            return true;
        }
    }

    //合并表
    private static Course[][] combineTable(Course[][] table1,Course[][]table2){
        //假定不起冲突,万一有冲突第一个表优先
        Course[][] table3=new Course[7][9];
        for(int i=0;i<7;i++){
            for(int j=0;j<9;j++){
                if(table1[i][j]!=null)
                    table3[i][j]=table1[i][j];
                else
                    table3[i][j]=table2[i][j];
            }
        }
        return table3;
    }

    //调用合并表并打印
    public static void printTable(RegularTable table1,RegularTable table2){
        Course[][] table=combineTable(table1.getTable(),table2.getTable());

        ///暂定的打印格式
        System.out.print("         第一节        第二节        第三节    ");
        System.out.print("    第四节        第五节        第六节    ");
        System.out.println("    第七节        第八节        第九节    ");
        for(int i=0;i<7;i++){
            switch (i){
                case 0:
                    System.out.print("周一:");
                    break;
                case 1:
                    System.out.print("周二:");
                    break;
                case 2:
                    System.out.print("周三:");
                    break;
                case 3:
                    System.out.print("周四:");
                    break;
                case 4:
                    System.out.print("周五:");
                    break;
                case 5:
                    System.out.print("周六:");
                    break;
                case 6:
                    System.out.print("周日:");
                    break;
            }
            for(int j=0;j<9;j++){
                if(table[i][j]!=null)
                    System.out.print("  "+table[i][j].getName()+"  ");
                else
                    System.out.print("      暂无      ");
            }
            System.out.println();
        }
    }

}
