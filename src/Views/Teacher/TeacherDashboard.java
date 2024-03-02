package Views.Teacher;

import Controller.TeacherController;
import Model.UserModels.TeacherModel;
import Views.Settings;
import Views.UserSession;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TeacherDashboard extends JFrame {
    private JPanel mainPanel;
    private JPanel teacherModuleTablePanel;
    private JTable teacherModuleTable;
    private JButton viewStudentsButton;
    private JButton settings;


    public void loadTable(){
        int userId = UserSession.getInstance().getUserId();

        TeacherModel teacher = new TeacherController().GetTeacherByUserId(userId);
        TeacherController teacherController = new TeacherController();
        String[] columnNames = {"Module Id", "Module Name","Pass Percent"};
        String[][] data = teacherController.GetModulesForTable(teacher.teacherId);

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        teacherModuleTable.setModel(model);

        JScrollPane scrollPane = new JScrollPane(teacherModuleTable);
        teacherModuleTablePanel.add(scrollPane);
        teacherModuleTable.revalidate();
        teacherModuleTable.repaint();
    }

    public void updateTable(){
        int userId = UserSession.getInstance().getUserId();

        TeacherModel teacher = new TeacherController().GetTeacherByUserId(userId);
        TeacherController teacherController = new TeacherController();
        String[] columnNames = {"Module Id", "Module Name","Pass Percent"};
        String[][] data = teacherController.GetModulesForTable(teacher.teacherId);

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        teacherModuleTable.setModel(model);
        teacherModuleTable.revalidate();
        teacherModuleTable.repaint();
    }
    public TeacherDashboard() {
        add(mainPanel);
        setSize(700, 400);
        setTitle("Teacher Dashboard");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);


        loadTable();

        int userId = UserSession.getInstance().getUserId();

        TeacherModel teacher = new TeacherController().GetTeacherByUserId(userId);
        TeacherController teacherController = new TeacherController();

        viewStudentsButton.addActionListener(e -> {
           String moduleId = JOptionPane.showInputDialog("Enter Module Id");
              if(moduleId.isEmpty() || !moduleId.matches("\\d+")){
                JOptionPane.showMessageDialog(null,"Invalid Module Id");

              }
              else{
                  if(!teacherController.IsModuleExists(Integer.parseInt(moduleId))) {
                      JOptionPane.showMessageDialog(null, "Module Not Found");
                  }
                  else{
                      new TeacherStudentView(Integer.parseInt(moduleId));
                  }
              }
        });
        settings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Settings settings = new Settings(TeacherDashboard.this);
            }
        });
    }
}
