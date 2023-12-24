package Model.CourseModels;

import java.util.ArrayList;

public class Course {
    public String courseName;
    public int courseId;
    public int years;
    private String courseStatus;
    public int studentLevel;
    public ArrayList<CourseModule> modules;
    public Course(String courseName, int courseId, int years, String courseStatus){
        this.courseName = courseName;
        this.courseId = courseId;
        this.years = years;
        this.courseStatus = courseStatus;
    }
    public String getCourseStatus(){
        return courseStatus;
    }
    public void setCourseStatus(String courseStatus){
        this.courseStatus = courseStatus;
    }
    public Course(){

    }
}
