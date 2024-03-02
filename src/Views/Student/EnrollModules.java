package Views.Student;

import Controller.StudentController;
import Model.CourseModels.CourseModule;
import Model.CourseModels.Semester;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class EnrollModules extends JFrame {
    private JPanel mainPanel;
    private JLabel Year;
    private JLabel semesterI;
    private JLabel semesterII;
    private JPanel module1Panel;
    private JPanel module2Panel;
    private JPanel module3Panel;
    private JPanel module4Panel;
    private JPanel module5Panel;
    private JPanel module6Panel;
    private JPanel module7Panel;
    private JPanel module8Panel;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JComboBox comboBox4;
    private JComboBox comboBox5;
    private JComboBox comboBox6;
    private JComboBox comboBox7;
    private JComboBox comboBox8;
    private JButton enrollButton;

    private String[][] getArraysForComboBox(ArrayList<CourseModule> courseModules){

        String array[][] = new String[4][2];
        for(CourseModule module : courseModules)
        {
            switch (module.moduleNumber) {
                case 1 :
                    array[0][0] = module.moduleName;
                    break;
                case 2 :
                    array[1][0] = module.moduleName;
                    break;
                case 3 :
                    if(module.isOptional){
                        if(module.optionalModuleNumber == 1) {
                            array[2][0] = module.moduleName;
                        }
                        else{
                            array[2][1] = module.moduleName;
                        }
                    }
                    else{
                        array[2][0] = module.moduleName;
                    }
                    break;
                case 4 :
                    if(module.isOptional){
                        if(module.optionalModuleNumber == 1) {
                            array[3][0] = module.moduleName;
                        }
                        else{
                            array[3][1] = module.moduleName;
                        }
                    }
                    else{
                        array[3][0] = module.moduleName;
                    }
                    break;
            }
        }
        return array;
    }

    public EnrollModules(StudentDashboard studentDashboard,int courseId,int year,int studentId) {
        add(mainPanel);
        setTitle("Enroll Modules");
        setSize(1000, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(studentDashboard);
        setVisible(true);

        StudentController studentController = new StudentController();
        ArrayList<CourseModule> courseModules = studentController.GetCourseModulesForEnrollment(courseId,year);


        int startSemester = 1;
        int endSemester = 2;
        switch (year){
            case 1:
                Year.setText("Year 1");
                semesterI.setText("Semester I");
                semesterII.setText("Semester II");
                break;
            case 2:
                Year.setText("Year 2");
                semesterI.setText("Semester III");
                semesterII.setText("Semester IV");

                startSemester = 3;
                endSemester = 4;

                break;
            case 3:
                Year.setText("Year 3");
                semesterI.setText("Semester V");
                semesterII.setText("Semester VI");

                startSemester = 5;
                endSemester = 6;

                break;
            case 4:
                Year.setText("Year 4");
                semesterI.setText("Semester VII");
                semesterII.setText("Semester VIII");

                startSemester = 7;
                endSemester = 8;
                break;
        }


        Semester semesterI = new Semester();
        Semester semesterII = new Semester();

        semesterI.courseModules = new ArrayList<>();
        semesterII.courseModules = new ArrayList<>();

        int finalStartSemester = startSemester;
        int finalEndSemester = endSemester;


        HashMap<String,Integer> courseModuleHashMap = new HashMap<>();
        courseModules.forEach(courseModule -> {
                courseModuleHashMap.put(courseModule.moduleName,courseModule.moduleId);
                if(courseModule.semester == finalStartSemester){
                    semesterI.courseModules.add(courseModule);
                }
                if(courseModule.semester == finalEndSemester){
                    semesterII.courseModules.add(courseModule);
                }
        });


        String array1[][] = getArraysForComboBox(semesterI.courseModules);
        String array2[][] = getArraysForComboBox(semesterII.courseModules);

        ComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>(array1[0]);
        comboBox1.setModel(comboBoxModel);
        comboBoxModel = new DefaultComboBoxModel<>(array1[1]);
        comboBox2.setModel(comboBoxModel);
        comboBoxModel = new DefaultComboBoxModel<>(array1[2]);
        comboBox3.setModel(comboBoxModel);
        comboBoxModel = new DefaultComboBoxModel<>(array1[3]);
        comboBox4.setModel(comboBoxModel);
        comboBoxModel = new DefaultComboBoxModel<>(array2[0]);
        comboBox5.setModel(comboBoxModel);
        comboBoxModel = new DefaultComboBoxModel<>(array2[1]);
        comboBox6.setModel(comboBoxModel);
        comboBoxModel = new DefaultComboBoxModel<>(array2[2]);
        comboBox7.setModel(comboBoxModel);
        comboBoxModel = new DefaultComboBoxModel<>(array2[3]);
        comboBox8.setModel(comboBoxModel);

        enrollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int moduleId[] = new int[8];



                if( comboBox3.getSelectedItem() == null){
                    JOptionPane.showMessageDialog(mainPanel, "Please Select All Modules");
                    return;
                }
                if(comboBox4.getSelectedItem() == null){
                    JOptionPane.showMessageDialog(mainPanel, "Please Select All Modules");
                    return;
                }
                if( comboBox7.getSelectedItem() == null){
                    JOptionPane.showMessageDialog(mainPanel, "Please Select All Modules");
                    return;
                }
                if(comboBox8.getSelectedItem() == null){
                    JOptionPane.showMessageDialog(mainPanel, "Please Select All Modules");
                    return;
                }


                moduleId[0] = courseModuleHashMap.get(Objects.requireNonNull(comboBox1.getSelectedItem()).toString());
                moduleId[1] = courseModuleHashMap.get(Objects.requireNonNull(comboBox2.getSelectedItem()).toString());
                moduleId[2] = courseModuleHashMap.get(Objects.requireNonNull(comboBox3.getSelectedItem()).toString());
                moduleId[3] = courseModuleHashMap.get(Objects.requireNonNull(comboBox4.getSelectedItem()).toString());
                moduleId[4] = courseModuleHashMap.get(Objects.requireNonNull(comboBox5.getSelectedItem()).toString());
                moduleId[5] = courseModuleHashMap.get(Objects.requireNonNull(comboBox6.getSelectedItem()).toString());
                moduleId[6] = courseModuleHashMap.get(Objects.requireNonNull(comboBox7.getSelectedItem()).toString());
                moduleId[7] = courseModuleHashMap.get(Objects.requireNonNull(comboBox8.getSelectedItem()).toString());

                String result = studentController.EnrollModules(moduleId,studentId,year);

                if (result.equals("Success")){
                    JOptionPane.showMessageDialog(mainPanel, "Modules Enrolled Successfully");
                    studentDashboard.updateModules(studentId);
                    dispose();
                }
                else {
                    JOptionPane.showMessageDialog(mainPanel, "Modules Enrollment Failed");
                }
            }
        });
    }
}
