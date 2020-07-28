package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FConnection {
    private static Connection instance=null;
    protected FConnection(){}

    public static Connection getInstance(){
        if(instance==null){
            try {
                System.out.println("proba");
                Class.forName("com.mysql.cj.jdbc.Driver");
                instance=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","aca");
                System.out.println("uspelo");
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }
}
