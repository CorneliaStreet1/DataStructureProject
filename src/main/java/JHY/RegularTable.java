package JHY;

import java.io.Serializable;

public class RegularTable implements Serializable {

    private static final long serialVersionUID=111L;

    private Course[][] table;

    public Course get(int day,int seq){
        return table[day-1][seq-1];
    }

    public Course[][] getTable() {
        return table;
    }

    public RegularTable() {
        table=new Course[7][11];
    }

    public boolean addLesson(int day, int seq, Course lesson){
        if(table[day-1][seq-1]!=null)
            return false;
        else{
            table[day-1][seq-1]=lesson;
            return true;
        }
    }

    public boolean deleteLesson(int day,int seq){
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
        Course[][] table3=new Course[7][11];
        for(int i=0;i<7;i++){
            for(int j=0;j<11;j++){
                if(table1[i][j]!=null)
                    table3[i][j]=table1[i][j];
                else
                    table3[i][j]=table2[i][j];
            }
        }
        return table3;
    }
    //调用合并表并打印、、、、、、、
    public static void printTable(RegularTable table1,RegularTable table2){
        Course[][] table=combineTable(table1.getTable(),table2.getTable());
        int length=32;
        int tempLen;
        System.out.print("    ");
        for(int i=0;i<11;i++){
            String s="第"+(i+1)+"节";
            tempLen=length-1;
            System.out.format("%-"+tempLen+"s",s);
        }
        System.out.println();
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
            for(int j=0;j<11;j++){
                if(table[i][j]!=null) {
                    tempLen=Math.round((float) (length-table[i][j].getName().length()*0.6));
                    System.out.format("%-"+tempLen+"s",table[i][j].getName());
                }
                else {
                    tempLen=length-1;
                    System.out.format("%-"+tempLen+"s","暂无");
                }
            }
            System.out.println();
        }
    }

    public void printTable(){
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
            for(int j=0;j<11;j++){
                if(table[i][j]!=null)
                    System.out.print("第"+(j+1)+"节 ");
                else;
            }
            System.out.println();
        }
    }
}
