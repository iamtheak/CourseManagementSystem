package MiddleWare;
import Database.DatabaseConnection;
import Model.AuthModels.LoginModel;
import Model.UserModels.UserBaseModel;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class AuthRepo implements IAuth{
    DatabaseConnection _conn;
    public AuthRepo(DatabaseConnection conn){
        _conn = conn;
    }
    public String studentLogin(LoginModel loginModel){
        try{
            Connection conn = _conn.getConnection();
            assert conn != null;
            CallableStatement statement = conn.prepareCall("{call dbo.studentLogin(?,?)}");
            return getString(loginModel, conn, statement);
        }
        catch(Exception ex){
            System.out.println("Connection Failed"+ ex.getMessage());
        }
        return "Failure";
    }

    private String getString(LoginModel loginModel, Connection conn, CallableStatement statement) throws SQLException {
        statement.setString("@Email",loginModel.email);
        statement.setString("@Password",loginModel.getPassword());

        ResultSet rs = statement.executeQuery();
        String result = "Failure";
        if (rs.next()) {
            result = rs.getString(1);
        }
        _conn.closeConnection(conn);
        return result;
    }

    public String TeacherLogin(LoginModel loginModel){
        try{
            Connection conn = _conn.getConnection();
            assert conn != null;
            CallableStatement statement = conn.prepareCall("{call dbo.TeacherLogin(?,?)}");
            return getString(loginModel, conn, statement);
        }
        catch(Exception ex){
            System.out.println("Connection Failed"+ ex.getMessage());
        }
        return "Failure";
    }
    public String adminLogin(LoginModel loginModel){
        try{
            Connection conn = _conn.getConnection();
            assert conn != null;
            CallableStatement statement = conn.prepareCall("{call dbo.AdminLogin(?,?)}");
            return getString(loginModel, conn, statement);
        }
        catch(Exception ex){
            System.out.println("Connection Failed"+ ex.getMessage());
        }
        return "Failure";
    }
    public UserBaseModel getUserInfo(String email) {
        try{
            Connection con = _conn.getConnection();

            if(con == null){
                throw new Exception("Connection Failed");
            }

            CallableStatement statement = con.prepareCall("{call dbo.SelUserByEmail(?)}");
            statement.setString(1,email);
            ResultSet rs = statement.executeQuery();

            UserBaseModel user = new UserBaseModel();
            if(rs.next()){
                user.userId = rs.getInt("UserId");
                user.firstName = rs.getString("FirstName");
                user.middleName = rs.getString("MiddleName");
                user.lastName = rs.getString("LastName");
                user.email = rs.getString("Email");
                user.dateOfBirth = rs.getDate("DateOfBirth").toLocalDate();
                user.phoneNumber = rs.getString("PhoneNumber");
                user.setPassword(rs.getString("Password"));
            }

            return user;

        }
        catch (Exception ex){
            System.out.println("Connection Failed"+ ex.getMessage());
        }

        return null;
    }
    public String changePassword(String email, String oldPassword, String newPassword){
        try{
            Connection con = _conn.getConnection();

            if(con == null){
                throw new Exception("Connection Failed");
            }

            CallableStatement statement = con.prepareCall("{call dbo.UpdPassword(?,?,?)}");
            statement.setString(1,email);
            statement.setString(2,oldPassword);
            statement.setString(3,newPassword);
            ResultSet rs = statement.executeQuery();

            String result = "Failure";
            if(rs.next()){
                result = rs.getString("Result");
            }
            con.close();
            return result;

        }
        catch (Exception ex){
            System.out.println("Connection Failed"+ ex.getMessage());
        }

        return "Failure";
    }

    public String changeInformation(int userId,String email,String phoneNumber){
        try {
            Connection con = _conn.getConnection();

            if(con == null){
                throw new Exception("Connection Failed");
            }

            CallableStatement statement = con.prepareCall("{call dbo.UpdInfo(?,?,?)}");
            statement.setInt(1,userId);
            statement.setString(2,email);
            statement.setString(3,phoneNumber);
            ResultSet rs = statement.executeQuery();

            String result = "Failure";
            if(rs.next()){
                result = rs.getString("Result");
            }
            con.close();
            return result;
        }
        catch (Exception ex){
            System.out.println("Connection Failed"+ ex.getMessage());
        }

        return "Failure";
    }


}
