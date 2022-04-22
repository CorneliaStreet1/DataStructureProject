package JYQ.CourseManager;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Homework implements Serializable {
    private String HomeworkName;
    private String CourseName;
    private LocalDateTime StartTime;
    private LocalDateTime EndTime;
    private String HomeworkContent;
    @Serial
    private static final long serialVersionUID = 114514L;
    public Homework(String homeworkName, String courseName, LocalDateTime startTime, LocalDateTime endTime, String homeworkContent) {
        HomeworkName = homeworkName;
        CourseName = courseName;
        StartTime = startTime;
        EndTime = endTime;
        HomeworkContent = homeworkContent;
    }

    public String getHomeworkName() {
        return HomeworkName;
    }

    public String getCourseName() {
        return CourseName;
    }

    public LocalDateTime getStartTime() {
        return StartTime;
    }

    public LocalDateTime getEndTime() {
        return EndTime;
    }

    public String getHomeworkContent() {
        return HomeworkContent;
    }

    @Override
    public String toString() {
        return  "作业名称:" + HomeworkName + '\n' +
                "课程名称:" + CourseName + '\n' +
                "开始时间:" + StartTime + "\n" +
                "截止时间:" + EndTime + '\n' +
                "作业内容:" + HomeworkContent + '\n';
    }

    public static void main(String[] args) {
        System.out.println(new Homework("N", "C",LocalDateTime.of(2000,11,21,0,0), LocalDateTime.of(2000,11,22,0,0),"114"));
    }
}
