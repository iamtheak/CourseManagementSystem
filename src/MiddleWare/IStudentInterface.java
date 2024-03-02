package MiddleWare;


import Model.CourseModels.ResultModel;
import Model.CourseModels.StudentCourseModule;
import Model.UserModels.StudentModel;

import java.util.ArrayList;

public interface IStudentInterface {
    String addStudent(StudentModel student);
    StudentModel getStudentDetail(int id);
    ResultModel getStudentReport(int studentId);
    ArrayList<StudentModel> getStudentList();
    ArrayList<StudentCourseModule> getEnrolledModules(int studentId);
    String enrollModules(int studentId, int moduleId,int year);
    StudentModel getStudentByUserId(int userId);
    String progressYear(int studentId);
}
