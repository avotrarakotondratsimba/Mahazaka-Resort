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
import java.util.Locale;

public class TemporaryBookedRoom {
    static Connection conn = ConnectDB.testConnection();
    static NumberFormat nbFormat = NumberFormat.getInstance(Locale.FRANCE);
    @SuppressWarnings("empty-statement")
    public static String[] temporaryBookedRoom(String roomNb) {
        if (conn != null) {
            try {
                // Utilisez la connexion pour exécuter une requête SQL et récupérer les données des employés
                String sql = "SELECT * FROM chambre WHERE numchambr = ?";
                String[] rowData = new String[4];
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, roomNb);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    // Traitez les données des employés ici
                    String number = rs.getString("numchambr");
                    String design = rs.getString("design");
                    String type = rs.getString("type");
                    String price = String.valueOf(nbFormat.format(rs.getInt("prixnuite")));
                    rowData[0] = number;     
                    rowData[1] = design;     
                    rowData[2] = type;     
                    rowData[3] = price;     
                }
                rs.close();
                pstmt.close();
                return rowData;
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
