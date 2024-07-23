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

public class DeleteVisitList {
    static Connection conn = ConnectDB.testConnection();
    static PreparedStatement pstmt = null;
    
    static void deleteVisitList(ArrayList<String[]> visitInfo) {
        if (conn != null) {
            try {
                // Créer la requête SQL d'insertion
                String sql = "DELETE FROM sejourner WHERE numchambr = ? AND dateentreesejour = ? AND nbrjour = ? AND nomclient = ? AND telephone = ? AND datesortiesejour = ?";
                pstmt = conn.prepareStatement(sql);
                for (String[] data : visitInfo) {
                  //  DeleteTakedTable.deleteTakedTable(data);
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
                        int rowsInserted = pstmt.executeUpdate();
                        if (rowsInserted == 0) {
                            System.out.println("An error is caused by suppression There.");
                        } else {
                            System.out.println("Done");
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
