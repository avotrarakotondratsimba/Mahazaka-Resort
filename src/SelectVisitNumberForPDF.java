/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author AVOTRA
 */
import java.sql.*;

public class SelectVisitNumberForPDF {
    static Connection conn = ConnectDB.testConnection();
    static PreparedStatement pstmt = null;
    
    static int selectVisitNumberForPDF(String type) throws SQLException {
        if (conn != null) {
            try {
                String query;
                int tmpNum = 0;
                query = switch (type) {
                    case "new" -> "SELECT idsejour FROM sejourner ORDER BY idsejour DESC limit 1";
                    default -> "SELECT idoccup FROM occuper ORDER BY idoccup DESC limit 1";
                };
                pstmt = conn.prepareStatement(query);  
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {   
                    String value;
                    if (type.equals("new")) value = "idsejour";
                    else value = "idoccup";                    
                    tmpNum = rs.getInt(value);                 
                }
                int nb = tmpNum + 1;
                rs.close();
                pstmt.close();
                return nb;
            }
            catch (SQLException e) {
                System.out.println("Error fetching data: " + e.getMessage());
                return 0;
            }
        } else {
            System.out.println("Connection error");
            return 0;
        }  
    }
}
