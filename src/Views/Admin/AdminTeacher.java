package Views.Admin;

import Controller.AdminController;
import Model.UserModels.TeacherModel;
import Views.SignUp;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminTeacher extends JFrame{
    private JPanel mainPanel;
    private JPanel sidePanel;
    private JPanel TeacherPanel;
    private JButton addTeacherButton;
    private JButton deleteTeacherButton;
    private JPanel teacherTablePanel;
    private JTable teacherTable;
    private JButton assignModuleButton;
    private JButton unassignModuleButton;

    public void loadTeacherTable(){
        AdminController adminController = new AdminController();

        String [] columnNames = {"Teacher Id", "First Name", "Middle Name", "Last Name", "Email", "Phone Number", "Date of Birth"};

        String [][] data = adminController.GetTeachersForTable();
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        teacherTable.setModel(model);

        JScrollPane scrollPane = new JScrollPane(teacherTable);
        teacherTablePanel.add(scrollPane);

        teacherTable.revalidate();
        teacherTable.repaint();
    }
    public void updateTeacherTable(){
        AdminController adminController = new AdminController();

        String [] columnNames = {"Teacher Id", "First Name", "Middle Name", "Last Name", "Email", "Phone Number", "Date of Birth"};

        String [][] data = adminController.GetTeachersForTable();
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        teacherTable.setModel(model);

        teacherTable.revalidate();
        teacherTable.repaint();
    }
    public AdminTeacher(){
        add(mainPanel);
        setTitle("Admin Teacher");
        setSize(1000, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        AdminSidePanel adminSidePanel = new AdminSidePanel(this);
        sidePanel.add(adminSidePanel.getAdminSidePanel());

        loadTeacherTable();
        addTeacherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignUp signUp = new SignUp(AdminTeacher.this);
                signUp.setVisible(true);

            }
        });

        deleteTeacherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = JOptionPane.showInputDialog(AdminTeacher.this,"Enter Teacher Id to delete");
                if (id == null || id.trim().isEmpty() || !id.matches("\\d+")) {
                    JOptionPane.showMessageDialog(null, "You must enter an ID.");
                }
                else{
                    AdminController adminController = new AdminController();

                    TeacherModel teacher = adminController.GetTeacherById(Integer.parseInt(id));
                    if(teacher == null){
                        JOptionPane.showMessageDialog(null, "Teacher Not Found");
                        return;
                    }
                    int teacherId = Integer.parseInt(id);
                    String result = adminController.DeleteTeacher(teacherId);

                    if(result.equals("Success")){
                        JOptionPane.showMessageDialog(null, "Teacher Deleted");
                        updateTeacherTable();
                    }
                    else if(result.equals("Assigned")){
                        JOptionPane.showMessageDialog(null, "Teacher is assigned to a Module. Cannot delete.");
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Teacher Deletion Failed");
                    }
                }
            }
        });
        assignModuleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AssignTeacher assignTeacher = new AssignTeacher(AdminTeacher.this);
                assignTeacher.setVisible(true);
                assignTeacher.setLocationRelativeTo(mainPanel);
            }
        });
        unassignModuleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = JOptionPane.showInputDialog(AdminTeacher.this,"Enter Teacher Id to unassign");
                if (id == null || id.trim().isEmpty() || !id.matches("\\d+") ){
                    JOptionPane.showMessageDialog(null, "You must enter an ID.");
                }
                else{
                    AdminController adminController = new AdminController();

                    TeacherModel teacher = adminController.GetTeacherById(Integer.parseInt(id));

                    if(teacher == null){
                        JOptionPane.showMessageDialog(null, "Teacher Not Found");
                        return;
                    }
                    UnAssignTeacher unAssignTeacher = new UnAssignTeacher(AdminTeacher.this,Integer.parseInt(id));
                    unAssignTeacher.setVisible(true);
                    unAssignTeacher.setLocationRelativeTo(mainPanel);
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new AdminTeacher();
        frame.setVisible(true);
    }
}
