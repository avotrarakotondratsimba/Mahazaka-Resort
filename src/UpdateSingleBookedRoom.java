/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author AVOTRA
 */
import java.sql.*;
import java.util.ArrayList;

public class UpdateSingleBookedRoom {
    static Connection conn = ConnectDB.testConnection();
    static PreparedStatement pstmt = null;
    
    static void updateSingleRoom(String[] data, String roomNb) throws SQLException {
        if (conn != null) {
            try {
                ArrayList<Integer> idReserv = SelectIdReserv.selectIdReserv(data);
                String query = "UPDATE reserver SET numchambr = ? WHERE idReserv = ?";
                pstmt = pstmt = conn.prepareStatement(query);  
                pstmt.setString(1, roomNb);
                pstmt.setInt(2, idReserv.get(0));
                pstmt.executeUpdate();
                System.out.println("Update done successfully");
            }
            catch (SQLException e) {
                System.out.println("Error updating booking: " + e.getMessage());
            }
        } else {
            System.out.println("Connection error");
        }  
    }
}
