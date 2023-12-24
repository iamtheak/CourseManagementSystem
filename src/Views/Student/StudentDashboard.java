package Views.Student;

import javax.swing.*;

public class StudentDashboard extends JFrame{
    private JPanel mainPanel;
    private JPanel topPanel;
    private JLabel userGreetLabel;
    private JLabel courseLabel;
    private JButton enrollModulesButton;
    private JButton viewResultButton;
    private JPanel studentModuleTablePanel;
    private JScrollPane studentTableScrollPane;
    private JTable studentModuleTable;

    public StudentDashboard(){
        add(mainPanel);
        setTitle("Student Dashboard");
        setSize(1000, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

}
