package MiddleWare;

import Model.AuthModels.LoginModel;
import Model.UserModels.UserBaseModel;

public interface IAuth {

    String studentLogin(LoginModel loginModel);
    String TeacherLogin(LoginModel loginModel);
    String adminLogin(LoginModel loginModel);
    UserBaseModel getUserInfo(String email);
    String changePassword(String email, String oldPassword, String newPassword);
    String changeInformation(int userId,String email,String phoneNumber);
}
