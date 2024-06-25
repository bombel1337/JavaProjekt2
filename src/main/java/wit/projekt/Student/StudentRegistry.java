package wit.projekt.Student;

import wit.projekt.Database.Database;
import wit.projekt.Group.Group;
import wit.projekt.Group.GroupRegistry;

import javax.swing.*;
import java.util.*;

public class StudentRegistry {
    private List<Student> students;
    private GroupRegistry groupRegistry;

    public StudentRegistry(List<String> data, GroupRegistry groupRegistry) {
        this.groupRegistry = groupRegistry;
        students = new ArrayList<>();

        if (!data.isEmpty()) {
            for (String line : data) {
                String[] parts = line.split(";");
                Student student = new Student(parts[0], parts[1], parts[2]);
                Group group = groupRegistry.getGroupByCode(parts[3]);
                student.setGroup(group);

                if (parts.length > 4) {
                    for (int i = 4; i < parts.length; i += 2) {
                        student.addGrade(parts[i], Integer.parseInt(parts[i + 1]));
                    }
                }
                students.add(student);
            }
        }
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
                newStudent.getAllGrades().putAll(student.getAllGrades());
                deleteStudent(albumNumber);
                students.add(newStudent);
                JOptionPane.showMessageDialog(null, "Zaktualizowano dane studenta: " + student.getName() + " " + student.getSurname() + " " + student.getAlbumNumber());
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
        for (Student s : students) {
            if (s.getAlbumNumber().equals(student.getAlbumNumber())) {
                s.setGroup(group);
                JOptionPane.showMessageDialog(null, "Zaktualizowano grupę studenta: " + s.getName() + " " + s.getSurname() + " na grupę " + (group != null ? group.getGroupCode() : "Brak grupy"));
                break;
            }
        }
    }

    public void saveDataToDB() {
        List<String> data = new ArrayList<>();
        for (Student student : students) {
            StringBuilder sb = new StringBuilder();
            sb.append(student.getName()).append(";")
              .append(student.getSurname()).append(";")
              .append(student.getAlbumNumber()).append(";")
              .append(student.getGroupCode());
            for (Map.Entry<String, Integer> entry : student.getAllGrades().entrySet()) {
                sb.append(";").append(entry.getKey()).append(";").append(entry.getValue());
            }
            data.add(sb.toString());
        }
        Database.save("students", data);
    }
}
