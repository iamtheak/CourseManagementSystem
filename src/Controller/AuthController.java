package Controller;
import Database.DatabaseConnection;
import MiddleWare.*;
import Model.AuthModels.LoginModel;
import Model.AuthModels.SignUpModel;
import Model.CourseModels.Course;
import Model.UserModels.StudentModel;
import Model.UserModels.TeacherModel;
import Model.UserModels.UserBaseModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class AuthController {
    IStudentInterface _student = new StudentRepo(new DatabaseConnection());
    ITeacherInterface _Teacher = new TeacherRepo(new DatabaseConnection());
    ICourse _course = new CourseRepo(new DatabaseConnection());
    IAuth _auth = new AuthRepo(new DatabaseConnection());

    public String[] GetCourseListForSignUp(){
        try{
            ArrayList<Course> courses = _course.getCourseList();
            System.out.println(courses);
            String[] courseList = new String[courses.size()];
            int count = 0;
            for(Course course: courses){
                courseList[count] = course.courseName;
                count++;
            }
            return courseList;
        }
        catch (Exception ex){
            System.out.println("Connection Failed"+ ex.getMessage());
        }

        return null;
    }

    public String Login(String role, LoginModel login){
        return switch (role) {
            case "Student" -> _auth.studentLogin(login);
            case "Teacher" -> _auth.TeacherLogin(login);
            case "Admin" -> _auth.adminLogin(login);
            default -> "Failure";
        };
    }
    public String StudentSignup(SignUpModel model,String Course){
        try{
            Boolean isValid = model.checkValidity();
            if(!isValid){
                return model.errorMessage;
            }
            Course course = _course.getCourseByName(Course);
            StudentModel student = new StudentModel();
            student.firstName = model.firstName;
            student.middleName = model.middleName;
            student.lastName = model.lastName;
            student.email = model.email;
            student.phoneNumber = model.phone;
            student.dateOfBirth = LocalDate.parse(model.dateOfBirth, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            student.setPassword(model.password);
            student.courseId = course.courseId;

            return _student.addStudent(student);
        }
        catch(Exception ex){
            System.out.println("Connection Failed"+ ex.getMessage());
        }

        return "Failure";

    }
    public String TeacherSignup(SignUpModel model){
        try {
            boolean isValid = model.checkValidity();

            if(!isValid){
                return model.errorMessage;
            }
            TeacherModel teacher = new TeacherModel();
            teacher.firstName = model.firstName;
            teacher.middleName = model.middleName;
            teacher.lastName = model.lastName;
            teacher.email = model.email;
            teacher.phoneNumber = model.phone;
            teacher.dateOfBirth = LocalDate.parse(model.dateOfBirth, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            teacher.setPassword(model.password);
            return _Teacher.addTeacher(teacher);
        }
        catch (Exception ex){
            System.out.println("Connection Failed"+ ex.getMessage());
        }
        return "Failure";
    }

    public UserBaseModel getUserInfo(String email) {
        return _auth.getUserInfo(email);
    }

}
