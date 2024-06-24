package wit.projekt.Student;

import wit.projekt.Frame.PaneController;
import wit.projekt.Group.GroupRegistry;
import wit.projekt.Group.Group;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class StudentGUI extends PaneController {
    private StudentRegistry studentRegistry = StudentRegistry.getInstance();
    private GroupRegistry groupRegistry = new GroupRegistry();

    public StudentGUI(String name) {
        super(name, new String[] { "name", "surname", "albumNumber", "groupCode" });

        for (Student student : studentRegistry.getStudents()) {
            addFieldToTable(student.getFields());
        }
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
            case "groupCode":
                return "Kod grupy";
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
            case "editButton":
                return "Edytuj ucznia";
            case "searchButton":
                return "Szukaj ucznia";
            default:
                return "";
        }
    }

    public void refreshTable() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Usuwa wszystkie wiersze z tabeli
        System.out.println("Refreshing table");
        List<Student> students = studentRegistry.getStudents();
        for (Student student : students) {
            System.out.println("Student: " + student.getName() + " " + student.getSurname() + ", Group: "
                    + student.getGroupCode());
            System.out.println("Fields: " + student.getFields());
            addFieldToTable(student.getFields());
        }
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        if (e.getActionCommand().equals("addButton")) {
            String name = fields.get("name").getText();
            String surname = fields.get("surname").getText();
            String albumNumber = fields.get("albumNumber").getText();
            String groupCode = fields.get("groupCode").getText();

            if (name.isEmpty() || surname.isEmpty() || albumNumber.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Wszystkie pola muszą być wypełnione");
                return;
            }

            Student student = new Student(name, surname, albumNumber);
            Group group = groupRegistry.getGroupByCode(groupCode);

            if (group != null) {
                group.addStudent(student);
                JOptionPane.showMessageDialog(null, "Dodano studenta i przypisano do grupy: " + groupCode);
            } else if (!groupCode.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Grupa o podanym kodzie nie istnieje");
                return;
            }

            studentRegistry.addStudent(student);
            refreshTable(); // Odświeża tabelę po dodaniu studenta
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

        if (e.getActionCommand().equals("editButton")) {
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Nie wybrano ucznia do edycji");
                return;
            }

            String albumNumber = table.getValueAt(selectedRow, 2).toString();
            String name = fields.get("name").getText();
            String surname = fields.get("surname").getText();

            if (name.isEmpty() || surname.isEmpty() || albumNumber.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Wszystkie pola muszą być wypełnione");
                return;
            }

            Student student = studentRegistry.editStudent(albumNumber, name, surname);

            if (student == null)
                return;

            refreshTable(); // Odświeża tabelę po edycji studenta
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
