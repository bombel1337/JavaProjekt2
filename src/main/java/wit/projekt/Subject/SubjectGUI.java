package wit.projekt.Subject;

import wit.projekt.Frame.PaneController;

public class SubjectGUI extends PaneController {

    private SubjectRegistry subjectRegistry = new SubjectRegistry();

    public SubjectGUI(String name) {
        super(name, new String[] { "name", "assessmentCriteria" });

        for (Subject subject : subjectRegistry.getSubjects()) {
            addFieldToTable(subject.getFields());
        }
    }

    @Override
    protected String getFieldNameFromID(String id) {
        switch (id) {
            case "name":
                return "Name";
            case "assessmentCriteria":
                return "Assessment Criteria";
            default:
                return "";
        }
    }

    @Override
    protected String getButtonNamesFromID(String id) {
        switch (id) {
            case "addButton":
                return "Add Subject";
            case "deleteButton":
                return "Delete Subject";
            case "editButton":
                return "Edit Subject";
            default:
                return "";
        }
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        if (e.getActionCommand().equals("addButton")) {
            String name = fields.get("name").getText();
            String criteria = fields.get("assessmentCriteria").getText();

            if (name.isEmpty() || criteria.isEmpty())
                return;

            Subject subject = new Subject(name);
            // Add logic to parse and add criteria
            subjectRegistry.addSubject(subject);
            addFieldToTable(subject.getFields());
        }

        if (e.getActionCommand().equals("deleteButton")) {
            if (selectedRow == -1)
                return;

            String name = table.getValueAt(selectedRow, 0).toString();

            subjectRegistry.deleteSubject(name);
            deleteRow(selectedRow);
        }

        if (e.getActionCommand().equals("editButton")) {
            if (selectedRow == -1)
                return;

            String name = fields.get("name").getText();
            String criteria = fields.get("assessmentCriteria").getText();
            String originalName = table.getValueAt(selectedRow, 0).toString();

            if (name.isEmpty() || criteria.isEmpty())
                return;

            Subject subject = subjectRegistry.editSubject(originalName, name);

            if (subject == null)
                return;

            // Update criteria logic
            editRow(subject.getFields(), selectedRow);
        }
    }
}
