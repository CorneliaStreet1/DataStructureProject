package JYQ.UserLogin;

import JYQ.Utils;

import java.io.File;

public class UserManager {
    public static final File CWD = new File(System.getProperty("user.dir"));
    public static final File UserFiles = Utils.join(CWD, "UserFiles");
    public static void CreateUser(UserInformation userInformation) {
        File userDirectory = Utils.join(UserFiles, userInformation.getUserName());
        userDirectory.mkdir();
        File user = Utils.join(userDirectory, userInformation.getUserName());
        Utils.writeObject(user, userInformation);
    }
    public static boolean hasUser(UserInformation userInformation) {
        File user = Utils.join(UserFiles, userInformation.getUserName());
        return user.exists();
    }
    public static boolean UserLogin(UserInformation userInformation) {
        boolean LoginSuccess = false;
        File userDirectory = Utils.join(UserFiles, userInformation.getUserName());
        File user = Utils.join(userDirectory, userInformation.getUserName());
        UserInformation StoredUser = Utils.readObject(user, UserInformation.class);
        if (userInformation.getUserName().equals(StoredUser.getUserName())
                && userInformation.getPassWord().equals(StoredUser.getPassWord())) {
            System.out.println("登陆成功，您已进入系统");
            LoginSuccess = true;
        }
        else {
            LoginSuccess = false;
        }
        return LoginSuccess;
    }
}
