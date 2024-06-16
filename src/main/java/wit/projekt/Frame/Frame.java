package wit.projekt.Frame;

import wit.projekt.Student.StudentGUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Frame {
    private static JFrame frame;
    static JPanel contentPane = new JPanel();

    public Frame() {
        frame = new JFrame("Dziennik elektroniczny");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 800, 600);

        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

        frame.setResizable(false);
        frame.setVisible(true);
    }

    public static void addToFrame(JTabbedPane tabbedPane) {
        contentPane.add(tabbedPane);

        frame.setContentPane(contentPane);
    }

}
