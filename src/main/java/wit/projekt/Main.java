package wit.projekt;

import wit.projekt.Frame.Frame;
import wit.projekt.Student.StudentGUI;
import wit.projekt.Group.GroupGUI;
import wit.projekt.Subject.SubjectGUI;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Frame frame = new Frame();

                StudentGUI studentGUI = new StudentGUI("STUDENTS");
                GroupGUI groupGUI = new GroupGUI("GROUPS", studentGUI);
                SubjectGUI subjectGUI = new SubjectGUI("SUBJECTS", studentGUI);  // Pass studentGUI here

                frame.addPanelToPane("Students", studentGUI.getPanel());
                frame.addPanelToPane("Groups", groupGUI.getPanel());
                frame.addPanelToPane("Subjects", subjectGUI.getPanel());

                frame.setVisible(true);
            }
        });
    }
}
