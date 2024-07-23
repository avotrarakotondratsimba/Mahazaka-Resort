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

public class DeleteTakedTable {
    static Connection conn = ConnectDB.testConnection();
    static PreparedStatement pstmt = null;
    
    public static void deleteTakedTable(String[] data) {
        if (conn != null) {
            try {
                String query = "SELECT idreserv FROM reserver WHERE numchambr = ? AND datereserv = ? AND dateentree = ? AND nbrjour = ? AND nomclient = ? AND mail = ? AND datesortie = ?";
                pstmt = conn.prepareStatement(query);  
                ArrayList<Integer> listIdReserv = new ArrayList<>();
                try {
                        Date bookingDate = DateConvert.convertStringToSqlDate(data[2]);
                        pstmt.setDate(2, bookingDate);
                        Date enterDate = DateConvert.convertStringToSqlDate(data[3]);
                        pstmt.setDate(3, enterDate);
                        Date exitDate = DateConvert.convertStringToSqlDate(data[4]);
                        pstmt.setDate(7, exitDate);
                } catch (SQLException | ParseException e) {
                    System.out.println(e);
                }
                    pstmt.setInt(4, Integer.parseInt(data[5]));
                    pstmt.setString(5, data[0]);
                    pstmt.setString(6, data[1]);
                String[] roomsNb = data[6].split(", ");
                for (String room : roomsNb) {
                    pstmt.setString(1, room);
                    // Exécuter la requête de suppression
                    ResultSet rs = pstmt.executeQuery();
                    if (rs.next()) {
                        int id = rs.getInt("idreserv");                 
                        listIdReserv.add(id);
                    }
                    rs.close();
                }
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
