package Utiliy;

public class Validators {
    public static boolean DateValidator(String date) {
       return date.matches("[0-9]{2}/[0-9]{2}/[0-9]{4}");
    }
    public static boolean EmailValidator(String email) {
        return email.matches("[A-Za-z0-9+_.-]+@(.+)$");
    }
    public static boolean PhoneValidator(String phone) {
        return phone.matches("9[7-8][0-9]{8}");
    }
    public static boolean NameValidator(String name) {
        return name.matches("[a-zA-Z\\s]{4,}");
    }

}
