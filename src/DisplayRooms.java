/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author AVOTRA
 */
import java.sql.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class DisplayRooms {
    static Connection conn = ConnectDB.testConnection();
    static NumberFormat nbFormat = NumberFormat.getInstance(Locale.FRANCE);
    public static ArrayList<String[]> selectRooms() {
        if (conn != null) {
            try {                
                ArrayList<String[]> listData = new ArrayList<>();
                // Utilisez la connexion pour exécuter une requête SQL et récupérer les données des employés
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM chambre ORDER BY CAST(SUBSTRING(numchambr FROM 2) AS INTEGER) ASC");
                while (rs.next()) {
                    // Traitez les données des employés ici
                    String number = rs.getString("numchambr");
                    String design = rs.getString("design");
                    String type = rs.getString("type");
                    String price = String.valueOf(nbFormat.format(rs.getInt("prixnuite")));
                    String[] rowData = {number, design, type, price};
                    listData.add(rowData);
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
