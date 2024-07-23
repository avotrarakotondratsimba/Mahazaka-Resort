/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author AVOTRA
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class RoomsCount {
    static Connection conn = ConnectDB.testConnection();

    public static int roomsCount() {
        if (conn != null) {
            try {
                int r = 0;
                String query = "SELECT COUNT(*) AS count FROM chambre";
                PreparedStatement pstmt = conn.prepareStatement(query);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    // Traitez les données des employés ici
                    r = rs.getInt("count");                   
                }
                rs.close();
                pstmt.close();
                return r;
            } 
            catch (SQLException e) {
                    System.out.println("Error fetching data: " + e.getMessage());
                    return 0;
            }
        } else {
            System.out.println("Connection error");
            return 0;
        }   
    }
}
