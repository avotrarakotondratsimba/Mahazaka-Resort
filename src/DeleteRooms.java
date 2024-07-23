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
public class DeleteRooms {
    static Connection conn = ConnectDB.testConnection();
    static PreparedStatement pstmt = null;
    public static void deletetRooms(ArrayList<String> listRoomId) {
        if (conn != null) {
            try {
                // Créer la requête SQL d'insertion
                String sql = "DELETE FROM chambre WHERE numchambr = ?";
                pstmt = conn.prepareStatement(sql);
                for (String id : listRoomId) {
                    pstmt.setString(1, id);
                    // Exécuter la requête de suppression
                    int rowsInserted = pstmt.executeUpdate();
                    if (rowsInserted == 0) {
                        System.out.println("An error is caused by suppression.");
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
