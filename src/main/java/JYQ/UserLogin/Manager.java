package JYQ.UserLogin;

import java.io.Serializable;

public class Manager extends UserInformation implements Serializable {
    public Manager(String UserName, String Password , Boolean isStudent) {
        super(UserName, Password, isStudent);
    }
}
