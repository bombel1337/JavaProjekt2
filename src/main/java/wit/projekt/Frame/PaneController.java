package wit.projekt.Frame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class PaneController implements ActionListener {
    private JPanel panel = new JPanel();

    protected PaneController(String name, String[] cols) {
        JScrollPane scrollPane = createTable(cols);
        JButton button = createButton("add", "Dodaj ucznia");

        panel.add(scrollPane);
        panel.add(button);
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

        Frame.addPanelToPane(name, panel);
    }

    private JScrollPane createTable(String[] cols) {
        JTable table = new JTable();

        DefaultTableModel model = new DefaultTableModel(cols, 0);
        table.setModel(model);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(300, 400));

        return scrollPane;
    }

    private JButton createButton(String id, String name) {
        JButton button = new JButton(name);
        button.setActionCommand(id);
        button.addActionListener(this);

        return button;
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("add")) {

        }
    }


}
