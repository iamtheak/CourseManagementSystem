package Views.Admin;

import javax.swing.*;
import java.util.ArrayList;

public class FourYearCourse extends JFrame {
    private JPanel mainPanel;
    private JTextField courseName;
    private JButton addEditButton;
    private JPanel sem1;
    private JPanel sem2;
    private JPanel sem3;
    private JPanel sem4;
    private JPanel sem5;
    private JPanel sem6;
    private JPanel sem7;
    private JPanel sem8;
    private JTextField studentLevel;

    private ArrayList<SemesterView> semesterViews;
    
    private ArrayList<SemViewWithElective> semViewWithElectives;
    
    public FourYearCourse(){
        add(mainPanel);
        setTitle("Four Year Course");
        setSize(1200, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        ArrayList<SemesterView> semesterViews = new ArrayList<>();
        for (int i = 0; i < 6; i++){
            semesterViews.add(new SemesterView((i + 1)));
        }

        ArrayList<SemViewWithElective> semViewWithElectives = new ArrayList<>();

        for (int i = 0; i < 2; i++){
            semViewWithElectives.add(new SemViewWithElective((i + 7)));
        }
        
        sem1.add(semesterViews.get(0).getMainPanel());
        sem2.add(semesterViews.get(1).getMainPanel());
        sem3.add(semesterViews.get(2).getMainPanel());
        sem4.add(semesterViews.get(3).getMainPanel());
        sem5.add(semesterViews.get(4).getMainPanel());
        sem6.add(semesterViews.get(5).getMainPanel());
        sem7.add(semViewWithElectives.get(0).getMainPanel());
        sem8.add(semViewWithElectives.get(1).getMainPanel());

    }

    public static void main(String[] args) {
        new FourYearCourse().setVisible(true);
    }
}
