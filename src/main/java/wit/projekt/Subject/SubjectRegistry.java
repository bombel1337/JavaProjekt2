package wit.projekt.Subject;

import java.util.ArrayList;
import java.util.List;

public class SubjectRegistry {
    private List<Subject> subjects = new ArrayList<>();

    public SubjectRegistry() {
        subjects.add(new Subject("JAVA", "Java Language"));
        subjects.add(new Subject("MATH", "Mathematics"));
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void addSubject(Subject subject) {
        subjects.add(subject);
    }

    public void deleteSubject(String code) {
        subjects.removeIf(subject -> subject.getCode().equals(code));
    }

    public void editSubject(String code, String newName) {
        for (Subject subject : subjects) {
            if (subject.getCode().equals(code)) {
                subject.setName(newName);
                break;
            }
        }
    }
}
