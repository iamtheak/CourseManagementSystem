package Model.CourseModels;

public class CMSModule {
    public String moduleName;
    public int moduleId;
    public int credits;
    public String moduleCode;
    public int passPercent;

    public CMSModule(String moduleName, int moduleId, int credits, String moduleCode, int passPercent){
        this.moduleName = moduleName;
        this.moduleId = moduleId;
        this.credits = credits;
        this.moduleCode = moduleCode;
        this.passPercent = passPercent;
    }

    public CMSModule(){

    }
}
