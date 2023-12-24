package MiddleWare;

import Model.UserModels.StudentModel;
import com.sun.jdi.connect.spi.Connection;

import java.sql.DriverManager;

public abstract class StudentInterface {
    public void addStudent(){
    }

    public void deleteStudent(){

    }

    public void updateStudent() {
    }


    public StudentModel getStudent(){
        return null;
    }

}
