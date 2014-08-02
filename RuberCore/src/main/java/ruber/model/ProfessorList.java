package ruber.model;

import java.util.ArrayList;

public class ProfessorList extends ArrayList<Professor> {

    public Professor getByDni(String dni) {
        for (Professor professor : this) {
            if (dni.equals(professor.getDni()))
                return professor;
        }
        return null;
    }
}
