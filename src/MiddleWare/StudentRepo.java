package MiddleWare;

import Database.DatabaseConnection;

import Model.CourseModels.ResultModel;
import Model.CourseModels.StudentCourseModule;
import Model.UserModels.StudentModel;
import Utiliy.DatabaseError;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class StudentRepo implements IStudentInterface{

    public StudentRepo(DatabaseConnection conn){
        _conn = conn;
    }
    private final DatabaseConnection _conn;
    public String addStudent(StudentModel student) {
        try{
            Connection con = _conn.getConnection();
            if(con == null){
                throw new DatabaseError("Connection Failed");
            }

            LocalDate utilDate = student.dateOfBirth;
            java.sql.Date sqlDate = java.sql.Date.valueOf(utilDate);


            String role = student.getRole();

            CallableStatement statement = con.prepareCall("{call InsUser(?,?,?,?,?,?,?,?,?)}");
            statement.setString(1,student.firstName);
            statement.setString(2,student.middleName);
            statement.setString(3,student.lastName);
            statement.setString(4,student.email);
            statement.setString(5,student.phoneNumber);
            statement.setDate(6,sqlDate);
            String password = student.getPassword();
            statement.setString(7,password);
            statement.setInt(8,student.courseId);
            statement.setString(9,role);
            ResultSet rs = statement.executeQuery();


            String result = "";
            if(rs.next()){
                result =  rs.getString("Result");
            }

            con.close();
            return result;


        }catch(Exception ex){
            System.out.println("Connection Failed"+ex);
        }


        return null;
    }
    public void updateStudent(){
        System.out.println("updateStudent");
    }
    public void deleteStudent(){
        System.out.println("deleteStudent");
    }
    public StudentModel getStudentDetail(int id){
        try {
            Connection con = _conn.getConnection();

            if(con == null){
                throw new DatabaseError("Connection Failed");
            }
            CallableStatement statement = con.prepareCall("{call SelStudentInfoById(?)}");
            statement.setInt(1,id);
            ResultSet rs = statement.executeQuery();
            StudentModel student = new StudentModel();

            rs.next();
            student.studentId = rs.getInt("StudentId");
            student.firstName =  rs.getString("FirstName");
            student.middleName = rs.getString("MiddleName");
            student.lastName = rs.getString("LastName");
            student.email = rs.getString("Email");
            student.dateOfBirth = rs.getDate("DateOfBirth").toLocalDate();
            student.level = rs.getInt("Level");
            student.phoneNumber = rs.getString("PhoneNumber");


            _conn.closeConnection(con);
            return student;


        }catch (Exception ex){
            System.out.println("Connection Failed"+ex);
        }

        return new StudentModel();
    }
    public ArrayList<StudentModel> getStudentList(){

        try{
               Connection con = _conn.getConnection();

                if(con == null){
                     throw new DatabaseError("Connection Failed");
                }
                ArrayList<StudentModel> students = new ArrayList<>();

                CallableStatement statement = con.prepareCall("{call SelStudentInfo()}");

                ResultSet rs = statement.executeQuery();
                while (rs.next()){
                    StudentModel student = new StudentModel();
                    student.studentId = rs.getInt("StudentId");
                    student.firstName =  rs.getString("FirstName");
                    student.middleName = rs.getString("MiddleName");
                    student.lastName = rs.getString("LastName");
                    student.email = rs.getString("Email");
                    student.dateOfBirth = rs.getDate("DateOfBirth").toLocalDate();
                    student.level = rs.getInt("Level");
                    student.courseName = rs.getString("CourseName");
                    student.phoneNumber = rs.getString("PhoneNumber");
                    students.add(student);
                }
                con.close();
                return students;

        }
        catch (Exception ex){
            System.out.println("Connection Failed"+ex);
        }
        return null;
    }
    public ResultModel getStudentReport(int studentId){

        try {
            Connection con = _conn.getConnection();

            if(con == null){
                throw new DatabaseError("Connection Failed");
            }
            CallableStatement statement = con.prepareCall("{call SelResultByStudentId(?)}");
            statement.setInt(1,studentId);
            ResultSet rs = statement.executeQuery();
            ResultModel result = new ResultModel();


            while (rs.next()){

                StudentCourseModule module = new StudentCourseModule();
                module.student = new StudentModel();

                module.moduleId = rs.getInt("ModuleId");
                module.moduleName = rs.getString("ModuleName");
                module.moduleCode = rs.getString("ModuleCode");
                module.passPercent = rs.getInt("PassPercent");
                module.setGrade(rs.getInt("Grade"));
                module.credits = rs.getInt("Credits");
                module.year = rs.getInt("Year");
                result.student.studentId = rs.getInt("StudentId");
                result.student.firstName = rs.getString("FirstName");
                result.student.middleName = rs.getString("MiddleName");
                result.student.lastName = rs.getString("LastName");


                result.courseModules.add(module);
            }


            con.close();
            return result;
        }
        catch (Exception ex){
            System.out.println("Error: "+ex);
        }

        return null;
    }
    public ArrayList<StudentCourseModule> getEnrolledModules(int studentId){
        try{
            Connection con = _conn.getConnection();

            if(con == null){
                throw new DatabaseError("Connection Failed");
            }
            ArrayList<StudentCourseModule> modules = new ArrayList<>();
            CallableStatement statement = con.prepareCall("{call SelStudentModules(?)}");
            statement.setInt(1,studentId);
            ResultSet rs = statement.executeQuery();


            while(rs.next()){
                StudentCourseModule module = new StudentCourseModule();

                module.student = new StudentModel();
                module.moduleId = rs.getInt("ModuleId");
                module.moduleName = rs.getString("ModuleName");
                module.moduleCode = rs.getString("ModuleCode");
                module.passPercent = rs.getInt("PassPercent");
                module.setGrade(rs.getInt("Grade"));
                module.credits = rs.getInt("Credits");
                module.semester = rs.getInt("Semester");
                module.year = rs.getInt("Year");
                module.student.studentId = rs.getInt("StudentId");
                modules.add(module);
            }
            con.close();
            return modules;
        }
        catch (Exception ex){
            System.out.println("Error: "+ex);
        }


        return null;
    }
    public StudentModel getStudentByUserId(int userId){

        try{
            Connection con = _conn.getConnection();

            if(con == null){
                throw new DatabaseError("Connection Failed");
            }

            CallableStatement statement = con.prepareCall("{call SelStudentByUserId(?)}");
            statement.setInt(1,userId);

            ResultSet rs = statement.executeQuery();
            StudentModel student = new StudentModel();

            rs.next();
            student.studentId = rs.getInt("StudentId");
            student.firstName =  rs.getString("FirstName");
            student.middleName = rs.getString("MiddleName");
            student.lastName = rs.getString("LastName");
            student.email = rs.getString("Email");
            student.dateOfBirth = rs.getDate("DateOfBirth").toLocalDate();
            student.level = rs.getInt("Level");
            student.phoneNumber = rs.getString("PhoneNumber");
            student.userId = rs.getInt("UserId");
            student.courseId = rs.getInt("CourseId");
            student.year = rs.getInt("Year");
            student.courseName = rs.getString("CourseName");

            con.close();
            return student;
        }
        catch (Exception ex){
            System.out.println("Error: "+ex);
        }

        return null;
    }
    public String enrollModules(int studentId, int moduleId,int year){
        try{
            Connection con = _conn.getConnection();

            if(con == null){
                throw new DatabaseError("Connection Failed");
            }

            CallableStatement statement = con.prepareCall("{call InsStudentModule(?,?,?)}");
            statement.setInt(1,moduleId);
            statement.setInt(2,studentId);
            statement.setInt(3,year);
            ResultSet rs = statement.executeQuery();
            String result = "";
            if(rs.next()){
                result =  rs.getString("Result");
            }

            con.close();
            return result;
        }
        catch (Exception ex){
            System.out.println("Error: "+ex);
        }
        return null;
    }

    public  String progressYear(int studentId){


        try{
            Connection con = _conn.getConnection();

            if(con == null){
                throw new DatabaseError("Connection Failed");
            }

            CallableStatement statement = con.prepareCall("{call ProgressYear(?)}");
            statement.setInt(1,studentId);
            ResultSet rs = statement.executeQuery();
            String result = "";
            if(rs.next()){
                result =  rs.getString("Result");
            }

            con.close();
            return result;

        }
        catch (Exception ex){
            System.out.println("Error: "+ex);
        }
        return "Failure";
    }

}
