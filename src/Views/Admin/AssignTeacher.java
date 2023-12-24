package Views.Admin;

import Controller.AdminController;
import Model.UserModels.TeacherModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AssignTeacher extends  JDialog{
    private JPanel mainPanel;
    private JComboBox modules;
    private JTextField teacher;
    private JButton assignTeacher;

    public AssignTeacher(AdminTeacher parent){
        add(mainPanel);
        setTitle("Assign Teacher");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parent);


        AdminController adminController = new AdminController();

        String[] moduleList = adminController.GetModuleNames();

        DefaultComboBoxModel moduleModel = new DefaultComboBoxModel(moduleList);

        modules.setModel(moduleModel);


        assignTeacher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String module = modules.getSelectedItem().toString();
                String teacherId = teacher.getText();

                if(module.equals("Select Module")){
                    JOptionPane.showMessageDialog(mainPanel, "Please select a module");
                }else if(teacherId.equals("")||!teacherId.matches(".*\\d.*")){
                    JOptionPane.showMessageDialog(mainPanel, "Please enter teacher id");
                }else{
                    int id = Integer.parseInt(teacherId);
                    TeacherModel teacher = adminController.GetTeacherById(id);
                    if(teacher == null){
                        JOptionPane.showMessageDialog(null, "Teacher Not Found");
                        return;
                    }
                    String result = adminController.AssignTeacher(module,id);
                    switch (result) {
                        case "Success" -> {
                            JOptionPane.showMessageDialog(mainPanel, "Teacher Assigned Successfully");
                            dispose();
                        }
                        case "LimitReached" -> JOptionPane.showMessageDialog(mainPanel, "Already Assigned 4 Modules");
                        case "NotFound" -> JOptionPane.showMessageDialog(mainPanel, "Teacher Not Found");
                        case "Assigned" -> JOptionPane.showMessageDialog(mainPanel, "Teacher Already Assigned");
                        default -> JOptionPane.showMessageDialog(mainPanel, "Error Occurred");
                    }
                }
            }
        });
    }
}
