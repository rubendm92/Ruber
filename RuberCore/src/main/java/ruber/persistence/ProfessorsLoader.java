package ruber.persistence;

import ruber.model.Professor;

import java.util.List;

public interface ProfessorsLoader {

    public List<Professor> load();
}
