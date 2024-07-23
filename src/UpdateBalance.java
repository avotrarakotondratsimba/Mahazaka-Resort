import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateBalance {
    static Connection conn = ConnectDB.testConnection();
    static PreparedStatement pstmt = null;
    
    static void updateBalance(int amount) {
        if (conn != null) {
            try {
                // Créer la requête SQL de mise à jour de la balance
                String sql = "UPDATE solde SET soldeactuel = soldeactuel + ? WHERE id = 1";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, amount);

                // Exécuter la requête de mise à jour
                int rowsUpdated = pstmt.executeUpdate();

                if (rowsUpdated > 0) {
                    System.out.println("Solde mis à jour avec succès.");
                } else {
                    System.out.println("La mise à jour de la solde a échoué.");
                }
            } catch (SQLException e) {
                System.out.println("Erreur lors de la mise à jour de la solde : " + e.getMessage());
            } finally {
                // Fermer la connexion et le PreparedStatement
                try {
                    if (pstmt != null) {
                        pstmt.close();
                    }
                } catch (SQLException e) {
                    System.out.println("Erreur lors de la fermeture du PreparedStatement : " + e.getMessage());
                }
            }
        } else {
            System.out.println("Erreur de connexion.");
        }
    }
}
