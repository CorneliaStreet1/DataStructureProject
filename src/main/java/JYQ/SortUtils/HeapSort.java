package JYQ.SortUtils;

import org.junit.Test;

import java.util.*;
import static org.junit.Assert.*;
public class HeapSort<T> {
    Comparator<T> comparator;
    PriorityQueue<T> items;

    public HeapSort(Comparator<T> comparator) {
        this.comparator = comparator;
        this.items = new PriorityQueue<>(comparator);
    }

    public List<T> HeapSort(List<T> objects) {
        assert this.items != null;
        this.items.addAll(objects);
        List<T> result = new ArrayList<>();
        while (!items.isEmpty()) {
            result.add(items.remove());
        }
        return result;
    }
    public static void main(String[] args) {
        List<Integer> test1 = new ArrayList<>();
        List<Integer> test2 = new ArrayList<>();
        int i = 0;
        while (i < 10) {
            int d = (int) (Math.random() * 10);
            test1.add(d);
            test2.add(d);
            i ++;
        }
        Comparator<Integer> c = (Integer i1, Integer i2) -> {
            return i1 - i2;
        };
        test1.sort(c);
        System.out.println(test1);
        System.out.println(new HeapSort<>(c).HeapSort(test2));
    }
}
