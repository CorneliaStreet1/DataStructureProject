package JHY;

public class Bupt implements School{
    private String Name;
    private String Address;
    public Bupt(String Name, String Address) {
        this.Address = Address;
        this.Name = Name;
    }
    public String getName() {
        return Name;
    }
    public String getAddress() {
        return Address;
    }
}
