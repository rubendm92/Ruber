package ruber.core.persistence;

import ruber.core.model.Professor;
import ruber.core.model.ProfessorList;

import java.util.List;

public interface ProfessorsLoader {

    public ProfessorList load();
}
