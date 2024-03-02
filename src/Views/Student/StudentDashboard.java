package Views.Student;

import Controller.StudentController;
import Model.CourseModels.StudentCourseModule;
import Model.UserModels.StudentModel;
import Model.UserModels.TeacherModel;
import Views.LoginForm;
import Views.UserSession;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StudentDashboard extends JFrame{
    private JPanel mainPanel;
    private JPanel topPanel;
    private JLabel userGreetLabel;
    private JLabel courseLabel;
    private JButton enrollModulesButton;
    private JButton viewResultButton;
    private JPanel studentModulePanel;
    private JButton logOutButton;


    private String[][] getDataForTable(int studentId){
        StudentController studentController = new StudentController();

        ArrayList<StudentCourseModule> studentCourseModules = studentController.GetEnrolledCourseModules(studentId);


        if(studentCourseModules.isEmpty()){
            return null;
        }
        ArrayList<ArrayList<TeacherModel>> teacherModels = new ArrayList<>();

        for(StudentCourseModule studentCourseModule: studentCourseModules){
            ArrayList<TeacherModel> teachers = studentController.GetTeachersOfModule(studentCourseModule.moduleId);
            teacherModels.add(teachers);
        }

        String data[][] = new String[8][4];
        for(int i=0;i<8;i++){
            data[i][0] = studentCourseModules.get(i).moduleName;
            data[i][1] = studentCourseModules.get(i).moduleCode;
            data[i][2] = String.valueOf(studentCourseModules.get(i).semester);

            String teachers = "";
            for(int j=0;j<teacherModels.get(i).size();j++){
                if(teacherModels.get(i) != null && !teacherModels.get(i).isEmpty()){
                    teachers += teacherModels.get(i).get(j).firstName+" "+teacherModels.get(i).get(j).lastName;
                    if(j!=teacherModels.get(i).size()-1){
                        teachers += ", ";
                    }
                }
            }
            data[i][3] = teachers;
        }

        return data;
    }
    public void loadModules(int studentId){

        String data[][] = getDataForTable(studentId);

        if(data == null){
            return;
        }
        MoudleAndTeacher moudleAndTeacher = new MoudleAndTeacher(data);
        studentModulePanel.add(moudleAndTeacher);
    }

    public void updateModules(int studentId) {
        studentModulePanel.removeAll();
        studentModulePanel.revalidate();
        studentModulePanel.repaint();
        loadModules(studentId);
    }

    public StudentDashboard(){
        add(mainPanel);
        setTitle("Student Dashboard");
        setSize(1000, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        StudentController studentController = new StudentController();

        int userId = UserSession.getInstance().getUserId();
        StudentModel student = studentController.GetStudentInfoByUserId(userId);


        userGreetLabel.setText("Welcome, "+student.firstName+" "+student.lastName);
        courseLabel.setText("Course: "+student.courseName);


        loadModules(student.studentId);
        enrollModulesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<StudentCourseModule> studentCourseModules = studentController.GetEnrolledCourseModules(student.studentId);


                if(studentCourseModules.size() >= 8){
                    JOptionPane.showMessageDialog(null,"You have already enrolled in all modules");
                    return;
                }

                boolean isPassed = studentController.IsPassed(student.studentId);
                if(!isPassed){
                    JOptionPane.showMessageDialog(null,"You have not passed modules of previous year");
                    return;
                }
                new EnrollModules(StudentDashboard.this,student.courseId,student.year,student.studentId);
            }
        });
        viewResultButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewResult(student.studentId);
            }
        });
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserSession.destroyInstance();
                dispose();
                LoginForm loginForm = new LoginForm();
            }
        });
    }

}
