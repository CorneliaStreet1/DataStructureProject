package JHY.Activity;

import HYH.Model.Boolean_model;
import HYH.System_main;
import JHY.IrregularTable;
import JYQ.Directories;
import JYQ.UserLogin.Student;
import JYQ.UserLogin.UserInformation;
import JYQ.Utils;

import java.io.EOFException;
import java.io.File;
import java.io.FilenameFilter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ActivityManager implements Boolean_model {

    public void Main(){

        Scanner sc = new Scanner(System.in);
        UserInformation CurrentUser = Utils.readObject(Utils.join(Directories.UserRepo, System_main.CurrentUserName), UserInformation.class);
        int option=0;int optionClass=0;
        System.out.println("来到课外信息管理--系~统~!");
        System.out.println("太美丽辣课外管理系统");
        System.out.println("还是看看下面的选项吧家人们");
        System.out.println();

        if (CurrentUser.isStudent()) {
            System.out.println("您当前的身份是学生");
            Student student = (Student) CurrentUser;
            File User=new File(Directories.UserFiles+"\\Class"+student.getClassNumber()
                    +"\\"+student.getUserName()+"\\StudentIrregularTable");
            File Class=new File(Directories.UserFiles+"\\Class"+student.getClassNumber()+"\\IrregularTable");

            IrregularTable table=new IrregularTable();
            IrregularTable tableClass=new IrregularTable();//先创建对象避免为null
            try {
                table = Utils.readObject(User, IrregularTable.class);
                tableClass=Utils.readObject(Class,IrregularTable.class);
            }catch (IllegalArgumentException e){/////只能抓这个异常
            }
            IrregularTable tableName=table.sortByName();
            IrregularTable tableClassName=tableClass.sortByName();

            while(option!=-1) {
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
                System.out.println("9:设定闹钟");

                Calendar tb=Calendar.getInstance();
                Calendar te=Calendar.getInstance();//引用类型,不新创建对象就共用了同一个
                option = readANum(9);

                switch (option) {
                    case 1: {
                        System.out.println("请输入活动的具体名称");
                        String name = sc.next();
                        System.out.println("请输出活动的具体地点");
                        String address = sc.next();
                        while (!getTimeBetween(tb, te)) ;
                        addActivity(table, tableName, tb, te, name, address);
                    }
                    break;
                    case 2: {
                        while (!getTimeBetween(tb, te)) ;
                        removeActivityByTime(table, tableName, tb, te);
                    }
                    break;
                    case 3: {
                        while (!getTimeBetween(tb, te)) ;
                        System.out.println("您班级在该时间段的活动如下:");
                        searchActivityByTime(tableClass, tb, te);
                        System.out.println("您个人在该时间段的活动如下:");
                        searchActivityByTime(table, tb, te);
                    }
                    break;
                    case 4: {
                        System.out.println("请输入活动的名称");
                        System.out.println("请确保活动名无误");
                        String name = sc.next();
                        removeActivityByName(table, tableName, name);
                    }
                    break;
                    case 5: {
                        System.out.println("请输入活动的名称");
                        System.out.println("请确保活动名无误");
                        String name = sc.next();
                        System.out.println("您班级名为" + '"' + name + '"' + "的活动如下:");
                        searchActivityByName(tableClassName, name);
                        System.out.println("您个人名为" + '"' + name + '"' + "的活动如下:");
                        searchActivityByName(tableName, name);
                    }
                    break;
                    case 6: {
                        System.out.println("您班级的活动如下");
                        tableClass.printTable();
                        System.out.println("您个人的活动如下");
                        table.printTable();
                    }
                    break;
                    case 7: {
                        System.out.println("您个人的活动如下");
                        table.printTable();
                    }
                    break;
                    case 8: {
                        System.out.println("您班级的活动如下");
                        tableClass.printTable();
                    }
                    break;
                    case 9: {
                        ;//闹钟??
                    }
                    break;
                }
            }
            Utils.writeObject(User,table);
        }else{
            System.out.println("您当前的身份是管理员");
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

                IrregularTable superTable=new IrregularTable();
                //得到所有IrregularTable
                File[] student=Class.listFiles();
                for(int i=0,j=0;i<student.length;i++){
                    if(student[i].isDirectory()) {
                        combineSuperTable(new File(student[i], "StudentIrregularTable"),superTable);
                    }
                }
                superTable=superTable.sortByTime();
                //superTable.printTable();/////
                //System.out.println("啊啊啊啊啊啊啊啊啊啊啊啊啊啊");
                dedepSuperTable(superTable);
                //superTable.printTable();////

                Class=new File(Class,"IrregularTable");
                IrregularTable table=new IrregularTable();
                try {
                    table = Utils.readObject(new File(Directories.UserFiles + "\\Class" + optionClass
                            ,"IrregularTable"), IrregularTable.class);
                }catch (IllegalArgumentException e){/////只能抓这个异常
                }
                IrregularTable tableName=table.sortByName();



                System.out.println("您已经进入Class" + optionClass);
                while (option!= -1) {

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
                        System.out.println("8:查看学生课程时间安排总表,方便避免时间冲突");


                        Calendar tb=Calendar.getInstance();
                        Calendar te=Calendar.getInstance();
                        option=readANum(8);///////

                        switch (option) {
                            case 1: {
                                System.out.println("请输入活动的具体名称");
                                String name = sc.next();
                                System.out.println("请输出活动的具体地点");
                                String address = sc.next();
                                while (!getTimeBetween(tb, te)) ;
                                if(!new Activity(name,tb, te,  address).detectTime(superTable)){
                                    System.out.println("和学生的活动安排又冲突,添加失败");
                                    System.out.println("可以先使用功能7,看看学生们的活动都安排在哪些时间段!");
                                    break;
                                }

                                addActivity(table, tableName, tb, te, name, address);
                            }
                            break;
                            case 2: {
                                while (!getTimeBetween(tb, te)) ;
                                removeActivityByTime(table, tableName, tb, te);
                            }
                            break;
                            case 3: {
                                while (!getTimeBetween(tb, te)) ;
                                System.out.println("Class"+optionClass+" 在该时间段的活动如下:");
                                searchActivityByTime(table, tb, te);
                            }
                            break;
                            case 4: {
                                System.out.println("请输入活动的名称");
                                System.out.println("请确保活动名无误");
                                String name = sc.next();
                                removeActivityByName(table, tableName, name);
                            }
                            break;
                            case 5: {
                                System.out.println("请输入活动的名称");
                                System.out.println("请确保活动名无误");
                                String name = sc.next();
                                System.out.println("Class"+optionClass+" 名为" + '"' + name + '"' + "的活动如下:");
                                searchActivityByName(tableName, name);
                            }
                            break;
                            case 6: {
                                System.out.println("Class"+optionClass+" 的班级活动如下");
                                table.printTable();
                            }
                            break;
                            case 7: {
                                System.out.println("学生有个人活动的时间段如下:");
                                superTable.printTimeTable();
                            }
                            break;
                            case 8: {
                                ;
                            }
                            break;
                            case 9: {
                                ;//闹钟??
                            }
                            break;
                        }
                }
                Utils.writeObject(Class,table);
            }
        }
        System.out.println("已退出课外信息管理系统,感谢您的使用!");
        return;
    }
    public boolean getTimeBetween(Calendar tb,Calendar te){
        Scanner sc=new Scanner(System.in);
        Boolean input=false;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日,HH:mm:ss");

        System.out.println("请参照下面的时间格式样例,输入活动开始时间");
        System.out.println("样例:2011年04月05日,14:00:00");
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
            System.out.println("输入的信息矛盾,开始时间要在结束时间之前,即将重新开始添加活动功能");
            System.out.println();
            return false;
        }
        return true;
    }

    public void addActivity(IrregularTable table,IrregularTable tableName,Calendar tb,Calendar te,String name,String address){
        Activity ac=new Activity(name,tb,te,address);
        if(table.addActivity(ac,0)){
            System.out.println("添加活动成功!");
        }else{
            System.out.println("添加活动失败!");
            return;
        }

        tableName.addActivity(ac,1);
        return;
    }

    public void searchActivityByTime(IrregularTable table,Calendar tb,Calendar te){

        int seq=table.getSeq(tb);
        if(seq==table.getList().size()) {
            System.out.println("暂无活动");
        }
        else {
            while (seq < table.getList().size() && (table.getList().get(seq).getTimeEnd().before(te)
                    || table.getList().get(seq).getTimeEnd().equals(te))) {
                table.getList().get(seq++).output();
            }
        }
        return;
    }

    public void removeActivityByTime(IrregularTable table,IrregularTable tableName,Calendar tb,Calendar te){

        int seq=table.getSeq(tb);
        while(seq<table.getList().size()&& (table.getList().get(seq).getTimeEnd().before(te)
                ||table.getList().get(seq).getTimeEnd().equals(te))){
            int seqName=tableName.getSeq(table.getList().get(seq).getName());//删前找序号
            table.getList().remove(seq);
            tableName.getList().remove(--seqName);
        }
        System.out.println("已经成功删除对应时间段内的活动!");
        return;
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
            int seq=table.getSeq(tableName.getList().get(seqName).getTimeEnd());
            tableName.getList().remove(seqName--);
            table.getList().remove(--seq);
        }
        System.out.println("已经删除所有同名的活动");
        return;
    }

    public int readANum(int max){
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

    public void combineSuperTable(File file,IrregularTable superTable){
        IrregularTable table=new IrregularTable();
        try {
            table = Utils.readObject(file, IrregularTable.class);
        }catch (IllegalArgumentException e){/////只能抓这个异常
        }

        for(int i=0;i<table.getList().size();i++){
            superTable.getList().add(table.getList().get(i));
        }
    }

    public void dedepSuperTable(IrregularTable superTable) {
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
///
    @Override
    public boolean run() {
        Main();
        return true;
    }

    @Override
    public void dailyRecord() {
;;
    }


}