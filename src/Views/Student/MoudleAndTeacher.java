package Views.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MoudleAndTeacher extends JPanel{
    private JPanel mainPanel;
    private JPanel teacherModulePanel;
    private JLabel moduleName;
    private JTable teacherModuleTable;

    public MoudleAndTeacher(String data[][]){
        add(mainPanel);

        String columnName[] = {"Module Name","Module Code","Semester","Teachers"};


        DefaultTableModel model = new DefaultTableModel(data, columnName);

        teacherModuleTable.setModel(model);

        JScrollPane scrollPane = new JScrollPane(teacherModuleTable);
        teacherModulePanel.add(scrollPane);

        teacherModuleTable.revalidate();
        teacherModuleTable.repaint();

    }
}
