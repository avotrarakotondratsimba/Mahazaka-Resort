/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author AVOTRA
 */
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.ResultSet;
public class OldBookingDestructor {
    static Connection conn = ConnectDB.testConnection();
    static PreparedStatement pstmt = null;
    public static void oldBookingDestructor() {
        if (conn != null) {
            try {
                LocalDate currentDate = LocalDate.now();
                Date curDate = Date.valueOf(currentDate);
                ArrayList<Integer> listIdReserv = new ArrayList<>();
                 // Préparer la requête SQL avec des paramètres
                String query = "SELECT idreserv FROM reserver WHERE dateentree < ?";
                pstmt = conn.prepareStatement(query);             
                // Définir les paramètres
                pstmt.setDate(1, curDate);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("idreserv");                 
                    listIdReserv.add(id);
                }
                rs.close();
                // Créer la requête SQL d'insertion
                String sql = "DELETE FROM occuper WHERE idreserv = ?";
                pstmt = conn.prepareStatement(sql);
                for (int id : listIdReserv) {
                    pstmt.setInt(1, id);
                    // Exécuter la requête de suppression
                    int rowsInserted = pstmt.executeUpdate();
                    if (rowsInserted == 0) {
                        System.out.println("An error is caused by suppression.");
                    }
                }
                
                String sql2 = "DELETE FROM reserver WHERE dateentree < ?";
                pstmt = conn.prepareStatement(sql2);
                pstmt.setDate(1, curDate);
                int rowsInserted = pstmt.executeUpdate();
                if (rowsInserted == 0) {
                    System.out.println("An error is caused by suppression.");
                }
                pstmt.close();
            } 
            catch (SQLException e) {
                    System.out.println("Error fetching data: " + e.getMessage());
            }
        } else {
            System.out.println("Connection error");
        }   
    }
}
