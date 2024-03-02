package Views.Admin;

import Controller.AdminController;
import Model.CourseModels.Course;
import Model.CourseModels.CourseModule;

import javax.swing.*;

public class SemViewWithElective extends JFrame{
    private JPanel mainPanel;
    private JComboBox module1;
    private JComboBox module2;
    private JComboBox module3Option1;
    private JComboBox module4Option1;
    private JComboBox module4Option2;
    private JComboBox module3Option2;
    private JLabel semesterName;

    private int semesterNumber;


    public boolean isModuleFilled(){
        return module1.getSelectedIndex() != 0 && module2.getSelectedIndex() != 0 && module3Option1.getSelectedIndex() != 0 && module4Option1.getSelectedIndex() != 0;
    }
    public CourseModule getModule1(){
        CourseModule courseModule = new CourseModule();
        courseModule.moduleName = module1.getSelectedItem().toString();
        courseModule.moduleNumber = 1;
        courseModule.semester = semesterNumber;
        courseModule.isOptional = false;
        return courseModule;
    }
    public CourseModule getModule2(){
        CourseModule courseModule = new CourseModule();
        courseModule.moduleName = module2.getSelectedItem().toString();
        courseModule.moduleNumber = 2;
        courseModule.semester = semesterNumber;
        courseModule.isOptional = false;
        return courseModule;
    }
    public CourseModule getModule3Option1(){
        CourseModule courseModule = new CourseModule();
        courseModule.moduleName = module3Option1.getSelectedItem().toString();
        courseModule.moduleNumber = 3;
        courseModule.semester = semesterNumber;
        courseModule.isOptional = false;
        courseModule.optionalModuleNumber = 1;
        return courseModule;
    }
    public CourseModule getModule3Option2(){
        CourseModule courseModule = new CourseModule();
        courseModule.moduleName = module3Option2.getSelectedItem().toString();
        courseModule.moduleNumber = 3;
        courseModule.semester = semesterNumber;
        courseModule.isOptional = true;
        courseModule.optionalModuleNumber = 2;
        return courseModule;
    }
    public CourseModule getModule4Option1(){
        CourseModule courseModule = new CourseModule();
        courseModule.moduleName = module4Option1.getSelectedItem().toString();
        courseModule.moduleNumber = 4;
        courseModule.isOptional = true;
        courseModule.semester = semesterNumber;
        courseModule.optionalModuleNumber = 1;
        return courseModule;
    }
    public CourseModule getModule4Option2(){
        CourseModule courseModule = new CourseModule();
        courseModule.moduleName = module4Option2.getSelectedItem().toString();
        courseModule.moduleNumber = 4;
        courseModule.semester = semesterNumber;
        courseModule.isOptional = true;
        courseModule.optionalModuleNumber = 2;
        return courseModule;
    }

    public void setModule1(String moduleName){
        module1.setSelectedItem(moduleName);
    }
    public void setModule2(String moduleName){
        module2.setSelectedItem(moduleName);
    }
    public void setModule3Option1(String moduleName){
        module3Option1.setSelectedItem(moduleName);
    }
    public void setModule3Option2(String moduleName){
        module3Option2.setSelectedItem(moduleName);
    }
    public void setModule4Option1(String moduleName){
        module4Option1.setSelectedItem(moduleName);
    }
    public void setModule4Option2(String moduleName){
        module4Option2.setSelectedItem(moduleName);
    }
    public JPanel getMainPanel(){
        return mainPanel;
    }
    public SemViewWithElective(int semesterNumber){
        semesterName.setText("Semester"+semesterNumber);
        this.semesterNumber = semesterNumber;

        add(mainPanel);

        AdminController adminController = new AdminController();
        String moduleNames[] = adminController.GetModuleNames();


        module1.setModel(new DefaultComboBoxModel(moduleNames));
        module2.setModel(new DefaultComboBoxModel(moduleNames));
        module3Option1.setModel(new DefaultComboBoxModel(moduleNames));
        module3Option2.setModel(new DefaultComboBoxModel(moduleNames));
        module4Option1.setModel(new DefaultComboBoxModel(moduleNames));
        module4Option2.setModel(new DefaultComboBoxModel(moduleNames));
    }

}
