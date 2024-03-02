package Model.CourseModels;

import Model.UserModels.StudentModel;

public class StudentCourseModule extends CourseModule{
    private int grade;

    public StudentModel student;
    public void setGrade(int grade){
        this.grade = grade;
    }
    public int getGrade(){
        return grade;
    }

}
