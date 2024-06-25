package wit.projekt.Subject;

public class StudentPoints {
    private String studentAlbumNumber;
    private String subjectName;
    private int thirdGradePoints;
    private int fourthGradePoints;
    private int fifthGradePoints;

    public StudentPoints(String studentAlbumNumber, String subjectName, int thirdGradePoints, int fourthGradePoints, int fifthGradePoints) {
        this.studentAlbumNumber = studentAlbumNumber;
        this.subjectName = subjectName;
        this.thirdGradePoints = thirdGradePoints;
        this.fourthGradePoints = fourthGradePoints;
        this.fifthGradePoints = fifthGradePoints;
    }

    public String getStudentAlbumNumber() {
        return studentAlbumNumber;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public int getThirdGradePoints() {
        return thirdGradePoints;
    }

    public void setThirdGradePoints(int points) {
        this.thirdGradePoints = points;
    }

    public int getFourthGradePoints() {
        return fourthGradePoints;
    }

    public void setFourthGradePoints(int points) {
        this.fourthGradePoints = points;
    }

    public int getFifthGradePoints() {
        return fifthGradePoints;
    }

    public void setFifthGradePoints(int points) {
        this.fifthGradePoints = points;
    }
}
