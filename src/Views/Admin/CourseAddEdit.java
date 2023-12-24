package Views.Admin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CourseAddEdit extends JFrame{
    private JLabel CourseAddEditLabel;
    private JPanel CourseAddEdit;
    private JComboBox comboBox1;
    private JPanel Semesters;
    private JPanel mainPanel;
    private JPanel sem1;
    private JPanel sem2;
    private JPanel sem3;
    private JPanel sem4;
    private JTextField textField1;
    private JButton addEdit;
    private JPanel semPanel;
    private ArrayList<SemesterView> semesterViews;
    private void updateSemester(){


        int semesters = Integer.parseInt(comboBox1.getSelectedItem().toString());
        sem1.removeAll();
        sem2.removeAll();
        sem3.removeAll();
        sem4.removeAll();

        ArrayList<SemesterView> semesterViews = new ArrayList<>();
        for (int i = 0; i < semesters; i++){
            semesterViews.add(new SemesterView((i + 1)));
        }

        this.semesterViews = semesterViews;

        sem1.add(semesterViews.get(0).getMainPanel());
        sem2.add(semesterViews.get(1).getMainPanel());

        if(semesters == 3){
            sem3.add(semesterViews.get(2).getMainPanel());
        }
        else if(semesters == 4){
            sem3.add(semesterViews.get(2).getMainPanel());
            sem4.add(semesterViews.get(3).getMainPanel());
        }

        Semesters.revalidate();
        Semesters.repaint();
    }

    public CourseAddEdit(){
        add(mainPanel);
        setTitle("Add/Edit Course");
        setSize(750, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);



        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateSemester();
            }
        });
        addEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args) {
        CourseAddEdit courseAddEdit = new CourseAddEdit();
        courseAddEdit.setVisible(true);
    }
}
