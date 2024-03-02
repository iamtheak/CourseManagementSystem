package Views.Admin;

import Controller.AdminController;
import Model.CourseModels.Course;
import Model.CourseModels.CourseModule;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class ThreeYearCourse extends JFrame{
    private JTextField courseName;
    private JButton addEditButton;
    private JPanel sem1;
    private JPanel sem2;
    private JPanel sem3;
    private JPanel sem4;
    private JPanel sem5;
    private JPanel sem6;
    private JPanel mainPanel;
    private JTextField studentLevel;
    ArrayList<SemesterView> semesterViews;
    ArrayList<SemViewWithElective> semViewWithElectives;

    public ThreeYearCourse(Course parentCourse){

        add(mainPanel);
        setTitle("Three Year Course");
        setSize(1000, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        addEditButton.setText("Edit Course");

        studentLevel.setText(String.valueOf(parentCourse.studentLevel));
        courseName.setText(parentCourse.courseName);
        ArrayList<CourseModule> courseModules = parentCourse.modules;
        ArrayList<SemesterView> semesterViews = new ArrayList<>();
        ArrayList<SemViewWithElective> semViewWithElectives = new ArrayList<>();

        for (int i = 0; i < 4; i++){
            semesterViews.add(new SemesterView((i + 1)));
        }

        for (int i = 0; i < 2; i++){
            semViewWithElectives.add(new SemViewWithElective((i + 5)));
        }

        this.semesterViews = semesterViews;


        sem1.add(semesterViews.get(0).getMainPanel());
        sem2.add(semesterViews.get(1).getMainPanel());
        sem3.add(semesterViews.get(2).getMainPanel());
        sem4.add(semesterViews.get(3).getMainPanel());

        this.semViewWithElectives = semViewWithElectives;

        sem5.add(semViewWithElectives.get(0).getMainPanel());
        sem6.add(semViewWithElectives.get(1).getMainPanel());

        courseModules.forEach(courseModule -> {
            if(courseModule.year  == 1){
                if(courseModule.semester == 1){
                    switch (courseModule.moduleNumber){
                        case 1:
                            semesterViews.get(0).setModule1(courseModule.moduleName);
                            break;
                        case 2:
                            semesterViews.get(0).setModule2(courseModule.moduleName);
                            break;
                        case 3:
                            semesterViews.get(0).setModule3(courseModule.moduleName);
                            break;
                        case 4:
                            semesterViews.get(0).setModule4(courseModule.moduleName);
                            break;
                    }
                }
                if(courseModule.semester == 2){
                    switch (courseModule.moduleNumber){
                        case 1:
                            semesterViews.get(1).setModule1(courseModule.moduleName);
                            break;
                        case 2:
                            semesterViews.get(1).setModule2(courseModule.moduleName);
                            break;
                        case 3:
                            semesterViews.get(1).setModule3(courseModule.moduleName);
                            break;
                        case 4:
                            semesterViews.get(1).setModule4(courseModule.moduleName);
                            break;
                    }
                }
            }
            else if(courseModule.year  == 2){
                if(courseModule.semester == 3){
                    switch (courseModule.moduleNumber){
                        case 1:
                            semesterViews.get(2).setModule1(courseModule.moduleName);
                            break;
                        case 2:
                            semesterViews.get(2).setModule2(courseModule.moduleName);
                            break;
                        case 3:
                            semesterViews.get(2).setModule3(courseModule.moduleName);
                            break;
                        case 4:
                            semesterViews.get(2).setModule4(courseModule.moduleName);
                            break;
                    }
                }
                if(courseModule.semester == 4){
                    switch (courseModule.moduleNumber){
                        case 1:
                            semesterViews.get(3).setModule1(courseModule.moduleName);
                            break;
                        case 2:
                            semesterViews.get(3).setModule2(courseModule.moduleName);
                            break;
                        case 3:
                            semesterViews.get(3).setModule3(courseModule.moduleName);
                            break;
                        case 4:
                            semesterViews.get(3).setModule4(courseModule.moduleName);
                            break;
                    }
                }
            }
            else if(courseModule.year  == 3){
              if(courseModule.semester == 5){
                  switch (courseModule.moduleNumber){
                      case 1:
                          semViewWithElectives.get(0).setModule1(courseModule.moduleName);
                          break;
                      case 2:
                          semViewWithElectives.get(0).setModule2(courseModule.moduleName);
                          break;
                      case 3:

                           if(courseModule.optionalModuleNumber == 1){
                               semViewWithElectives.get(0).setModule3Option1(courseModule.moduleName);
                           }
                           else{
                               semViewWithElectives.get(0).setModule3Option2(courseModule.moduleName);
                           }
                          break;
                      case 4:

                          if(courseModule.optionalModuleNumber == 1){
                              semViewWithElectives.get(0).setModule4Option1(courseModule.moduleName);
                          }
                          else{
                              semViewWithElectives.get(0).setModule4Option2(courseModule.moduleName);
                          }

                          break;
                  }
              }
                if(courseModule.semester == 6){
                    switch (courseModule.moduleNumber){
                        case 1:
                            semViewWithElectives.get(1).setModule1(courseModule.moduleName);
                            break;
                        case 2:
                            semViewWithElectives.get(1).setModule2(courseModule.moduleName);
                            break;
                        case 3:

                            if(courseModule.optionalModuleNumber == 1){

                                System.out.println(courseModule.optionalModuleNumber);
                                semViewWithElectives.get(1).setModule3Option1(courseModule.moduleName);
                            }
                            else{
                                semViewWithElectives.get(1).setModule3Option2(courseModule.moduleName);
                            }
                            break;
                        case 4:

                            if(courseModule.optionalModuleNumber == 1){
                                semViewWithElectives.get(1).setModule4Option1(courseModule.moduleName);
                            }
                            else{
                                semViewWithElectives.get(1).setModule4Option2(courseModule.moduleName);
                            }
                            break;
                    }
                }

            }
        });

        addEditButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Course course = new Course();


                if(studentLevel.getText().isEmpty() || studentLevel.getText().toString().matches("(3-9)")){
                    JOptionPane.showMessageDialog(null, "Please enter student level from 3 to 9");
                    return;
                }
                if (courseName.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Please enter course name");
                    return;
                }

                course.studentLevel = Integer.parseInt(studentLevel.getText());
                course.years = 3;
                course.courseName = courseName.getText();

                System.out.println(parentCourse.courseId);
                course.courseId = parentCourse.courseId;

                course.modules = new ArrayList<>();


                HashSet<String> moduleNames = new HashSet<>();

                if(semesterViews.get(0).isModuleFilled() && semesterViews.get(1).isModuleFilled() && semesterViews.get(2).isModuleFilled() && semesterViews.get(3).isModuleFilled() && semViewWithElectives.get(0).isModuleFilled() && semViewWithElectives.get(1).isModuleFilled()) {

                    for(int i= 0; i < 4; i++){

                        SemesterView semesterView = semesterViews.get(i);
                        int year = 0;
                        if(i < 2){
                            year = 1;
                        }
                        else{
                            year = 2;
                        }
                        CourseModule courseModule1 = semesterView.getModule1();
                        courseModule1.year = year;
                        course.modules.add(courseModule1);

                        CourseModule courseModule2 = semesterView.getModule2();
                        courseModule2.year = year;
                        course.modules.add(courseModule2);

                        CourseModule courseModule3 = semesterView.getModule3();
                        courseModule3.year = year;
                        course.modules.add(courseModule3);

                        CourseModule courseModule4 = semesterView.getModule4();
                        courseModule4.year = year;
                        course.modules.add(courseModule4);

                        moduleNames.add(courseModule1.moduleName);
                        moduleNames.add(courseModule2.moduleName);
                        moduleNames.add(courseModule3.moduleName);
                        moduleNames.add(courseModule4.moduleName);

                    }
                    for(int i= 0; i < 2; i++){
                        SemViewWithElective semViewWithElective = semViewWithElectives.get(i);

                        CourseModule courseModule1 = semViewWithElective.getModule1();
                        courseModule1.year = 3;
                        course.modules.add(courseModule1);

                        CourseModule courseModule2 = semViewWithElective.getModule2();
                        courseModule2.year = 3;
                        course.modules.add(courseModule2);

                        CourseModule courseModule3Option1 = semViewWithElective.getModule3Option1();
                        courseModule3Option1.year = 3;
                        course.modules.add(courseModule3Option1);

                        CourseModule courseModule3Option2 = semViewWithElective.getModule3Option2();
                        courseModule3Option2.year = 3;
                        course.modules.add(courseModule3Option2);

                        CourseModule courseModule4Option1 = semViewWithElective.getModule4Option1();
                        courseModule4Option1.year = 3;
                        course.modules.add(courseModule4Option1);

                        CourseModule courseModule4Option2 = semViewWithElective.getModule4Option2();
                        courseModule4Option2.year = 3;
                        course.modules.add(courseModule4Option2);


                        moduleNames.add(courseModule1.moduleName);
                        moduleNames.add(courseModule2.moduleName);
                        moduleNames.add(courseModule3Option1.moduleName);
                        moduleNames.add(courseModule3Option2.moduleName);
                        moduleNames.add(courseModule4Option1.moduleName);
                        moduleNames.add(courseModule4Option2.moduleName);


                    }

                    if(moduleNames.size() != course.modules.size()){
                        JOptionPane.showMessageDialog(null, "Please enter unique module names");
                        return;
                    }
                    AdminController adminController = new AdminController();
                    String result = adminController.UpdateCourse(course);
                    if(result.equals("Success")){
                        JOptionPane.showMessageDialog(null, "Course edited successfully");
                        dispose();
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Error: "+result);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Please fill all the modules");
                }

            }
        });


    }
    public ThreeYearCourse(AdminCourse adminCourse){
        add(mainPanel);
        setTitle("Three Year Course");
        setSize(1000, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        ArrayList<SemesterView> semesterViews = new ArrayList<>();

        for (int i = 0; i < 4; i++){
            semesterViews.add(new SemesterView((i + 1)));
        }

        this.semesterViews = semesterViews;

        sem1.add(semesterViews.get(0).getMainPanel());
        sem2.add(semesterViews.get(1).getMainPanel());
        sem3.add(semesterViews.get(2).getMainPanel());
        sem4.add(semesterViews.get(3).getMainPanel());

        ArrayList<SemViewWithElective> semViewWithElectives = new ArrayList<>();

        for (int i = 0; i < 2; i++){
            semViewWithElectives.add(new SemViewWithElective((i + 5)));
        }
        this.semViewWithElectives = semViewWithElectives;

        sem5.add(semViewWithElectives.get(0).getMainPanel());
        sem6.add(semViewWithElectives.get(1).getMainPanel());
        addEditButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Course course = new Course();


                if(studentLevel.getText().isEmpty() || studentLevel.getText().toString().matches("(3-9)")){
                    JOptionPane.showMessageDialog(null, "Please enter student level from 3 to 9");
                    return;
                }
                if (courseName.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Please enter course name");
                    return;
                }

                course.studentLevel = Integer.parseInt(studentLevel.getText());
                course.years = 3;
                course.courseName = courseName.getText();

                course.modules = new ArrayList<>();

                HashSet<String> moduleNames = new HashSet<>();

                if(semesterViews.get(0).isModuleFilled() && semesterViews.get(1).isModuleFilled() && semesterViews.get(2).isModuleFilled() && semesterViews.get(3).isModuleFilled() && semViewWithElectives.get(0).isModuleFilled() && semViewWithElectives.get(1).isModuleFilled()) {

                    for(int i= 0; i < 4; i++){

                        SemesterView semesterView = semesterViews.get(i);
                        int year = 0;
                        if(i < 2){
                            year = 1;
                        }
                        else{
                            year = 2;
                        }
                        CourseModule courseModule1 = semesterView.getModule1();
                        courseModule1.year = year;
                        course.modules.add(courseModule1);

                        CourseModule courseModule2 = semesterView.getModule2();
                        courseModule2.year = year;
                        course.modules.add(courseModule2);

                        CourseModule courseModule3 = semesterView.getModule3();
                        courseModule3.year = year;
                        course.modules.add(courseModule3);

                        CourseModule courseModule4 = semesterView.getModule4();
                        courseModule4.year = year;
                        course.modules.add(courseModule4);

                        moduleNames.add(courseModule1.moduleName);
                        moduleNames.add(courseModule2.moduleName);
                        moduleNames.add(courseModule3.moduleName);
                        moduleNames.add(courseModule4.moduleName);

                    }
                    for(int i= 0; i < 2; i++){
                        SemViewWithElective semViewWithElective = semViewWithElectives.get(i);

                        CourseModule courseModule1 = semViewWithElective.getModule1();
                        courseModule1.year = 3;
                        course.modules.add(courseModule1);

                        CourseModule courseModule2 = semViewWithElective.getModule2();
                        courseModule2.year = 3;
                        course.modules.add(courseModule2);

                        CourseModule courseModule3Option1 = semViewWithElective.getModule3Option1();
                        courseModule3Option1.year = 3;
                        course.modules.add(courseModule3Option1);

                        CourseModule courseModule3Option2 = semViewWithElective.getModule3Option2();
                        courseModule3Option2.year = 3;
                        course.modules.add(courseModule3Option2);

                        CourseModule courseModule4Option1 = semViewWithElective.getModule4Option1();
                        courseModule4Option1.year = 3;
                        course.modules.add(courseModule4Option1);

                        CourseModule courseModule4Option2 = semViewWithElective.getModule4Option2();
                        courseModule4Option2.year = 3;
                        course.modules.add(courseModule4Option2);

                        moduleNames.add(courseModule1.moduleName);
                        moduleNames.add(courseModule2.moduleName);
                        moduleNames.add(courseModule3Option1.moduleName);
                        moduleNames.add(courseModule3Option2.moduleName);
                        moduleNames.add(courseModule4Option1.moduleName);
                        moduleNames.add(courseModule4Option2.moduleName);

                    }

                    if(moduleNames.size() != course.modules.size()){
                        JOptionPane.showMessageDialog(null, "Please enter unique module names");
                        return;
                    }

                    AdminController adminController = new AdminController();
                    String result = adminController.AddCourse(course);
                    if(result.equals("Success")){
                        JOptionPane.showMessageDialog(null, "Course added successfully");
                        adminCourse.updateCourseTable();
                        dispose();
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Error: "+result);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Please fill all the modules");
                }

            }
        });
    }
}
