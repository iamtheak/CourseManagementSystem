package MiddleWare;

import Model.CourseModels.CMSModule;
import Model.CourseModels.Course;
import Model.CourseModels.CourseModule;

import java.util.ArrayList;

public interface ICourse {
    String addCourse(Course course);
    String updateCourse(Course course);
    String deleteCourse(int courseId);
    Course viewCourseById(int id);
    String addModule(CMSModule module);
    String updateModule(CMSModule module);
    String deleteModule(int moduleId);
    CMSModule viewModuleById(int id);
    ArrayList<CMSModule> getModules();
    String updateCourseModule(CourseModule courseModule,int courseId);
    ArrayList<CourseModule> getCourseModules(int courseId);
    public ArrayList<CourseModule> getCourseModules();
    ArrayList<Course> getCourseList();
    public String changeCourseStatus(int courseId, String status);
    ArrayList<Course> getCourseList(String admin);
    void markCoursework();
    Course getCourseByName(String name);
    String assignTeacher(String moduleName, int teacherId);
    String unassignTeacher(String moduleName, int teacherId);

}

