package Views.Admin;

import Controller.AdminController;
import Model.CourseModels.CMSModule;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
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
    private JTextField searchBar;
    private JButton assignTeacher;
    private TableRowSorter<TableModel> rowSorter;

    public void updateTable(){
        AdminController adminController = new AdminController();

        String[] columnNames = {"Module Id","Module Name", "Module Code", "Credits", "Pass Percent"};
        String[][] data = adminController.GetModulesForTable();

        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        modulesTable.setModel(model);

        rowSorter.setModel(modulesTable.getModel());
        modulesTable.revalidate();
        modulesTable.repaint();

    }
    public void loadTable() {
        AdminController adminController = new AdminController();

        String[] columnNames = {"Module Id","Module Name", "Module Code", "Credits", "Pass Percent"};
        String[][] data = adminController.GetModulesForTable();

        DefaultTableModel model = new DefaultTableModel(data, columnNames);


        modulesTable.setModel(model);
        rowSorter = new TableRowSorter<>(modulesTable.getModel());
        modulesTable.setRowSorter(rowSorter);

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
                if (id == null || id.trim().isEmpty() ||!id.matches("\\d+")) {
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
       searchBar.getDocument().addDocumentListener(new DocumentListener() {
           @Override
           public void insertUpdate(DocumentEvent e) {
               String text = searchBar.getText();

               if (text.trim().length() == 0) {
                   rowSorter.setRowFilter(null);
               } else {
                   rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
               }
           }

           @Override
           public void removeUpdate(DocumentEvent e) {
               String text = searchBar.getText();

               if (text.trim().length() == 0) {
                   rowSorter.setRowFilter(null);
               } else {
                   rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
               }
           }
           @Override
           public void changedUpdate(DocumentEvent e) {
               throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
           }
       });


    }

}
