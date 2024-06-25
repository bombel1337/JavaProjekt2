package wit.projekt.Student;

import wit.projekt.Frame.PaneController;
import wit.projekt.Group.Group;
import wit.projekt.Group.GroupRegistry;
import wit.projekt.Subject.Subject;
import wit.projekt.Subject.SubjectRegistry;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class StudentGUI extends PaneController {

    private StudentRegistry studentRegistry;
    private GroupRegistry groupRegistry;

    public StudentGUI(String name, StudentRegistry studentRegistry, GroupRegistry groupRegistry, SubjectRegistry subjectRegistry) {
        super(name, new String[]{"Imię", "Nazwisko", "Numer Albumu", "Grupa"});

        this.studentRegistry = studentRegistry;
        this.groupRegistry = groupRegistry;

        List<Subject> subjects = subjectRegistry.getSubjects();
        for (Subject subject : subjects) {
            addColumn(subject.getCode());
        }

        for (Student student : studentRegistry.getStudents()) {
            addFieldToTable(student.getFields());
            for (Subject subject : subjects) {
                Integer grade = student.getGrade(subject.getCode());
                if (grade != null) {
                    model.setValueAt(grade, model.getRowCount() - 1, model.findColumn(subject.getCode()));
                }
            }
        }

        fields.put("name", new JTextField(10));
        fields.put("surname", new JTextField(10));
        fields.put("albumNumber", new JTextField(10));
        fields.put("group", new JTextField(10));

        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        buttonPanel.add(new JLabel("Imię:"));
        buttonPanel.add(fields.get("name"));
        buttonPanel.add(new JLabel("Nazwisko:"));
        buttonPanel.add(fields.get("surname"));
        buttonPanel.add(new JLabel("Numer albumu:"));
        buttonPanel.add(fields.get("albumNumber"));
        buttonPanel.add(new JLabel("Grupa:"));
        buttonPanel.add(fields.get("group"));

        JButton addButton = createButton("addButton", "Dodaj ucznia");
        buttonPanel.add(addButton);

        JButton deleteButton = createButton("deleteButton", "Usuń ucznia");
        buttonPanel.add(deleteButton);

        JButton editButton = createButton("editButton", "Edytuj ucznia");
        buttonPanel.add(editButton);

        JButton searchButton = createButton("searchButton", "Szukaj ucznia");
        buttonPanel.add(searchButton);
    }

    @Override
    protected String getFieldNameFromID(String id) {
        switch (id) {
            case "name":
                return "Imię";
            case "surname":
                return "Nazwisko";
            case "albumNumber":
                return "Numer albumu";
            case "group":
                return "Grupa";
            default:
                return "";
        }
    }

    @Override
    protected String getButtonNamesFromID(String id) {
        switch (id) {
            case "addButton":
                return "Dodaj ucznia";
            case "deleteButton":
                return "Usuń ucznia";
            case "searchButton":
                return "Szukaj ucznia";
            default:
                return "";
        }
    }

    public JPanel getPanel() {
        return this;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("addButton")) {
            String name = fields.get("name").getText();
            String surname = fields.get("surname").getText();
            String albumNumber = fields.get("albumNumber").getText();
            String groupCode = fields.get("group").getText();

            if (name.isEmpty() || surname.isEmpty() || albumNumber.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Wszystkie pola muszą być wypełnione");
                return;
            }

            Student student = new Student(name, surname, albumNumber);
            Group group = groupRegistry.getGroupByCode(groupCode);

            if (group != null) {
                group.addStudent(student);
            }

            studentRegistry.addStudent(student);
            addFieldToTable(student.getFields());
        }

        if (e.getActionCommand().equals("deleteButton")) {
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Nie wybrano ucznia do usunięcia");
                return;
            }

            String albumNumber = table.getValueAt(selectedRow, 2).toString();
            studentRegistry.deleteStudent(albumNumber);
            deleteRow(selectedRow);
        }

        if (e.getActionCommand().equals(("editButton"))) {
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Nie wybrano ucznia do edycji");
                return;
            }

            String oldAlbumNumber = table.getValueAt(selectedRow, 2).toString();

            String name = fields.get("name").getText();
            String surname = fields.get("surname").getText();
            String albumNumber = fields.get("albumNumber").getText();
            String groupCode = fields.get("group").getText();

            if (name.isEmpty() || surname.isEmpty() || albumNumber.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Wszystkie pola muszą być wypełnione");
                return;
            }

            Student student = new Student(name, surname, albumNumber);
            Group group = groupRegistry.getGroupByCode(groupCode);

            if (group != null) {
                group.addStudent(student);
            }

            studentRegistry.editStudent(oldAlbumNumber, student);
            editRow(student.getFields(), selectedRow);
        }

        if (e.getActionCommand().equals("searchButton")) {
            String albumNumber = fields.get("albumNumber").getText();
            if (!albumNumber.isEmpty()) {
                searchStudent(albumNumber);
            } else {
                JOptionPane.showMessageDialog(null, "Wprowadź numer albumu studenta do wyszukania.");
            }
        }
    }

    public void refreshTable() {
        model.setRowCount(0);
        for (Student student : studentRegistry.getStudents()) {
            addFieldToTable(student.getFields());
            for (String subjectCode : student.getAllGrades().keySet()) {
                Integer grade = student.getGrade(subjectCode);
                if (grade != null) {
                    model.setValueAt(grade, model.getRowCount() - 1, model.findColumn(subjectCode));
                }
            }
        }
    }

    public void addColumn(String subjectCode) {
        if (!columnExists(subjectCode)) {
            model.addColumn(subjectCode);
        }
    }

    public boolean columnExists(String subjectCode) {
        return model.findColumn(subjectCode) != -1;
    }

    public void updateStudentGrade(String studentAlbumNumber, String subjectCode, int grade) {
        int columnIndex = model.findColumn(subjectCode);
        if (columnIndex == -1) {
            JOptionPane.showMessageDialog(null, "Kolumna dla przedmiotu " + subjectCode + " nie istnieje.");
            return;
        }
        for (int i = 0; i < model.getRowCount(); i++) {
            if (model.getValueAt(i, 2).equals(studentAlbumNumber)) {
                model.setValueAt(grade, i, columnIndex);
                studentRegistry.getStudentByAlbumNumber(studentAlbumNumber).addGrade(subjectCode, grade);
                break;
            }
        }

    }

    public void searchStudent(String albumNumber) {
        Student student = studentRegistry.getStudentByAlbumNumber(albumNumber);

        if (student == null) {
            JOptionPane.showMessageDialog(null, "Nie znaleziono studenta o numerze albumu: " + albumNumber);
            return;
        } else {
            JOptionPane.showMessageDialog(null, "Znaleziono studenta:\n" +
                    "Imię: " + student.getName() + "\n" +
                    "Nazwisko: " + student.getSurname() + "\n" +
                    "Numer albumu: " + student.getAlbumNumber() + "\n" +
                    "Grupa: " + student.getGroupCode());
        }
    }

}
