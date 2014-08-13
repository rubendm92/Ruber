package ruber.core.persistence;

import ruber.core.model.Professor;

import java.util.List;

public interface ProfessorsLoader {

    public List<Professor> load();
}
