package wit.projekt.Subject;

import java.util.ArrayList;
import java.util.List;

public class StudentPointsRegistry {
    private List<StudentPoints> studentPointsList = new ArrayList<>();

    public void addStudentPoints(StudentPoints studentPoints) {
        studentPointsList.add(studentPoints);
    }

    public void deleteStudentPoints(String studentAlbumNumber, String subjectName) {
        studentPointsList.removeIf(points -> points.getStudentAlbumNumber().equals(studentAlbumNumber) && points.getSubjectName().equals(subjectName));
    }

    public StudentPoints getStudentPoints(String studentAlbumNumber, String subjectName) {
        for (StudentPoints points : studentPointsList) {
            if (points.getStudentAlbumNumber().equals(studentAlbumNumber) && points.getSubjectName().equals(subjectName)) {
                return points;
            }
        }
        return null;
    }

    public List<StudentPoints> getAllStudentPoints() {
        return studentPointsList;
    }
}
