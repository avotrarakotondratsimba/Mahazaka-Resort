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

public class SelectDateResrv {
    static Connection conn = ConnectDB.testConnection();
    static PreparedStatement pstmt = null;
    
    static Date selectDateResrv(String[] data) {
        if (conn != null) {
            try {
                String query = "SELECT datereserv FROM reserver WHERE numchambr = ? AND dateentree = ? AND nbrjour = ? AND nomclient = ? AND mail = ? AND datesortie = ?";
                pstmt = conn.prepareStatement(query);  
                Date result = null;
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
                        result = rs.getDate("datereserv");                 
                    }
                    rs.close();
                }
                pstmt.close();
                return result;
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
