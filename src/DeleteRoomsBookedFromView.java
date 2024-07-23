/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author AVOTRA
 */
import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;

public class DeleteRoomsBookedFromView {
    static Connection conn = ConnectDB.testConnection();
    static PreparedStatement pstmt = null;
    
    static void deleteRoomsBookedFromView(String[] data) throws ParseException {
        if (conn != null) {
            try {
                // 1. Récupérer les identifiants des réservations à exclure
                ArrayList<Integer> listIdReserv = SelectIdReserv.selectIdReserv(data);
                
                // 2. Créer une vue temporaire excluant les réservations spécifiées
                StringBuilder exclusionIds = new StringBuilder();
                for (int id : listIdReserv) {
                    exclusionIds.append(id).append(",");
                }
                exclusionIds.deleteCharAt(exclusionIds.length() - 1); // Supprimer la virgule finale
                
                String createTempViewSQL = "CREATE OR REPLACE VIEW tmp_vue_reserve AS " +
                                           "SELECT * FROM reserver WHERE idreserv NOT IN (" + exclusionIds + ")";
                pstmt = conn.prepareStatement(createTempViewSQL);
                pstmt.executeUpdate();
                System.out.println("tmp_vue_reserve created successfully");
                
                // 3. Supprimer l'ancienne vue vue_reserve
                String dropOldViewSQL = "DROP VIEW IF EXISTS vue_reserve";
                pstmt = conn.prepareStatement(dropOldViewSQL);
                pstmt.executeUpdate();
                System.out.println("Vue vue_reserve deleted successfully.");
                
                // 4. Renommer la vue temporaire tmp_vue_reserve en vue_reserve
                String renameTempViewSQL = "ALTER VIEW tmp_vue_reserve RENAME TO vue_reserve";
                pstmt = conn.prepareStatement(renameTempViewSQL);
                pstmt.executeUpdate();
                System.out.println("Vue temporaire renommée en vue_reserve avec succès.");
                
                
            } 
            catch (SQLException e) {
                    System.out.println("Error deleting data from view: " + e.getMessage());
            }
        } else {
            System.out.println("Connection error");
        } 
    }
}
