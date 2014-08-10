package ruber.model;

import ruber.viewmodels.ProfessorNotFoundException;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class ProfessorList extends ArrayList<Professor> {

    public Professor getByDni(String dni) {
        try {
            return stream().filter(professor -> professor.getDni().equals(dni)).findFirst().get();
        } catch (NoSuchElementException ex) {
            throw new ProfessorNotFoundException();
        }
    }
}
