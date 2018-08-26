package statisticclassroomfeedback;
import java.sql.*;
import javax.swing.*;

class MySqlConnect {
    private static final String USERNAME = "root";
    private static final String PASSWORD = "12345678";
    private static final String CONN_STRING = "jdbc:mysql://localhost/classroomFeedback";
    
    Connection conn = null;
    public static Connection ConnectDB(){
        try{
            Class.forName("com.mysql.jdbc.Driver");   
            Connection conn = DriverManager.getConnection(CONN_STRING,USERNAME,PASSWORD);
            return conn;
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }
}
