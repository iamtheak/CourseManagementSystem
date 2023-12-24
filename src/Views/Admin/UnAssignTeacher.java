package Views.Admin;

import Controller.AdminController;

import javax.swing.*;

public class UnAssignTeacher extends JDialog{
    private JTextField txtTeacherId;
    private JComboBox modules;
    private JButton unassignButton;
    private JPanel mainPanel;

    public UnAssignTeacher(AdminTeacher parent,int teacherId){
        add(mainPanel);
        setTitle("Unassign Teacher");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parent);

        AdminController adminController = new AdminController();
        String[] moduleList = adminController.GetAssignedModuleNames(teacherId);

        DefaultComboBoxModel moduleModel = new DefaultComboBoxModel(moduleList);
        modules.setModel(moduleModel);

        txtTeacherId.setText(String.valueOf(teacherId));
        txtTeacherId.setEnabled(false);


        unassignButton.addActionListener(e -> {
            String module = modules.getSelectedItem().toString();
            String result = adminController.UnAssignTeacher(module,teacherId);
            switch (result) {
                case "Success" -> {
                    JOptionPane.showMessageDialog(mainPanel, "Teacher Unassigned Successfully");
                    dispose();
                }
                case "NotFound" -> JOptionPane.showMessageDialog(mainPanel, "Module Not Found");
                default -> JOptionPane.showMessageDialog(mainPanel, "Error Occurred");
            }
        });
    }
}
