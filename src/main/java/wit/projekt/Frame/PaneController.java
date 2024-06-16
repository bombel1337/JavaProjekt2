package wit.projekt.Frame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class PaneController {
    List<JPanel> panels = new ArrayList<>();
    JTabbedPane tabbedPane = new JTabbedPane();

    protected PaneController(String name, String[] cols) {
        panels.add(new JPanel());
        int index = panels.size() - 1;
        this.tabbedPane.addTab(name, panels.get(index));

        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel(cols, 0);
        table.setModel(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(300, 400));

        panels.get(index).add(scrollPane);

        Frame.addToFrame(tabbedPane);
    }

    protected void addRow(String[] row) {
        DefaultTableModel model = (DefaultTableModel) ((JTable) ((JScrollPane) panels.get(0).getComponent(0)).getViewport().getView()).getModel();
        model.addRow(row);
    }
}
