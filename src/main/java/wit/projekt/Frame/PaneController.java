package wit.projekt.Frame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class PaneController extends JPanel implements ActionListener {
    protected JPanel fieldPanel = new JPanel();
    protected JPanel buttonPanel = new JPanel();
    protected JTable table;
    protected DefaultTableModel model;
    protected HashMap<String, JTextField> fields = new HashMap<>();
    protected int selectedRow = -1;

    protected PaneController(String name, String[] columnNames) {
        setLayout(new BorderLayout());
        model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return true; // Allow editing of all cells
            }
        };

        table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) { // Ensure selectedRow is valid
                    for (String key : fields.keySet()) {
                        int columnIndex = model.findColumn(key);
                        if (columnIndex >= 0) {
                            fields.get(key).setText(table.getValueAt(selectedRow, columnIndex).toString());
                        }
                    }
                }
            }
        });

        // Add double-click listener for editing cells
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = table.rowAtPoint(e.getPoint());
                    int col = table.columnAtPoint(e.getPoint());
                    if (row >= 0 && col >= 0) {
                        table.editCellAt(row, col);
                    }
                }
            }
        });

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(fieldPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    protected void addFieldToTable(ArrayList<Object> rowData) {
        model.addRow(rowData.toArray());
    }

    protected JTextField addField(String name) {
        JLabel label = new JLabel(name);
        JTextField textField = new JTextField(10);
        fieldPanel.add(label);
        fieldPanel.add(textField);
        fields.put(name, textField);
        return textField;
    }

    protected JButton createButton(String actionCommand, String name) {
        JButton button = new JButton(name);
        button.setActionCommand(actionCommand);
        button.addActionListener(this);
        return button;
    }

    protected void deleteRow(int row) {
        model.removeRow(row);
    }

    protected void editRow(ArrayList<Object> rowData, int row) {
        for (int i = 0; i < rowData.size(); i++) {
            model.setValueAt(rowData.get(i), row, i);
        }
    }

    protected abstract String getFieldNameFromID(String id);

    protected abstract String getButtonNamesFromID(String id);
}
