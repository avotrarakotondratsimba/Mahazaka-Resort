//import javax.swing.*;
//import javax.swing.table.*;
//import java.awt.*;
//
//public class CenteredHeaderRenderer extends DefaultTableCellRenderer {
//    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
//                                                   boolean hasFocus, int row, int column) {
//        // Appeler la méthode getTableCellRendererComponent de la classe parente
//        Component headerComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
//
//        // Centrer le texte de l'en-tête
//        if (headerComponent instanceof JLabel) {
//            ((JLabel) headerComponent).setHorizontalAlignment(SwingConstants.CENTER);
//        }
//
//        return headerComponent;
//    }
//}

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class CenteredHeaderRenderer extends DefaultTableCellRenderer {
    private final int headerHeight;
    private final Color backgroundColor;
    private final Color foregroundColor;


    public CenteredHeaderRenderer(int headerHeight, Color backgroundColor, Color foregroundColor) {
        this.headerHeight = headerHeight;
        this.backgroundColor = backgroundColor;
        this.foregroundColor = foregroundColor;
        setHorizontalAlignment(SwingConstants.CENTER); // Centre le texte
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                   boolean hasFocus, int row, int column) {
        Component headerComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        // Définir la hauteur du composant de l'en-tête
        Dimension headerSize = headerComponent.getPreferredSize();
        headerSize.height = headerHeight;
        headerComponent.setPreferredSize(headerSize);
        
         // Définir la couleur de fond
        headerComponent.setBackground(backgroundColor); // Couleur de fond (SteelBlue)

        // Définir la couleur du texte
        headerComponent.setForeground(foregroundColor); // Couleur du texte (blanc)
//
//        // Définir la police et la taille de la police
        headerComponent.setFont(new Font("Sergoe UI", Font.BOLD, 14)); // Police Arial, Gras, Taille 16

        return headerComponent;
    }
}

