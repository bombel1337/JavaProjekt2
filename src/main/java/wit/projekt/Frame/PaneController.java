package wit.projekt.Frame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public abstract class PaneController implements ActionListener {
    private JPanel panel = new JPanel();

    private JPanel fieldPanel = new JPanel();
    protected JPanel buttonPanel = new JPanel();
    private JPanel tablePanel = new JPanel();

    protected JTable table = new JTable();
    protected int selectedRow = -1;
    protected HashMap<String, JTextField> fields = new HashMap<>();

    protected PaneController(String name, String[] cols) {
        tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = createTable(cols);
        tablePanel.add(scrollPane);

        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        JButton deleteButton = createButton("deleteButton", getButtonNamesFromID("deleteButton"));
        buttonPanel.add(deleteButton);

        JButton editButton = createButton("editButton", getButtonNamesFromID("editButton"));
        buttonPanel.add(editButton);

        tablePanel.add(buttonPanel);

        fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.Y_AXIS));

        JButton searchButton = createButton("searchButton", getButtonNamesFromID("searchButton"));
        fieldPanel.add(searchButton);

        for (String col : cols) {
            fields.put(col, addField(getFieldNameFromID(col)));
        }

        JButton addButton = createButton("addButton", getButtonNamesFromID("addButton"));
        fieldPanel.add(addButton);

        panel.add(tablePanel);
        panel.add(fieldPanel);

        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

        Frame.addPanelToPane(name, panel);
    }

    private JScrollPane createTable(String[] cols) {
        String[] labels = new String[cols.length];
        for (int i = 0; i < cols.length; i++) {
            labels[i] = getFieldNameFromID(cols[i]);
        }

        DefaultTableModel model = new DefaultTableModel(labels, 0);
        table.setModel(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        table.getSelectionModel().addListSelectionListener(e -> {
            selectedRow = table.getSelectedRow();
        });

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(400, 400));

        return scrollPane;
    }

    protected void addFieldToTable(ArrayList<String> fields) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addRow(fields.toArray());
    }

    protected JButton createButton(String id, String name) {
        JButton button = new JButton(name);
        button.setActionCommand(id);
        button.addActionListener(this);
        return button;
    }

    protected JTextField addField(String name) {
        JLabel label = new JLabel(name);
        fieldPanel.add(label);

        JTextField textField = new JTextField(20);
        fieldPanel.add(textField);
        return textField;
    }

    protected void deleteRow(int row) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.removeRow(row);
    }

    protected void editRow(ArrayList<String> fields, int row) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.removeRow(row);
        model.insertRow(row, fields.toArray());
    }

    protected abstract String getFieldNameFromID(String id);

    protected abstract String getButtonNamesFromID(String id);

    public abstract void actionPerformed(ActionEvent e);
}
