package wit.projekt.Student;

import wit.projekt.Frame.PaneController;

public class StudentGUI extends PaneController {

    private StudentRegistry studentRegistry = new StudentRegistry();

    public StudentGUI(String name) {
        super(name, new String[]{"name", "surname", "albumNumber"});

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
            default:
                return "";
        }
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        if (e.getActionCommand().equals("addButton")) {
            String name = fields.get("name").getText();
            String surname = fields.get("surname").getText();
            String albumNumber = fields.get("albumNumber").getText();

            if (name.isEmpty()
                    || surname.isEmpty()
                    || albumNumber.isEmpty())
                return;

            Student student = new Student(name, surname, albumNumber);
            studentRegistry.addStudent(student);
            addFieldToTable(student.getFields());
        }

        if (e.getActionCommand().equals("deleteButton")) {
            if (selectedRow == -1)
                return;

            String albumNumber = table.getValueAt(selectedRow, 2).toString();

            studentRegistry.deleteStudent(albumNumber);
            deleteRow(selectedRow);
        }

        if (e.getActionCommand().equals("editButton")) {


            String name = fields.get("name").getText();
            String surname = fields.get("surname").getText();
            String albumNumber = table.getValueAt(selectedRow, 2).toString();

            System.out.println(name + " " + surname + " " + albumNumber);

            if (name.isEmpty()
                    || surname.isEmpty()
                    || albumNumber.isEmpty())
                return;

            Student student = studentRegistry.editStudent(albumNumber, name, surname);

            if (student == null)
                return;

            System.out.println(student.getName() + " " + student.getSurname() + " " + student.getAlbumNumber());

            editRow(student.getFields(), selectedRow);
        }
    }
}
