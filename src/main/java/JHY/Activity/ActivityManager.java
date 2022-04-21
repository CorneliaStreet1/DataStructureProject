package JHY.Activity;

import HYH.Model.Boolean_model;
import HYH.System_main;
import JYQ.Directories;
import JYQ.UserLogin.Student;
import JYQ.UserLogin.UserInformation;
import JYQ.Utils;

import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ActivityManager implements Boolean_model {










    public static void main(){
        Scanner sc = new Scanner(System.in);
        File CurrentUserFile = Utils.join(Directories.UserRepo, System_main.CurrentUserName);
        UserInformation CurrentUser = Utils.readObject(CurrentUserFile, UserInformation.class);
        if (CurrentUser.isStudent()) {
            int option;
            Student student = (Student) CurrentUser;

            System.out.println("来到课外信息管理--系~统~!");
            System.out.println("太美丽辣课外管理系统");
            System.out.println("还是看看下面的选项吧家人们");

            System.out.println("请选择下面的功能,并输入其前面的序号数字");
            System.out.println("1:增加活动");
            System.out.println("2:删除活动");
            System.out.println("3:设定闹钟");

            /*先打印活动表*/////////

            //输入数字,不然重复输入
            while(true){
                try {
                    if (sc.hasNextInt()) {
                        option = sc.nextInt();
                        if (option < 4 && option > 0)
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

            if(option==1){
                System.out.println("请输入活动的具体名称");
                String name=sc.next();
                System.out.println("请参照下面的时间格式样例,输入活动开始时间");
                System.out.println("样例:2011年04月05日,14:00:00");

            }



        }
    }



    @Override
    public boolean run() {

        return false;
    }

    @Override
    public void dailyRecord() {

    }
}
