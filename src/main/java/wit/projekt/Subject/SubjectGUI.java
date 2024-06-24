package wit.projekt.Subject;

import wit.projekt.Frame.PaneController;

import javax.swing.*;

public class SubjectGUI extends PaneController {

    private SubjectRegistry subjectRegistry = new SubjectRegistry();

    public SubjectGUI(String name) {
        super(name, new String[] { "name", "thirdGrade", "fourthGrade", "fithGrade" });

        for (Subject subject : subjectRegistry.getSubjects()) {
            addFieldToTable(subject.getFields());
        }
    }

    @Override
    protected String getFieldNameFromID(String id) {
        switch (id) {
            case "name":
                return "Nazwa przedmiotu";
            case "thirdGrade":
                return "Ocena 3";
            case "fourthGrade":
                return "Ocena 4";
            case "fithGrade":
                return "Ocena 5";
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
            case "editButton":
                return "Edytuj przedmiot";
            case "searchButton":
                return "Szukaj przedmiotu";
            default:
                return "";
        }
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        if (e.getActionCommand().equals("addButton")) {
            String name = fields.get("name").getText();
            String thirdGrade = fields.get("thirdGrade").getText();
            String fourthGrade = fields.get("fourthGrade").getText();
            String fifthGrade = fields.get("fithGrade").getText();

            if (name.isEmpty() || thirdGrade.isEmpty() || fourthGrade.isEmpty() || fifthGrade.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Wszystkie pola muszą być wypełnione");
                return;
            }

            Subject subject = new Subject(name, Integer.parseInt(thirdGrade), Integer.parseInt(fourthGrade),
                    Integer.parseInt(fifthGrade));
            // Add logic to parse and add criteria
            subjectRegistry.addSubject(subject);
            addFieldToTable(subject.getFields());
        }

        if (e.getActionCommand().equals("deleteButton")) {
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Nie wybrano grupy do usunięcia");
                return;
            }

            String name = table.getValueAt(selectedRow, 0).toString();

            subjectRegistry.deleteSubject(name);
            deleteRow(selectedRow);
        }

        if (e.getActionCommand().equals("editButton")) {
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Nie wybrano grupy do usunięcia");
                return;
            }

            String name = fields.get("name").getText();
            String thirdGrade = fields.get("thirdGrade").getText();
            String fourthGrade = fields.get("fourthGrade").getText();
            String fifthGrade = fields.get("fithGrade").getText();
            String originalName = table.getValueAt(selectedRow, 0).toString();

            if (name.isEmpty() || thirdGrade.isEmpty() || fourthGrade.isEmpty() || fifthGrade.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Wszystkie pola muszą być wypełnione");
                return;
            }

            Subject subject = subjectRegistry.editSubject(originalName, name, Integer.parseInt(thirdGrade),
                    Integer.parseInt(fourthGrade), Integer.parseInt(fifthGrade));

            if (subject == null)
                return;

            // Update criteria logic
            editRow(subject.getFields(), selectedRow);
        }

        if (e.getActionCommand().equals("searchButton")) {
            String name = fields.get("name").getText();
            if (!name.isEmpty()) {
                searchSubject(name);
            } else {
                JOptionPane.showMessageDialog(null, "Wprowadź nazwę przedmiotu do wyszukania.");
            }
        }
    }

    public void searchSubject(String name) {
        Subject subject = subjectRegistry.getSubjectByName(name);
        if (subject != null) {
            JOptionPane.showMessageDialog(null, "Znaleziono przedmiot:\n" +
                    "Nazwa: " + subject.getName() + "\n" +
                    "Ocena 3: " + subject.getThirdGrade() + " pkt" + "\n" +
                    "Ocena 4: " + subject.getFourthGrade() + " pkt" + "\n" +
                    "Ocena 5: " + subject.getFifthGrade() + " pkt");

        } else {
            JOptionPane.showMessageDialog(null, "Nie znaleziono przedmiotu o nazwie: " + name);
        }
    }
}