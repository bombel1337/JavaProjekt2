package wit.projekt.Student;

import wit.projekt.Group.Group;
import wit.projekt.Group.GroupRegistry;
import wit.projekt.Subject.SubjectRegistry;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StudentRegistryTest {

    private StudentRegistry studentRegistry;
    private GroupRegistry groupRegistry;
    private SubjectRegistry subjectRegistry;

    @BeforeEach
    public void setUp() {
        List<String> studentData = new ArrayList<>();
        List<String> groupData = new ArrayList<>();
        List<String> subjectData = new ArrayList<>();
        
        studentRegistry = new StudentRegistry(studentData);
        groupRegistry = new GroupRegistry(groupData);
        subjectRegistry = new SubjectRegistry(subjectData);
    }

    @Test
    public void testAddStudent() {
        Student student = new Student("John", "Doe", "12345");
        studentRegistry.addStudent(student);

        assertEquals(1, studentRegistry.getStudents().size());
        assertEquals("John", studentRegistry.getStudentByAlbumNumber("12345").getName());
    }

    @Test
    public void testEditStudent() {
        Student student = new Student("John", "Doe", "12345");
        studentRegistry.addStudent(student);

        Student editedStudent = new Student("John", "Smith", "12345");
        studentRegistry.editStudent("12345", editedStudent);

        assertEquals("Smith", studentRegistry.getStudentByAlbumNumber("12345").getSurname());
    }

    @Test
    public void testDeleteStudent() {
        Student student = new Student("John", "Doe", "12345");
        studentRegistry.addStudent(student);

        studentRegistry.deleteStudent("12345");

        assertNull(studentRegistry.getStudentByAlbumNumber("12345"));
        assertEquals(0, studentRegistry.getStudents().size());
    }

    @Test
    public void testAssignGroupToStudent() {
        Student student = new Student("John", "Doe", "12345");
        Group group = new Group("G01", "CS", "Computer Science Group");

        studentRegistry.addStudent(student);
        groupRegistry.addGroup(group);

        studentRegistry.assignGroupToStudent(student, group);

        assertEquals("G01", studentRegistry.getStudentByAlbumNumber("12345").getGroupCode());
    }
}
