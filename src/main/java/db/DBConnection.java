package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection instance;
    private Connection connection;

    private DBConnection() throws SQLException {
            String URL ="jdbc:mysql://localhost:3306/thogakade";
            String user ="root";
            String password = "root123";
        connection= DriverManager.getConnection(URL,user,password);
    }

    public static DBConnection getInstance() throws  SQLException{
        return instance==null? instance=new DBConnection():instance;
    }

    public  Connection getConnection(){
        return  connection;
    }

}
