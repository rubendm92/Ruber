package ruber.persistence;

import ruber.model.TeachingList;

import java.time.LocalDate;

public interface TeachingsLoader {

    public TeachingList load(LocalDate day);
}
