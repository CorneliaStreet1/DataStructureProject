package JHY;

import java.io.Serializable;
import java.util.ArrayList;

public class RegularTable implements Serializable {

    private Course[][] table;

    public Course[][] getTable() {
        return table;
    }

    //创建对象同时初始化
    public RegularTable() {
        table=new Course[7][9];
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
        for(int i=0;i<7;i++){
            for(int j=0;j<9;j++){
                if(table[i][j]!=null)
                System.out.print(table[i][j].getName()+" ");
                else
                    System.out.print(" x  ");
            }
            System.out.println();
        }
    }

}
