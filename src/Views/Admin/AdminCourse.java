package Views.Admin;

import Controller.AdminController;
import Model.CourseModels.Course;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminCourse extends JFrame{
    private JButton addCourse;
    private JButton deleteCourse;
    private JButton editButton;
    private JTable courseTable;
    private JPanel AdminCourseMainPanel;
    private JPanel sidePanel;
    private JPanel upperPanel;
    private JButton changeStatusButton;
    private JPanel courseTablePanel;



    public void updateCourseTable(){
        AdminController adminController = new AdminController();

        String[] columnNames = {"Course Id","Course Name", "Course Years", "Course Status"};
        String[][] data = adminController.GetCoursesForTable();

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        courseTable.setModel(model);

        courseTable.revalidate();
        courseTable.repaint();
    }
    public void loadCourseTable(){
        AdminController adminController = new AdminController();
        String[] columnNames = {"Course Id","Course Name", "Course Years", "Course Status"};
        String[][] data = adminController.GetCoursesForTable();
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        courseTable.setModel(model);
        JScrollPane scrollPane = new JScrollPane(courseTable);
        courseTablePanel.add(scrollPane);
        courseTable.revalidate();
        courseTable.repaint();
    }
    public AdminCourse(){

        AdminSidePanel sidePanel1 = new AdminSidePanel(this);

        setContentPane(AdminCourseMainPanel);
        setTitle("Admin Course");
        setSize(1000, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        JPanel sidePanel2 = sidePanel1.getAdminSidePanel();
        sidePanel.add(sidePanel2);

        loadCourseTable();
        addCourse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = JOptionPane.showInputDialog(null, "Enter Year 2-3:");
                if (id == null || id.trim().isEmpty() || !id.matches("[2-3]")) {
                    JOptionPane.showMessageDialog(null, "You must enter a course of 2-3 years.");
                }
                else{
                    int year = Integer.parseInt(id);
                    if(year == 2){
                        TwoYearCourse twoYearCourse = new TwoYearCourse(AdminCourse.this);
                        twoYearCourse.setVisible(true);
                        twoYearCourse.setLocationRelativeTo(AdminCourseMainPanel);
                    }
                    else if(year == 3){
                        ThreeYearCourse threeYearCourse = new ThreeYearCourse(AdminCourse.this);
                        threeYearCourse.setVisible(true);
                        threeYearCourse.setLocationRelativeTo(AdminCourseMainPanel);
                    }
                }
            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = JOptionPane.showInputDialog(null, "Enter Course Id:");

                AdminController adminController = new AdminController();

                if (id == null || id.trim().isEmpty() || !id.matches("[0-9]+")) {
                    JOptionPane.showMessageDialog(null, "You must enter a valid course id.");
                }
                else{
                    int courseId = Integer.parseInt(id);
                    Course course = adminController.GetCourseById(courseId);

                    if(course != null){
                        if(course.years == 2){
                            TwoYearCourse twoYearCourse = new TwoYearCourse(course,AdminCourse.this);
                            twoYearCourse.setVisible(true);
                            twoYearCourse.setLocationRelativeTo(AdminCourseMainPanel);
                        }
                        else if(course.years == 3){
                            ThreeYearCourse threeYearCourse = new ThreeYearCourse(course);
                            threeYearCourse.setVisible(true);
                            threeYearCourse.setLocationRelativeTo(AdminCourseMainPanel);
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Course not found.");
                    }
                }
            }
        });
        changeStatusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeCourseStatus changeCourseStatus = new changeCourseStatus(AdminCourse.this);
                changeCourseStatus.setVisible(true);
                changeCourseStatus.setLocationRelativeTo(AdminCourseMainPanel);
            }
        });
        deleteCourse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = JOptionPane.showInputDialog(null, "Enter Course Id:");

                AdminController adminController = new AdminController();

                if (id == null || id.trim().isEmpty() || !id.matches("[0-9]+")) {
                    JOptionPane.showMessageDialog(null, "You must enter a valid course id.");
                }
                else{
                    int courseId = Integer.parseInt(id);
                    String result = adminController.DeleteCourse(courseId);
                    if(result.equals("Success")){
                        JOptionPane.showMessageDialog(null, "Course Deleted");
                        updateCourseTable();
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Course Deletion Failed");
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        AdminCourse adminCourse = new AdminCourse();
        adminCourse.setVisible(true);
    }

}
