package JYQ.UserLogin;

import HYH.Model.Boolean_model;

import java.io.IOException;
import java.util.Scanner;

public class WelcomeInterface implements Boolean_model {
    public static boolean PrintWelcomeMessage() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=====================欢迎来到北京邮电大学校园导航及课程管理系统!===============");
        System.out.println("输入1选择已有用户登录，输入2选择新用户注册:");
        boolean LoginSuccess = false;
        int option = scanner.nextInt();
        try {
            if (option == 1) {
                System.out.println("请注意，由于Windows的文件系统是大小写不敏感的，所以用户名也是大小写不敏感的。");
                System.out.println("请在下一行输入您的用户名:");
                String name = scanner.next();
                System.out.println("请在下一行输入您的密码:");
                String Password = scanner.next();
                UserInformation ReadInUserInformation = new UserInformation(name, Password, false);
                if (UserManager.hasUser(ReadInUserInformation)) {
                    LoginSuccess = UserManager.UserLogin(ReadInUserInformation);
                    if (!LoginSuccess) {
                        System.out.println("用户名正确但密码错误，程序已退出。");
                        return false;
                    }
                    else {
                        return true;
                    }
                }
                else {
                    System.out.println("登录失败，用户不存在。程序已退出。");
                    return false;
                }
            }
            else if (option == 2) {
                System.out.println("请注意，由于Windows的文件系统是大小写不敏感的，所以用户名也是大小写不敏感的。");
                System.out.println("您要以学生身份注册还是老师身份注册?输入y表示以学生身份，输入n表示以老师身份。");
                String Y = scanner.next();
                boolean isStudent;
                if (Y.compareToIgnoreCase("y") == 0) {
                    isStudent = true;
                }
                else {
                    isStudent = false;
                }
                System.out.println("请在下一行输入一个令您满意的用户名:");
                String name = scanner.next();
                System.out.println("\n请在下一行输入您的密码:");
                String Password = scanner.next();
                UserInformation userInformation = new UserInformation(name, Password, isStudent);
                if (UserManager.hasUser(userInformation)) {
                    System.out.println("用户已存在，创建用户失败，请直接登录");
                    System.out.println("请在下一行输入您的用户名:");
                    String Name = scanner.next();
                    System.out.println("请在下一行输入您的密码:");
                    String password = scanner.next();
                    UserManager.UserLogin(new UserInformation(Name,  password, false));
                }
                else {
                    UserManager.CreateUser(userInformation);
                    System.out.println("创建用户成功。");
                    return false;
                }
            }
            else {
                throw new IOException();
            }
        }
        catch (IOException e) {
            System.out.println("您输入的选项有误，请重新输入。已重启程序。");
            WelcomeInterface.PrintWelcomeMessage();
        }
        return false;
    }

    public static void main(String[] args) {
        WelcomeInterface.PrintWelcomeMessage();
    }

    @Override
    public boolean run() {
        return WelcomeInterface.PrintWelcomeMessage();
    }

    @Override
    public void dailyRecord() {
        return;
    }
}
