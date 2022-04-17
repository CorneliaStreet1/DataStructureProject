package JHY;
public class TimePair {
    private int startTimeHour;
    private int startTimeMin;
    private int endTimeHour;
    private int endTimeMin;
    public TimePair(int startTimeHour, int startTimeMin, int endTimeHour, int endTimeMin) {
        this.startTimeHour = startTimeHour;
        this.startTimeMin = startTimeMin;
        this.endTimeHour = endTimeHour;
        this.endTimeMin = endTimeMin;
    }
    public int getStartTimeHour() {
        return startTimeHour;
    }

    public int getStartTimeMin() {
        return startTimeMin;
    }

    public int getEndTimeHour() {
        return endTimeHour;
    }

    public int getEndTimeMin() {
        return endTimeMin;
    }
}