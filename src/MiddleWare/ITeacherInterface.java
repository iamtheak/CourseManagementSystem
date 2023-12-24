package MiddleWare;

import Model.CourseModels.CourseModule;
import Model.UserModels.TeacherModel;

import java.util.ArrayList;

public interface ITeacherInterface {
    void updateTeacher();
    String addTeacher(TeacherModel teacher);
    String deleteTeacher(int id);
    TeacherModel viewTeacherById(int id);
    ArrayList<TeacherModel> getTeacherList();
    ArrayList<CourseModule> getAssignedModules(int teacherId);

}
