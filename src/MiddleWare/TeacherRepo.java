package MiddleWare;

import Database.DatabaseConnection;
import Model.CourseModels.CourseModule;
import Model.UserModels.TeacherModel;
import Utiliy.DatabaseError;

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


}
