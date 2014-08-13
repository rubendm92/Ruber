package ruber.core.persistence;

import ruber.core.model.TeachingList;

import java.time.LocalDate;

public interface TeachingsLoader {

    public TeachingList load(LocalDate day);
}
