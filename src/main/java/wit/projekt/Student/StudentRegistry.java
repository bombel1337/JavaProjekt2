package wit.projekt.Student;

import wit.projekt.Group.Group;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;

public class StudentRegistry {
    private static StudentRegistry instance;
    private List<Student> students;

    private StudentRegistry() {
        students = new ArrayList<>();
        students.add(new Student("Jan", "Kowalski", "123456"));
        students.add(new Student("Adam", "Nowak", "654321"));
    }

    public static StudentRegistry getInstance() {
        if (instance == null) {
            instance = new StudentRegistry();
        }
        return instance;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        students.add(student);
        JOptionPane.showMessageDialog(null, "Dodano studenta: " + student.getName() + " " + student.getSurname());
    }

    public Student editStudent(String albumNumber, String newName, String newSurname) {
        for (Student student : students) {
            if (student.getAlbumNumber().equals(albumNumber)) {
                student.setName(newName);
                student.setSurname(newSurname);
                JOptionPane.showMessageDialog(null,
                        "Zaktualizowano dane studenta: " + student.getName() + " " + student.getSurname());
                return student;
            }
        }
        return null;
    }

    public void deleteStudent(String albumNumber) {
        students.removeIf(student -> student.getAlbumNumber().equals(albumNumber));
    }

    public Student getStudentByAlbumNumber(String albumNumber) {
        for (Student student : students) {
            if (student.getAlbumNumber().equals(albumNumber)) {
                return student;
            }
        }
        return null;
    }

    public void assignGroupToStudent(Student student, Group group) {
        System.out.println("Assigning group " + (group != null ? group.getGroupCode() : "null") + " to student "
                + student.getAlbumNumber());
        for (Student s : students) {
            if (s.getAlbumNumber().equals(student.getAlbumNumber())) {
                s.setGroup(group);
                System.out.println("Student " + s.getAlbumNumber() + " group set to " + s.getGroupCode());
                JOptionPane.showMessageDialog(null, "Zaktualizowano grupę studenta: " + s.getName() + " "
                        + s.getSurname() + " na grupę " + (group != null ? group.getGroupCode() : "Brak grupy"));
                break;
            }
        }
        System.out.println("After assignment:");
        for (Student s : students) {
            System.out.println(s.getFields());
        }
    }
}
