/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author AVOTRA
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class BookingList {
    static Connection conn = ConnectDB.testConnection();
    static SimpleDateFormat sdf = new SimpleDateFormat("dd / MM / yyyy");
    public static ArrayList<String[]> bookingList() {
        if (conn != null) {
            try {
                 LocalDate currentDate = LocalDate.now();
                Date curDate = Date.valueOf(currentDate);
                ArrayList<String[]> listData = new ArrayList<>();
                 // Préparer la requête SQL avec des paramètres
                String query = "SELECT datereserv, dateentree, nbrjour, nomclient, mail, datesortie, STRING_AGG(numchambr, ', ' ORDER BY numchambr) AS chambres FROM reserver WHERE dateentree >= ? AND idreserv NOT IN (SELECT idreserv FROM occuper) GROUP BY nomclient, mail, datereserv, dateentree, nbrjour, datesortie ORDER BY dateentree ASC";
                PreparedStatement pstmt = conn.prepareStatement(query);
                // Définir les paramètres
                pstmt.setDate(1, curDate);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    // Traitez les données des employés ici
                    String number = rs.getString("chambres");                   
                    Date tmpBookingDate = rs.getDate("datereserv");
                    String bookingDate = sdf.format(tmpBookingDate);                   
                    Date tmpEnterDate = rs.getDate("dateentree");
                    String enterDate = sdf.format(tmpEnterDate);                   
                    String nbDay = String.valueOf(rs.getInt("nbrjour"));
                    String name = rs.getString("nomclient");
                    String mail = rs.getString("mail");
                    Date tmpExitDate = rs.getDate("datesortie");
                    String exitDate = sdf.format(tmpExitDate);
                    String[] rowData = {name, mail, bookingDate, enterDate, exitDate, nbDay, number};                   
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
