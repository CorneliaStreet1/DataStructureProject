package JHY;



public class Demo {
    public static void main(String[] args) {
        Course c=new Course("高数","教三");
        Course a=new Course("英语","沙河");
        RegularTable rt=new RegularTable();
        RegularTable ntr=new RegularTable();
        rt.addLesson(2,2,c);
        ntr.addLesson(5,5,a);
        RegularTable.printTable(rt,ntr);
    }
}
