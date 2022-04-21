package JHY.IrregularTable;


import JHY.Activity.ClassActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;

public class ClassIrregularTable {

    private ArrayList<ClassActivity> list;

    public ClassIrregularTable() {
    }

    public ArrayList<ClassActivity> getList() {
        return list;
    }

    public boolean addActivity(ClassActivity ac){
            int index=getSeq(ac);
            /*还要和课程冲突检测*///////////////////
            if(ac.detectTime(list.get(index))){//没冲突就肯定在索引处添加
                list.add(index,ac);
                return true;
            }
            return false;
    }
////
    public boolean removeActivity(ClassActivity ac){
        return false;
    }

    public int getSeq(ClassActivity ac){
        //为空也行
        int low=0,high=list.size()-1,mid;
        while(low<=high){
            mid=(low+high)/2;
            if(ac.getTimeBegin()  .before (list.get(mid).getTimeEnd()) ){
                high=mid-1;
            }else {
                low = mid + 1;
            }
        }
        return low;
    }
}
