package JYQ.UserLogin;

import java.io.IOException;
import java.util.Scanner;

public class WelcomeInterface {
    public static void PrintWelcomeMessage() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=====================欢迎来到北京邮电大学校园导航及课程管理系统!===============");
        System.out.println("输入1选择已有用户登录，输入2选择新用户注册:");
        int option = scanner.nextInt();
        try {
            if (option == 1) {
                System.out.println("请注意，由于Windows的文件系统是大小写不敏感的，所以用户名也是大小写不敏感的。");
                System.out.println("请在下一行输入您的用户名:");
                String name = scanner.next();
                System.out.println("请在下一行输入您的密码:");
                String Password = scanner.next();
                UserInformation ReadInUserInformation = new UserInformation(name, Password);
                if (UserManager.hasUser(ReadInUserInformation)) {
                    boolean LoginSuccess = UserManager.UserLogin(ReadInUserInformation);
                    if (!LoginSuccess) {
                        System.out.println("用户名正确但密码错误，程序已退出。");
                        System.exit(0);
                    }
                }
                else {
                    System.out.println("登录失败，用户不存在。程序已退出。");
                    System.exit(0);
                }
            }
            else if (option == 2) {
                System.out.println("请注意，由于Windows的文件系统是大小写不敏感的，所以用户名也是大小写不敏感的。");
                System.out.println("请在下一行输入一个令您满意的用户名:");
                String name = scanner.next();
                System.out.println("\n请在下一行输入您的密码:");
                String Password = scanner.next();
                UserInformation userInformation = new UserInformation(name, Password);
                if (UserManager.hasUser(userInformation)) {
                    System.out.println("用户已存在，创建用户失败，请直接登录");
                    System.out.println("请在下一行输入您的用户名:");
                    String Name = scanner.next();
                    System.out.println("请在下一行输入您的密码:");
                    String password = scanner.next();
                    UserManager.UserLogin(new UserInformation(Name,  password));
                }
                else {
                    UserManager.CreateUser(userInformation);
                    System.out.println("创建用户成功，请重启程序以登录。");
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
    }

    public static void main(String[] args) {
        WelcomeInterface.PrintWelcomeMessage();
    }
}
