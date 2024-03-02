package Controller;

import Database.DatabaseConnection;
import MiddleWare.*;
import Model.CourseModels.CourseModule;
import Model.CourseModels.ResultModel;
import Model.CourseModels.StudentCourseModule;
import Model.UserModels.StudentModel;
import Model.UserModels.TeacherModel;

import java.util.ArrayList;
import java.util.HashMap;

public class StudentController {
    IStudentInterface _student = new StudentRepo(new DatabaseConnection());
    ITeacherInterface _teacher = new TeacherRepo(new DatabaseConnection());
    ICourse _course = new CourseRepo(new DatabaseConnection());
    public StudentModel GetStudentInfoByUserId(int userId){
        return  _student.getStudentByUserId(userId);
    }
    public ArrayList<StudentCourseModule> GetEnrolledCourseModules(int studentId){
        return _student.getEnrolledModules(studentId);
    }
    public ArrayList<TeacherModel> GetTeachersOfModule(int moduleId){
        return _teacher.getTeacherListByModule(moduleId);
    }
    public ArrayList<CourseModule> GetCourseModulesForEnrollment(int courseId,int year){
        return _course.getCourseModulesForEnrollment(courseId,year);
    }
    public ArrayList<CourseModule> GetCourseModules(int courseId){
        return _course.getCourseModules(courseId);
    }
    public String EnrollModules(int moduleIds[], int studentId,int year){

        String result = "";
        for(int moduleId: moduleIds){

            System.out.println("Module Id: "+moduleId);
            result = _student.enrollModules(studentId,moduleId,year);
        }
        return result;
    }
    public ResultModel GetStudentReport(int studentId){
        return _student.getStudentReport(studentId);
    }

    public String ProgressYear(int studentId){
        return _student.progressYear(studentId);
    }

    public boolean IsPassed(int studentId){
        ResultModel result = _student.getStudentReport(studentId);

        HashMap<Integer, Integer> moduleGrades = new HashMap<>();
        for(int i=0;i<result.courseModules.size();i++){

            int year = result.courseModules.get(i).year;

            if(moduleGrades.containsKey(year)){
                moduleGrades.put(year,moduleGrades.get(year)+1);
            }
            else{
                moduleGrades.put(year,1);
            }
        }

        for (int year: moduleGrades.keySet()){
            if(moduleGrades.get(year) < 4){
                return false;
            }
        }
        return true;
    }

    public StudentModel GetStudentDetail(int id){
        return _student.getStudentDetail(id);
    }
}
