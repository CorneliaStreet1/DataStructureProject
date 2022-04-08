package HYH.Information;

import java.util.Calendar;

public class Course extends Information{
    //地点类 上课地点
    //HashMap<Calendar beginTime,Calendar endTime>
        //一个课程会有多节课，每节课都会有开始结束时间
        //LinkElement就是为了解决Course一堆时间和后面链表不匹配的问题
        //上面的HashMap还能解决LinkElement被删除时删除Course对应时间的问题
    //linkedlist<Homework> 或用其他数据结构存Homework也行
    //linkedlist<Book> 或用其他数据结构存Book也行
    //linkedlist<Exam> 或用其他数据结构存Exam也行
}
