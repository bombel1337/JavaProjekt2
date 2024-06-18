package wit.projekt.Frame;

import wit.projekt.Student.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public abstract class PaneController implements ActionListener {
    private JPanel panel = new JPanel();

    private JPanel fieldPanel = new JPanel();
    private JPanel tablePanel = new JPanel();
    private JPanel buttonPanel = new JPanel();

    protected JTable table = new JTable();
    protected int selectedRow = -1;
    protected HashMap<String, JTextField> fields = new HashMap<>();

    //TODO: for now hardcoded, should be dynamic based on class fields
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

        fields.put(cols[0], addField(getFieldNameFromID(cols[0])));
        fields.put(cols[1], addField(getFieldNameFromID(cols[1])));
        fields.put(cols[2], addField(getFieldNameFromID(cols[2])));
        JButton addButton = createButton("addButton", getButtonNamesFromID("addButton"));
        fieldPanel.add(addButton);

        panel.add(tablePanel);
        panel.add(fieldPanel);

        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

        Frame.addPanelToPane(name, panel);
    }

    private JScrollPane createTable(String[] cols) {
        String[] labels = {getFieldNameFromID(cols[0]), getFieldNameFromID(cols[1]), getFieldNameFromID(cols[2])};

        DefaultTableModel model = new DefaultTableModel(labels, 0);
        table.setModel(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        table.getSelectionModel().addListSelectionListener(e -> {
            selectedRow = table.getSelectedRow();
        });

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(300, 400));

        return scrollPane;
    }

    protected void addFieldToTable(ArrayList<String> fields) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addRow(fields.toArray());
    }

    private JButton createButton(String id, String name) {
        JButton button = new JButton(name);
        button.setActionCommand(id);
        button.addActionListener(this);

        return button;
    }

    private JTextField addField(String name) {
        JLabel label = new JLabel(name);
        fieldPanel.add(label);

        JTextField textField = new JTextField(20);
        fieldPanel.add(textField);
        //textField.setActionCommand(id);

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
