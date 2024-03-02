package MiddleWare;

import Database.DatabaseConnection;
import Model.CourseModels.CourseModule;
import Model.CourseModels.StudentCourseModule;
import Model.UserModels.StudentModel;
import Model.UserModels.TeacherModel;
import Model.UserModels.UserBaseModel;
import Utiliy.DatabaseError;

import java.security.PublicKey;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

public class TeacherRepo implements ITeacherInterface{

    DatabaseConnection _conn;
    public void updateTeacher() {
        System.out.println("updateTeacher");
    }


    public ArrayList<TeacherModel> getTeacherListByModule(int moduleId) {
        try {
            Connection con = _conn.getConnection();

            if(con == null){
                throw new DatabaseError("Connection Failed");
            }

            CallableStatement statement = con.prepareCall("{call SelTeacherByModuleId(?)}");
            statement.setInt(1,moduleId);

            ResultSet rs = statement.executeQuery();
            ArrayList<TeacherModel> teachers = new ArrayList<>();

            while(rs.next()){
                TeacherModel teacher = new TeacherModel();
                teacher.firstName = rs.getString("FirstName");
                teacher.middleName = rs.getString("MiddleName");
                teacher.lastName = rs.getString("LastName");
                teacher.email = rs.getString("Email");
                teacher.teacherId = rs.getInt("TeacherId");
                teachers.add(teacher);
            }

            con.close();
            return teachers;
        }
        catch (Exception ex){
            System.out.println("Error: "+ex);
        }
        return null;
    }
    public TeacherRepo(DatabaseConnection conn){
        _conn = conn;
    }

