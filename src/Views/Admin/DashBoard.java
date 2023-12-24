package Views.Admin;

import Model.UserModels.UserBaseModel;
import Views.UserSession;

import javax.swing.*;

public class DashBoard extends JFrame{
    private JLabel AdminCourseNumber;
    private JLabel AdminStudentNumber;
    private JLabel AdminTeacherNumber;
    private JPanel dashboardMainPanel;
    private JPanel sidePanel;

    public DashBoard(){
        AdminSidePanel sidePanel1 = new AdminSidePanel(this);
        setContentPane(dashboardMainPanel);
        setTitle("Admin Dashboard");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        this.setVisible(true);
        JPanel sidePanel2 = sidePanel1.getAdminSidePanel();
        sidePanel.add(sidePanel2);


        int userId = UserSession.getInstance().getUserId();

        System.out.println(userId);
    }
}
