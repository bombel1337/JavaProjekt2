package wit.projekt.Subject;

import wit.projekt.Frame.PaneController;
import wit.projekt.Student.Student;
import wit.projekt.Student.StudentGUI;
import wit.projekt.Student.StudentRegistry;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SubjectGUI extends PaneController {

    private SubjectRegistry subjectRegistry;
    private StudentGUI studentGUI;
    private StudentRegistry studentRegistry;

    public SubjectGUI(String name, SubjectRegistry subjectRegistry, StudentRegistry studentRegistry, StudentGUI studentGUI) {
        super(name, new String[]{"Kod przedmiotu", "Nazwa przedmiotu"});
        this.subjectRegistry = subjectRegistry;
        this.studentRegistry = studentRegistry;
        this.studentGUI = studentGUI;

        for (Subject subject : subjectRegistry.getSubjects()) {
            addFieldToTable(subject.getFields());
        }

        fields.put("code", new JTextField(10));
        fields.put("name", new JTextField(10));
        fields.put("studentAlbumNumber", new JTextField(10));
        fields.put("points", new JTextField(10));

        fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.X_AXIS));

        fieldPanel.add(new JLabel("Numer albumu studenta:"));
        fieldPanel.add(fields.get("studentAlbumNumber"));
        fieldPanel.add(new JLabel("Liczba punktów:"));
        fieldPanel.add(fields.get("points"));

        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        buttonPanel.add(new JLabel("Kod przedmiotu:"));
        buttonPanel.add(fields.get("code"));
        buttonPanel.add(new JLabel("Nazwa:"));
        buttonPanel.add(fields.get("name"));

        JButton addButton = createButton("addButton", "Dodaj przedmiot");
        buttonPanel.add(addButton);

        JButton deleteButton = createButton("deleteButton", "Usuń przedmiot");
        buttonPanel.add(deleteButton);

        JButton addPointsButton = createButton("addPointsButton", "Dodaj punkty");
        fieldPanel.add(addPointsButton);

        JButton editButton = createButton("editButton", "Edytuj przedmiot");
        buttonPanel.add(editButton);

        JButton searchButton = createButton("searchButton", "Szukaj ucznia");
        buttonPanel.add(searchButton);
    }

    @Override
    protected String getFieldNameFromID(String id) {
        switch (id) {
            case "code":
                return "Kod przedmiotu";
            case "name":
                return "Nazwa";
            default:
                return "";
        }
    }

    @Override
    protected String getButtonNamesFromID(String id) {
        switch (id) {
            case "addButton":
                return "Dodaj przedmiot";
            case "deleteButton":
                return "Usuń przedmiot";
            case "searchButton":
                return "Szukaj przedmiotu";
            case "addPointsButton":
                return "Dodaj punkty";
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
            String code = fields.get("code").getText();
            String name = fields.get("name").getText();

            if (code.isEmpty() || name.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Kod i nazwa przedmiotu muszą być wypełnione");
                return;
            }

            Subject subject = new Subject(code, name);
            subjectRegistry.addSubject(subject);
            addFieldToTable(subject.getFields());

            // Dodanie nowej kolumny do tabeli studentów
            studentGUI.addColumn(code);
        }

        if (e.getActionCommand().equals("deleteButton")) {
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Nie wybrano przedmiotu do usunięcia");
                return;
            }

            String code = table.getValueAt(selectedRow, 0).toString();
            subjectRegistry.deleteSubject(code);
            deleteRow(selectedRow);

            // Usunięcie kolumny z tabeli studentów
            // This part would need to be implemented in the StudentGUI class
        }

        if (e.getActionCommand().equals("addPointsButton")) {
            String studentAlbumNumber = fields.get("studentAlbumNumber").getText();
            String pointsStr = fields.get("points").getText();

            if (studentAlbumNumber.isEmpty() || pointsStr.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Numer albumu studenta i liczba punktów muszą być wypełnione");
                return;
            }

            int points;
            try {
                points = Integer.parseInt(pointsStr);
                if (points < 0 || points > 100) {
                    JOptionPane.showMessageDialog(null, "Liczba punktów musi być liczbą z zakresu 0-100");
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Liczba punktów musi być liczbą");
                return;
            }

            Student student = studentRegistry.getStudentByAlbumNumber(studentAlbumNumber);
            if (student == null) {
                JOptionPane.showMessageDialog(null, "Nie znaleziono studenta o podanym numerze albumu");
                return;
            }

            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Nie wybrano przedmiotu do przypisania punktów");
                return;
            }

            String subjectCode = table.getValueAt(selectedRow, 0).toString();

            // Dodanie kolumny dla przedmiotu, jeśli nie istnieje
            if (!studentGUI.columnExists(subjectCode)) {
                studentGUI.addColumn(subjectCode);
            }

            int grade = calculateGrade(points);

            studentGUI.updateStudentGrade(studentAlbumNumber, subjectCode, grade);

            JOptionPane.showMessageDialog(null, "Punkty zostały dodane pomyślnie, ocena: " + grade);
        }

        if (e.getActionCommand().equals("editButton")) {
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Nie wybrano przedmiotu do edycji");
                return;
            }

            String oldCode = table.getValueAt(selectedRow, 0).toString();

            String code = fields.get("code").getText();
            String name = fields.get("name").getText();

            if (code.isEmpty() || name.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Kod i nazwa przedmiotu muszą być wypełnione");
                return;
            }

            Subject subject = new Subject(code, name);
            subjectRegistry.editSubject(oldCode, subject);
            editRow(subject.getFields(), selectedRow);

        }

        if (e.getActionCommand().equals("searchButton")) {
            String code = fields.get("code").getText();
            if (!code.isEmpty()) {
                searchSubject(code);
            } else {
                JOptionPane.showMessageDialog(null, "Wprowadź nazwę przedmiotu do wyszukania.");
            }
        }
    }

    private int calculateGrade(int points) {
        if (points > 90) {
            return 5;
        } else if (points > 70) {
            return 4;
        } else if (points > 50) {
            return 3;
        } else {
            return 2;
        }
    }

    public void searchSubject(String code) {
        Subject subject = subjectRegistry.getSubjectByCode(code);
        if (subject != null) {
            JOptionPane.showMessageDialog(null, "Znaleziono przedmiot:\n" +
                    "Nazwa: " + subject.getName() + "\n" +
                    "Kod: " + subject.getCode());

        } else {
            JOptionPane.showMessageDialog(null, "Nie znaleziono przedmiotu o nazwie: " + code);
        }
    }
}
