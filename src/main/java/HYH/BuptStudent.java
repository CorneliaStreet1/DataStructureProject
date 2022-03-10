package HYH;

public class BuptStudent implements Student{
    private String name;
    private String ID;
    public BuptStudent(String name, String ID) {
        this.ID = ID;
        this.name =name;
    }
    public String getName() {
        return name;
    }
    public String getID() {
        return ID;
    }
}
