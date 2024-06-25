package wit.projekt.Group;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wit.projekt.Student.StudentRegistry;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GroupRegistryTest {

    private GroupRegistry groupRegistry;
    private StudentRegistry studentRegistry;

    @BeforeEach
    public void setUp() {
        List<String> groupData = new ArrayList<>();
        List<String> studentData = new ArrayList<>();

        groupRegistry = new GroupRegistry(groupData);
        studentRegistry = new StudentRegistry(studentData, groupRegistry);
    }

    @Test
    public void testAddGroup() {
        Group group = new Group("CS101", "Computer Science", "CS Group");
        groupRegistry.addGroup(group);

        assertEquals(1, groupRegistry.getGroups().size());
        assertEquals("Computer Science", groupRegistry.getGroupByCode("CS101").getSpecialization());
    }

    @Test
    public void testEditGroup() {
        Group group = new Group("CS101", "Computer Science", "CS Group");
        groupRegistry.addGroup(group);

        Group editedGroup = new Group("CS101", "Advanced Computer Science", "Advanced CS Group");
        groupRegistry.editGroup("CS101", editedGroup);

        assertEquals("Advanced Computer Science", groupRegistry.getGroupByCode("CS101").getSpecialization());
    }

    @Test
    public void testDeleteGroup() {
        Group group = new Group("CS101", "Computer Science", "CS Group");
        groupRegistry.addGroup(group);

        groupRegistry.deleteGroup("CS101");

        assertNull(groupRegistry.getGroupByCode("CS101"));
        assertEquals(0, groupRegistry.getGroups().size());
    }
}
