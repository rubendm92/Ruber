package ruber.persistence;

import ruber.model.Professor;
import ruber.model.TeachingList;

public interface SignedTeachingsSaver {

    public void save(Professor professor, TeachingList teachings);
}