    public String addTeacher(TeacherModel teacher) {
        try{
            Connection con = _conn.getConnection();
            if(con == null){
                throw new DatabaseError("Connection Failed");
            }

            LocalDate utilDate = teacher.dateOfBirth;
            java.sql.Date sqlDate = java.sql.Date.valueOf(utilDate);

            System.out.println(sqlDate);
            String role = teacher.getRole();

            CallableStatement statement = con.prepareCall("{call InsUser(?,?,?,?,?,?,?,?,?)}");
            statement.setString(1,teacher.firstName);
            statement.setString(2,teacher.middleName);
            statement.setString(3,teacher.lastName);
            statement.setString(4,teacher.email);
            statement.setString(5,teacher.phoneNumber);
            statement.setDate(6,sqlDate);
            String password = teacher.getPassword();
            statement.setString(7,password);
            statement.setInt(8,0);
            statement.setString(9,role);
            ResultSet rs = statement.executeQuery();


            String result = "";
            if(rs.next()){
                result =  rs.getString("Result");
            }

            _conn.closeConnection(con);
            return result;


        }catch(Exception ex){
            System.out.println("Connection Failed"+ex);
        }


        return null;
    }
    public String deleteTeacher(int id) {

        try{
            Connection con = _conn.getConnection();


            if(con == null){
                throw new DatabaseError("Connection Failed");
            }

            CallableStatement statement = con.prepareCall("{call DelTeacher(?)}");
            statement.setInt(1,id);
            ResultSet rs = statement.executeQuery();

            String result = "";
            if(rs.next()){
                result =  rs.getString("Result");
            }

            _conn.closeConnection(con);
            return result;
        }
        catch (Exception ex){
            System.out.println("Error: "+ex);
        }

        return "Failure";
    }
    public TeacherModel viewTeacherById(int id) {
        try{
            Connection con = _conn.getConnection();

            if(con == null){
                throw new DatabaseError("Connection Failed");
            }

            CallableStatement statement = con.prepareCall("SELECT * FROM Teacher t inner join UserInfo u on t.userId = u.userId where t.teacherId = ?");
            statement.setInt(1,id);

            ResultSet rs = statement.executeQuery();

            TeacherModel teacher = new TeacherModel();

            if(rs.next()){
                teacher.firstName = rs.getString("FirstName");
                teacher.middleName = rs.getString("MiddleName");
                teacher.lastName = rs.getString("LastName");
                teacher.email = rs.getString("Email");
                teacher.teacherId = rs.getInt("TeacherId");
                teacher.phoneNumber = rs.getString("PhoneNumber");
                teacher.dateOfBirth = rs.getDate("DateOfBirth").toLocalDate();
                teacher.userId = rs.getInt("UserId");
            }

            con.close();
            return teacher;
        }
        catch (Exception ex){
            System.out.println("Error: "+ex);
        }
        return null;
    }
    public ArrayList<TeacherModel> getTeacherList() {
        try{
            Connection conn = _conn.getConnection();

            if(conn == null){
                throw new DatabaseError("Connection to database failed");
            }

            ArrayList<TeacherModel> teachers = new ArrayList<>();

            CallableStatement stmt = conn.prepareCall("SELECT * FROM Teacher t inner join UserInfo u on t.userId = u.userId");

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                TeacherModel teacher = new TeacherModel();
                teacher.firstName = rs.getString("FirstName");
                teacher.middleName = rs.getString("MiddleName");
                teacher.lastName = rs.getString("LastName");
                teacher.email = rs.getString("Email");
                teacher.teacherId = rs.getInt("TeacherId");
                teacher.phoneNumber = rs.getString("PhoneNumber");
                teacher.dateOfBirth = rs.getDate("DateOfBirth").toLocalDate();
                teacher.userId = rs.getInt("UserId");
                teachers.add(teacher);
            }

            return teachers;
        }
        catch (Exception ex){
            System.out.println("Error: "+ex);
        }
        return null;
    }
    public ArrayList<CourseModule> getAssignedModules(int teacherId) {
        try{
            Connection con = _conn.getConnection();

            if(con == null){
                throw new DatabaseError("Connection Failed");
            }

            CallableStatement statement = con.prepareCall("{call SelTeacherModules(?)}");
            statement.setInt(1,teacherId);

            ResultSet rs = statement.executeQuery();
            ArrayList<CourseModule> modules = new ArrayList<>();

            while(rs.next()){
                CourseModule module = new CourseModule();
                module.moduleId = rs.getInt("ModuleId");
                module.moduleName = rs.getString("ModuleName");
                module.moduleCode = rs.getString("ModuleCode");
                modules.add(module);
            }

            return modules;
        }
        catch (Exception ex){
            System.out.println("Error: "+ex);
        }
        return null;
    }
    public ArrayList<StudentCourseModule> getStudentListByModule(int moduleId){
        try{
            Connection con = _conn.getConnection();

            if(con == null){
                throw new DatabaseError("Connection Failed");
            }

            CallableStatement statement = con.prepareCall("{call SelStudentByModuleId(?)}");
            statement.setInt(1,moduleId);

            ResultSet rs = statement.executeQuery();
            ArrayList<StudentCourseModule> students = new ArrayList<>();

            while(rs.next()){
                StudentCourseModule studentModule = new StudentCourseModule();

                studentModule.student = new StudentModel();
                studentModule.student.studentId = rs.getInt("StudentId");
                studentModule.student.firstName = rs.getString("FirstName");
                studentModule.student.middleName = rs.getString("MiddleName");
                studentModule.student.lastName = rs.getString("LastName");
                studentModule.student.level = rs.getInt("Level");
                studentModule.setGrade(rs.getInt("Grade"));
                studentModule.moduleId = rs.getInt("ModuleId");
                studentModule.moduleName = rs.getString("ModuleName");
                studentModule.moduleCode = rs.getString("ModuleCode");
                studentModule.passPercent = rs.getInt("PassPercent");
                students.add(studentModule);
            }

            con.close();
            return students;
        }
        catch (Exception ex){
            System.out.println("Error: "+ex);
        }

        return null;
    }
    public  String giveMark(int studentId,int moduleId ,int mark){

        try{
            Connection con = _conn.getConnection();

            if(con == null){
                throw new DatabaseError("Connection Failed");
            }

            CallableStatement statement = con.prepareCall("{call InsStudentModuleGrade(?,?,?)}");
            statement.setInt(1,studentId);
            statement.setInt(2,moduleId);
            statement.setInt(3,mark);

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

    public TeacherModel  getTeacherByUserId(int userId){
        try{
            Connection con = _conn.getConnection();

            if(con == null){
                throw new DatabaseError("Connection Failed");
            }

            CallableStatement statement = con.prepareCall("SELECT * FROM Teacher as t INNER JOIN UserInfo AS ui on ui.UserId = t.UserId WHERE t.userId = ?");
            statement.setInt(1,userId);

            ResultSet rs = statement.executeQuery();

            TeacherModel teacher = new TeacherModel();

            if(rs.next()){
                teacher.firstName = rs.getString("FirstName");
                teacher.middleName = rs.getString("MiddleName");
                teacher.lastName = rs.getString("LastName");
                teacher.email = rs.getString("Email");
                teacher.teacherId = rs.getInt("TeacherId");
                teacher.phoneNumber = rs.getString("PhoneNumber");
                teacher.dateOfBirth = rs.getDate("DateOfBirth").toLocalDate();
                teacher.userId = rs.getInt("UserId");
            }

            con.close();
            return teacher;
        }
        catch (Exception ex){
            System.out.println("Error: "+ex);
        }
        return null;
    }
}
