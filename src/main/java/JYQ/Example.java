package JYQ;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Example {
    private static class StringLenghtComparator implements Comparator<String> {
        public int compare(String o1 , String o2) {
            return o1.length() - o2.length();
        }
    }

    public static void main(String[] args) {
        String s1 = "CCCCC";
        String s2 = "BBBBBB";
        int t1 = s1.compareTo(s2);//按字典序，s1更大
        List<String> strings = new ArrayList<>();
        Comparator<String> comparator = new StringLenghtComparator();
        int t2 = comparator.compare(s1,s2);
        System.out.println("t1 = " + t1 + " t2 = " + t2);
        for (String s:args) {
            System.out.println(s);
        }
    }
}
