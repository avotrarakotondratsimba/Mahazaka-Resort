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

public class SaveVisit {
    static Connection conn = ConnectDB.testConnection();
    static PreparedStatement pstmt = null;
    
    static void saveVisit(String[] customerInfo, Date[] visitDate, String[] rooms, int duration) {
        if (conn != null) {
            try {
                // Créer la requête SQL d'insertion
                String sql = "INSERT INTO sejourner (numchambr, dateentreesejour, nbrjour, nomclient, telephone, datesortiesejour)  VALUES (?, ?, ?, ?, ?, ?)";
                pstmt = conn.prepareStatement(sql);
                pstmt.setDate(2, visitDate[0]);
                pstmt.setInt(3, duration);
                pstmt.setString(4, customerInfo[0]);
                pstmt.setString(5, customerInfo[1]);
                pstmt.setDate(6, visitDate[1]);
                for (String room : rooms) {
                    pstmt.setString(1, room);
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
