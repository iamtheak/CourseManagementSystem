package Model.UserModels;

import Model.CourseModels.CMSModule;

import java.util.ArrayList;

public class TeacherModel extends UserBaseModel{
    public int teacherId;
    public ArrayList<CMSModule> modules;
    public TeacherModel(){
        role = "Teacher";
    }

}
