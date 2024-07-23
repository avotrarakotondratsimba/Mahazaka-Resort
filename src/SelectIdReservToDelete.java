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

public class SelectIdReservToDelete {
    static Connection conn = ConnectDB.testConnection();
    static PreparedStatement pstmt = null;
    
    static ArrayList<Integer> selectIdReservToDelete(String[] data) {
        if (conn != null) {
            try {
                String query = "SELECT idreserv FROM reserver WHERE numchambr = ? AND dateentree = ? AND nbrjour = ? AND nomclient = ? AND mail = ? AND datesortie = ?";
                pstmt = conn.prepareStatement(query);  
                ArrayList<Integer> listIdReserv = new ArrayList<>();
                try {
                    Date enterDate = DateConvert.convertStringToSqlDate(data[2]);
                    pstmt.setDate(2, enterDate);
                    Date exitDate = DateConvert.convertStringToSqlDate(data[3]);
                    pstmt.setDate(6, exitDate);
                } catch (SQLException | ParseException e) {
                    System.out.println(e);
                }
                 pstmt.setInt(3, Integer.parseInt(data[4]));
                pstmt.setString(4, data[0]);
                pstmt.setString(5, data[1]);
                String[] roomsNb = data[5].split(", ");
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
                pstmt.close();
                return listIdReserv;
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
