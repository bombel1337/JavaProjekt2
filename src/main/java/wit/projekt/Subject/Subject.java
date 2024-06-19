package wit.projekt.Subject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Subject {
    String name;
    Map<String, Integer> assessmentCriteria;

    public Subject(String name) {
        this.name = name;
        this.assessmentCriteria = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addCriterion(String criterion, int maxPoints) {
        assessmentCriteria.put(criterion, maxPoints);
    }

    public void editCriterion(String criterion, int newMaxPoints) {
        if (assessmentCriteria.containsKey(criterion)) {
            assessmentCriteria.put(criterion, newMaxPoints);
        }
    }

    public void removeCriterion(String criterion) {
        assessmentCriteria.remove(criterion);
    }

    public ArrayList<String> getFields() {
        ArrayList<String> fields = new ArrayList<>();
        fields.add(name);
        fields.add(assessmentCriteria.toString());
        return fields;
    }
}

class SubjectRegistry {
    List<Subject> subjects = new ArrayList<>();

    // TODO: add subjects from database
    public SubjectRegistry() {
        Subject java = new Subject("Java Language");
        java.addCriterion("Exam", 100);
        java.addCriterion("Project", 50);
        subjects.add(java);

        Subject math = new Subject("Mathematics");
        math.addCriterion("Colloquium", 70);
        math.addCriterion("Exercises", 30);
        subjects.add(math);
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void addSubject(Subject subject) {
        subjects.add(subject);
    }

    public Subject editSubject(String name, String newName) {
        for (Subject subject : subjects) {
            if (subject.getName().equals(name)) {
                subject.setName(newName);
                return subject;
            }
        }
        return null;
    }

    public void deleteSubject(String name) {
        subjects.removeIf(subject -> subject.getName().equals(name));
    }
}
