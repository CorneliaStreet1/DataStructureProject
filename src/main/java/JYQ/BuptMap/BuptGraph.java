package JYQ.BuptMap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

public class MyMap implements Serializable{
    private ArrayList<LinkedList<Integer>> Buildings;
    public MyMap(int BuildingNumber) {
        Buildings = new ArrayList<>(BuildingNumber);
    }
    
}
