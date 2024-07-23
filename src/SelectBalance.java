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

public class SelectBalance {
    static Connection conn = ConnectDB.testConnection();
    static NumberFormat nbFormat = NumberFormat.getInstance(Locale.FRANCE);
    static String selectBalance() {
        if (conn != null) {
            try {
                int result;
                String r = null;
                String query = "SELECT soldeactuel FROM solde WHERE id = ?";
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setInt(1, 1);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    // Traitez les données des employés ici
                    result = rs.getInt("soldeactuel");  
                    r = String.valueOf(nbFormat.format(result));
                }
                rs.close();
                pstmt.close();
                return r;
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
