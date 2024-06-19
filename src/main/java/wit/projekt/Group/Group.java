package wit.projekt.Group;

import wit.projekt.Student.Student;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private String groupCode;
    private String specialization;
    private String description;
    private List<Student> students;

    public Group(String groupCode, String specialization, String description) {
        this.groupCode = groupCode;
        this.specialization = specialization;
        this.description = description;
        this.students = new ArrayList<>();
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        if (!students.contains(student)) {
            students.add(student);
            student.setGroup(this);
        }
    }

    public void removeStudent(Student student) {
        if (students.contains(student)) {
            students.remove(student);
            student.setGroup(null);
        }
    }

    public ArrayList<String> getFields() {
        ArrayList<String> fields = new ArrayList<>();
        fields.add(groupCode);
        fields.add(specialization);
        fields.add(description);
        return fields;
    }
}
