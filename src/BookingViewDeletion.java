/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author AVOTRA
 */
import java.sql.*;

public class BookingViewDeletion {
    static Connection conn = ConnectDB.testConnection();

    static void deleteBookingView() {
        if (conn != null) {
            try {
                // Requête SQL pour supprimer la vue
                String dropViewQuery = "DROP VIEW IF EXISTS vue_reserve";
                
                // Suppression de la vue
                Statement stmt = conn.createStatement();
                stmt.executeUpdate(dropViewQuery);
                
                System.out.println("vue_reserve deleted successfully");
            } 
            catch (SQLException e) {
                System.out.println("Error deleting view: " + e.getMessage());
            }
        } else {
            System.out.println("Connection error");
        }   
    }
}
