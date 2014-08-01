package ruber.model;

import java.time.LocalTime;
import java.util.ArrayList;

public class TeachingList extends ArrayList<Teaching> {

    public TeachingList getTeachingsForProfessor(Professor professor) {
        TeachingList list = new TeachingList();
        stream().filter((teaching) -> teaching.isGivenBy(professor)).forEach((teaching) -> list.add(teaching));
        return list;
    }

    public ProfessorList getProfessorsWithTeachingsForTime(LocalTime time) {
        ProfessorList professors = new ProfessorList();
        return professors;
    }
}
