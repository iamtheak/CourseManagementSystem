package MiddleWare;

import Model.UserModels.StudentModel;

import java.util.ArrayList;

public interface IStudentInterface {
    String addStudent(StudentModel student);
    void updateStudent();
    void deleteStudent();
    StudentModel getStudentDetail(int id);
    void getStudentReport();
    ArrayList<StudentModel> getStudentList();
}
