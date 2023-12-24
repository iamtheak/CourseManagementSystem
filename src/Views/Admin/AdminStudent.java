package Views.Admin;

import javax.swing.*;

public class AdminStudent extends JFrame{
    private JPanel mainPanel;
    private JPanel sidePanel;
    private JButton viewResultButton;
    private JTable table1;
    private JPanel studentTablePanel;

    public AdminStudent(){
        add(mainPanel);
        setTitle("Admin Student");
        setSize(1000, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        AdminSidePanel adminSidePanel = new AdminSidePanel(this);
        sidePanel.add(adminSidePanel.getAdminSidePanel());
    }
}
