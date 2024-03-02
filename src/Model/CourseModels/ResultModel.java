package Model.CourseModels;

import Model.UserModels.StudentModel;

import java.util.ArrayList;

public class ResultModel {
    public ArrayList<StudentCourseModule> courseModules;

    public StudentModel student;
    public float gpa;
    public int passedModules;
    public int totalModules;
    public  boolean isPassed;
    public ResultModel(ArrayList<StudentCourseModule> courseModules, float gpa){
        this.courseModules = courseModules;
        this.gpa = gpa;
    }

    public ResultModel(){
        courseModules = new ArrayList<>();
        student = new StudentModel();
    }
}
