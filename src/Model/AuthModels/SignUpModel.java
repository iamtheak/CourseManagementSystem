package Model.AuthModels;

import Utiliy.Validators;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class SignUpModel {
    public String firstName;
    public String lastName;
    public String middleName;
    public String email;
    public String phone;

    public String password;
    public String confirmPassword;
    public String dateOfBirth;
    public String errorMessage;
    public boolean checkValidity(){
        return isFirsNameValid() && isLastNameValid() && isMiddleNameValid() && isEmailValid() && isPhoneValid() && isDateOfBirthValid() && isPasswordValid() && isPasswordMatched();
    }
    public boolean isFirsNameValid(){
       boolean isValid = Validators.NameValidator(firstName);
       if(!isValid){
              errorMessage = "Name must be at least 4 characters long and must contain only alphabets";
       }
       return isValid;
    }
    public boolean isLastNameValid(){
        boolean isValid = Validators.NameValidator(lastName);
        if(!isValid){
            errorMessage = "Name must be at least 4 characters long and must contain only alphabets";
        }
        return isValid;
    }
    public boolean isMiddleNameValid(){

        if(middleName == null || middleName.isEmpty()){
            return true;
        }
        boolean isValid = Validators.NameValidator(middleName);
        if(!isValid){
            errorMessage = "Name must be at least 4 characters long and must contain only alphabets";
        }
        return isValid;
    }
    public boolean isEmailValid(){
        boolean isValid =  Validators.EmailValidator(email);

        if(!isValid){
            errorMessage = "Email must be in valid format";
        }
        return isValid;
    }
    public boolean isPhoneValid(){
        boolean isValid =  Validators.PhoneValidator(phone);
        if(!isValid){
            errorMessage = "Phone number must be 10 digits long";
        }
        return isValid;
    }
    public boolean isDateOfBirthValid(){
        boolean isValid = Validators.DateValidator(dateOfBirth);
        if(!isValid){
            errorMessage = "Date of birth must be in dd/mm/yyyy format";
            return isValid;
        }
        LocalDate dob = LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate currentDate = LocalDate.now();

        int years = Period.between(dob,currentDate).getYears();

        if(years < 18){
            errorMessage = "You must be 21 years old to sign up";
            isValid = false;
        }
        return isValid;
    }
    public boolean isPasswordValid(){
        boolean isValid = Pattern.matches("[a-zA-Z]+[@|&][0-9]+$",password);
        if(!isValid){
            errorMessage = "Password should be in format of characters @ or & and end with a number";
        }
        return isValid;
    }
    public boolean isPasswordMatched(){
        boolean isValid =  password.equals(confirmPassword);
        if(!isValid){
            errorMessage = "Password and confirm password must be same";
        }
        return isValid;
    }

}
