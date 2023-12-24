package Controller;
import MiddleWare.*;
import Views.Prompt;

public class test {
    StudentRepository _studentRepository = new StudentRepository();

    public void show(){
        Prompt prompt = new Prompt();
        prompt.pack();
        prompt.setVisible(true);
        System.exit(0);
    }

    public static void main(String[] args){
        test t = new test();
    }

}
