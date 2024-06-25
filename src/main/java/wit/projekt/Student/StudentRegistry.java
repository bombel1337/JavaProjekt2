package wit.projekt.Student;

import wit.projekt.Database.Database;
import wit.projekt.Group.Group;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRegistry {
    private List<Student> students;

    public StudentRegistry(List<String> data) {
        students = new ArrayList<>();
        
        if (!data.isEmpty()) {
            for (int i = 0; i < data.size(); i += 4) {
                Student student = new Student(data.get(i), data.get(i + 1), data.get(i + 2));
                // Assuming data for grades will be added here
                students.add(student);
            }
        }
        //students.add(new Student("Jan", "Kowalski", "123456"));
        //students.add(new Student("Adam", "Nowak", "654321"));
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        students.add(student);
        JOptionPane.showMessageDialog(null, "Dodano studenta: " + student.getName() + " " + student.getSurname());
    }

    public Student editStudent(String albumNumber, Student newStudent) {
        for (Student student : students) {
            if (student.getAlbumNumber().equals(albumNumber)) {
                // Retain existing grades
                newStudent.getAllGrades().putAll(student.getAllGrades());
                deleteStudent(albumNumber);
                students.add(newStudent);

                JOptionPane.showMessageDialog(null,
                        "Zaktualizowano dane studenta: " + student.getName() + " " + student.getSurname() + " "
                                + student.getAlbumNumber());
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

    public void saveDataToDB() {
        List<String> data = new ArrayList<>();
        for (Student student : students) {
            data.add(student.getName());
            data.add(student.getSurname());
            data.add(student.getAlbumNumber());
            data.add(student.getGroupCode());
            // Assuming data for grades will be added here
        }
        Database.save("students", data);
    }
}
