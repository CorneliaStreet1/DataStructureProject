package JHY;

import JHY.Activity.Activity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

public class IrregularTable implements Serializable {
    private static final long serialVersionUID=3333L;

    private ArrayList<Activity> list;

    public IrregularTable() {
        list=new ArrayList<>();
    }

    public ArrayList<Activity> getList() {
        return list;
    }

    public void setList(ArrayList<Activity>list){
        this.list=list;
    }

    public int getSeq(Calendar ca){
        //为空也行
        int low=0,high=list.size()-1,mid;
        while(low<=high){
            mid=(low+high)/2;
            if(ca .before (list.get(mid).getTimeBegin()) ){
                high=mid-1;
            }else {
                low = mid + 1;
            }
        }
        return low;
    }

    public int getSeq(String name){
        //为空也行
        int low=0,high=list.size()-1,mid;
        while(low<=high){
            mid=(low+high)/2;
            if(name.hashCode()<list.get(mid).getName().hashCode()){
                high=mid-1;
            }else {
                low = mid + 1;
            }
        }
        return low;
    }

    public IrregularTable sortByName(){
        IrregularTable it=new IrregularTable();
        ArrayList<Activity>temp;
        for(int i=0;i<list.size();i++){
            it.getList().add(list.get(i));
        }
        divideN(0,list.size()-1);
        temp=it.getList();
        it.setList(this.getList());
        this.setList(temp);
        return it;
    }

    public IrregularTable sortByTime(){
        IrregularTable it=new IrregularTable();
        ArrayList<Activity>temp;
        for(int i=0;i<list.size();i++){
            it.getList().add(list.get(i));
        }
        divideC(0,list.size()-1);
        temp=it.getList();
        it.setList(this.getList());
        this.setList(temp);
        return it;
    }

    public void divideN(int startIndex,int endIndex){
        if(startIndex<endIndex && (endIndex-startIndex)>=1){
            int mid = (endIndex + startIndex)/2;
            divideN(startIndex, mid);
            divideN(mid+1, endIndex);
            mergerN(startIndex,mid,endIndex);
        }
    }

    public void divideC(int startIndex,int endIndex){
        if(startIndex<endIndex && (endIndex-startIndex)>=1){
            int mid = (endIndex + startIndex)/2;
            divideC(startIndex, mid);
            divideC(mid+1, endIndex);
            mergerC(startIndex,mid,endIndex);
        }
    }

    public void mergerN(int startIndex,int midIndex,int endIndex){
        ArrayList<Activity> mergedSortedArray = new ArrayList<>();

        int leftIndex = startIndex;
        int rightIndex = midIndex+1;

        while(leftIndex<=midIndex && rightIndex<=endIndex){
            if(list.get(leftIndex).getName().hashCode()<=list.get(rightIndex).getName().hashCode()){
                mergedSortedArray.add(list.get(leftIndex));
                leftIndex++;
            }else{
                mergedSortedArray.add(list.get(rightIndex));
                rightIndex++;
            }
        }
        while(leftIndex<=midIndex){
            mergedSortedArray.add(list.get(leftIndex));
            leftIndex++;
        }

        while(rightIndex<=endIndex){
            mergedSortedArray.add(list.get(rightIndex));
            rightIndex++;
        }

        int i = 0;
        int j = startIndex;
        while(i<mergedSortedArray.size()){
            list.set(j, mergedSortedArray.get(i++));
            j++;
        }
    }

    public void mergerC(int startIndex,int midIndex,int endIndex){
        ArrayList<Activity> mergedSortedArray = new ArrayList<>();

        int leftIndex = startIndex;
        int rightIndex = midIndex+1;

        while(leftIndex<=midIndex && rightIndex<=endIndex){
            if(!list.get(leftIndex).getTimeBegin().after(list.get(rightIndex).getTimeBegin())){
                mergedSortedArray.add(list.get(leftIndex));
                leftIndex++;
            }else{
                mergedSortedArray.add(list.get(rightIndex));
                rightIndex++;
            }
        }

        while(leftIndex<=midIndex){
            mergedSortedArray.add(list.get(leftIndex));
            leftIndex++;
        }

        while(rightIndex<=endIndex){
            mergedSortedArray.add(list.get(rightIndex));
            rightIndex++;
        }

        int i = 0;
        int j = startIndex;
        while(i<mergedSortedArray.size()){
            list.set(j, mergedSortedArray.get(i++));
            j++;
        }
    }

    public void printTable(){
        if(list.size()==0) {
            System.out.println("当前暂无活动,快去添加活动吧!");
            return;
        }
        else{
            for(int i=0;i<list.size();i++){
                list.get(i).output();
            }
        }
        return;
    }

    public void printTimeTable(){
        if(list.size()==0) {
            System.out.println("当前暂无活动");
            return;
        }
        else{
            for(int i=0;i<list.size();i++){
                list.get(i).outputTime();
            }
        }
        return;
    }

    public boolean detectTime(int day,int seq){
        for(int i=0;i<list.size();i++) {
            if((list.get(i).getTimeBegin().get(Calendar.DAY_OF_WEEK)+5)%7==day-1){
                if(!list.get(i).detectTime(seq))
                    return false;
            }
        }
        return true;
    }
}
