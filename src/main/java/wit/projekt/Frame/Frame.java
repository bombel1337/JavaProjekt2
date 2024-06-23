package wit.projekt.Frame;

import wit.projekt.Main;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

public class Frame extends JFrame {
    private JTabbedPane tabbedPane = new JTabbedPane();

    public Frame() {
        setTitle("Dziennik elektroniczny");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        add(tabbedPane, BorderLayout.CENTER);
    }

    public void addPanelToPane(String name, JPanel panel) {
        tabbedPane.addTab(name, panel);
    }
}