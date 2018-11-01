package ch20;

import ch20.unitTest.UseCase;

import java.util.List;

/**
 * @author: yuki
 * @date: 2018/10/27
 */
public class PasswordUtil {

    @UseCase(id=46, description = "password must contain at least one numeric")
    public boolean validatePassword(String password){
        return password.matches("\\w*\\d\\w*");
    }

    @UseCase(id=47)
    public String encryptPassword(String password){
        return new StringBuilder(password).reverse().toString();
    }

    @UseCase(id=48, description = "new password cannot equal previously used ones")
    public boolean checkForNewPassword(List<String> prevPasswords, String password){
        return !prevPasswords.contains(password);
    }

}
