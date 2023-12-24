package Database;

import java.sql.Connection;

public final class DatabaseConnection {
    public final String connectionString = "jdbc:sqlserver://ADARSHA:1433;database=CMS;encrypt=true;trustServerCertificate=true";
    public final String username = "sa";
    public final String password = "saa";
    public final String driverString = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public final Connection getConnection(){
        try{
            Class.forName(driverString);
            return java.sql.DriverManager.getConnection(connectionString, username, password);
        }catch(Exception ex){
            System.out.println("Connection Failed"+ ex.getMessage());
        }
        return null;
    }
    public final void closeConnection(Connection con){
        try{
            con.close();
        }catch(Exception ex){
            System.out.println("Connection Failed"+ ex.getMessage());
        }
    }
}
