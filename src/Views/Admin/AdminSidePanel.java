package Views.Admin;

import Views.LoginForm;
import Views.Settings;
import Views.UserSession;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminSidePanel extends JFrame{
    private JPanel mainPanel;
    private JButton adminDashboard;
    private JButton adminCourses;
    private JButton adminTeacher;
    private JButton settings;
    private JPanel AdminSidePanel;
    private JButton modulesButton;


    public AdminSidePanel(JFrame parent){
        add(AdminSidePanel);
        setTitle("Admin Side Panel");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        adminDashboard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DashBoard dashBoard = new DashBoard();
                dashBoard.setVisible(true);
                parent.dispose();
            }
        });

        adminCourses.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminCourse adminCourse = new AdminCourse();
                adminCourse.setVisible(true);
                parent.dispose();
            }
        });

        adminTeacher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminTeacher adminTeacher = new AdminTeacher();
                adminTeacher.setVisible(true);
                parent.dispose();
            }
        });
//
        modulesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminModule adminModule = new AdminModule();
                adminModule.setVisible(true);
                parent.dispose();
            }
        });

        settings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Settings adminSettings = new Settings(parent);
                adminSettings.setVisible(true);
            }
        });

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JPanel getAdminSidePanel() {
        return AdminSidePanel;
    }

}


