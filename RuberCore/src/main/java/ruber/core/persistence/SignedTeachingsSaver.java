package ruber.core.persistence;

import ruber.core.model.Professor;
import ruber.core.model.TeachingList;

public interface SignedTeachingsSaver {

    public void save(Professor professor, TeachingList teachings);
}
