package Views.Admin;

import Controller.AdminController;
import Model.CourseModels.CMSModule;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminModule extends JFrame {

    private JPanel mainPanel;
    private JButton addModule;
    private JButton editModuleButton;
    private JButton deleteModuleButton;
    private JTable modulesTable;
    private JPanel sidePanel;
    private JPanel tablePanel;
    private JButton assignTeacher;


    public void updateTable(){
        AdminController adminController = new AdminController();

        String[] columnNames = {"Module Id","Module Name", "Module Code", "Credits", "Pass Percent"};
        String[][] data = adminController.GetModulesForTable();

        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        modulesTable.setModel(model);
        modulesTable.revalidate();
        modulesTable.repaint();


    }
    public void loadTable(){
        AdminController adminController = new AdminController();

        String[] columnNames = {"Module Id","Module Name", "Module Code", "Credits", "Pass Percent"};
        String[][] data = adminController.GetModulesForTable();

        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        modulesTable.setModel(model);

        JScrollPane scrollPane = new JScrollPane(modulesTable);
        tablePanel.add(scrollPane);

        modulesTable.revalidate();
        modulesTable.repaint();
    }
    public AdminModule(){
        add(mainPanel);
        setTitle("Admin Module");
        setSize(1000, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        AdminSidePanel adminSidePanel = new AdminSidePanel(this);
        sidePanel.add(adminSidePanel.getAdminSidePanel());

        loadTable();

        AdminController adminController = new AdminController();
        addModule.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ModuleAddEdit moduleAddEdit = new ModuleAddEdit(AdminModule.this);
                moduleAddEdit.setVisible(true);
                moduleAddEdit.setLocationRelativeTo(mainPanel);

            }
        });
        editModuleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = JOptionPane.showInputDialog(null, "Enter ID:");
                if (id == null || id.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "You must enter an ID.");
                } else {
                    int moduleId = Integer.parseInt(id);
                    CMSModule module = adminController.GetModuleById(moduleId);

                    if(module != null){
                        ModuleAddEdit moduleAddEdit = new ModuleAddEdit(AdminModule.this,module);
                        moduleAddEdit.setVisible(true);
                        moduleAddEdit.setLocationRelativeTo(mainPanel);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Module Not Found");
                    }

                }

            }
        });
        deleteModuleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = JOptionPane.showInputDialog(null, "Enter ID:");
                if (id == null || id.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "You must enter an ID.");
                } else {
                    int moduleId = Integer.parseInt(id);
                    String result = adminController.DeleteModule(moduleId);
                    if(result.equals("Success")){
                        JOptionPane.showMessageDialog(null, "Module Deleted Successfully");
                        updateTable();
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Module Delete Failed");
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        AdminModule adminModule = new AdminModule();
        adminModule.setVisible(true);
    }


}
