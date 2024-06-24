package wit.projekt.Frame;

import wit.projekt.Main;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

public class Frame {
    private static JFrame frame;
    private static JTabbedPane tabbedPane = new JTabbedPane();

    public Frame() {
        frame = new JFrame("Dziennik elektroniczny");

        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setBounds(100, 100, 800, 600);

        //contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        //contentPane.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

        frame.setResizable(false);
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                Main.saveOnExit();

                System.exit(0);
            }
        });
    }

    public static void addPanelToPane(String name, JPanel panel) {
        tabbedPane.addTab(name, panel);
        frame.add(tabbedPane);
    }

}