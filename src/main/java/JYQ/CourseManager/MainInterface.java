package JYQ.CourseManager;
import java.time.*;
public class MainInterface {
    public static void main(String[] args) {
        LocalDateTime lcd = LocalDateTime.of(0,1,1,8,0);
        System.out.println(lcd);
        LocalTime localTime = LocalTime.of(8,0);
        System.out.println(localTime);
    }
}
