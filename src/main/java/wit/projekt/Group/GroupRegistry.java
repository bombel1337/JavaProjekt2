package wit.projekt.Group;

import wit.projekt.Database.Database;

import java.util.ArrayList;
import java.util.List;

public class GroupRegistry {
    private List<Group> groups = new ArrayList<>();

    public GroupRegistry(List<String> data) {
        if (!data.isEmpty()) {
            for (String line : data) {
                String[] parts = line.split(";");
                groups.add(new Group(parts[0], parts[1], parts[2]));
            }
        }
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
            StringBuilder sb = new StringBuilder();
            sb.append(group.getGroupCode()).append(";")
              .append(group.getSpecialization()).append(";")
              .append(group.getDescription());
            data.add(sb.toString());
        }
        Database.save("groups", data);
    }
}
