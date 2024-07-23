/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author AVOTRA
 */
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class AvailableRoomsCount {
    static Connection conn = ConnectDB.testConnection();

    public static int availableRoomsCount(Date startDate, Date endDate) {
        if (conn != null) {
            try {
                int r = 0;
                String query = "SELECT COUNT(*) AS count FROM chambre WHERE numchambr NOT IN (SELECT numchambr FROM reserver WHERE dateentree <= ? AND datesortie > ? OR dateentree < ? AND datesortie >= ? OR ? <= dateentree AND ? >= datesortie) AND numchambr NOT IN (SELECT numchambr FROM sejourner WHERE dateentreesejour <= ? AND datesortiesejour > ? OR dateentreesejour < ? AND datesortiesejour >= ? OR ? <= dateentreesejour AND ? >= datesortiesejour)";
                PreparedStatement pstmt = conn.prepareStatement(query);
                // Définir les paramètres
                pstmt.setDate(1, startDate);
                pstmt.setDate(2, startDate);
                pstmt.setDate(3, endDate);
                pstmt.setDate(4, endDate);
                pstmt.setDate(5, startDate);
                pstmt.setDate(6, endDate);
                pstmt.setDate(7, startDate);
                pstmt.setDate(8, startDate);
                pstmt.setDate(9, endDate);
                pstmt.setDate(10, endDate);
                pstmt.setDate(11, startDate);
                pstmt.setDate(12, endDate);
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
