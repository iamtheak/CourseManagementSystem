package Model.UserModels;

public class StudentModel extends UserBaseModel {
    public int studentId;

    public int courseId;

    public int courseName;
    public int level;
    public StudentModel(){
        role = "Student";
    }
}
