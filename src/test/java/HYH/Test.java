package HYH;
import static org.junit.Assert.assertEquals;
/**
 * @author TODO:加上你的名字
 */
public class Test {
        @org.junit.Test
        public void Tests() {
            Student t = new BuptStudent("homo", "114514");
            String Name = "homo";
            String ID = "114514";
            assertEquals(Name, t.getName());
            assertEquals(ID, t.getID());
        }
}
