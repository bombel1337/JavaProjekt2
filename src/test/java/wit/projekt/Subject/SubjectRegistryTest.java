package wit.projekt.Subject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wit.projekt.Group.GroupRegistry;
import wit.projekt.Student.StudentRegistry;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SubjectRegistryTest {

    private SubjectRegistry subjectRegistry;
    private StudentRegistry studentRegistry;
    private GroupRegistry groupRegistry;

    @BeforeEach
    public void setUp() {
        List<String> subjectData = new ArrayList<>();
        List<String> studentData = new ArrayList<>();
        List<String> groupData = new ArrayList<>();

        groupRegistry = new GroupRegistry(groupData);
        subjectRegistry = new SubjectRegistry(subjectData);
        studentRegistry = new StudentRegistry(studentData, groupRegistry);
    }

    @Test
    public void testAddSubject() {
        Subject subject = new Subject("MATH101", "Mathematics");
        subjectRegistry.addSubject(subject);

        assertEquals(1, subjectRegistry.getSubjects().size());
        assertEquals("Mathematics", subjectRegistry.getSubjectByCode("MATH101").getName());
    }

    @Test
    public void testEditSubject() {
        Subject subject = new Subject("MATH101", "Mathematics");
        subjectRegistry.addSubject(subject);

        Subject editedSubject = new Subject("MATH101", "Advanced Mathematics");
        subjectRegistry.editSubject("MATH101", editedSubject);

        assertEquals("Advanced Mathematics", subjectRegistry.getSubjectByCode("MATH101").getName());
    }

    @Test
    public void testDeleteSubject() {
        Subject subject = new Subject("MATH101", "Mathematics");
        subjectRegistry.addSubject(subject);

        subjectRegistry.deleteSubject("MATH101");

        assertNull(subjectRegistry.getSubjectByCode("MATH101"));
        assertEquals(0, subjectRegistry.getSubjects().size());
    }
}
