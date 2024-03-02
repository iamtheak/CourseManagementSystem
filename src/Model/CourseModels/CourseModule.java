package Model.CourseModels;

public class CourseModule extends CMSModule {

    public int courseId;
    public int semester;
    public int moduleNumber;
    public Boolean isOptional;
    public int year;
    public int optionalModuleNumber;

    public CourseModule(String moduleName, int moduleID, int credits, Boolean isOptional, String moduleCode,int passPercent,int semester, int courseId, int moduleNumber,int year, int optionalModuleNumber){
        super(moduleName, moduleID, credits, moduleCode, passPercent);
        this.isOptional = isOptional;
        this.semester = semester;
        this.courseId = courseId;
        this.moduleNumber = moduleNumber;
        this.year = year;
        this.optionalModuleNumber = optionalModuleNumber;
    }
    public CourseModule(){

    }
}
