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

public class DeleteArrivedVisitList {
    static Connection conn = ConnectDB.testConnection();
    static PreparedStatement pstmt = null;
    
    static void deleteArrivedVisitList(ArrayList<String[]> visitInfo) {
        if (conn != null) {
            try {
                for (String[] info : visitInfo) {
                    ArrayList<Integer> idReserv = SelectIdReservToDelete.selectIdReservToDelete(info);
                    for (int id : idReserv) {
                        // Supprimer la réservation
//                        String sql = "DELETE FROM reserver WHERE idreserv = ?";
//                        pstmt = conn.prepareStatement(sql);
//                        pstmt.setInt(1, id);
//                        int rowsInserted = pstmt.executeUpdate();
//                        if (rowsInserted == 0) {
//                            System.out.println("An error is caused by suppression (DeleteArrivedVisitList_reserver).");
//                        }
                        
                        // Supprimer l'occupation
                        String sql = "DELETE FROM occuper WHERE idreserv = ?";
                        pstmt = conn.prepareStatement(sql);
                        pstmt.setInt(1, id);
                        int rowsInserted = pstmt.executeUpdate();
                        if (rowsInserted == 0) {
                            System.out.println("An error is caused by suppression (DeleteArrivedVisitList_occuper).");
                        }
                    }
                }
            } 
            catch (SQLException e) {
                    System.out.println("Error deleting data: " + e.getMessage());
            }
        } else {
            System.out.println("Connection error");
        } 
    }
}
