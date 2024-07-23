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

public class DisplayVisitList {
    static Connection conn = ConnectDB.testConnection();
    static SimpleDateFormat sdf = new SimpleDateFormat("dd / MM / yyyy");
    
    static ArrayList<String[]> displayVisitList() {
         if (conn != null) {
            try {
                ArrayList<String[]> listData = new ArrayList<>();
                 // Préparer la requête SQL avec des paramètres
                String query = "SELECT dateentreesejour, nbrjour, nomclient, telephone, datesortiesejour, STRING_AGG(numchambr, ', ' ORDER BY numchambr) AS chambres FROM sejourner GROUP BY nomclient, telephone, dateentreesejour, nbrjour, datesortiesejour ORDER BY dateentreesejour DESC";
                PreparedStatement pstmt = conn.prepareStatement(query);
                // Définir les paramètres
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    // Traitez les données des employés ici
                    String number = rs.getString("chambres");                                 
                    Date tmpEnterDate = rs.getDate("dateentreesejour");
                    String enterDate = sdf.format(tmpEnterDate);                   
                    String nbDay = String.valueOf(rs.getInt("nbrjour"));
                    String name = rs.getString("nomclient");
                    String phone = rs.getString("telephone");
                    Date tmpExitDate = rs.getDate("datesortiesejour");
                    String exitDate = sdf.format(tmpExitDate);
                    String[] rowData = {name, phone, enterDate, exitDate, nbDay, number};                   
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
