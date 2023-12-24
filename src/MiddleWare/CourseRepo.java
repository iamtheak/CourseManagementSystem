package MiddleWare;

import Database.DatabaseConnection;
import Model.CourseModels.CMSModule;
import Model.CourseModels.Course;
import Model.CourseModels.CourseModule;
import Utiliy.DatabaseError;

import java.sql.*;
import java.util.ArrayList;

public class CourseRepo implements ICourse{

    public CourseRepo(DatabaseConnection conn){
        _conn = conn;
    }
    private final DatabaseConnection _conn;
    public String mapCourseAndModule(CourseModule courseModule,int courseId){
        try{
            Connection con = _conn.getConnection();
            if(con == null){
                throw new DatabaseError("Connection Failed");
            }

            if(!(courseModule.optionalModuleNumber > 0)){
                courseModule.optionalModuleNumber = 0;
            }
            CallableStatement statement = con.prepareCall("{call InsCourseModule(?,?,?,?,?,?,?)}");
            statement.setInt(1,courseId);
            statement.setString(2,courseModule.moduleName);
            statement.setInt(3,courseModule.year);
            statement.setInt(4,courseModule.semester);
            statement.setInt(5,courseModule.ModuleNumber);
            statement.setBoolean(6,courseModule.isOptional);
            statement.setInt(7,courseModule.optionalModuleNumber);

            ResultSet rs =  statement.executeQuery();
            if(rs.next()){
                return rs.getString("Result");
            }
            _conn.closeConnection(con);
        }
        catch (Exception ex){
            System.out.println("Connection Failed"+ex);
        }
        return "Failure";
    }
    public String addCourse(Course course) {

        try{
            Connection con = _conn.getConnection();
            if(con == null){
                throw new DatabaseError("Connection Failed");
            }

            System.out.println(course.courseName+ course.years+ course.studentLevel);
            CallableStatement statement = con.prepareCall("{call InsCourse(?,?,?,?)}");
            statement.setString(1,course.courseName);
            statement.setInt(2,course.years);
            statement.setInt(3,course.studentLevel);
            statement.setString(4,"Active");
            int courseId = 0;
            ResultSet rs =  statement.executeQuery();
            if(rs.next()){
                courseId = rs.getInt("Result");
            }
            Boolean isSuccessful = false;
            if(courseId != 0){
                for(CourseModule module : course.modules){
                    if(module.moduleName != null){
                        String result = mapCourseAndModule(module,courseId);
                        if (result.equals("Success")){
                            isSuccessful = true;

                        }
                    }
                }
                if(isSuccessful){
                    return "Success";
                }
                else{
                    CallableStatement statement1 = con.prepareCall("{call DelCourseById(?)}");
                    statement1.setInt(1,courseId);
                    ResultSet rs1 =  statement1.executeQuery();
                }
            }
            _conn.closeConnection(con);

        }
        catch (Exception ex){
            System.out.println("Connection Failed"+ex);
        }
        return "Failure";
    }
    public String updateCourse(Course course){
        try {
            Connection con = _conn.getConnection();
            if(con == null){
                throw new DatabaseError("Connection Failed");
            }

            CallableStatement statement = con.prepareCall("{call UpdCourse(?,?,?,?)}");
            statement.setInt(1,course.courseId);
            statement.setString(2,course.courseName);
            statement.setInt(3,course.years);
            statement.setInt(4,course.studentLevel);

            ResultSet rs =  statement.executeQuery();


            String result = "Failure";
            if(rs.next()){
                result = rs.getString("Result");
            }
            _conn.closeConnection(con);
            return result;
        }
        catch (Exception ex){
            System.out.println("Connection Failed"+ex);
        }

        return "Failure";
    }
    public String deleteCourse(int courseId){
        try{
            Connection con = _conn.getConnection();
            if(con == null){
                throw new DatabaseError("Connection Failed");
            }

            CallableStatement statement = con.prepareCall("call DelCourse(?)");
            statement.setInt(1,courseId);

            ResultSet rs =  statement.executeQuery();

            String result = "Failure";
            if(rs.next()){
                result  = rs.getString("Result");
            }
            _conn.closeConnection(con);
            return result;

        }
        catch (Exception ex){
            System.out.println("Connection Failed"+ex);
        }
        return "Failure";
    }
    public Course viewCourseById(int id){

        try{

            Connection con = _conn.getConnection();

            CallableStatement statement = con.prepareCall("SELECT * FROM Course WHERE CourseId = ?");
            statement.setInt(1,id);
            ResultSet rs = statement.executeQuery();
            Course course = new Course();
            if (rs.next()){

                course.courseId = rs.getInt("CourseId");
                course.courseName = rs.getString("CourseName");
                course.years = rs.getInt("Years");
                course.studentLevel = rs.getInt("StudentLevel");
                String status = rs.getString("CourseStatus");
                course.setCourseStatus(status);
            }

            _conn.closeConnection(con);

            return course;

        }
        catch (Exception ex){
            System.out.println("Connection Failed"+ex);
        }

        return null;
    }
    public ArrayList<CourseModule> getCourseModules(){
        return null;
    }
    public String addModule(CMSModule module){
        try{
            Connection con = _conn.getConnection();
            if(con == null){
                throw new DatabaseError("Connection Failed");
            }

            CallableStatement statement = con.prepareCall("{call InsModule(?,?,?,?)}");
            statement.setString(1,module.moduleName);
            statement.setInt(2,module.credits);
            statement.setString(3,module.moduleCode);
            statement.setInt(4,module.passPercent);

            ResultSet rs =  statement.executeQuery();


            String result = "Failure";
            if(rs.next()){
                result  = rs.getString("Result");
            }
            _conn.closeConnection(con);
            return result;
        }
        catch (Exception ex){
            System.out.println("Connection Failed"+ex);
        }
        return "Failure";
    }
    public String updateModule(CMSModule module){
        try{
            Connection con = _conn.getConnection();
            if(con == null){
                throw new DatabaseError("Connection Failed");
            }
            CallableStatement statement = con.prepareCall("{call UpdModule(?,?,?,?,?)}");
            statement.setInt(1,module.moduleId);
            statement.setString(2,module.moduleName);
            statement.setInt(3,module.credits);
            statement.setString(4,module.moduleCode);
            statement.setInt(5,module.passPercent);

            ResultSet rs =  statement.executeQuery();


            String result = "Failure";
            if(rs.next()){
                result =  rs.getString("Result");
            }
            _conn.closeConnection(con);
            return result;
        }
        catch (Exception ex){
            System.out.println("Connection Failed"+ex);
        }

        return "Failure";
    }
    public String deleteModule(int moduleId){
        try{
            Connection con = _conn.getConnection();
            if(con == null){
                throw new DatabaseError("Connection Failed");
            }

            CallableStatement statement = con.prepareCall("{call DelModule(?)}");
            statement.setInt(1,moduleId);

            ResultSet rs =  statement.executeQuery();

            String result = "Failure";
            if(rs.next()){
                result =  rs.getString("Result");
            }
            _conn.closeConnection(con);
            return result;
        }
        catch (Exception ex){
            System.out.println("Connection Failed"+ex);
        }
        return "Failure";
    }
    public CMSModule viewModuleById(int id){
        try{
            Connection con = _conn.getConnection();
            if(con == null){
                throw new DatabaseError("Connection Failed");
            }

            CallableStatement statement = con.prepareCall("SELECT * FROM Module WHERE ModuleId = ?");
            statement.setInt(1,id);
            ResultSet rs = statement.executeQuery();

            CMSModule module = new CMSModule();


            if(rs.next()){
                module.moduleId = rs.getInt("ModuleId");
                module.moduleName = rs.getString("ModuleName");
                module.moduleCode = rs.getString("ModuleCode");
                module.credits = rs.getInt("Credits");
                module.passPercent = rs.getInt("PassPercent");
            }


            con.close();
            if(module.moduleId != 0){
                return module;
            }
            else{
                return null;
            }
        }
        catch (Exception ex){
            System.out.println("Connection Failed"+ex);
        }

        return null;
    }
    public ArrayList<CMSModule> getModules(){
        try {
            Connection con = _conn.getConnection();
            if (con == null) {
                throw new DatabaseError("Connection Failed");
            }
            CallableStatement statement = con.prepareCall("SELECT * FROM Module");
            ResultSet rs = statement.executeQuery();

            ArrayList<CMSModule> moduleList = new ArrayList<>();
            while (rs.next()) {
                CMSModule module = new CMSModule();
                module.moduleId = rs.getInt("ModuleId");
                module.moduleName = rs.getString("ModuleName");
                module.moduleCode = rs.getString("ModuleCode");
                module.credits = rs.getInt("Credits");
                module.passPercent = rs.getInt("PassPercent");
                moduleList.add(module);
            }
            con.close();
            return moduleList;
        }
        catch (Exception ex){
            System.out.println("Connection Failed"+ex);
        }

        return null;
    }
    public ArrayList<CourseModule> getCourseModules(int courseId){
        try{
            Connection con = _conn.getConnection();
            if(con == null){
                throw new DatabaseError("Connection Failed");
            }

            CallableStatement statement = con.prepareCall("{call SelCourseModuleById(?)}");
            statement.setInt(1,courseId);
            ResultSet rs = statement.executeQuery();

            ArrayList<CourseModule> moduleList = new ArrayList<>();
            while(rs.next()){
                CourseModule module = new CourseModule();
                module.moduleId = rs.getInt("ModuleId");
                module.moduleName = rs.getString("ModuleName");
                module.moduleCode = rs.getString("ModuleCode");
                module.year = rs.getInt("Year");
                module.semester = rs.getInt("Semester");
                module.ModuleNumber = rs.getInt("ModuleNumber");
                module.isOptional = rs.getBoolean("IsOptional");
                module.optionalModuleNumber = rs.getInt("OptionNumber");
                moduleList.add(module);
            }
            con.close();
            return moduleList;
        }
        catch (Exception ex){
            System.out.println("Connection Failed"+ex);
        }

        return null;
    }
    public ArrayList<Course> getCourseList(String Admin){
       try{
           Connection con = _conn.getConnection();
           if(con == null){
               throw new DatabaseError("Connection Failed");
           }

           CallableStatement statement = con.prepareCall("SELECT * FROM Course WHERE CourseStatus != 'Deleted'");
           ResultSet rs = statement.executeQuery();

           ArrayList<Course> courseList = new ArrayList<>();
           while(rs.next()){
               Course course = new Course();
               course.courseId = rs.getInt("CourseId");
               course.courseName = rs.getString("CourseName");
               course.years = rs.getInt("Years");
               String status = rs.getString("CourseStatus");
               course.setCourseStatus(status);

               courseList.add(course);
           }

           con.close();
           return courseList;

       }
       catch (Exception ex){
           System.out.println("Connection Failed"+ex);
       }

       return null;
   }
    public ArrayList<Course> getCourseList(){
        try{
            Connection con = _conn.getConnection();
            if(con == null){
                throw new DatabaseError("Connection Failed");
            }

            CallableStatement statement = con.prepareCall("SELECT * FROM Course WHERE CourseStatus = 'Active'");
            ResultSet rs = statement.executeQuery();


            ArrayList<Course> courseList = new ArrayList<>();
            while(rs.next()){
                Course course = new Course();
                course.courseId = rs.getInt("CourseId");
                course.courseName = rs.getString("CourseName");
                course.years = rs.getInt("Years");
                String status = rs.getString("CourseStatus");
                course.setCourseStatus(status);


                courseList.add(course);
            }
            con.close();
            return courseList;

        }
        catch (Exception ex){
            System.out.println("Connection Failed"+ex);
        }

        return null;
    }
    public Course getCourseByName(String name){

        try{
            Connection con = _conn.getConnection();
            if(con == null){
                throw new DatabaseError("Connection Failed");
            }

            CallableStatement statement = con.prepareCall("SELECT * FROM Course WHERE CourseName = ?");
            statement.setString(1,name);
            ResultSet rs = statement.executeQuery();


            Course course = new Course();
            if(rs.next()){

                course.courseId = rs.getInt("CourseId");
                course.courseName = rs.getString("CourseName");
                course.years = rs.getInt("Years");
                String status = rs.getString("CourseStatus");
                course.setCourseStatus(status);
                course.studentLevel = rs.getInt("StudentLevel");
            }

            con.close();
            return course;
        }
        catch (Exception ex){
            System.out.println("Connection Failed"+ex);
        }
        
        return null;
    }
    public String updateCourseModule(CourseModule courseModule,int courseId){

        try{
            Connection con = _conn.getConnection();
            if (con == null){
                throw new DatabaseError("Connection Failed");
            }
            CallableStatement statement = con.prepareCall("{call dbo.UpdCourseModuleMap(?,?,?,?,?,?)}");

            statement.setInt(1,courseId);
            statement.setString(2,courseModule.moduleName);
            statement.setInt(3,courseModule.year);
            statement.setInt(4,courseModule.semester);
            statement.setInt(5,courseModule.ModuleNumber);
            statement.setInt(6,courseModule.optionalModuleNumber);

            ResultSet rs =  statement.executeQuery();

            String result = "Failure";
            if(rs.next()){
                result =  rs.getString("Result");
            }
            _conn.closeConnection(con);
            return result;
        }
        catch (Exception ex){
            System.out.println("Connection Failed"+ex);
        }
        return "Failure";
    }
    public  String changeCourseStatus(int courseId, String status){
        try{
            Connection con = _conn.getConnection();
            if(con == null){
                throw new DatabaseError("Connection Failed");
            }

            CallableStatement statement = con.prepareCall("{call UpdCourseStatus(?,?)}");
            statement.setInt(1,courseId);
            statement.setString(2,status);

            ResultSet rs =  statement.executeQuery();


            String result = "Failure";
            if(rs.next()){
                result =  rs.getString("Result");
            }

            _conn.closeConnection(con);
            return result;
        }
        catch (Exception ex){
            System.out.println("Connection Failed"+ex);
        }
        return "Failure";
    }
    public String assignTeacher(String moduleName, int teacherId){
        try {
            Connection con = _conn.getConnection();
            if(con == null){
                throw new DatabaseError("Connection Failed");
            }

            CallableStatement statement = con.prepareCall("{call InsTeacherModule(?,?)}");
            statement.setString(1,moduleName);
            statement.setInt(2,teacherId);

            ResultSet rs =  statement.executeQuery();


            String result = "Failure";
            if(rs.next()){
                result =  rs.getString("Result");
            }

            _conn.closeConnection(con);
            return result;
        }
        catch (Exception ex){
            System.out.println("Connection Failed"+ex);
        }
        return null;
    }
    public String unassignTeacher(String moduleName, int teacherId){
        try {

            Connection con = _conn.getConnection();

            if(con == null){
                throw new DatabaseError("Connection Failed");
            }

            CallableStatement statement = con.prepareCall("{call DelTeacherModule(?,?)}");
            statement.setString(1,moduleName);
            statement.setInt(2,teacherId);

            ResultSet rs = statement.executeQuery();
            String result = "Failure";
            if (rs.next()){
                result =  rs.getString("Result");
            }
            con.close();
            return result;
        }
        catch (Exception ex){
            System.out.println("Connection Failed"+ex);
        }

        return "Failure";
    }
    public void markCoursework(){
        System.out.println("markCoursework");
    }
}
