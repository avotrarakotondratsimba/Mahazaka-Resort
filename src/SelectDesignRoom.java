/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author AVOTRA
 */
import java.sql.*;
import java.util.ArrayList;

public class SelectDesignRoom {
    static Connection conn = ConnectDB.testConnection();
    static PreparedStatement pstmt = null;
    
    static ArrayList<String> selectDesignRooms(String[] roomsNb) {
        if (conn != null) {
            try {
                ArrayList<String> designList = new ArrayList<>();
                String query = "SELECT design FROM chambre WHERE numchambr = ?";
                pstmt = conn.prepareStatement(query);  
                for (String room : roomsNb) {
                    pstmt.setString(1, room);
                    ResultSet rs = pstmt.executeQuery();
                    if (rs.next()) {                      
                        designList.add(rs.getString("design"));                 
                    }
                    rs.close();
                }               
               
                pstmt.close();
                return designList;
            }
            catch (SQLException e) {
                System.out.println("Error fetching data: " + e.getMessage());
                return null;
            }
        } else {
            System.out.println("Connection error");
            return null;
        } 
    }
}
