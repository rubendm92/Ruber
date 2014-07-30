package ruber.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Teaching {

    private final Subject subject;
    private final Schedule schedule;
    private final String group;
    private final Map<Professor, Signature> signatures;

    public Teaching(Subject subject, Schedule schedule, String group) {
        this.subject = subject;
        this.schedule = schedule;
        this.group = group;
        this.signatures = new LinkedHashMap<>();
    }

    public String getSubjectName() {
        return subject.getName();
    }

    public String getDegree() {
        return subject.getDegree();
    }

    public String getGroup() {
        return group;
    }

    public String getClassroom() {
        return schedule.getClassroom();
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public List<Professor> getProfessors() {
        return new ArrayList<>(signatures.keySet());
    }

    public void sign(Professor professor, Signature signature) {
        signatures.replace(professor, signature);
    }

    public Map<Professor, Signature> getSignatures() {
        return signatures;
    }

    public boolean isGivenBy(Professor professor) {
        return signatures.keySet().contains(professor);
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Teaching teaching = (Teaching) object;
        return ((group.equals(teaching.group)) && (schedule.equals(teaching.schedule)) && (subject.equals(teaching.subject)));
    }

    @Override
    public int hashCode() {
        int result = subject.hashCode();
        result = 31 * result + schedule.hashCode();
        result = 31 * result + group.hashCode();
        return result;
    }

    public void addProfessor(Professor professor) {
        signatures.put(professor, null);
    }
}
