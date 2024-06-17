package wit.projekt.Student;

import wit.projekt.Frame.PaneController;

public class StudentGUI extends PaneController {

    private StudentRegistry studentRegistry = new StudentRegistry();

    public StudentGUI(String name) {
        super(name, new String[]{"Imię", "Nazwisko", "Numer albumu"});

        for (Student student : studentRegistry.getStudents()) {
            addFieldToTable(student.getFields());
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
            String albumNumber = table.getValueAt(selectedRow, 2).toString();

            studentRegistry.deleteStudent(albumNumber);
            deleteRow(selectedRow);
        }

        if (e.getActionCommand().equals("editButton")) {


            String name = fields.get("name").getText();
            String surname = fields.get("surname").getText();
            String albumNumber = fields.get("albumNumber").getText();

            if (name.isEmpty()
                    || surname.isEmpty()
                    || albumNumber.isEmpty())
                return;

            Student student = studentRegistry.editStudent(name, surname, albumNumber);

            if (student == null)
                return;

            editRow(student.getFields(), selectedRow);
        }
    }
}
