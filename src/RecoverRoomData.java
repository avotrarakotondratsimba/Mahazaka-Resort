
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author AVOTRA
 */
public class RecoverRoomData {
    static Connection conn = ConnectDB.testConnection();
    
    public static String[] selectRoom(String id) {
        if (conn != null) {
            String[] listData;
            listData = new String[4];
            PreparedStatement stmt = null;
            ResultSet rs = null;
            
            try {
                // La requête SQL avec un placeholder ?
                String sql = "SELECT * FROM chambre WHERE numchambr = ?";

                // Créer un PreparedStatement
                stmt = conn.prepareStatement(sql);

                // Définir la valeur du placeholder avec le paramètre id
                stmt.setString(1, id);

                // Exécuter la requête
                rs = stmt.executeQuery();
                
                if (rs.next()) {
                    // Traitez les données des employés ici
                    listData[0] = rs.getString("numchambr");
                    listData[1] = rs.getString("design");
                    listData[2] = rs.getString("type");
                    listData[3] = String.valueOf(rs.getInt("prixnuite"));
                }
                rs.close();
                stmt.close();
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
