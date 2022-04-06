package JYQ.UserLogin;

import java.io.Serializable;

public class UserInformation implements Serializable {
    //用户名是不能更改的，因为需要使用用户名来和用户数据绑定，更改用户名
    private final String UserName;
    private String PassWord;
    public UserInformation(String userName, String PassWord) {
        this.UserName = userName;
        this.PassWord = PassWord;
    }
    public String getUserName() {
        return this.UserName;
    }
    public String getPassWord() {
        return this.PassWord;
    }

    /**
     * 用于更改用户密码。
     * @param OldPassword 旧密码
     * @param newPassword 新密码
     */
    public void setPassWord(String OldPassword, String newPassword) {
        if (OldPassword.equals(this.PassWord)) {
            this.PassWord = newPassword;
            System.out.println("修改密码成功，请重新启动程序以重新登录。");
            System.exit(0);
        }
        else {
            System.out.println("旧密码错误，请重试");
        }
    }
}
