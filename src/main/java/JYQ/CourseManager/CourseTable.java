package JYQ.CourseManager;

import JHY.Course;
import JHY.RegularTable;

import java.time.LocalTime;
import java.util.LinkedList;

@SuppressWarnings("unchecked")
public class CourseTable {
    private LinkedList<TableInformation>[] CourseTable;

    public LinkedList<TableInformation>[] getCourseTable() {
        return CourseTable;
    }

    public CourseTable() {
        CourseTable = new LinkedList[7];
        for (int i = 0 ; i < CourseTable.length; i ++) {
            CourseTable[i] = new LinkedList<>();
        }
    }
    private void initTable() {
        for (int i = 1 ; i <= 7 ; i ++) {
            CourseTable[i - 1].addLast(new TableInformation(1, i, LocalTime.of(8,0), LocalTime.of(8, 45), null));
            CourseTable[i - 1].addLast(new TableInformation(2, i, LocalTime.of(8,50), LocalTime.of(9, 35), null));
            CourseTable[i - 1].addLast(new TableInformation(3, i, LocalTime.of(9,50), LocalTime.of(10, 35), null));
            CourseTable[i - 1].addLast(new TableInformation(4, i, LocalTime.of(10,40), LocalTime.of(11, 25), null));
            CourseTable[i - 1].addLast(new TableInformation(5, i, LocalTime.of(11,30), LocalTime.of(12, 15), null));
            CourseTable[i - 1].addLast(new TableInformation(6, i, LocalTime.of(13,0), LocalTime.of(13, 45), null));
            CourseTable[i - 1].addLast(new TableInformation(7, i, LocalTime.of(13,50), LocalTime.of(14, 35), null));
            CourseTable[i - 1].addLast(new TableInformation(8, i, LocalTime.of(14,45), LocalTime.of(15, 30), null));
            CourseTable[i - 1].addLast(new TableInformation(9, i, LocalTime.of(15,40), LocalTime.of(16, 25), null));
        }
    }
    public void getCompleteTable(RegularTable r1 , RegularTable r2) {
        this.initTable();
        for (int i = 0 ; i < 7 ; i ++) {
            for (int j = 0 ; j < 9 ; j ++) {
                Course course;
                if (r1.get(i,j) != null){
                    course = r1.get(i, j);
                }
                else {
                    course = r2.get(i,j);
                }
                this.CourseTable[i].get(j).setCourse(course);
            }
        }
    }
    public void PrintTable() {
        for (int i = 1 ; i <= 7 ; i ++) {
            System.out.print("周" + i + ":   ");
            for (TableInformation t: CourseTable[i - 1]) {
                System.out.print(t + "  ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        JYQ.CourseManager.CourseTable table = new CourseTable();
        table.initTable();
        table.PrintTable();
    }
}
