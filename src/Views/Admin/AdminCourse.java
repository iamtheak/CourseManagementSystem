package Views.Admin;

import Controller.AdminController;
import Model.CourseModels.Course;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

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
    private JButton publishResult;
    private JTextField searchBar;
    private JLabel Search;
    private TableRowSorter<TableModel> rowSorter = new TableRowSorter<>();


    public void updateCourseTable(){
        AdminController adminController = new AdminController();

        String[] columnNames = {"Course Id","Course Name", "Course Years", "Course Status"};
        String[][] data = adminController.GetCoursesForTable();

        DefaultTableModel model = new DefaultTableModel(data, columnNames);


        courseTable.setModel(model);

        rowSorter.setModel(courseTable.getModel());
        courseTable.revalidate();
        courseTable.repaint();
    }
    public void loadCourseTable(){
        AdminController adminController = new AdminController();
        String[] columnNames = {"Course Id","Course Name", "Course Years", "Course Status"};
        String[][] data = adminController.GetCoursesForTable();
        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        courseTable.setModel(model);


        rowSorter = new TableRowSorter<>(courseTable.getModel());
        courseTable.setRowSorter(rowSorter);
        JScrollPane scrollPane = new JScrollPane(courseTable);
        courseTablePanel.add(scrollPane);
        courseTable.revalidate();
        courseTable.repaint();
    }
    public AdminCourse() {

        AdminSidePanel sidePanel1 = new AdminSidePanel(this);

        setContentPane(AdminCourseMainPanel);
        setTitle("Admin Course");
        setSize(1000, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        JPanel sidePanel2 = sidePanel1.getAdminSidePanel();
        sidePanel.add(sidePanel2);

        loadCourseTable();
        addCourse.addActionListener(e -> {
            String id = JOptionPane.showInputDialog(null, "Enter Year 2-3:");
            if (id == null || id.trim().isEmpty() || !id.matches("[2-3]")) {
                JOptionPane.showMessageDialog(null, "You must enter a course of 2-3 years.");
            } else {
                int year = Integer.parseInt(id);
                if (year == 2) {
                    TwoYearCourse twoYearCourse = new TwoYearCourse(AdminCourse.this);
                    twoYearCourse.setVisible(true);
                    twoYearCourse.setLocationRelativeTo(AdminCourseMainPanel);
                } else if (year == 3) {
                    ThreeYearCourse threeYearCourse = new ThreeYearCourse(AdminCourse.this);
                    threeYearCourse.setVisible(true);
                    threeYearCourse.setLocationRelativeTo(AdminCourseMainPanel);
                }
            }
        });
        editButton.addActionListener(e -> {
            String id = JOptionPane.showInputDialog(null, "Enter Course Id:");

            AdminController adminController = new AdminController();

            if (id == null || id.trim().isEmpty() || !id.matches("[0-9]+")) {
                JOptionPane.showMessageDialog(null, "You must enter a valid course id.");
            } else {
                int courseId = Integer.parseInt(id);
                Course course = adminController.GetCourseById(courseId);

                if (course != null) {
                    if (course.years == 2) {
                        TwoYearCourse twoYearCourse = new TwoYearCourse(course, AdminCourse.this);
                        twoYearCourse.setVisible(true);
                        twoYearCourse.setLocationRelativeTo(AdminCourseMainPanel);
                    } else if (course.years == 3) {
                        ThreeYearCourse threeYearCourse = new ThreeYearCourse(course);
                        threeYearCourse.setVisible(true);
                        threeYearCourse.setLocationRelativeTo(AdminCourseMainPanel);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Course not found.");
                }
            }
        });
        changeStatusButton.addActionListener(e -> {
            changeCourseStatus changeCourseStatus = new changeCourseStatus(AdminCourse.this);
            changeCourseStatus.setVisible(true);
            changeCourseStatus.setLocationRelativeTo(AdminCourseMainPanel);
        });
        deleteCourse.addActionListener(e -> {
            String id = JOptionPane.showInputDialog(null, "Enter Course Id:");

            AdminController adminController = new AdminController();

            if (id == null || id.trim().isEmpty() || !id.matches("[0-9]+")) {
                JOptionPane.showMessageDialog(null, "You must enter a valid course id.");
            } else {
                int courseId = Integer.parseInt(id);
                String result = adminController.DeleteCourse(courseId);
                if (result.equals("Success")) {
                    JOptionPane.showMessageDialog(null, "Course Deleted");
                    updateCourseTable();
                } else {
                    JOptionPane.showMessageDialog(null, "Course Deletion Failed");
                }
            }
        });
        publishResult.addActionListener(e -> {
            String courseId = JOptionPane.showInputDialog(null, "Enter Course Id:");
            if (courseId == null || courseId.trim().isEmpty() || !courseId.matches("[0-9]+")) {
                JOptionPane.showMessageDialog(null, "You must enter a valid course id.");
            } else {
                int id = Integer.parseInt(courseId);
                AdminController adminController = new AdminController();

                Course course = adminController.GetCourseById(id);

                if(course == null){
                    JOptionPane.showMessageDialog(null, "Course not found.");
                    return;
                }
                String year = JOptionPane.showInputDialog(null, "Enter Year:");

                if (year == null || year.trim().isEmpty() || !year.matches("[0-9]+")) {
                    JOptionPane.showMessageDialog(null, "You must enter a valid year.");
                } else {
                    int year1 = Integer.parseInt(year);
                    if (year1 <= course.years) {
                        String result = adminController.PublishResult(id, year1);

                        if (result.equals("Success")) {
                            JOptionPane.showMessageDialog(null, "Result Published");
                        }
                        else if(result.equals("Published")){
                            JOptionPane.showMessageDialog(null, "Result is already published");
                        }

                        else {
                            JOptionPane.showMessageDialog(null, "Result Publication Failed");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Year must be less than or equal to course years.");
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
