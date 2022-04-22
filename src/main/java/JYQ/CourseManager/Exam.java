package JYQ.CourseManager;

import java.io.Serializable;
import java.time.LocalDateTime;
public class Exam implements Serializable {
    private String Address;
    private LocalDateTime Date;
    private String Name;
    private int TimeLength;
    public Exam(String address, LocalDateTime date, String name, int timeLength) {
        Address = address;
        Date = date;
        Name = name;
        TimeLength = timeLength;
    }
    public String getAddress() {
        return Address;
    }

    public int getTimeLength() {
        return TimeLength;
    }

    public LocalDateTime getDate() {
        return Date;
    }

    public String getName() {
        return Name;
    }
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("考试名称:");
        stringBuilder.append(this.Name);
        stringBuilder.append("  考试地点:");
        stringBuilder.append(this.Address);
        stringBuilder.append("  考试日期:");
        stringBuilder.append(this.Date.toString());
        stringBuilder.append("  考试时长(单位:分钟):");
        stringBuilder.append(this.TimeLength);
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        Exam exam = new Exam("N201", LocalDateTime.now(),"期中考试", 60);
        System.out.println(exam);
    }
}
