package wit.projekt.Subject;

import wit.projekt.Database.Database;

import java.util.ArrayList;
import java.util.List;

public class SubjectRegistry {
    List<Subject> subjects = new ArrayList<>();

    // TODO: add subjects from database
    public SubjectRegistry(List<String> data) {
        if (data.size() > 0) {
            for (int i = 0; i < data.size(); i += 4) {
                subjects.add(new Subject(data.get(i), Integer.parseInt(data.get(i + 1)), Integer.parseInt(data.get(i + 2)), Integer.parseInt(data.get(i + 3))));
            }
        }

        //Subject java = new Subject("Java Language", 50, 30, 10);
        //subjects.add(java);

        //Subject math = new Subject("Mathematics", 40, 30, 20);
        //subjects.add(math);
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void addSubject(Subject subject) {
        subjects.add(subject);
    }

    public Subject editSubject(String name, String newName, int newThirdGrade, int newFourthGrade, int newFithGrade) {
        for (Subject subject : subjects) {
            if (subject.getName().equals(name)) {
                subject.setName(newName);
                subject.editCriterion("Third", newThirdGrade);
                subject.editCriterion("Fourth", newThirdGrade + newFourthGrade + newFourthGrade);
                subject.editCriterion("Fith", newThirdGrade + newFourthGrade + newFithGrade);
                return subject;
            }
        }
        return null;
    }

    public void deleteSubject(String name) {
        subjects.removeIf(subject -> subject.getName().equals(name));
    }

    public void saveDataToDB() {
        List<String> data = new ArrayList<>();
        for (Subject subject : subjects) {
            data.add(subject.getName());
            data.add(subject.getGrade("Third").toString());
            data.add(subject.getGrade("Fourth").toString());
            data.add(subject.getGrade("Fith").toString());
        }
        Database.save("subjects", data);
    }

    public Subject getSubjectByName(String name) {
        for (Subject subject : subjects) {
            if (subject.getName().equals(name)) {
                return subject;
            }
        }
        return null;
    }
}

