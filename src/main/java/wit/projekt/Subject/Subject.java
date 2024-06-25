package wit.projekt.Subject;

import java.util.ArrayList;

public class Subject {
    private String code;
    private String name;

    public Subject(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Object> getFields() {
        ArrayList<Object> fields = new ArrayList<>();
        fields.add(code);
        fields.add(name);
        return fields;
    }
}
