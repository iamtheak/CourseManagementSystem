package Model.UserModels;

public class StudentModel extends UserBaseModel {
    public int studentId;
    public int courseId;
    public String courseName;
    public int level;
    public int year;
    public StudentModel(){
        role = "Student";
    }
}
