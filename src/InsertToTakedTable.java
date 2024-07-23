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
import java.util.ArrayList;

public class InsertToTakedTable {
    static Connection conn = ConnectDB.testConnection();
    static PreparedStatement pstmt = null;
    
    static void insertToTakedTable(ArrayList<String> bookingInfo) {
        if (conn != null) {
            try {
                String[] bInfo = {bookingInfo.get(0), bookingInfo.get(1), bookingInfo.get(2), bookingInfo.get(3), bookingInfo.get(4), bookingInfo.get(5), bookingInfo.get(6)};
                ArrayList<Integer> idReserv = SelectIdReserv.selectIdReserv(bInfo);
                // Créer la requête SQL d'insertion
                String sql = "INSERT INTO occuper (idreserv)  VALUES (?)";
                pstmt = conn.prepareStatement(sql);
                
                for (int id : idReserv) {
                    pstmt.setInt(1, id);
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
