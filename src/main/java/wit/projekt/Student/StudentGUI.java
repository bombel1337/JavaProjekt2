package wit.projekt.Student;

import wit.projekt.Frame.PaneController;

public class StudentGUI extends PaneController {

    private StudentRegistry studentRegistry = new StudentRegistry();

    public StudentGUI(String name) {
        super(name, new String[]{"Imię", "Nazwisko", "Numer albumu"});
    }

}
