package JYQ;
import static org.junit.Assert.*;
/**
 * @author jiangyiqing
 */
public class Test {
    /**
     * 这种注释叫做Java的文档注释，这种注释将会在打包的时候被抽取出来作为代码的库文档。
     */
    @org.junit.Test
    public void Tests() {
        Teacher t = new BuptTeacher("homo", "114514");
        String Name = "homo";
        String ID = "114514";
        assertEquals(Name, t.getName());
        assertEquals(ID, t.getID());
    }
}
