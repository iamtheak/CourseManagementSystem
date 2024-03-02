package Views.Student;

import Controller.StudentController;
import Model.CourseModels.ResultModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class ViewResult extends JFrame{
    private JPanel mainPanel;
    private JPanel ResultPanel;
    private JPanel resultTabelPanel;
    private JTable resultTable;
    private JLabel resultName;
    private JLabel status;
    private JLabel credits;

    public ViewResult(int studentId){
        add(mainPanel);
        setSize(700,400);
        setTitle("Result");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);

        StudentController studentController = new StudentController();
        String[] columnNames = {"Module Id","Module Name", "Module Grade","Status"};
        ResultModel result = studentController.GetStudentReport(studentId);

        String data[][] = new String[result.courseModules.size()][4];

        String name = "Result for "+result.student.firstName+" "+result.student.middleName+result.student.lastName;

        resultName.setText(name);

        int passedModules = 0;
        int totalCredits = 0;


        for(int i=0;i<result.courseModules.size();i++){
            data[i][0] = String.valueOf(result.courseModules.get(i).moduleId);
            data[i][1] = result.courseModules.get(i).moduleName;

            int grade = result.courseModules.get(i).getGrade();
            if(grade >= result.courseModules.get(i).passPercent){
                totalCredits += result.courseModules.get(i).credits;
                data[i][3] = "Passed";
                passedModules++;
            }
            else{
                data[i][3] = "Failed";
            }
            data[i][2] = String.valueOf(grade);
        }

        status.setText("Status: "+passedModules+" out of "+result.courseModules.size()+" modules passed");
        credits.setText("Total Credits: "+totalCredits);

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        resultTable.setModel(model);
        JScrollPane scrollPane = new JScrollPane(resultTable);
        resultTabelPanel.add(scrollPane);
        resultTable.revalidate();
        resultTable.repaint();



    }

}
