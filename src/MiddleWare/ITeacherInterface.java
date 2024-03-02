package MiddleWare;

import Model.CourseModels.CourseModule;
import Model.CourseModels.StudentCourseModule;
import Model.UserModels.TeacherModel;

import java.util.ArrayList;

public interface ITeacherInterface {
    String addTeacher(TeacherModel teacher);
    String deleteTeacher(int id);
    TeacherModel viewTeacherById(int id);
    ArrayList<TeacherModel> getTeacherList();
    ArrayList<CourseModule> getAssignedModules(int teacherId);
    ArrayList<TeacherModel> getTeacherListByModule(int moduleId);
    ArrayList<StudentCourseModule> getStudentListByModule(int moduleId);
    String giveMark(int studentId,int moduleId, int mark);
    TeacherModel getTeacherByUserId(int userId);

}
