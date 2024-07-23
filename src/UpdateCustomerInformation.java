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

public class UpdateCustomerInformation {
    static Connection conn = ConnectDB.testConnection();
    static PreparedStatement pstmt = null;
    
    static void updateCustomerInformation(String[] data, String name, String mail) {
        if (conn != null) {
            try {
                ArrayList<Integer> idReserv = SelectIdReserv.selectIdReserv(data);
                String query = "UPDATE reserver SET nomclient = ?, mail = ? WHERE idReserv = ?";
                pstmt = pstmt = conn.prepareStatement(query);  
                pstmt.setString(1, name);
                pstmt.setString(2, mail);
                for (int id : idReserv) {
                    pstmt.setInt(3, id);
                    pstmt.executeUpdate();
                }
                System.out.println("Update done successfully");
            }
            catch (SQLException e) {
                System.out.println("Error updating booking: " + e.getMessage());
            }
        } else {
            System.out.println("Connection error");
        }  
    }
    
    static void updateCustomerInformation(String[] data, String name, String mail, String roomNb) {
        if (conn != null) {
            try {
                ArrayList<Integer> idReserv = SelectIdReserv.selectIdReserv(data);
                System.out.println(name + " " + mail + " " + roomNb + " " + idReserv.get(0));
                String query = "UPDATE reserver SET nomclient = ?, mail = ?, numchambr = ? WHERE idReserv = ?";
                pstmt = pstmt = conn.prepareStatement(query);  
                pstmt.setString(1, name);
                pstmt.setString(2, mail);
                pstmt.setString(3, roomNb);
                pstmt.setInt(4, idReserv.get(0));
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
