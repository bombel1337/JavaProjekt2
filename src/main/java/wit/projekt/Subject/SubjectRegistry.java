package wit.projekt.Subject;

import wit.projekt.Database.Database;

import java.util.ArrayList;
import java.util.List;

public class SubjectRegistry {
    private List<Subject> subjects = new ArrayList<>();

    public SubjectRegistry(List<String> data) {
        if (data.size() > 0) {
            for (int i = 0; i < data.size(); i += 2) {
                subjects.add(new Subject(data.get(i), data.get(i + 1)));
            }
        }
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

    public void editSubject(String code, Subject newSubject) {
        for (Subject subject : subjects) {
            if (subject.getCode().equals(code)) {
                deleteSubject(code);
                addSubject(newSubject);
                break;
            }
        }
    }

    public Subject getSubjectByCode(String code) {
        for (Subject subject : subjects) {
            if (subject.getCode().equals(code)) {
                return subject;
            }
        }
        return null;
    }

    public void saveDataToDB() {
        List<String> data = new ArrayList<>();
        for (Subject subject : subjects) {
            data.add(subject.getCode());
            data.add(subject.getName());
        }
        Database.save("subjects", data);
    }
}
