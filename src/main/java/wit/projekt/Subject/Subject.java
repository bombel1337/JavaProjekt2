package wit.projekt.Subject;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    String name;
    Integer thirdGrade;
    Integer fourthGrade;
    Integer fithGrade;

    public Subject(String name, Integer thirdGrade, Integer fourthGrade, Integer fithGrade) {
        this.name = name;
        this.thirdGrade = thirdGrade;
        this.fourthGrade = fourthGrade;
        this.fithGrade = fithGrade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void editCriterion(String grade, int newMaxPoints) {
        switch (grade) {
            case "Third":
                thirdGrade = newMaxPoints;
                break;
            case "Fourth":
                fourthGrade = newMaxPoints;
                break;
            case "Fith":
                fithGrade = newMaxPoints;
                break;
        }
    }

    public void removeCriterion(String grade) {
        switch (grade) {
            case "Third":
                thirdGrade = 0;
                break;
            case "Fourth":
                fourthGrade = 0;
                break;
            case "Fith":
                fithGrade = 0;
                break;
        }
    }

    public ArrayList<String> getFields() {
        ArrayList<String> fields = new ArrayList<>();
        fields.add(name);
        fields.add(thirdGrade.toString());
        fields.add(fourthGrade.toString());
        fields.add(fithGrade.toString());
        return fields;
    }

    public int getThirdGrade() {
        return thirdGrade;
    }

    public int getFourthGrade() {
        return fourthGrade;
    }

    public int getFifthGrade() {
        return fithGrade;
    }
}

class SubjectRegistry {
    List<Subject> subjects = new ArrayList<>();

    // TODO: add subjects from database
    public SubjectRegistry() {
        Subject java = new Subject("Java Language", 50, 30, 10);
        subjects.add(java);

        Subject math = new Subject("Mathematics", 40, 30, 20);
        subjects.add(math);
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
                subject.editCriterion("Fourth", newFourthGrade);
                subject.editCriterion("Fith", newFithGrade);
                return subject;
            }
        }
        return null;
    }

    public void deleteSubject(String name) {
        subjects.removeIf(subject -> subject.getName().equals(name));
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