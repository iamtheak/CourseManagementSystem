package Model.UserModels;


import java.time.LocalDate;
import java.util.Date;

/*
 Base User Model For all other user models to extend from.
 Includes first name, middle name, last name, email, and role.
 Public fields for easy access but user role is private for security reasons.
*/
public class UserBaseModel {
    public int userId;
    public String firstName;
    public String middleName;
    public String lastName;
    public String email;
    public String phoneNumber;
    public LocalDate dateOfBirth;
    protected String role;

    protected String password;
    // Getters and Setters for accessing private fields.
    public void setRole(String role){
        this.role = role;
    }
    public String getRole(){
        return role;
    }

    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return password;
    }

}