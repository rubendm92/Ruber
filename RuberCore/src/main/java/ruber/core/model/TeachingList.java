package ruber.core.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

public class TeachingList extends ArrayList<Teaching> {

    public TeachingList getCurrentTeachingsForProfessor(Professor professor) {
        TeachingList list = new TeachingList();
        stream().filter(teaching -> teaching.isGivenBy(professor)).forEach(list::add);
        return list;
    }

    public TeachingList getAllTeachingsForProfessor(Professor professor) {
        TeachingList list = new TeachingList();
        stream().filter(teaching -> teaching.isGivenBy(professor)).forEach(list::add);
        return list;
    }

    public ProfessorList getProfessorsWithTeachingsForTime(LocalTime time) {
        ProfessorList professors = new ProfessorList();
        forEach((teaching) -> addProfessors(professors, teaching));
        professors.sort((professor1, professor2) -> professor1.getName().compareTo(professor2.getName()));
        return professors;
    }

    private void addProfessors(ProfessorList professors, Teaching teaching) {
        for (Professor professor : teaching.getProfessorsThatHaveNotSignedYet()) {
            if (professors.contains(professor)) continue;
            professors.add(professor);
        }
    }

    public Map<String, TeachingList> getByDegree() {
        final Map<String, TeachingList> map = new LinkedHashMap<>();
        for (Teaching teaching : this) {
            if (map.get(teaching.getDegree()) == null)
                map.put(teaching.getDegree(), new TeachingList());
            map.get(teaching.getDegree()).add(teaching);
        }
        return map;
    }
}
