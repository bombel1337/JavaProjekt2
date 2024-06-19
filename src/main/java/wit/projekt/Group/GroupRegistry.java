package wit.projekt.Group;

import java.util.ArrayList;
import java.util.List;

public class GroupRegistry {
    private List<Group> groups = new ArrayList<>();

    public GroupRegistry() {
        groups.add(new Group("G01", "Computer Science", "CS Group"));
        groups.add(new Group("G02", "Mathematics", "Math Group"));
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void addGroup(Group group) {
        groups.add(group);
    }

    public Group editGroup(String groupCode, String newSpecialization, String newDescription) {
        for (Group group : groups) {
            if (group.getGroupCode().equals(groupCode)) {
                group.setSpecialization(newSpecialization);
                group.setDescription(newDescription);
                return group;
            }
        }
        return null;
    }

    public void deleteGroup(String groupCode) {
        groups.removeIf(group -> group.getGroupCode().equals(groupCode));
    }

    public Group getGroupByCode(String groupCode) {
        for (Group group : groups) {
            if (group.getGroupCode().equals(groupCode)) {
                return group;
            }
        }
        return null;
    }
}
