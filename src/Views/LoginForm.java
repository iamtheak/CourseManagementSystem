package Views;

import Controller.AuthController;
import Model.AuthModels.LoginModel;
import Model.UserModels.UserBaseModel;
import Views.Admin.DashBoard;
import Views.Student.StudentDashboard;
import Views.Teacher.TeacherDashboard;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class LoginForm extends JFrame{
    private JButton loginButton;
    private JPasswordField txtPassword;
    private JTextField txtEmail;
    private JComboBox loginRole;
    private JPanel mainPanel;
    private JButton signUpButton;
    private JPanel imagePanel;

    public LoginForm() {

        this.setSize(790, 400);
        this.setContentPane(this.mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);


        ImageIcon icon = new ImageIcon("src/Images/Student.jpg");


        JLabel imageLabel = new JLabel(icon);
        imagePanel.add(imageLabel);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = txtEmail.getText();
                String password = txtPassword.getText();
                String role = Objects.requireNonNull(loginRole.getSelectedItem()).toString();

                LoginModel login = new LoginModel(email, password);
                AuthController auth = new AuthController();

                String result = auth.Login(role, login);
                System.out.println(result);
                if(!result.equals("Success")){
                    JOptionPane.showMessageDialog(mainPanel, "Login Failed");
                }else{
                    JOptionPane.showMessageDialog(mainPanel, "Login Successful");


                    UserBaseModel user = auth.getUserInfo(email);
                    UserSession.createInstance(user);


                    System.out.println(role);
                    if(role.equals("Admin")){
                        DashBoard form = new DashBoard();
                        dispose();
                    }
                    else if(role.equals("Student")){
                        StudentDashboard form = new StudentDashboard();
                        form.setVisible(true);
                        dispose();
                    }
                else if(role.equals("Teacher")){
                    TeacherDashboard form = new TeacherDashboard();
                    dispose();
                }

                }
            }
        });
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignUp form = new SignUp("Student");
                dispose();
            }
        });
    }

}
