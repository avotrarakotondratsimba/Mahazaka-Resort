
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author AVOTRA
 */
public class RegisterTakenRooms {
    static Connection conn = ConnectDB.testConnection();
    static PreparedStatement pstmt = null;
    public static void registerTakenRooms(int nbRooms) {
        if (conn != null) {
            try {
                String selectSQL = "SELECT idreserv FROM reserver ORDER BY idreserv DESC limit ?";
                pstmt = conn.prepareStatement(selectSQL);
                ArrayList<Integer> roomsNb = new ArrayList<>();
                pstmt.setInt(1, nbRooms);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    roomsNb.add(rs.getInt("idreserv"));
                }
                
                // Créer la requête SQL d'insertion
//                String sql = "INSERT INTO occuper (idreserv) VALUES (?)";
//                pstmt = conn.prepareStatement(sql);
//                for (int room : roomsNb) {
//                    pstmt.setInt(1, room);
//                    int rowsInserted = pstmt.executeUpdate();
//                    if (rowsInserted > 0) {
//                        System.out.println("Une nouvelle ligne a été insérée avec succès.");
//                    }
//                }
            } 
            catch (SQLException e) {
                    System.out.println("Error adding data: " + e.getMessage());
            }
        } else {
            System.out.println("Connection error");
        } 
    }
}
