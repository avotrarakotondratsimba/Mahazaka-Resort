
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author AVOTRA
 */
public class UpdateRoomData {
    static Connection conn = ConnectDB.testConnection();
    static PreparedStatement pstmt = null;
    public static void updateRoom(String[] listRoomData) {
        if (conn != null) {
            try {
                // Créer la requête SQL d'insertion
                String sql = "UPDATE chambre SET design = ?, type = ?, prixnuite = ? where numchambr = ?";
                pstmt = conn.prepareStatement(sql);

                pstmt.setString(1, listRoomData[1]);
                pstmt.setString(2, listRoomData[2]);
                pstmt.setInt(3, Integer.parseInt(listRoomData[3]));
                pstmt.setString(4, listRoomData[0]);

                // Exécuter la requête d'insertion
                int rowsInserted = pstmt.executeUpdate();

                if (rowsInserted == 0) {
                    System.out.println("An error was occured during the update.");
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
