/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author AVOTRA
 */
import java.sql.*;

public class ConnectDB {
    public static String url = "jdbc:postgresql://localhost:5432/hotel";
    public static String user = "postgres";
    public static String password = "mydb";
    public static Connection testConnection() {
        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected successfully!");
            
            return conn;
        }
        catch (SQLException e) {
            System.out.println("Connection error: " + e.getMessage());
            return null;
        }
    }
}