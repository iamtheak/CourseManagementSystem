package Views.Admin;

import Controller.AdminController;
import Model.CourseModels.CourseModule;

import javax.swing.*;

public class SemesterView extends JFrame{
    private JLabel SemesterName;
    private JComboBox module1;
    private JPanel mainPanel;
    private JComboBox module2;
    private JComboBox module3;
    private JComboBox module4;

    private int semesterNumber;

    public JPanel getMainPanel(){
        return mainPanel;
    }

    public boolean isModuleFilled(){
        return module1.getSelectedIndex() != 0 && module2.getSelectedIndex() != 0 && module3.getSelectedIndex() != 0 && module4.getSelectedIndex() != 0;
    }
    public SemesterView(int semesterNumber){

        this.semesterNumber = semesterNumber;
        SemesterName.setText("Semester " + semesterNumber);
        add(mainPanel);

        AdminController adminController = new AdminController();
        String moduleNames[] = adminController.GetModuleNames();


        module1.setModel(new DefaultComboBoxModel(moduleNames));
        module2.setModel(new DefaultComboBoxModel(moduleNames));
        module3.setModel(new DefaultComboBoxModel(moduleNames));
        module4.setModel(new DefaultComboBoxModel(moduleNames));
    }
    public CourseModule getModule1(){

        CourseModule courseModule = new CourseModule();
        courseModule.moduleName = module1.getSelectedItem().toString();
        courseModule.ModuleNumber = 1;
        courseModule.semester = semesterNumber;
        courseModule.isOptional = false;
        return courseModule;
    }
    public CourseModule getModule2(){
        CourseModule courseModule = new CourseModule();
        courseModule.moduleName = module2.getSelectedItem().toString();
        courseModule.ModuleNumber = 2;
        courseModule.semester = semesterNumber;
        courseModule.isOptional = false;
        return courseModule;
    }
    public CourseModule getModule3(){
        CourseModule courseModule = new CourseModule();
        courseModule.moduleName = module3.getSelectedItem().toString();
        courseModule.ModuleNumber = 3;
        courseModule.semester = semesterNumber;
        courseModule.isOptional = false;
        return courseModule;
    }
    public CourseModule getModule4(){
        CourseModule courseModule = new CourseModule();
        courseModule.moduleName = module4.getSelectedItem().toString();
        courseModule.ModuleNumber = 4;
        courseModule.semester = semesterNumber;
        courseModule.isOptional = false;
        return courseModule;
    }
    public void setModule1(String moduleName){
        module1.setSelectedItem(moduleName);
    }
    public void setModule2(String moduleName){
        module2.setSelectedItem(moduleName);
    }
    public void setModule3(String moduleName){
        module3.setSelectedItem(moduleName);
    }
    public void setModule4(String moduleName){
        module4.setSelectedItem(moduleName);
    }
}
