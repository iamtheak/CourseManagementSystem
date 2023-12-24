package Views;

import Controller.AuthController;
import Model.AuthModels.SignUpModel;
import Views.Admin.AdminTeacher;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class SignUp extends JFrame{

    AuthController _auth = new AuthController();

    public SignUp(AdminTeacher adminTeacher){
        this.setContentPane(SignUpPanel);
        this.setSize(500, 500);
        this.setTitle("Sign Up");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        btnLogin.setVisible(false);
        signUpSelector.setVisible(false);
        signupSelectorLabel.setVisible(false);

        txtPassword.setVisible(false);
        txtConfirmPassword.setVisible(false);
        signUpTitle.setText("Add a Teacher");
        labelPassword.setVisible(false);
        lableConfirmPassword.setVisible(false);

        btnSignUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignUpModel model = new SignUpModel();
                model.email = txtEmail.getText();
                model.password = txtPassword.getText();
                model.firstName = textFirstname.getText();
                model.lastName = textLastname.getText();
                model.middleName = textMiddlename.getText();
                model.phone = txtPhoneNumber.getText();
                model.dateOfBirth = txtDateOfBirth.getText();
                model.password = "Teacher@123";
                model.confirmPassword = "Teacher@123";

                String result = _auth.TeacherSignup(model);

                if(result.equals("Success")){
                    JOptionPane.showMessageDialog(null, "Sign Up Successful");
                    adminTeacher.updateTeacherTable();
                    dispose();
                }
                else if(result.equals("Exists")){
                    JOptionPane.showMessageDialog(null, "Email Already Exists");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Sign Up Failed"+result);
                }
            }
        });
    }
    public SignUp(String role){
        if(role.equals("Student")){
            this.signUpTitle.setText("Sign Up as " + role);

            String [] courses = _auth.GetCourseListForSignUp();
            signUpSelector.setModel(new DefaultComboBoxModel(courses));
        }
        else{
            this.signUpTitle.setText("Add a" + role);
        }
        this.setContentPane(SignUpPanel);
        this.setSize(500, 500);
        this.setTitle("Sign Up");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginForm form = new LoginForm();
                dispose();
            }
        });

        btnSignUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignUpModel model = new SignUpModel();
                model.email = txtEmail.getText();
                model.password = txtPassword.getText();
                model.firstName = textFirstname.getText();
                model.lastName = textLastname.getText();
                model.middleName = textMiddlename.getText();
                model.phone = txtPhoneNumber.getText();
                model.dateOfBirth = txtDateOfBirth.getText();
                model.confirmPassword = txtConfirmPassword.getText();
                String course = signUpSelector.getSelectedItem().toString();

                String result;

                if(role.equals("Student")){
                    result = _auth.StudentSignup(model, course);

                    if(result.equals("Success")){
                        JOptionPane.showMessageDialog(null, "Sign Up Successful");

                        dispose();
                        LoginForm form = new LoginForm();
                    }
                    else if(result.equals("Exists")){
                        JOptionPane.showMessageDialog(null, "Email Already Exists");
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Sign Up Failed"+result);
                    }
                }
                else if(role.equals("Teacher")){
                    result = _auth.TeacherSignup(model);
                }

            }
        });
    }
    private JPanel SignUpPanel;
    private JTextField textFirstname;
    private JTextField textMiddlename;
    private JTextField textLastname;
    private JTextField txtEmail;
    private JTextField txtPhoneNumber;
    private JTextField txtDateOfBirth;
    private JTextField txtPassword;
    private JTextField txtConfirmPassword;
    private JComboBox signUpSelector;
    private JButton btnSignUp;
    private JButton btnLogin;
    private JLabel signupSelectorLabel;
    private JLabel signUpTitle;
    private JLabel lableConfirmPassword;
    private JLabel labelPassword;
}
