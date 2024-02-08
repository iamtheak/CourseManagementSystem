package MiddleWare;

import Model.UserModels.StudentModel;
import com.sun.jdi.connect.spi.Connection;

import java.sql.*;

public class StudentRepository extends StudentInterface{
    public void addStudent(){
        try{
            Connection con = (Connection) DriverManager.getConnection("jdbc:sqlserver://ADARSHA:1433;database=Library","sa","saa");


        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public void deleteStudent(){

    }

    public void updateStudent(){

    }

    public StudentModel getStudent(){
        return new StudentModel();
    }
}
