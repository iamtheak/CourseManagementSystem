package Controller;

import Database.DatabaseConnection;
import MiddleWare.*;
import Model.CourseModels.CMSModule;
import Model.CourseModels.Course;
import Model.CourseModels.CourseModule;
import Model.UserModels.StudentModel;
import Model.UserModels.TeacherModel;

import java.awt.event.MouseAdapter;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class AdminController {

    IStudentInterface _student = new StudentRepo(new DatabaseConnection());
    ITeacherInterface _teacher = new TeacherRepo(new DatabaseConnection());
    ICourse _course = new CourseRepo(new DatabaseConnection());

    public String[][] GetModulesForTable(){
        ArrayList<CMSModule> modules = _course.getModules();
        String moudules[][] = new String[modules.size()][5];
        for(CMSModule module : modules){
            moudules[modules.indexOf(module)][0] = String.valueOf(module.moduleId);
            moudules[modules.indexOf(module)][1] = module.moduleName;
            moudules[modules.indexOf(module)][2] = module.moduleCode;
            moudules[modules.indexOf(module)][3] = String.valueOf(module.credits);
            moudules[modules.indexOf(module)][4] = String.valueOf(module.passPercent);
        }
        return moudules;
    }public CMSModule GetModuleById(int id){

        try{
            return  _course.viewModuleById(id);
        }
        catch (Exception ex){
            System.out.println("Error: "+ex);
        }

        return null;
    }
   public String AddModule(String name,String code,int credits,int passPercent){
        CMSModule module = new CMSModule(name,0,credits,code,passPercent);

        return _course.addModule(module);
   }
   public String EditModule(int id,String name,String code,int credits,int passPercent){
        CMSModule module = new CMSModule(name,id,credits,code,passPercent);
        return _course.updateModule(module);
   }
   public String[] GetModuleNames(){
        try {
            ArrayList<CMSModule> modules = _course.getModules();
            String[] moduleNames = new String[modules.size()+1];
            moduleNames[0] = "Select Module";
            for (int i = 0; i < modules.size(); i++) {
                moduleNames[i+1] = modules.get(i).moduleName;
            }
            return moduleNames;
        }
        catch (Exception ex){
            System.out.println("Error: "+ex);
        }
        return null;
   }
   public String DeleteModule(int id){

        try{
            return _course.deleteModule(id);
        }
        catch (Exception ex){
            System.out.println("Error: "+ex);
        }
        return "Failure";
   }
   public String AddCourse(Course course){
        return _course.addCourse(course);
   }
   public String UpdateCourse(Course course){
        String result = _course.updateCourse(course);

        if(result.equals("Failure")){
            return result;
        }
        boolean isValid = false;
        for(CourseModule module: course.modules){

            String resp = _course.updateCourseModule(module,course.courseId);
            if(resp.equals("Success")){
                isValid = true;
            }
        }
        if(isValid){
            return "Success";
        }
        return "Failure";
   }
   public String[][] GetCoursesForTable(){

        ArrayList<Course> courses = _course.getCourseList("Admin");
        String courseList[][] = new String[courses.size()][4];
        for(Course course: courses){
            courseList[courses.indexOf(course)][0] = String.valueOf(course.courseId);
            courseList[courses.indexOf(course)][1] = course.courseName;
            courseList[courses.indexOf(course)][2] = course.years+"";
            courseList[courses.indexOf(course)][3] = course.getCourseStatus();

        }
        return courseList;

   }
   public String ChangeStatus(int courseId,String status){
        return _course.changeCourseStatus(courseId,status);
   }
   public String DeleteCourse(int courseId){
        return _course.changeCourseStatus(courseId,"Deleted");
   }
   public Course GetCourseById(int id){
        Course course = _course.viewCourseById(id);
        course.modules = _course.getCourseModules(id);
        return course;
   }
   public String DeleteTeacher(int id){
        return _teacher.deleteTeacher(id);
   }

   public String[][] GetTeachersForTable(){
        ArrayList<TeacherModel> teachers = _teacher.getTeacherList();
        String teacherList[][] = new String[teachers.size()][7];
        for(TeacherModel teacher: teachers){
            teacherList[teachers.indexOf(teacher)][0] = String.valueOf(teacher.teacherId);
            teacherList[teachers.indexOf(teacher)][1] = teacher.firstName;
            teacherList[teachers.indexOf(teacher)][2] = teacher.middleName;
            teacherList[teachers.indexOf(teacher)][3] = teacher.lastName;
            teacherList[teachers.indexOf(teacher)][4] = teacher.email;
            teacherList[teachers.indexOf(teacher)][5] = teacher.phoneNumber;
            teacherList[teachers.indexOf(teacher)][6] = teacher.dateOfBirth.toString();
        }

        return teacherList;
   }
   public String GetCourseCount(){
       ArrayList<Course> courses = _course.getCourseList("Admin");
       return String.valueOf(courses.size());
   }
   
   public String GetTeacherCount(){
       ArrayList<TeacherModel> teachers = _teacher.getTeacherList();
       return String.valueOf(teachers.size());
   }
   
//    public String GetStudentCount(){
//         ArrayList<StudentModel> students = _student.getStudentList();
//         return String.valueOf(teachers.size());
//    }
    public String[] GetTeacherNames(){
        try {
            ArrayList<TeacherModel> teachers = _teacher.getTeacherList();
            String[] teacherNames = new String[teachers.size()+1];
            teacherNames[0] = "Select Teacher";
            for (int i = 0; i < teachers.size(); i++) {
                teacherNames[i+1] = teachers.get(i).firstName+" "+" "+teachers.get(i).middleName+teachers.get(i).lastName;
            }
            return teacherNames;
        }
        catch (Exception ex){
            System.out.println("Error: "+ex);
        }
        return null;
    }

    public String AssignTeacher(String moduleName,int teacherId){

       try {
           ArrayList<CourseModule> modules = _teacher.getAssignedModules(teacherId);

           if(modules.size() == 4){
               return "LimitReached";
           }
           if(!modules.isEmpty()) {
               for (CourseModule module : modules) {
                   if (module.moduleName.equals(moduleName)) {
                       return "Assigned";
                   }
               }
           }
           return _course.assignTeacher(moduleName,teacherId);
       }
         catch (Exception ex){
              System.out.println("Error: "+ex);
         }
       return "Failure";
    }


    public TeacherModel GetTeacherById(int id){
        return _teacher.viewTeacherById(id);
    }
    public String[] GetAssignedModuleNames(int teacherId){
        try {
            ArrayList<CourseModule> modules = _teacher.getAssignedModules(teacherId);
            String[] moduleNames = new String[modules.size()];
            for (int i = 0; i < modules.size(); i++) {
                moduleNames[i] = modules.get(i).moduleName;
            }
            return moduleNames;
        }
        catch (Exception ex){
            System.out.println("Error: "+ex);
        }
        return null;
    }

    public String UnAssignTeacher(String moduleName,int teacherId){
        return _course.unassignTeacher(moduleName,teacherId);
    }
}
