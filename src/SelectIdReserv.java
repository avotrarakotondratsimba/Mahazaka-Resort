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

public class SelectIdReserv {
    static Connection conn = ConnectDB.testConnection();
    static PreparedStatement pstmt = null;
    
    static ArrayList<Integer> selectIdReserv(String[] data) {
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
