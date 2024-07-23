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

public class DeleteBookingVisit {
    static Connection conn = ConnectDB.testConnection();
    static PreparedStatement pstmt = null;
    
    static void deleteBookingVisit(ArrayList<String> bookingInfo) {
        if (conn != null) {
            try {                        
                String[] info = {bookingInfo.get(0), bookingInfo.get(1), bookingInfo.get(2), bookingInfo.get(3), bookingInfo.get(4), bookingInfo.get(5), bookingInfo.get(6)};
                ArrayList<Integer> idReserv = SelectIdReserv.selectIdReserv(info);
                // Créer la requête SQL d'insertion
                String sql = "DELETE FROM reserver WHERE idreserv = ?";
                pstmt = conn.prepareStatement(sql);
                for (int id : idReserv) {
                    pstmt.setInt(1, id);
                    // Exécuter la requête de suppression
                    int rowsInserted = pstmt.executeUpdate();
                    if (rowsInserted == 0) {
                        System.out.println("An error is caused by suppression.");
                    }
                }
                sql = "DELETE FROM occuper WHERE idreserv = ?";
                pstmt = conn.prepareStatement(sql);
                for (int id : idReserv) {
                    pstmt.setInt(1, id);
                    // Exécuter la requête de suppression
                    int rowsInserted = pstmt.executeUpdate();
                    if (rowsInserted == 0) {
                        System.out.println("An error is caused by suppression.");
                    }
                }
                pstmt.close();
            } 
            catch (SQLException e) {
                System.out.println("Error fetching data: " + e.getMessage());
            }
        }
    }
}
