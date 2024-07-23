/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author AVOTRA
 */
import java.sql.*;

public class AddNewRoom {
    static Connection conn = ConnectDB.testConnection();
    static PreparedStatement pstmt = null;
    public static void insertRoom(String[] listRoomData) {
        if (conn != null) {
            try {
                // Créer la requête SQL d'insertion
                String sql = "INSERT INTO chambre VALUES (?, ?, ?, ?)";
                pstmt = conn.prepareStatement(sql);
                
                pstmt.setString(1, listRoomData[0]);
                pstmt.setString(2, listRoomData[1]);
                pstmt.setString(3, listRoomData[2]);
                pstmt.setInt(4, Integer.parseInt(listRoomData[3]));

                // Exécuter la requête d'insertion
                int rowsInserted = pstmt.executeUpdate();

                if (rowsInserted > 0) {
                    System.out.println("Une nouvelle ligne a été insérée avec succès.");
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
