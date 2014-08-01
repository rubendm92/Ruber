package ruber.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.stream.Stream;

public class TeachingList extends ArrayList<Teaching> {

    public TeachingList getTeachingsForProfessor(Professor professor) {
        TeachingList list = new TeachingList();
        stream().filter((teaching) -> teaching.isGivenBy(professor)).forEach((teaching) -> list.add(teaching));
        return list;
    }

    public ProfessorList getProfessorsWithTeachingsForTime(LocalTime time) {
        ProfessorList professors = new ProfessorList();
        teachingsThatCanBeSigned(time).forEach((teaching) -> professors.addAll(teaching.getProfessors()));
        return professors;
    }

    private Stream<Teaching> teachingsThatCanBeSigned(LocalTime time) {
        return stream().filter((teaching) -> teaching.canBeSigned(time));
    }
}
