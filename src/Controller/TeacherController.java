package Controller;

import Database.DatabaseConnection;
import MiddleWare.*;
import Model.CourseModels.CMSModule;
import Model.CourseModels.CourseModule;
import Model.CourseModels.StudentCourseModule;
import Model.UserModels.StudentModel;
import Model.UserModels.TeacherModel;

import java.util.ArrayList;

public class TeacherController {
    ITeacherInterface _teacher = new TeacherRepo(new DatabaseConnection());

    ICourse _course = new CourseRepo(new DatabaseConnection());
    public String[][] GetModulesForTable(int teacherId){
        ArrayList<CourseModule> modules = _teacher.getAssignedModules(teacherId);
        String moudules[][] = new String[modules.size()][5];
        for(CourseModule module : modules){
            moudules[modules.indexOf(module)][0] = String.valueOf(module.moduleId);
            moudules[modules.indexOf(module)][1] = module.moduleName;
            moudules[modules.indexOf(module)][2] = module.moduleCode;
            moudules[modules.indexOf(module)][3] = String.valueOf(module.passPercent);
        }
        return moudules;
    }
    public String[][] GetStudentsOfModule(int moduleId){
        ArrayList<StudentCourseModule> students = _teacher.getStudentListByModule(moduleId);

        if(students == null)
            return null;

        String data[][] = new String[students.size()][3];
        for(int i=0;i<students.size();i++){
            data[i][0] = String.valueOf(students.get(i).student.studentId);
            data[i][1] = students.get(i).student.firstName+" "+students.get(i).student.middleName+" "+students.get(i).student.lastName;
            data[i][2] = String.valueOf(students.get(i).getGrade());
        }
        return data;
    }
    public String GiveMark(int studentId, int moduleId,int mark){
        return _teacher.giveMark(studentId,moduleId,mark);
    }
    public TeacherModel GetTeacherByUserId(int userId){
        return _teacher.getTeacherByUserId(userId);
    }

    public boolean IsModuleExists(int moduleId){
        CMSModule module =  _course.viewModuleById(moduleId);
        System.out.println(module.moduleName);
        return module != null;
    }
}
