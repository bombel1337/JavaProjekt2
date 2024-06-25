package wit.projekt;

import wit.projekt.Database.Database;
import wit.projekt.Frame.Frame;
import wit.projekt.Group.GroupGUI;
import wit.projekt.Group.GroupRegistry;
import wit.projekt.Student.StudentGUI;
import wit.projekt.Student.StudentRegistry;
import wit.projekt.Subject.SubjectGUI;
import wit.projekt.Subject.SubjectRegistry;

import javax.swing.*;

public class Main {
    static Database database = new Database();

    static GroupRegistry groupRegistry = new GroupRegistry(database.get("groups"));
    static StudentRegistry studentRegistry = new StudentRegistry(database.get("students"), groupRegistry);
    static SubjectRegistry subjectRegistry = new SubjectRegistry(database.get("subjects"));

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Frame frame = new Frame();

            StudentGUI studentGUI = new StudentGUI("STUDENTS", studentRegistry, groupRegistry, subjectRegistry);
            GroupGUI groupGUI = new GroupGUI("GROUPS", groupRegistry, studentRegistry, studentGUI);
            SubjectGUI subjectGUI = new SubjectGUI("SUBJECTS", subjectRegistry, studentRegistry, studentGUI);

            frame.addPanelToPane("Students", studentGUI.getPanel());
            frame.addPanelToPane("Groups", groupGUI.getPanel());
            frame.addPanelToPane("Subjects", subjectGUI.getPanel());

            frame.setVisible(true);
        });
    }

    public static void saveOnExit() {
        studentRegistry.saveDataToDB();
        groupRegistry.saveDataToDB();
        subjectRegistry.saveDataToDB();
        database.saveFile();

    }
}
