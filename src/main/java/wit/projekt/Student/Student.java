package wit.projekt.Student;

import java.util.ArrayList;
import wit.projekt.Group.Group;

public class Student {
    private String name;
    private String surname;
    private String albumNumber;
    private Group group;

    public Student(String name, String surname, String albumNumber) {
        this.name = name;
        this.surname = surname;
        this.albumNumber = albumNumber;
        this.group = null;
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

    public ArrayList<Object> getFields() {
        ArrayList<Object> fields = new ArrayList<>();
        fields.add(name);
        fields.add(surname);
        fields.add(albumNumber);
        fields.add(getGroupCode());
        return fields;
    }
}
