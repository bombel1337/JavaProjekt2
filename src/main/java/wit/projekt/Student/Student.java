package wit.projekt.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import wit.projekt.Group.Group;

public class Student {
    private String name;
    private String surname;
    private String albumNumber;
    private Group group;
    private Map<String, Integer> subjectGrades; // Map to hold subject codes and corresponding grades

    public Student(String name, String surname, String albumNumber) {
        this.name = name;
        this.surname = surname;
        this.albumNumber = albumNumber;
        this.group = null;
        this.subjectGrades = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAlbumNumber(String albumNumber) {
        this.albumNumber = albumNumber;
    }

    public String getAlbumNumber() {
        return albumNumber;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getGroupCode() {
        return group != null ? group.getGroupCode() : "Brak grupy";
    }

    public void addGrade(String subjectCode, int grade) {
        subjectGrades.put(subjectCode, grade);
    }

    public Integer getGrade(String subjectCode) {
        return subjectGrades.get(subjectCode);
    }

    public Map<String, Integer> getAllGrades() {
        return subjectGrades;
    }

    public ArrayList<Object> getFields() {
        ArrayList<Object> fields = new ArrayList<>();
        fields.add(name);
        fields.add(surname);
        fields.add(albumNumber);
        fields.add(getGroupCode());
        return fields;
    }
}
