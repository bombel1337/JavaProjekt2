package wit.projekt.Student;

import java.util.ArrayList;
import java.util.List;

public class Student {
    String name;
    String surname;
    String albumNumber;

    public Student(String name, String surname, String albumNumber) {
        this.name = name;
        this.surname = surname;
        this.albumNumber = albumNumber;
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

    public ArrayList<String> getFields() {
        ArrayList<String> fields = new ArrayList<>();
        fields.add(name);
        fields.add(surname);
        fields.add(albumNumber);

        return fields;
    }
}

class StudentRegistry {
    List<Student> students = new ArrayList<>();

    //TODO: add students from database
    public StudentRegistry() {
        students.add(new Student("Jan", "Kowalski", "123456"));
        students.add(new Student("Adam", "Nowak", "654321"));
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public Student editStudent(String albumNumber, String newName, String newSurname) {
        for (Student student : students) {
            if (student.getAlbumNumber().equals(albumNumber)) {
                student.setName(newName);
                student.setSurname(newSurname);
                return student;
            }
        }
        return null;
    }

    public void deleteStudent(String albumNumber) {
        students.removeIf(student -> student.getAlbumNumber().equals(albumNumber));
    }

}