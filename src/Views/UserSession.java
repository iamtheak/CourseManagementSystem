package Views;

import Model.UserModels.UserBaseModel;

public class UserSession {
    private static UserSession instance;

    private UserBaseModel user;


    private UserSession(UserBaseModel user) {
        this.user = user;
    }

    public static void createInstance(UserBaseModel user) {
        if(instance == null) {
            instance = new UserSession(user);
        }
    }
    public static  void destroyInstance() {
        instance = null;
    }

    public static UserSession getInstance() {
        return instance;
    }

    public int getUserId() {
        return user.userId;
    }
    public String getEmail() {
        return user.email;
    }
    public String getPhoneNumber() {
        return user.phoneNumber;
    }

}

