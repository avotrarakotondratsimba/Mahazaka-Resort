import java.awt.Component;
import javax.swing.*;
import javax.swing.table.*;

public class SingleCheckboxEditor extends DefaultCellEditor {
    private JCheckBox checkBox;
    private Integer selectedRow;
    private JTable table;

    public SingleCheckboxEditor(JCheckBox checkBox) {
        super(checkBox);
        this.checkBox = checkBox;
        checkBox.setHorizontalAlignment(SwingConstants.CENTER);
        checkBox.addActionListener(e -> {
            if (table != null && selectedRow != null) {
                for (int row = 0; row < table.getRowCount(); row++) {
                    if (row != selectedRow) {
                        table.setValueAt(Boolean.FALSE, row, table.getSelectedColumn());
                    }
                }
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected,
                                                 int row, int column) {
        this.table = table;
        this.selectedRow = row;
        return super.getTableCellEditorComponent(table, value, isSelected, row, column);
    }
}
