
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.Date;
import java.text.NumberFormat;
import java.util.Locale;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author AVOTRA
 */
public class AvailableBookingRooms {
    static Connection conn = ConnectDB.testConnection();
    static NumberFormat nbFormat = NumberFormat.getInstance(Locale.FRANCE);
    public static ArrayList<String[]> selectRooms(Date startDate, Date endDate) {
        if (conn != null) {
            try {
                
                ArrayList<String[]> listData = new ArrayList<>();
                 // Préparer la requête SQL avec des paramètres
                String query = "SELECT * FROM chambre WHERE numchambr NOT IN (SELECT numchambr FROM reserver WHERE dateentree <= ? AND datesortie > ? OR dateentree < ? AND datesortie >= ? OR ? <= dateentree AND ? >= datesortie) AND numchambr NOT IN (SELECT numchambr FROM sejourner WHERE dateentreesejour <= ? AND datesortiesejour > ? OR dateentreesejour < ? AND datesortiesejour >= ? OR ? <= dateentreesejour AND ? >= datesortiesejour) ORDER BY CAST(SUBSTRING(numchambr FROM 2) AS INTEGER) ASC;";
                PreparedStatement pstmt = conn.prepareStatement(query);
                // Définir les paramètres
                pstmt.setDate(1, startDate);
                pstmt.setDate(2, startDate);
                pstmt.setDate(3, endDate);
                pstmt.setDate(4, endDate);
                pstmt.setDate(5, startDate);
                pstmt.setDate(6, endDate);
                pstmt.setDate(7, startDate);
                pstmt.setDate(8, startDate);
                pstmt.setDate(9, endDate);
                pstmt.setDate(10, endDate);
                pstmt.setDate(11, startDate);
                pstmt.setDate(12, endDate);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    // Traitez les données des employés ici
                    String number = rs.getString("numchambr");
                    String design = rs.getString("design");
                    String type = rs.getString("type");
                    String price = String.valueOf(nbFormat.format(rs.getInt("prixnuite")));
                    String[] rowData = {number, design, type, price};
                    listData.add(rowData);
                }
                rs.close();
                pstmt.close();
                return listData;
            } 
            catch (SQLException e) {
                    System.out.println("Error fetching data: " + e.getMessage());
                    return null;
            }
        } else {
            System.out.println("Connection error");
            return null;
        }       
    }
}
