package wit.projekt.Group;

import wit.projekt.Database.Database;

import java.util.ArrayList;
import java.util.List;

public class GroupRegistry {
    private List<Group> groups = new ArrayList<>();

    public GroupRegistry(List<String> data) {
        if (data.size() > 0) {
            for (int i = 0; i < data.size(); i += 3) {
                groups.add(new Group(data.get(i), data.get(i + 1), data.get(i + 2)));
            }
        }
        //groups.add(new Group("G01", "Computer Science", "CS Group"));
        //groups.add(new Group("G02", "Mathematics", "Math Group"));
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void addGroup(Group group) {
        groups.add(group);
    }

    public Group editGroup(String groupCode, Group newGroup) {
        for (Group group : groups) {
            if (group.getGroupCode().equals(groupCode)) {
                deleteGroup(groupCode);
                groups.add(newGroup);
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

    public void saveDataToDB() {
        List<String> data = new ArrayList<>();
        for (Group group : groups) {
            data.add(group.getGroupCode());
            data.add(group.getSpecialization());
            data.add(group.getDescription());
        }
        Database.save("groups", data);
    }
}
