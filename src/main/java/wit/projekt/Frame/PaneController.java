package wit.projekt.Frame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class PaneController extends JPanel implements ActionListener {
    protected JPanel fieldPanel = new JPanel();
    protected JPanel buttonPanel = new JPanel();
    protected JTable table = new JTable();
    protected DefaultTableModel model;
    protected HashMap<String, JTextField> fields = new HashMap<>();
    protected int selectedRow = -1;

    protected PaneController(String name, String[] columnNames) {
        setLayout(new BorderLayout());
        model = new DefaultTableModel(columnNames, 0);
        table.setModel(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    for (String key : fields.keySet()) {
                        fields.get(key).setText(table.getValueAt(selectedRow, model.findColumn(key)).toString());
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
