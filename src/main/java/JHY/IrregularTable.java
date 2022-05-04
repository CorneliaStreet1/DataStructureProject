package JHY;

import JHY.Activity.Activity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class IrregularTable implements Serializable {
    private static final long serialVersionUID=3333L;

    private ArrayList<Activity> list;

    public IrregularTable() {
        list=new ArrayList<>();
    }/////初始化要创建变量对象

    public ArrayList<Activity> getList() {
        return list;
    }

    public boolean addActivity(Activity ac,int timeOrName){
        int index;
        if(timeOrName==0){
            index=getSeq(ac.getTimeBegin());
        }else{
            index=getSeq(ac.getName());
            list.add(index,ac);
            return true;
        }

        /*还要和课程冲突检测*///////////////////
        if(list.size()==index){
            list.add(ac);
            return true;
        }

        if(ac.detectTime(list.get(index))){//没冲突就肯定在索引处添加
            list.add(index,ac);
            return true;
        }else{
            System.out.println("和活动表发生时间冲突,添加活动失败,建议您先看看活动表来避免时间冲突");
        }
        return false;
    }

    public int getSeq(Calendar ca){
        //为空也行
        int low=0,high=list.size()-1,mid;
        while(low<=high){
            mid=(low+high)/2;
            if(ca .before (list.get(mid).getTimeEnd()) ){
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
        int[] arr=new int[list.size()];
        HashMap<Integer,Activity> hs=new HashMap<>();
        for(int i=0;i<list.size();i++){
            arr[i]=list.get(i).getName().hashCode();
            hs.put(list.get(i).getName().hashCode(),list.get(i));
        }
        mergerSort(arr,0,arr.length-1,new int[arr.length]);

        IrregularTable it=new IrregularTable();
        for(int i=0;i<list.size();i++){
            it.getList().add(hs.get(arr[i]));
        }
        return it;
    }

    public IrregularTable sortByTime(){
        HashMap<Calendar,Activity> hs=new HashMap<>();
        for(int i=0;i< list.size();i++){
            hs.put(list.get(i).getTimeBegin(),list.get(i));
        }
        Calendar[] arr=hs.keySet().toArray(new Calendar[0]);

        mergerSort(arr,0,arr.length-1,new Calendar[arr.length]);

        IrregularTable it=new IrregularTable();
        for(int i=0;i<list.size();i++){
            it.getList().add(hs.get(arr[i]));
        }
        return it;
    }

    public void mergerSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergerSort(arr, left, mid, temp);
            mergerSort(arr, mid + 1, right, temp);
            mergerUtils(arr, left, mid, right, temp);
        }
    }

    public void mergerSort(Calendar[] arr, int left, int right, Calendar[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergerSort(arr, left, mid, temp);
            mergerSort(arr, mid + 1, right, temp);
            mergerUtils(arr, left, mid, right, temp);
        }
    }

    public void mergerUtils(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;
        int j = mid + 1;
        int t = 0;
        //比较左右两边的数组大小，并依次进行合并的过程
        while (i <= mid && j <= right) {
            temp[t++] = arr[i] <= arr[j]?arr[i++]:arr[j++];
        }
        //那边的数据还有剩余，我们就将剩下的元素拷贝过去
        while (i <= mid) {
            temp[t] = arr[i];
            t += 1;
            i += 1;
        }
        while (j <= right) {
            temp[t] = arr[j];
            t += 1;
            j += 1;
        }
        //将临时数组中与已经排列好的数据复制到原数组中
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right) {
            arr[tempLeft] = temp[t];
            t += 1;
            tempLeft += 1;
        }
    }

    public void mergerUtils(Calendar[] arr, int left, int mid, int right, Calendar[] temp) {
        int i = left;
        int j = mid + 1;
        int t = 0;
        //比较左右两边的数组大小，并依次进行合并的过程
        while (i <= mid && j <= right) {
            temp[t++] =( arr[i].before(arr[j]) || arr[i].equals(arr[j]) ) ?arr[i++]:arr[j++];
        }
        //那边的数据还有剩余，我们就将剩下的元素拷贝过去
        while (i <= mid) {
            temp[t] = arr[i];/////////////
            t += 1;
            i += 1;
        }
        while (j <= right) {
            temp[t] = arr[j];
            t += 1;
            j += 1;
        }
        //将临时数组中与已经排列好的数据复制到原数组中
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right) {
            arr[tempLeft] = temp[t];
            t += 1;
            tempLeft += 1;
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

    public void detectTime(int day,int seq){
        for(int i=0;i<list.size();i++) {
            if((list.get(i).getTimeBegin().get(Calendar.DAY_OF_WEEK)+5)%7!=day-1){
                list.get(i).detectTime(seq);
            }
        }
    }/////////needtest
}
