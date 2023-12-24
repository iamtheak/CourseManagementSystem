package Views.Admin;

import Controller.AdminController;
import Model.CourseModels.Course;
import Model.CourseModels.CourseModule;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TwoYearCourse extends JFrame{
    private JPanel mainPanel;
    private JTextField courseName;
    private JButton addEditButton;
    private JPanel Year1;
    private JPanel Year2;
    private JPanel sem1;
    private JPanel sem3;
    private JPanel sem4;
    private JPanel sem2;
    private JTextField studentLevel;


    ArrayList<SemesterView> semesterViews;

    public TwoYearCourse(Course parentCourse,AdminCourse parent){
        add(mainPanel);
        setTitle("Two Year Course");
        setSize(750, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        ArrayList<SemesterView> semesterViews = new ArrayList<>();
        this.semesterViews = semesterViews;

        addEditButton.setText("Edit");
        courseName.setText(parentCourse.courseName);

        studentLevel.setText(String.valueOf(parentCourse.studentLevel));

        for (int i = 0; i < 4; i++){
            semesterViews.add(new SemesterView((i + 1)));
        }
        sem1.add(semesterViews.get(0).getMainPanel());
        sem2.add(semesterViews.get(1).getMainPanel());
        sem3.add(semesterViews.get(2).getMainPanel());
        sem4.add(semesterViews.get(3).getMainPanel());

        ArrayList<CourseModule> courseModules = parentCourse.modules;

        courseModules.forEach(courseModule -> {
            if(courseModule.year == 1){
                if(courseModule.semester == 1){
                    switch (courseModule.ModuleNumber){
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
                    switch (courseModule.ModuleNumber){
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

            if(courseModule.year == 2){
                if(courseModule.semester == 3){
                    switch (courseModule.ModuleNumber){
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
                    switch (courseModule.ModuleNumber){
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
        });

        addEditButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Course course = new Course();
                course.courseId = parentCourse.courseId;

                if(studentLevel.getText().isEmpty() || studentLevel.getText().toString().matches("(3-9)")){
                    JOptionPane.showMessageDialog(null, "Please enter student level from 3 to 9");
                    return;
                }
                if (courseName.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Please enter course name");
                    return;
                }
                course.studentLevel = Integer.parseInt(studentLevel.getText());
                course.years = 2;
                course.courseName = courseName.getText();

                course.modules = new ArrayList<>();

                if(semesterViews.get(0).isModuleFilled() && semesterViews.get(1).isModuleFilled() && semesterViews.get(2).isModuleFilled() && semesterViews.get(3).isModuleFilled()) {

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
                    }
                    AdminController adminController = new AdminController();
                    String result = adminController.UpdateCourse(course);

                    if (result.equals("Success")){
                        JOptionPane.showMessageDialog(null, "Course Edited successfully");
                        dispose();
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Course Edit failed");
                    }

                }
                else{
                    JOptionPane.showMessageDialog(null, "Please select all modules for each semester");
                }
            }

            });
    }
    public TwoYearCourse(AdminCourse parent){
        add(mainPanel);
        setTitle("Two Year Course");
        setSize(750, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        ArrayList<SemesterView> semesterViews = new ArrayList<>();
        this.semesterViews = semesterViews;

        for (int i = 0; i < 4; i++){
            semesterViews.add(new SemesterView((i + 1)));
        }
        sem1.add(semesterViews.get(0).getMainPanel());
        sem2.add(semesterViews.get(1).getMainPanel());
        sem3.add(semesterViews.get(2).getMainPanel());
        sem4.add(semesterViews.get(3).getMainPanel());
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
                course.years = 2;
                course.courseName = courseName.getText();

                course.modules = new ArrayList<>();

                if(semesterViews.get(0).isModuleFilled() && semesterViews.get(1).isModuleFilled() && semesterViews.get(2).isModuleFilled() && semesterViews.get(3).isModuleFilled()) {

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
                    }
                    AdminController adminController = new AdminController();
                    String result = adminController.AddCourse(course);

                    if (result.equals("Success")){
                        JOptionPane.showMessageDialog(null, "Course added successfully");

                        parent.updateCourseTable();
                        dispose();
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Course addition failed");
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Please select all modules for each semester");
                }
            }
        });
    }
}
