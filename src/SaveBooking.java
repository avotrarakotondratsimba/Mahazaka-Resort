/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author AVOTRA
 */
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Date;

public class SaveBooking {
    static Connection conn = ConnectDB.testConnection();
    static PreparedStatement pstmt = null;
    
    public static void saveBooking(Date[] dateInfo, String[] roomsNumber, String[] customerInfo, int dayNumber) {
        if (conn != null) {
            try {
                // Créer la requête SQL d'insertion
                String sql = "INSERT INTO reserver  VALUES (?, ?, ?, ?, ?, ?, ?)";
                pstmt = conn.prepareStatement(sql);
                
                for (String room : roomsNumber) {
                    pstmt.setString(1, room);
                    pstmt.setDate(2, dateInfo[0]);
                    pstmt.setDate(3, dateInfo[1]);
                    pstmt.setInt(4, dayNumber);
                    pstmt.setString(5, customerInfo[0]);
                    pstmt.setString(6, customerInfo[1]);
                    pstmt.setDate(7, dateInfo[2]);
                    // Exécuter la requête d'insertion
                    int rowsInserted = pstmt.executeUpdate();

                    if (rowsInserted > 0) {
                        System.out.println("Une nouvelle ligne a été insérée avec succès.");
                    }
                }
                    
            } 
            catch (SQLException e) {
                    System.out.println("Error adding data: " + e.getMessage());
            }
        } else {
            System.out.println("Connection error");
        } 
    }
}
