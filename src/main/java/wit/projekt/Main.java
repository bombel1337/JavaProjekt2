package wit.projekt;

import wit.projekt.Database.Database;
import wit.projekt.Frame.Frame;
import wit.projekt.Group.GroupRegistry;
import wit.projekt.Student.Student;
import wit.projekt.Student.StudentGUI;
import wit.projekt.Group.GroupGUI;
import wit.projekt.Student.StudentRegistry;
import wit.projekt.Subject.SubjectGUI;
import wit.projekt.Subject.SubjectRegistry;

import javax.swing.*;
import java.util.HashMap;

public class Main {
    static Database database = new Database();

    static StudentRegistry studentRegistry = new StudentRegistry(database.get("Student"));
    static GroupRegistry groupRegistry = new GroupRegistry(database.get("Group"));
    static SubjectRegistry subjectRegistry = new SubjectRegistry(database.get("Subject"));

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new Frame();

                StudentGUI studentGUI = new StudentGUI("STUDENTS", studentRegistry, groupRegistry);
                GroupGUI groupGUI = new GroupGUI("GROUPS", groupRegistry, studentRegistry, studentGUI);
                SubjectGUI subjectGUI = new SubjectGUI("SUBJECTS", subjectRegistry);

            }
        });
    }

    public static void saveOnExit() {
        studentRegistry.saveDataToDB();
        groupRegistry.saveDataToDB();
        subjectRegistry.saveDataToDB();
    }
}
