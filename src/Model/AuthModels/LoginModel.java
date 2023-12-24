package Model.AuthModels;

public class LoginModel {
    public String email;
    private String password;
    public LoginModel(String email, String password){
        this.email = email;
        this.password = password;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }
}
