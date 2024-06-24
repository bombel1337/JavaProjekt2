package wit.projekt.Group;

import wit.projekt.Frame.PaneController;
import wit.projekt.Student.Student;
import wit.projekt.Student.StudentGUI;
import wit.projekt.Student.StudentRegistry;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;

public class GroupGUI extends PaneController {

    private GroupRegistry groupRegistry = new GroupRegistry();
    private StudentRegistry studentRegistry = StudentRegistry.getInstance();
    private StudentGUI studentGUI;

    public GroupGUI(String name, StudentGUI studentGUI) {
        super(name, new String[] { "groupCode", "specialization", "description" });
        this.studentGUI = studentGUI;

        for (Group group : groupRegistry.getGroups()) {
            addFieldToTable(group.getFields());
        }

        fields.put("studentAlbumNumber", addField("Numer albumu studenta"));

        JButton assignButton = createButton("assignButton", getButtonNamesFromID("assignButton"));
        buttonPanel.add(assignButton);

        JButton unassignButton = createButton("unassignButton", getButtonNamesFromID("unassignButton"));
        buttonPanel.add(unassignButton);
    }

    @Override
    protected String getFieldNameFromID(String id) {
        switch (id) {
            case "groupCode":
                return "Kod grupy";
            case "specialization":
                return "Specjalizacja";
            case "description":
                return "Opis";
            case "studentAlbumNumber":
                return "Numer albumu studenta";
            default:
                return "";
        }
    }

    @Override
    protected String getButtonNamesFromID(String id) {
        switch (id) {
            case "addButton":
                return "Dodaj grupę";
            case "deleteButton":
                return "Usuń grupę";
            case "editButton":
                return "Edytuj grupę";
            case "assignButton":
                return "Przypisz studenta";
            case "unassignButton":
                return "Usuń przypisanie";
            case "searchButton":
                return "Szukaj grupę";
            default:
                return "";
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("addButton")) {
            String groupCode = fields.get("groupCode").getText();
            String specialization = fields.get("specialization").getText();
            String description = fields.get("description").getText();

            if (groupCode.isEmpty() || specialization.isEmpty() || description.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Wszystkie pola muszą być wypełnione");
                return;
            }

            Group group = new Group(groupCode, specialization, description);
            groupRegistry.addGroup(group);
            addFieldToTable(group.getFields());
        }

        if (e.getActionCommand().equals("deleteButton")) {
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Nie wybrano grupy do usunięcia");
                return;
            }

            String groupCode = table.getValueAt(selectedRow, 0).toString();
            groupRegistry.deleteGroup(groupCode);
            deleteRow(selectedRow);
        }

        if (e.getActionCommand().equals("editButton")) {
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Nie wybrano grupy do edycji");
                return;
            }

            String groupCode = table.getValueAt(selectedRow, 0).toString();
            String specialization = fields.get("specialization").getText();
            String description = fields.get("description").getText();

            if (groupCode.isEmpty() || specialization.isEmpty() || description.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Wszystkie pola muszą być wypełnione");
                return;
            }

            Group group = groupRegistry.editGroup(groupCode, specialization, description);

            if (group == null)
                return;

            editRow(group.getFields(), selectedRow);
        }

        if (e.getActionCommand().equals("assignButton")) {
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Nie wybrano grupy do przypisania studenta");
                return;
            }

            String groupCode = table.getValueAt(selectedRow, 0).toString();
            String studentAlbumNumber = fields.get("studentAlbumNumber").getText();

            if (studentAlbumNumber.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Numer albumu studenta nie może być pusty");
                return;
            }

            Group group = groupRegistry.getGroupByCode(groupCode);
            Student student = studentRegistry.getStudentByAlbumNumber(studentAlbumNumber);

            if (group != null && student != null) {
                System.out
                        .println("Assigning student " + student.getAlbumNumber() + " to group " + group.getGroupCode());
                group.addStudent(student);
                studentRegistry.assignGroupToStudent(student, group);
                System.out.println("After assignment in GroupGUI:");
                for (Student s : studentRegistry.getStudents()) {
                    System.out.println(s.getFields());
                }
                JOptionPane.showMessageDialog(null, "Przypisano studenta: " + student.getName() + " "
                        + student.getSurname() + " do grupy " + group.getGroupCode());
                studentGUI.refreshTable(); // Odświeżenie tabeli studentów po przypisaniu
            } else {
                JOptionPane.showMessageDialog(null, "Nie znaleziono grupy lub studenta");
            }
        }

        if (e.getActionCommand().equals("unassignButton")) {
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Nie wybrano grupy do usunięcia przypisania studenta");
                return;
            }

            String groupCode = table.getValueAt(selectedRow, 0).toString();
            String studentAlbumNumber = fields.get("studentAlbumNumber").getText();

            if (studentAlbumNumber.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Numer albumu studenta nie może być pusty");
                return;
            }

            Group group = groupRegistry.getGroupByCode(groupCode);
            Student student = studentRegistry.getStudentByAlbumNumber(studentAlbumNumber);

            if (group != null && student != null) {
                System.out.println(
                        "Unassigning student " + student.getAlbumNumber() + " from group " + group.getGroupCode());
                group.removeStudent(student);
                studentRegistry.assignGroupToStudent(student, null);
                JOptionPane.showMessageDialog(null, "Usunięto przypisanie studenta: " + student.getName() + " "
                        + student.getSurname() + " z grupy " + group.getGroupCode());
                studentGUI.refreshTable(); // Odświeżenie tabeli studentów po usunięciu przypisania
            } else {
                JOptionPane.showMessageDialog(null, "Nie znaleziono grupy lub studenta");
            }
        }

        if (e.getActionCommand().equals("searchButton")) {
            String groupCode = fields.get("groupCode").getText();
            if (!groupCode.isEmpty()) {
                searchGroup(groupCode);
            } else {
                JOptionPane.showMessageDialog(null, "Wprowadź nazwę grupy do wyszukania.");
            }
        }

    }

    private void searchGroup(String groupCode) {
        Group group = groupRegistry.getGroupByCode(groupCode);

        if (group == null) {
            JOptionPane.showMessageDialog(null, "Nie znaleziono grupy o nazwie: " + groupCode);
            return;
        } else {
            JOptionPane.showMessageDialog(null, "Znaleziono grupę:\n" +
                    "Nazwa: " + group.getGroupCode() + "\n" +
                    "Specjalizacja: " + group.getSpecialization() + "\n" +
                    "Opis: " + group.getDescription());
        }
    }
}
