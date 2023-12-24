package Model.UserModels;

/*
 Base User Model For all other user models to extend from.
 Includes first name, middle name, last name, email, and role.
 Public fields for easy access but user role is private for security reasons.
*/
class UserBaseModel {
    public String firstName;
    public String middleName;
    public String lastName;
    public String email;
    private String role;

    // Getters and Setters for accessing private fields.
    public void setRole(String role){
        this.role = role;
    }
    public String getRole(){
        return role;
    }


}