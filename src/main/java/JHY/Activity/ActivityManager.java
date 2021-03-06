package JHY.Activity;

import HYH.DailyRecord.RecordOperate;
import HYH.Model.Boolean_model;
import HYH.System_main;
import JHY.Course;
import JHY.IrregularTable;
import JHY.RegularTable;
import JYQ.Directories;
import JYQ.UserLogin.Student;
import JYQ.UserLogin.UserInformation;
import JYQ.Utils;
import java.io.File;
import java.io.FileFilter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ActivityManager implements Boolean_model {

    public void Main(){

        Scanner sc = new Scanner(System.in);
        UserInformation CurrentUser = Utils.readObject(Utils.join(Directories.UserRepo, System_main.CurrentUserName), UserInformation.class);
        int option=0;
        int optionClass=0;
        System.out.println("来到课外信息管理系统!");

        if (CurrentUser.isStudent()) {
            Student student = (Student) CurrentUser;
            //table学生活动表 tableClass学生班级活动表 tableName学生活动表（名字排序） tableClassName学生班级活动表（名字排序）
            //tableCourse学生课程表 tableClassCourse班级课程表
            File User=new File(Directories.UserFiles+"\\Class"+student.getClassNumber()
                    +"\\"+student.getUserName()+"\\StudentIrregularTable");
            File UserCourse=new File(Directories.UserFiles+"\\Class"+student.getClassNumber()
                    +"\\"+student.getUserName()+"\\StudentRegularTable");
            File Class=new File(Directories.UserFiles+"\\Class"+student.getClassNumber()+"\\IrregularTable");
            File ClassCourse=new File(Directories.UserFiles+"\\Class"+student.getClassNumber()+"\\RegularTable");
            IrregularTable table=new IrregularTable();
            IrregularTable tableClass=new IrregularTable();
            RegularTable tableCourse=new RegularTable();
            RegularTable tableClassCourse=new RegularTable();
            try {
                table = Utils.readObject(User, IrregularTable.class);
            }catch (IllegalArgumentException e){
            }
            try {
                tableClass=Utils.readObject(Class,IrregularTable.class);
            }catch (IllegalArgumentException e){
            }
            try {
                tableCourse = Utils.readObject(UserCourse, RegularTable.class);
                tableClassCourse=Utils.readObject(ClassCourse,RegularTable.class);
            }catch (IllegalArgumentException e){
            }
            IrregularTable tableName=table.sortByName();
            IrregularTable tableClassName=tableClass.sortByName();

            while(true) {
                System.out.println();
                System.out.println("请选择下面的功能,并输入其前面的序号数字");
                System.out.println("输入数字 -1 可以退出系统");
                System.out.println("1:增加活动");
                System.out.println("2:按 时间段 删除活动");
                System.out.println("3:按 时间段 搜索活动");
                System.out.println("4:按 活动名 删除活动");
                System.out.println("5:按 活动名 搜索活动");
                System.out.println("6:打印 总 活动表");
                System.out.println("7:打印 个人 活动表");
                System.out.println("8:打印 班级 活动表");

                Calendar tb=Calendar.getInstance();
                Calendar te=Calendar.getInstance();
                option = readANum(8);
                if(option==-1)break;

                switch (option) {
                    case 1: {
                        writeDialog(option,"添加活动");
                        System.out.println("请输入活动的具体名称");
                        String name = sc.next();
                        System.out.println("请输出活动的具体地点");
                        String address = sc.next();
                        while (!getTimeBetween(tb, te,1)) ;
                        Activity ac=new Activity(name ,tb,te,address);
                        if(detect(ac,tableCourse,tableClassCourse,table,tableClass)){
                            addActivity(table, tableName, ac);
                        }
                    }
                    break;
                    case 2: {
                        writeDialog(option,"按时间段删除活动");
                        RecordOperate.WriteRecord("进入“删除活动”模块\n");
                        while (!getTimeBetween(tb, te,0)) ;
                        removeActivityByTime(table, tableName, tb, te);
                    }
                    break;
                    case 3: {
                        writeDialog(option,"按时间段查看活动");
                        while (!getTimeBetween(tb, te,0)) ;
                        System.out.println("您班级在该时间段的活动如下:");
                        searchActivityByTime(tableClass, tb, te);
                        System.out.println();
                        System.out.println("您个人在该时间段的活动如下:");
                        searchActivityByTime(table, tb, te);
                    }
                    break;
                    case 4: {
                        writeDialog(option,"按活动名删除活动");
                        System.out.println("请输入活动的名称");
                        System.out.println("请确保活动名无误");
                        String name = sc.next();
                        removeActivityByName(table, tableName, name);
                    }
                    break;
                    case 5: {
                        writeDialog(option,"按活动名查看活动");
                        System.out.println("请输入活动的名称");
                        System.out.println("请确保活动名无误");
                        String name = sc.next();
                        System.out.println("**您班级名为" + '"' + name + '"' + "的活动如下**");
                        searchActivityByName(tableClassName, name);
                        System.out.println();
                        System.out.println("**您个人名为" + '"' + name + '"' + "的活动如下**");
                        searchActivityByName(tableName, name);
                    }
                    break;
                    case 6: {
                        writeDialog(option,"打印总活动表");
                        System.out.println("**您班级的活动如下**");
                        tableClass.printTable();
                        System.out.println();
                        System.out.println("**您个人的活动如下**");
                        table.printTable();
                    }
                    break;
                    case 7: {
                        writeDialog(option,"打印个人活动表");
                        System.out.println("**您个人的活动如下**");
                        table.printTable();
                    }
                    break;
                    case 8: {
                        writeDialog(option,"打印班级活动表");
                        System.out.println("**您班级的活动如下**");
                        tableClass.printTable();
                    }
                    break;
                }
            }
            writeDialog(-1,"退出课外信息管理系统");
            Utils.writeObject(User,table);
        }else{
            while(true) {
                File Class;
                System.out.println();
                System.out.println("请输入*数字*进入相应的班级");
                System.out.println("输入数字 -1 可以*保存并退出*系统");
                while(true) {
                    optionClass = readANum();
                    Class = new File(Directories.UserFiles + "\\Class" + optionClass);
                    if(optionClass==-1)break;
                    if(Class.exists())break;
                    else System.out.println("当前班级不存在,请重新输入");
                }
                if(optionClass==-1)break;
                //superTable学生活动表 superTableCourse学生课程表 table班级活动表 tableCourse班级课程表 tableName班级活动表名字排序
                IrregularTable superTable=getTableActivity(Class);
                RegularTable superTableCourse=getTableCourse(Class);
                Class=new File(Class,"IrregularTable");
                IrregularTable table=new IrregularTable();
                RegularTable tableCourse=new RegularTable();
                try {
                    tableCourse = Utils.readObject(new File(Directories.UserFiles + "\\Class" + optionClass
                            ,"RegularTable"), RegularTable.class);
                }catch (IllegalArgumentException e){
                    System.out.println("error");
                }
                try {
                    table = Utils.readObject(new File(Directories.UserFiles + "\\Class" + optionClass
                            ,"IrregularTable"), IrregularTable.class);
                }catch (IllegalArgumentException e){
                }
                IrregularTable tableName=table.sortByName();

                System.out.println("您已经进入Class" + optionClass);
                while (true) {

                        System.out.println();
                        System.out.println("请选择下面的功能,并输入其前面的序号数字");
                        System.out.println("输入数字 -1 可以*保存并退出*当前班级");
                        System.out.println("1:为 Class"+optionClass+" 增加活动");
                        System.out.println("2:按 时间段 为 Class"+optionClass+" 删除活动");
                        System.out.println("3:按 时间段 在 Class"+optionClass+" 搜索活动");
                        System.out.println("4:按 活动名 为 Class"+optionClass+" 删除活动");
                        System.out.println("5:按 活动名 在 Class"+optionClass+" 搜索活动");
                        System.out.println("6:打印 Class"+optionClass+"的 班级活动表");
                        System.out.println("7:查看学生活动时间安排总表,方便避免时间冲突");
                        System.out.println("8:查看学生课程安排总表,方便避免时间冲突");

                        Calendar tb=Calendar.getInstance();
                        Calendar te=Calendar.getInstance();
                        option=readANum(8);
                        if(option==-1)
                            break;
                        switch (option) {
                            case 1: {
                                writeDialog(option,"增加活动");
                                System.out.println("请输入活动的具体名称");
                                String name = sc.next();
                                System.out.println("请输出活动的具体地点");
                                String address = sc.next();
                                while (!getTimeBetween(tb, te,1)) ;
                                Activity ac=new Activity(name,tb,te,address);
                                if(detect(ac,superTableCourse,tableCourse,superTable,table))
                                    addActivity(table, tableName, ac);
                            }
                            break;
                            case 2: {
                                writeDialog(option,"按时间段删除活动");
                                while (!getTimeBetween(tb, te,0)) ;
                                removeActivityByTime(table, tableName, tb, te);
                            }
                            break;
                            case 3: {
                                writeDialog(option,"按时间段搜索活动");
                                while (!getTimeBetween(tb, te,0)) ;
                                System.out.println("Class"+optionClass+" 在该时间段的活动如下:");
                                searchActivityByTime(table, tb, te);
                            }
                            break;
                            case 4: {
                                writeDialog(option,"按活动名段删除活动");
                                System.out.println("请输入活动的名称");
                                System.out.println("请确保活动名无误");
                                String name = sc.next();
                                removeActivityByName(table, tableName, name);
                            }
                            break;
                            case 5: {
                                writeDialog(option,"按活动名段搜索活动");
                                System.out.println("请输入活动的名称");
                                System.out.println("请确保活动名无误");
                                String name = sc.next();
                                System.out.println("Class"+optionClass+" 名为" + '"' + name + '"' + "的活动如下:");
                                searchActivityByName(tableName, name);
                            }
                            break;
                            case 6: {
                                writeDialog(option,"打印班级活动表");
                                System.out.println("Class"+optionClass+" 的班级活动如下");
                                table.printTable();
                            }
                            break;
                            case 7: {
                                writeDialog(option,"查看学生活动时间安排表");
                                System.out.println("学生有个人活动的时间段如下:");
                                superTable.printTimeTable();
                            }
                            break;
                            case 8: {
                                writeDialog(option,"查看学生课程安排表");
                                System.out.println("班级的有安排的课程如下:");
                                tableCourse.printTable();
                                System.out.println("学生们有安排的所有个人课程如下:");
                                System.out.println("----");
                                superTableCourse.printTable();
                            }
                            break;
                        }
                }
                writeDialog(-1,"退出课外信息管理系统");
                Utils.writeObject(Class,table);
            }
        }
        System.out.println("已退出课外信息管理系统,感谢您的使用!");
        return;
    }
    //给班级文件夹,找下面的学生文件夹
    private static File[] getFiles(File file,String name){
        File[] files=file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                if(pathname.isDirectory()){
                    String[] list=pathname.list();
                    for(int i=0;i<list.length;i++){
                        if(list[i].equals(name))
                            return true;
                    }
                }
                return false;
            }
        });
        return files;
    }

    public boolean getTimeBetween(Calendar tb,Calendar te,int isAdd){
        Scanner sc=new Scanner(System.in);
        Boolean input=false;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日,HH:mm:ss");

        System.out.println("请参照下面的时间格式样例,输入活动开始时间");
        System.out.println("样例:2022年04月05日,14:00:00");
        input=false;
        while(!input) {
            String timeBegin = sc.next();
            input=true;
            try {
                tb.setTime(sdf.parse(timeBegin));
            } catch (ParseException e) {
                System.out.println("您的输入有误,请仔细核对格式!然后重新输入");
                input=false;
            }
        }

        System.out.println("请继续参照上面的格式样例,输入活动结束时间");
        input=false;
        while(!input) {
            String timeBegin = sc.next();
            input=true;
            try {
                te.setTime(sdf.parse(timeBegin));
            } catch (ParseException e) {
                System.out.println("您的输入有误,请仔细核对格式!然后重新输入");
                input=false;
            }
        }

        if(!tb.before(te)){
            System.out.println("输入的信息矛盾,开始时间要在结束时间之前");
            System.out.println("已重新开始添加活动功能");
            System.out.println();
            return false;
        }

        if(isAdd==1){
            if(!(tb.get(Calendar.DATE)==te.get(Calendar.DATE)&&
                    tb.get(Calendar.MONTH)==te.get(Calendar.MONTH)&&
                    tb.get(Calendar.YEAR)==te.get(Calendar.YEAR))){
                System.out.println("输入信息不符合规范,活动的开始时间和结束时间要在同一天");
                System.out.println("已重新开始添加活动功能");
                System.out.println();
                return false;
            }
        }
        return true;
    }

    public void addActivity(IrregularTable table,IrregularTable tableName,Activity ac){
        int index=table.getSeq(ac.getTimeBegin());
        table.getList().add(index,ac);
        index=tableName.getSeq(ac.getName());
        tableName.getList().add(index,ac);
        System.out.println("添加活动成功");
        return;
    }

    public void searchActivityByTime(IrregularTable table,Calendar tb,Calendar te){
        Calendar ca=Calendar.getInstance();
        ca.set(tb.get(Calendar.YEAR),tb.get(Calendar.MONTH),tb.get(Calendar.DATE),0,0,0);
        int seq=table.getSeq(ca);
        int i=0;
        Activity ac=new Activity(" ",tb,te," ");
        while(seq<table.getList().size()&&table.getList().get(seq).timeBegin.before(te)){
            if(!table.getList().get(seq).detectTime(ac)){
                table.getList().get(seq).output();
                i++;
            }
            seq++;
        }
        if(i==0){
            System.out.println("暂无活动");
        }
    }

    public void removeActivityByTime(IrregularTable table,IrregularTable tableName,Calendar tb,Calendar te){
        Calendar ca=Calendar.getInstance();
        ca.set(tb.get(Calendar.YEAR),tb.get(Calendar.MONTH),tb.get(Calendar.DATE),0,0,0);
        int seq=table.getSeq(ca);
        Activity ac=new Activity(" ",tb,te," ");
        while(seq<table.getList().size()&&table.getList().get(seq).timeBegin.before(te)){
            if(!table.getList().get(seq).detectTime(ac)){
                int seqName=tableName.getSeq(table.getList().get(seq).getName())-1;
                table.getList().remove(seq);
                tableName.getList().remove(seqName);
            }else{
                seq++;
            }
        }
        System.out.println("已经成功删除对应时间段内的活动!");
    }
    //同名的活动,新后添加的活动在前面
    public void searchActivityByName(IrregularTable table,String name){
        int seq=table.getSeq(name);
        if( ((--seq)==-1) || (!table.getList().get(seq).getName().equals(name)) ){
            System.out.println();
            System.out.println("暂无活动");
            return;////
        }
        while(seq>-1&&table.getList().get(seq).getName().equals(name)){///多看看临界条件
            table.getList().get(seq--).output();
        }
        return;
    }

    public void removeActivityByName(IrregularTable table,IrregularTable tableName,String name){
        int seqName=tableName.getSeq(name);
        if( ((--seqName)==-1)||(!tableName.getList().get(seqName).getName().equals(name)) ){
            System.out.println("未搜索到同名活动,请核对输入的活动名,重新启动搜索功能吧");
            return;
        }
        while(seqName>-1&&tableName.getList().get(seqName).getName().equals(name)){
            int seq=table.getSeq(tableName.getList().get(seqName).getTimeBegin())-1;//不同名但是同开始时间
            while(!(table.getList().get(seq).getName().equals(tableName.getList().get(seqName).getName())&&
                    table.getList().get(seq).getTimeEnd().equals(tableName.getList().get(seqName).getTimeEnd())&&
                    table.getList().get(seq).getAddress().equals(tableName.getList().get(seqName).getAddress())))
                    seq--;
            tableName.getList().remove(seqName--);
            table.getList().remove(seq);
        }
        System.out.println("已经删除所有同名的活动");
        return;
    }

    public static int readANum(int max){
        Scanner sc=new Scanner(System.in);
        int option;
        while(true){
            try {
                if (sc.hasNextInt()) {
                    option = sc.nextInt();
                    if (option <max+1 && option > 0||option==-1)
                        break;
                    else
                        throw new InputMismatchException();
                } else{
                    String s=sc.next();
                    throw new InputMismatchException();
                }
            }catch(InputMismatchException e){
                System.out.println("您当前的输入有误,请重新输入");
            }
        }
        return option;
    }

    public int readANum(){
        Scanner sc=new Scanner(System.in);
        int option;
        while(true){
            try {
                if (sc.hasNextInt()) {
                    option = sc.nextInt();
                    if (option > 0||option==-1)
                        break;
                    else
                        throw new InputMismatchException();
                } else{
                    String s=sc.next();
                    throw new InputMismatchException();
                }
            }catch(InputMismatchException e){
                System.out.println("您当前的输入有误,请重新输入");
            }
        }
        return option;
    }

    public static IrregularTable getTableActivity(File Class){
        IrregularTable superTable=getSuperTable(Class);
        superTable=superTable.sortByTime();
        dedepSuperTable(superTable);
        return superTable;
    }

    private static IrregularTable getSuperTable(File file){
        File[] student=ActivityManager.getFiles(file,"StudentIrregularTable");
        IrregularTable superTable=new IrregularTable();
        for(int i=0,j=0;i<student.length;i++){
            combineSuperTable(new File(student[i], "StudentIrregularTable"),superTable);
        }
        return superTable;
    }

    private static void combineSuperTable(File file,IrregularTable superTable){
        IrregularTable table=new IrregularTable();
        try {
            table = Utils.readObject(file, IrregularTable.class);
        }catch (IllegalArgumentException e){
        }
        for(int i=0;i<table.getList().size();i++){
            superTable.getList().add(table.getList().get(i));
        }
    }

    private static void dedepSuperTable(IrregularTable superTable) {
        for (int i = 0; i < superTable.getList().size() - 1; ) {
            if (superTable.getList().get(i).getTimeEnd().after(superTable.getList().get(i + 1).getTimeEnd())
                    || superTable.getList().get(i).getTimeEnd().equals(superTable.getList().get(i + 1).getTimeEnd())) {
                superTable.getList().remove(i+1);
            } else if (superTable.getList().get(i).getTimeEnd().after(superTable.getList().get(i + 1).getTimeBegin())
                    || superTable.getList().get(i).getTimeEnd().equals(superTable.getList().get(i + 1).getTimeBegin())) {
                superTable.getList().get(i).setTimeEnd(superTable.getList().get(i + 1).getTimeEnd());
                superTable.getList().remove(i+1);
            } else {
                i++;
            }
        }
    }
    //传进来班级文件夹
    public static RegularTable getTableCourse(File file){
        File[] student=ActivityManager.getFiles(file,"StudentRegularTable");
        RegularTable superTableCourse=new RegularTable();
        for(int i=0,j=0;i<student.length;i++){
            combineSuperTableCourse(new File(student[i], "StudentRegularTable"),superTableCourse);
        }
        return superTableCourse;
    }

    private static void combineSuperTableCourse(File file,RegularTable superTableCourse){
        RegularTable table=new RegularTable();
        try {
            table = Utils.readObject(file, RegularTable.class);
        }catch (IllegalArgumentException e){
        }
        Course tempCourse=new Course(" "," "," ");
        for(int i=0;i<7;i++){
            for(int j=0;j<11;j++){
                if(superTableCourse.getTable()[i][j]==null&&table.getTable()[i][j]!=null){
                    superTableCourse.getTable()[i][j]=tempCourse;
                }
            }
        }
    }

    public static boolean detect(Activity ac,RegularTable PersonRe,RegularTable ClassRe,IrregularTable PersonIr,IrregularTable ClassIr){
        if(!ac.detectTime(PersonIr)){
            System.out.println("您添加的活动和个人的活动有时间冲突!!!");
            System.out.println("确定继续添加?");
            System.out.println("输入数字 1 继续,输入数字 -1 取消添加");
            if((readANum(1))==-1)
                return false;
        }
        if(!ac.detectTime(ClassIr)){
            System.out.println("您添加的活动和班级的活动有时间冲突!!!");
            System.out.println("确定继续添加?");
            System.out.println("输入数字 1 继续,输入数字 -1 取消添加");
            if((readANum(1))==-1)
                return false;
        }
        if(!ac.detectTime(PersonRe)){
            System.out.println("您添加的活动和个人的课程有时间冲突!!!");
            System.out.println("确定继续添加?");
            System.out.println("输入数字 1 继续,输入数字 -1 取消添加");
            if((readANum(1))==-1)
                return false;
        }
        if(!ac.detectTime(ClassRe)){
            System.out.println("您添加的活动和班级的课程有时间冲突!!!");
            System.out.println("确定继续添加?");
            System.out.println("输入数字 1 继续,输入数字 -1 取消添加");
            if((readANum(1))==-1)
                return false;
        }
        return true;
    }
    public static boolean detect(int day,int seq, RegularTable PersonRe,RegularTable ClassRe,IrregularTable PersonIr,IrregularTable ClassIr){
        if(PersonRe.getTable()[day-1][seq-1]!=null){
            System.out.println("您添加的课程和个人的课程有时间冲突!!!");
            System.out.println("确定强行添加?");
            System.out.println("输入数字 1 继续,输入数字 -1 取消添加");
            if((readANum(1))==-1)
                return false;
        }
        if(ClassRe.getTable()[day-1][seq-1]!=null){
            System.out.println("您添加的课程和班级的课程有时间冲突!!!");
            System.out.println("确定强行添加?");
            System.out.println("输入数字 1 继续,输入数字 -1 取消添加");
            if((readANum(1))==-1)
                return false;
        }
        if(!PersonIr.detectTime(day,seq)){
            System.out.println("您添加的课程和个人的活动有时间冲突!!!");
            System.out.println("确定强行添加?");
            System.out.println("输入数字 1 继续,输入数字 -1 取消添加");
            if((readANum(1))==-1)
                return false;
        }
        if(!ClassIr.detectTime(day,seq)){
            System.out.println("您添加的课程和班级的活动有时间冲突!!!");
            System.out.println("确定强行添加?");
            System.out.println("输入数字 1 继续,输入数字 -1 取消添加");
            if((readANum(1))==-1)
                return false;
        }
        return true;
    }

    public void writeDialog(int op,String s){
        RecordOperate.WriteRecord("用户输入："+op+"\n");
        RecordOperate.WriteRecord("系统：进入”"+s+"“模块\n");
    }

    @Override
    public void dailyRecord(){;}

    @Override
    public boolean run() {
        Main();
        return true;
    }

    public static void main(String[] args) {
        new ActivityManager().Main();
    }
}