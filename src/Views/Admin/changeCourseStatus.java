package Views.Admin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Controller.AdminController;

public class changeCourseStatus extends JDialog {
    private JPanel mainPanel;
    private JTextField courseId;
    private JComboBox status;
    private JButton changeStatusButton;

    public changeCourseStatus(AdminCourse adminCourse){

        this.add(mainPanel);
        setTitle("Change Course Status");
        setSize(400, 200);
        setLocationRelativeTo(adminCourse);
        changeStatusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminController adminController = new AdminController();

                if(courseId.getText().isEmpty() || status.getSelectedItem().toString().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Please fill all the fields");
                    return;
                }
                String status = (String) changeCourseStatus.this.status.getSelectedItem();
                int courseId = Integer.parseInt(changeCourseStatus.this.courseId.getText());

                String result = adminController.ChangeStatus(courseId, status);
                if(result.equals("Success")){
                    JOptionPane.showMessageDialog(null, "Course Status Changed");
                    adminCourse.updateCourseTable();
                    changeCourseStatus.this.dispose();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Course Status Change Failed");
                }
            }
        });
    }
}
