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

public class DeleteBookingRooms {
    static Connection conn = ConnectDB.testConnection();
    static PreparedStatement pstmt = null;
    
    public static void deleteBookingRooms(ArrayList<String[]> listRoomsData) {
        if (conn != null) {
            try {
                // Créer la requête SQL d'insertion
                String sql = "DELETE FROM reserver WHERE numchambr = ? AND datereserv = ? AND dateentree = ? AND nbrjour = ? AND nomclient = ? AND mail = ? AND datesortie = ?";
                pstmt = conn.prepareStatement(sql);
                for (String[] data : listRoomsData) {
                  //  DeleteTakedTable.deleteTakedTable(data);
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
                        int rowsInserted = pstmt.executeUpdate();
                        if (rowsInserted == 0) {
                            System.out.println("An error is caused by suppression There.");
                        }
                    }
                    
                }
            } 
            catch (SQLException e) {
                    System.out.println("Error deleting data: " + e.getMessage());
            }
        } else {
            System.out.println("Connection error");
        } 
    }
}
