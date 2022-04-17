package JYQ.UserLogin;

import JHY.RegularTable;
import JYQ.Utils;

import java.io.File;

public class UserManager {
    public static final File CWD = new File(System.getProperty("user.dir"));
    public static final File UserFiles = Utils.join(CWD, "UserFiles");
    public static final File UserRepo = Utils.join(UserFiles, "UserRepo");
    public static void CreateUser(UserInformation userInformation) {
        if (!UserRepo.exists()) {
            UserRepo.mkdir();
        }
        File user = Utils.join(UserRepo, userInformation.getUserName());
        if (userInformation.isStudent()) {
            File ClassDir = Utils.join(UserFiles,"Class" + ((Student)userInformation).getClassNumber());
            File StudentDir = Utils.join(ClassDir, userInformation.getUserName());
            File ClassRegularTable = Utils.join(ClassDir, "RegularTable");
            File StudentRegularTable = Utils.join(StudentDir, "StudentRegularTable");
            if (!ClassDir.exists()) {
                ClassDir.mkdir();
            }
            if (!ClassRegularTable.exists()) {
                Utils.writeObject(ClassRegularTable, new RegularTable());
            }
            StudentDir.mkdir();
            Utils.writeObject(StudentRegularTable, new RegularTable());
        }
        else {

        }
        Utils.writeObject(user, userInformation);
    }
    public static boolean hasUser(UserInformation userInformation) {
        File user = Utils.join(UserRepo, userInformation.getUserName());
        return user.exists();
    }
    public static boolean UserLogin(UserInformation userInformation) {
        boolean LoginSuccess = false;
        File user = Utils.join(UserRepo, userInformation.getUserName());
        UserInformation StoredUser = Utils.readObject(user,UserInformation.class);
        /*if (isStudent) {
            StoredUser = Utils.readObject(user, Student.class);
        }
        else {
            StoredUser = Utils.readObject(user, Manager.class);
        }*/
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
