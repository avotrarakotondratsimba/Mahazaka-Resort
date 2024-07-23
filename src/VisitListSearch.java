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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;

public class VisitListSearch {
    static Connection conn = ConnectDB.testConnection();
    static SimpleDateFormat sdf = new SimpleDateFormat("dd / MM / yyyy");
    
    static ArrayList<String[]> visitListSearch(String keyWord, String filter) {
        if (conn != null) {
            try {
                
                ArrayList<String[]> listData = new ArrayList<>();
                String keyW = "%" + keyWord + "%";
                 // Préparer la requête SQL avec des paramètres
                String query, enter, exit, contact;
                switch (filter) {
                   case "Séjournants" -> {
                       query = "SELECT dateentreesejour, nbrjour, nomclient, telephone, datesortiesejour, STRING_AGG(numchambr, ', ' ORDER BY numchambr) AS chambres FROM sejourner WHERE LOWER(nomclient) LIKE LOWER(?) GROUP BY nomclient, telephone, dateentreesejour, nbrjour, datesortiesejour ORDER BY dateentreesejour DESC";
                       enter = "dateentreesejour";
                       exit = "datesortiesejour";
                       contact = "telephone";
                    }
                   default -> {
                       query = "SELECT r.dateentree AS dateentree, r.nbrjour AS nbrjour, r.nomclient AS nomclient, r.mail AS mail, r.datesortie AS datesortie, STRING_AGG(r.numchambr, ', ' ORDER BY r.numchambr) AS chambres FROM reserver r JOIN occuper o ON r.idreserv = o.idreserv WHERE LOWER(r.nomclient) LIKE LOWER(?) GROUP BY r.nomclient, r.mail, r.dateentree, r.nbrjour, r.datesortie ORDER BY r.dateentree DESC";
                       enter = "dateentree";
                       exit = "datesortie";
                       contact = "mail";
                    }
                }
                PreparedStatement pstmt = conn.prepareStatement(query);
                // Définir les paramètres
                pstmt.setString(1, keyW);
                
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    // Traitez les données des employés ici
                    String number = rs.getString("chambres");                                 
                    Date tmpEnterDate = rs.getDate(enter);
                    String enterDate = sdf.format(tmpEnterDate);                   
                    String nbDay = String.valueOf(rs.getInt("nbrjour"));
                    String name = rs.getString("nomclient");
                    String mail = rs.getString(contact);
                    Date tmpExitDate = rs.getDate(exit);
                    String exitDate = sdf.format(tmpExitDate);
                    String[] rowData = {name, mail, enterDate, exitDate, nbDay, number};                   
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
