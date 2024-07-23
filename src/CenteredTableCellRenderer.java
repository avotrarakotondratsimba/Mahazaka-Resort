import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class CenteredTableCellRenderer extends DefaultTableCellRenderer {
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                   boolean hasFocus, int row, int column) {
        // Appeler la méthode getTableCellRendererComponent de la classe parente
        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // Centrer le contenu dans la cellule
        if (cellComponent instanceof JLabel) {
            ((JLabel) cellComponent).setHorizontalAlignment(SwingConstants.CENTER);
        }

        return cellComponent;
    }
}
