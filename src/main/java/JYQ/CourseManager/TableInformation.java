package JYQ.CourseManager;

import JHY.Course;

import java.time.LocalTime;

public class TableInformation {
    /**
     * 表示的是这是一天中的第几节课
     */
     private int ClassNum;
    /**
     * 表示的是这是周几
     */
    private int DayNum;
    private LocalTime StartTime;
    private LocalTime EndTime;
    private JHY.Course Course;
    public TableInformation(int classNum, int dayNum, LocalTime startTime, LocalTime endTime, Course course) {
        ClassNum = classNum;
        DayNum = dayNum;
        StartTime = startTime;
        EndTime = endTime;
        Course = course;
    }

    public int getClassNum() {
        return ClassNum;
    }

    public int getDayNum() {
        return DayNum;
    }

    public LocalTime getStartTime() {
        return StartTime;
    }

    public LocalTime getEndTime() {
        return EndTime;
    }

    public Course getCourse() {
        return Course;
    }

    public void setCourse(JHY.Course course) {
        Course = course;
    }
    @Override
    public String toString() {
        String name;
        if (Course == null) {
            name = "无";
        }
        else {
            name = Course.getName();
        }
        StringBuilder stringBuilder = new StringBuilder(name);
        stringBuilder.append(" 周" + DayNum);
        stringBuilder.append(" 第" + ClassNum + "节(");
        stringBuilder.append(StartTime +"到" + EndTime + ")");
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        TableInformation tableInformation = new TableInformation(1,1,LocalTime.of(8,0),LocalTime.of(8,45), new Course("计算机网络"));
        System.out.println(tableInformation);
    }
}
