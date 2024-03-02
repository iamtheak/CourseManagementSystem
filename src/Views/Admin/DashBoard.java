package Views.Admin;

import Controller.AdminController;
import Controller.StudentController;
import Model.UserModels.StudentModel;
import Views.Student.ViewResult;
import Views.UserSession;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashBoard extends JFrame{
    private JPanel dashboardMainPanel;
    private JPanel sidePanel;
    private JPanel enrolledStudentsPanel;
    private JTable enrolledStudentsTable;
    private JLabel courseCount;
    private JLabel teacherCount;
    private JLabel studentCount;
    private JButton viewResultButton;
    private JTextField searchBar;
    private TableRowSorter<TableModel> rowSorter = new TableRowSorter<>();
    public void loadEnrolledStudentsTable(){
        AdminController adminController = new AdminController();

        String[] columnNames = {"Student Id", "First Name", "Middle Name", "Last Name", "Email", "Phone Number", "Date of Birth","Course Name","Level","Year"};
        String[][] data = adminController.GetStudentsForTable();
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        enrolledStudentsTable.setModel(model);
        JScrollPane scrollPane = new JScrollPane(enrolledStudentsTable);

        rowSorter.setModel(enrolledStudentsTable.getModel());
        enrolledStudentsTable.setRowSorter(rowSorter);

        enrolledStudentsPanel.add(scrollPane);
        enrolledStudentsTable.revalidate();
        enrolledStudentsTable.repaint();
    }

    public DashBoard(){
        AdminSidePanel sidePanel1 = new AdminSidePanel(this);
        setContentPane(dashboardMainPanel);
        setTitle("Admin Dashboard");
        setSize(1000, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        this.setVisible(true);
        JPanel sidePanel2 = sidePanel1.getAdminSidePanel();
        sidePanel.add(sidePanel2);


        AdminController adminController = new AdminController();
        courseCount.setText("Total Courses: "+String.valueOf(adminController.GetCourseCount()));
        teacherCount.setText("Total Teachers: "+String.valueOf(adminController.GetTeacherCount()));
        studentCount.setText("Total Students: "+String.valueOf(adminController.GetStudentCount()));

        loadEnrolledStudentsTable();

        searchBar.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = searchBar.getText();

                if (text.trim().isEmpty()) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = searchBar.getText();

                if (text.trim().isEmpty()) {
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


        viewResultButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                StudentController studentController = new StudentController();
                String id   = JOptionPane.showInputDialog("Enter Student Id");
                if(id.isEmpty() || !id.matches("\\d+")){
                    JOptionPane.showMessageDialog(null,"Invalid Student Id");
                }
                else {
                    StudentModel student = studentController.GetStudentDetail(Integer.parseInt(id));

                    if (student == null) {
                        JOptionPane.showMessageDialog(null, "Student Not Found");
                    } else {
                        new ViewResult(Integer.parseInt(id));
                    }
                }
            }
        });
    }
}
