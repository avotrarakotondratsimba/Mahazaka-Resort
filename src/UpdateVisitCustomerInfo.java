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

public class UpdateVisitCustomerInfo {
    static Connection conn = ConnectDB.testConnection();
    static PreparedStatement pstmt = null;
    
    static void updateVisitCustomerInfo(String[] data, String name, String contact, String type) throws ParseException {
        if (conn != null) {
            try {
                String query;
                if (type.equals("séjourner")) query = "UPDATE sejourner SET nomclient = ?, telephone = ? WHERE numchambr = ? AND dateentreesejour = ? AND datesortiesejour = ? AND nbrjour = ? AND telephone = ? AND nomclient = ?";
                else query = "UPDATE reserver SET nomclient = ?, mail = ? WHERE numchambr = ? AND dateentree = ? AND datesortie = ? AND nbrjour = ? AND mail = ? AND nomclient = ?";
                pstmt = pstmt = conn.prepareStatement(query);  
                pstmt.setString(1, name);
                pstmt.setString(2, contact);
                pstmt.setDate(4, DateConvert.convertStringToSqlDate(data[2]));
                pstmt.setDate(5, DateConvert.convertStringToSqlDate(data[3]));
                pstmt.setInt(6, Integer.parseInt(data[4]));
                pstmt.setString(7, data[1]);
                pstmt.setString(8, data[0]);
                for (String room : data[5].split(", ")) {
                    pstmt.setString(3, room);
                    pstmt.executeUpdate();
                }
                System.out.println("Update done successfully");
            }
            catch (SQLException e) {
                System.out.println("Error updating booking: " + e.getMessage());
            }
        } else {
            System.out.println("Connection error");
        }  
    }
}
