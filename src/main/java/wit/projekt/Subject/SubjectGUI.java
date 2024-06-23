package wit.projekt.Subject;

import wit.projekt.Frame.PaneController;
import wit.projekt.Student.Student;
import wit.projekt.Student.StudentGUI;
import wit.projekt.Student.StudentRegistry;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SubjectGUI extends PaneController {

    private SubjectRegistry subjectRegistry = new SubjectRegistry();
    private StudentGUI studentGUI;
    private StudentRegistry studentRegistry = StudentRegistry.getInstance();

    public SubjectGUI(String name, StudentGUI studentGUI) {
        super(name, new String[]{"code", "name"});
        this.studentGUI = studentGUI;

        for (Subject subject : subjectRegistry.getSubjects()) {
            addFieldToTable(subject.getFields());
        }

        fields.put("code", new JTextField(10));
        fields.put("name", new JTextField(10));
        fields.put("studentAlbumNumber", new JTextField(10));
        fields.put("grade", new JTextField(10));

        fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.X_AXIS));

        fieldPanel.add(new JLabel("Numer albumu studenta:"));
        fieldPanel.add(fields.get("studentAlbumNumber"));
        fieldPanel.add(new JLabel("Ocena:"));
        fieldPanel.add(fields.get("grade"));

        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        buttonPanel.add(new JLabel("Kod przedmiotu:"));
        buttonPanel.add(fields.get("code"));
        buttonPanel.add(new JLabel("Nazwa:"));
        buttonPanel.add(fields.get("name"));

        JButton addButton = createButton("addButton", "Dodaj przedmiot");
        buttonPanel.add(addButton);

        JButton deleteButton = createButton("deleteButton", "Usuń przedmiot");
        buttonPanel.add(deleteButton);

        JButton addGradeButton = createButton("addGradeButton", "Dodaj ocenę");
        fieldPanel.add(addGradeButton);
    }

    @Override
    protected String getFieldNameFromID(String id) {
        switch (id) {
            case "code":
                return "Kod przedmiotu";
            case "name":
                return "Nazwa";
            case "studentAlbumNumber":
                return "Numer albumu studenta";
            case "grade":
                return "Ocena";
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
            case "addGradeButton":
                return "Dodaj ocenę";
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

        if (e.getActionCommand().equals("addGradeButton")) {
            String studentAlbumNumber = fields.get("studentAlbumNumber").getText();
            String gradeStr = fields.get("grade").getText();

            if (studentAlbumNumber.isEmpty() || gradeStr.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Numer albumu studenta i ocena muszą być wypełnione");
                return;
            }

            int grade;
            try {
                grade = Integer.parseInt(gradeStr);
                if (grade < 1 || grade > 5) {
                    JOptionPane.showMessageDialog(null, "Ocena musi być liczbą z zakresu 1-5");
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Ocena musi być liczbą");
                return;
            }

            Student student = studentRegistry.getStudentByAlbumNumber(studentAlbumNumber);
            if (student == null) {
                JOptionPane.showMessageDialog(null, "Nie znaleziono studenta o podanym numerze albumu");
                return;
            }

            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Nie wybrano przedmiotu do przypisania oceny");
                return;
            }

            String subjectCode = table.getValueAt(selectedRow, 0).toString();

            // Dodanie kolumny dla przedmiotu, jeśli nie istnieje
            if (!studentGUI.columnExists(subjectCode)) {
                studentGUI.addColumn(subjectCode);
            }

            studentGUI.updateStudentGrade(studentAlbumNumber, subjectCode, grade);

            JOptionPane.showMessageDialog(null, "Ocena została dodana pomyślnie");
        }
    }
}
