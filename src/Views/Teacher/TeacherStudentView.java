package Views.Teacher;

import Controller.TeacherController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;



public class TeacherStudentView extends JFrame{
    private JPanel mainPanel;
    private JLabel moduleName;
    private JButton giveMarkButton;
    private JPanel studentTablePanel;
    private JTable studentTable;


    public void loadTable(int moduleId){
        TeacherController teacherController = new TeacherController();
        String[] columnNames = {"Student Id","Student Name","Grade"};
        String[][] data= teacherController.GetStudentsOfModule(moduleId);


        if(data == null){
            data = new String[0][3];
        }
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        studentTable.setModel(model);
        JScrollPane scrollPane = new JScrollPane(studentTable);
        studentTablePanel.add(scrollPane);
        studentTable.revalidate();
        studentTable.repaint();

    }

    public void updateTable(int moduleId){
        TeacherController teacherController = new TeacherController();
        String[] columnNames = {"Student Id","Student Name","Grade"};
        String[][] data= teacherController.GetStudentsOfModule(moduleId);

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        studentTable.setModel(model);
        studentTable.revalidate();
        studentTable.repaint();
    }
    public TeacherStudentView(int moduleId){
        add(mainPanel);
        setSize(700,400);
        setTitle("Students");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);

        TeacherController teacherController = new TeacherController();

        loadTable(moduleId);

        giveMarkButton.addActionListener(e -> {

            String studentId = JOptionPane.showInputDialog("Enter Student Id");

            if(studentId.isEmpty() || !studentId.matches("\\d+")){
                JOptionPane.showMessageDialog(null,"Invalid Student Id");
                return;
            }
            else{
                String mark = JOptionPane.showInputDialog("Enter Mark");
                if(mark.isEmpty() || !mark.matches("\\d+") || Integer.parseInt(mark) > 100 || Integer.parseInt(mark) < 0){
                    JOptionPane.showMessageDialog(null,"Invalid Mark");
                }
                else{
                    String result =  teacherController.GiveMark(Integer.parseInt(studentId),moduleId,Integer.parseInt(mark));

                    if(result.equals("Success")){
                        JOptionPane.showMessageDialog(null,"Mark Given");
                        updateTable(moduleId);
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Error: "+result);
                    }
                }
            }
        });
    }
}
