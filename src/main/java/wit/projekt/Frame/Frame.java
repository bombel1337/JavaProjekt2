package wit.projekt.Frame;

import wit.projekt.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

public class Frame extends JFrame {
    private JTabbedPane tabbedPane = new JTabbedPane();

    public Frame() {
        setTitle("Dziennik elektroniczny");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Main.saveOnExit();

                System.exit(0);
            }
        });

        add(tabbedPane, BorderLayout.CENTER);
    }

    public void addPanelToPane(String name, JPanel panel) {
        tabbedPane.addTab(name, panel);
    }
}