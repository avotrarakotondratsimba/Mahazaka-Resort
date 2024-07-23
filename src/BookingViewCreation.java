/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author AVOTRA
 */
import java.sql.*;

public class BookingViewCreation {
    static Connection conn = ConnectDB.testConnection();
    
    static void bookingViewCreation() {
        if (conn != null) {
            try {
                // Requête SQL pour créer la vue
                String createViewQuery = "CREATE VIEW vue_reserve AS "
                                      + "SELECT * FROM reserver";
                
                // Création de la vue
                Statement stmt = conn.createStatement();
                stmt.executeUpdate(createViewQuery);
                
                System.out.println("vue_reserve created successfully");
            } 
            catch (SQLException e) {
                    System.out.println("Error creating view: " + e.getMessage());
            }
        } else {
            System.out.println("Connection error");
        }   
    }
}
