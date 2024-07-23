/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author AVOTRA
 */
import java.sql.*;

public class SelectAmount {
    static Connection conn = ConnectDB.testConnection();
    static String selectAmount(String roomNb) {
        if (conn != null) {
            try {
                String result = null;
                String query = "SELECT prixnuite FROm chambre WHERE numchambr = ?";
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setString(1, roomNb);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    // Traitez les données des employés ici
                    result = rs.getString("prixnuite");                   
                }
                rs.close();
                pstmt.close();
                return result;
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
