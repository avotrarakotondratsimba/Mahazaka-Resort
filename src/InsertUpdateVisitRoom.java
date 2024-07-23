/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author AVOTRA
 */
import java.sql.Connection;
import java.sql.*;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.util.ArrayList;

public class InsertUpdateVisitRoom {
    static Connection conn = ConnectDB.testConnection();
    static PreparedStatement pstmt = null;
    
    static void insertUpdateVisitRoom(String[] data, ArrayList<String> rooms, String type) throws ParseException {
        if (conn != null) {
            try {
                // Créer la requête SQL d'insertion
                String sql;
                if (type.equals("séjourner")) sql = "INSERT INTO sejourner (numchambr, dateentreesejour, nbrjour, nomclient, telephone, datesortiesejour)  VALUES (?, ?, ?, ?, ?, ?)";
                else sql = "INSERT INTO reserver (numchambr, dateentree, nbrjour, nomclient, mail, datesortie, datereserv)  VALUES (?, ?, ?, ?, ?, ?, ?)";
                pstmt = conn.prepareStatement(sql);
                pstmt.setDate(2, DateConvert.convertStringToSqlDate(data[2]));
                pstmt.setDate(6, DateConvert.convertStringToSqlDate(data[3]));
                pstmt.setInt(3, Integer.parseInt(data[4]));
                pstmt.setString(5, data[1]);
                pstmt.setString(4, data[0]);
                if (!type.equals("séjourner")) {
                    Date bookingDate = SelectDateResrv.selectDateResrv(data);
                    pstmt.setDate(7, bookingDate);
                }
                for (String room : rooms) {
                    pstmt.setString(1, room);
                    // Exécuter la requête d'insertion
                    int rowsInserted = pstmt.executeUpdate();

                    if (rowsInserted > 0) {
                        System.out.println("Une nouvelle ligne a été insérée avec succès.");
                    }
                }
                if (!type.equals("séjourner")) {
                    sql = "SELECT idreserv FROM reserver WHERE numchambr = ? AND dateentree = ? AND nbrjour = ? AND nomclient = ? AND mail = ? AND datesortie = ? AND datereserv = ?";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setDate(2, DateConvert.convertStringToSqlDate(data[2]));
                    pstmt.setDate(6, DateConvert.convertStringToSqlDate(data[3]));
                    pstmt.setInt(3, Integer.parseInt(data[4]));
                    pstmt.setString(5, data[1]);
                    pstmt.setString(4, data[0]);
                    if (!type.equals("séjourner")) {
                        Date bookingDate = SelectDateResrv.selectDateResrv(data);
                        pstmt.setDate(7, bookingDate);
                    }
                    ArrayList<Integer> listIdReserv = new ArrayList<>();
                    for (String room : rooms) {
                        pstmt.setString(1, room);
                        // Exécuter la requête de suppression
                        ResultSet rs = pstmt.executeQuery();
                        if (rs.next()) {
                            listIdReserv.add(rs.getInt("idreserv"));                 
                        }
                        rs.close();
                    }
                    sql = "INSERT INTO occuper (idreserv)  VALUES (?)";
                    pstmt = conn.prepareStatement(sql);

                    for (int id : listIdReserv) {
                        pstmt.setInt(1, id);
                        // Exécuter la requête d'insertion
                        int rowsInserted = pstmt.executeUpdate();

                        if (rowsInserted > 0) {
                            System.out.println("Une nouvelle ligne a été insérée avec succès.");
                        }
                    }
                } 
            }
            catch (SQLException e) {
                    System.out.println("Error adding data: " + e.getMessage());
            }
        } else {
            System.out.println("Connection error");
        } 
    }
}
