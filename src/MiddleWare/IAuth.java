package MiddleWare;

import Model.AuthModels.LoginModel;
import Model.UserModels.UserBaseModel;

public interface IAuth {

    String studentLogin(LoginModel loginModel);
    String TeacherLogin(LoginModel loginModel);
    String adminLogin(LoginModel loginModel);
    UserBaseModel getUserInfo(String email);
}
