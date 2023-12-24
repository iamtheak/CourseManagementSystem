package Views;
public class UserSession {
    private static UserSession instance;
    private int userId;
    private String userName;
    private String userRole;
    private UserSession(int userId,String userName,String userRole) {
        this.userId = userId;
    }

    public static void createInstance(int userId,String userName,String userRole) {
        if(instance == null) {
            instance = new UserSession(userId,userName,userRole);
        }
    }

    public static UserSession getInstance() {
        return instance;
    }

    public int getUserId() {
        return userId;
    }
}

