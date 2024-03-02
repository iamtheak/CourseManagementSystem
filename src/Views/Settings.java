package Views;

import Controller.AuthController;

import javax.swing.*;
import java.awt.*;


public class Settings extends JFrame{
    private JPanel mainPanel;
    private JTextField txtEmail;
    private JTextField txtPhoneNumber;
    private JButton editProfileButton;
    private JTextField txtOldPassword;
    private JTextField textNewPassword;
    private JButton changePassword;
    private JButton logoutButton;
    private JTextField txtConfirmPassword;


    public void setData(){
        UserSession userSession = UserSession.getInstance();
        txtEmail.setText(userSession.getEmail());
        txtPhoneNumber.setText(userSession.getPhoneNumber());
    }
    public Settings(JFrame parentFrame){
        add(mainPanel);
        setSize(700,400);
        setTitle("Settings");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);

        setData();
        logoutButton.addActionListener(e -> {
            UserSession.destroyInstance();

            Frame[] frames = JFrame.getFrames();

            for (Frame frame : frames) {
                frame.dispose();
            }
            parentFrame.dispose();

            LoginForm loginForm = new LoginForm();
            dispose();
        });
        changePassword.addActionListener(e -> {
            String oldPassword = txtOldPassword.getText();
            String newPassword = textNewPassword.getText();
            String confirmPassword = txtConfirmPassword.getText();

            if(oldPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()){
                JOptionPane.showMessageDialog(mainPanel, "Please fill all the fields");
            }else if(!newPassword.equals(confirmPassword)){
                JOptionPane.showMessageDialog(mainPanel, "Passwords do not match");
            }else{

                if(!newPassword.matches("[a-zA-Z]+[@|&][0-9]+$")){
                    JOptionPane.showMessageDialog(mainPanel, "Password should be in format of characters @ or & and end with a number");
                    return;
                }
                if(oldPassword.equals(newPassword)){
                    JOptionPane.showMessageDialog(mainPanel, "New password should be different from old password");
                    return;
                }


                AuthController authController = new AuthController();
                String result = authController.ChangePassword(UserSession.getInstance().getEmail(), oldPassword, newPassword);
                if(result.equals("Success")){
                    JOptionPane.showMessageDialog(mainPanel, "Password Changed Successfully");
                }else{
                    JOptionPane.showMessageDialog(mainPanel, "Password Change Failed");
                }
            }
        });
        editProfileButton.addActionListener(e -> {
            String email = txtEmail.getText();
            String phoneNumber = txtPhoneNumber.getText();

            if(email.isEmpty() || phoneNumber.isEmpty()){
                JOptionPane.showMessageDialog(mainPanel, "Please fill all the fields");
            }else{
                if(!email.matches("[A-Za-z0-9+_.-]+@(.+)$")){
                    JOptionPane.showMessageDialog(mainPanel, "Email must be in valid format");
                    return;
                }
                if(!phoneNumber.matches("9[7-8][0-9]{8}")){
                    JOptionPane.showMessageDialog(mainPanel, "Phone number must be 10 digits long");
                    return;
                }

                AuthController authController = new AuthController();
                String result = authController.ChangeInformation(UserSession.getInstance().getUserId(), email, phoneNumber);
                if(result.equals("Success")){
                    JOptionPane.showMessageDialog(mainPanel, "Information Changed Successfully");

                    UserSession.destroyInstance();

                    UserSession.createInstance(authController.getUserInfo(email));
                }else{
                    JOptionPane.showMessageDialog(mainPanel, "Information Change Failed");
                }
            }
        });
    }
}
