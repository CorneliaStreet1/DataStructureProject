package JHY;
import static org.junit.Assert.*;

/**
 * @author //FixMe
 */
public class Test {
    @org.junit.Test
    public void Tests() {
        School t = new Bupt("homo", "哼哼哼街114514号1919180楼");
        String Name = "homo";
        String Address = "哼哼哼啊啊啊啊啊啊啊啊";
        assertEquals(Name, t.getName());
        assertEquals(Address, t.getAddress());
    }
}
