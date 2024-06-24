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

    public Integer getGrade(String grade) {
        switch (grade) {
            case "Third":
                return thirdGrade;
            case "Fourth":
                return fourthGrade;
            case "Fith":
                return fithGrade;
            default:
                return 0;
        }
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

