package JYQ;
public class BuptTeacher implements Teacher{
    private String name;
    private String ID;
    public BuptTeacher(String name, String ID) {
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
