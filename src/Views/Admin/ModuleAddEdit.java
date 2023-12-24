package Views.Admin;


import Controller.AdminController;
import Model.CourseModels.CMSModule;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModuleAddEdit extends JDialog{
    private JTextField moduleName;
    private JTextField moduleCode;
    private JTextField passPercent;
    private JTextField credits;
    private JButton addEditButton;
    private JPanel mainPanel;
    private AdminModule adminModule;

    private Boolean validateForm(){
        if(moduleName.getText().isEmpty() || moduleCode.getText().isEmpty() || passPercent.getText().isEmpty() || credits.getText().isEmpty()){
            return false;
        }
        if(!passPercent.getText().matches("[0-9]+") || !credits.getText().matches("[0-9]+")){
            return false;
        }
        return true;
    }
    public ModuleAddEdit(AdminModule module) {

        this.adminModule = module;

        this.setSize(300, 300);
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        addEditButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!validateForm()){
                    JOptionPane.showMessageDialog(mainPanel, "Please Fill All Fields");
                    return;
                }
                String name = moduleName.getText();
                String code = moduleCode.getText();
                String passPercentText = passPercent.getText();
                String creditsText = credits.getText();

                int pass = Integer.parseInt(passPercentText);
                int credit = Integer.parseInt(creditsText);

                AdminController admin = new AdminController();
                String result = admin.AddModule(name, code, credit, pass);
                if(result.equals("Success")) {
                    JOptionPane.showMessageDialog(mainPanel, "Module Added Successfully");
                    adminModule.updateTable();
                    dispose();
                }else{
                    JOptionPane.showMessageDialog(mainPanel, "Module Add Failed");
                }

            }
        });
    }
    public ModuleAddEdit(AdminModule module, CMSModule moduleData) {

        this.adminModule = module;

        this.setSize(300, 300);
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        moduleName.setText(moduleData.moduleName);
        moduleCode.setText(moduleData.moduleCode);
        passPercent.setText(String.valueOf(moduleData.passPercent));
        credits.setText(String.valueOf(moduleData.credits));
        addEditButton.setText("Edit");
        addEditButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!validateForm()){
                    JOptionPane.showMessageDialog(mainPanel, "Please Fill All Fields");
                    return;
                }
                String name = moduleName.getText();
                String code = moduleCode.getText();
                String passPercentText = passPercent.getText();
                String creditsText = credits.getText();

                int pass = Integer.parseInt(passPercentText);
                int credit = Integer.parseInt(creditsText);

                AdminController admin = new AdminController();
                String result = admin.EditModule(moduleData.moduleId, name, code, credit, pass);
                if(result.equals("Success")) {
                    JOptionPane.showMessageDialog(mainPanel, "Module Edited Successfully");
                    adminModule.updateTable();
                    dispose();
                }else{
                    JOptionPane.showMessageDialog(mainPanel, "Module Edit Failed");
                }
            }
        });
    }

}
