package ruber.core.model;

import java.time.LocalTime;
import java.util.*;

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

    public String getStringSchedule() {
        return schedule.toString();
    }

    public List<Professor> getProfessors() {
        return new ArrayList<>(signatures.keySet());
    }

    public void sign(Professor professor, Signature signature) {
        signatures.replace(professor, signature);
    }

    public boolean isGivenBy(Professor professor) {
        return signatures.keySet().contains(professor);
    }

    public void addProfessor(Professor professor) {
        signatures.put(professor, null);
    }

    public String getFormattedSubjectName() {
        return subject.getFormattedName();
    }

    public boolean canBeSigned(LocalTime time) {
        return schedule.canBeSigned(time);
    }

    public byte[] getSignatureBytes(Professor professor) {
        return signatures.get(professor).getSignature();
    }

    public List<Professor> getProfessorsThatHaveNotSignedYet() {
        ProfessorList professors = new ProfessorList();
        signatures.entrySet().stream().filter(set -> set.getValue() == null).forEach(set -> professors.add(set.getKey()));
        return professors;
    }

    public Professor getProfessorForWhomSigned(Professor professor) {
        return (teachingWasSignedBy(professor) ? getProfessor(getFirstEntry(professor)) : null);
    }

    private Professor getProfessor(Optional<Map.Entry<Professor, Signature>> entry) {
        return entry.get().getKey();
    }

    private boolean teachingWasSignedBy(Professor professor) {
        return getFirstEntry(professor).isPresent();
    }

    private Optional<Map.Entry<Professor, Signature>> getFirstEntry(Professor professor) {
        return signatures.entrySet().stream().filter(set -> set.getValue() != null && set.getValue().getProfessor().equals(professor)).findFirst();
    }

    @Override
    public boolean equals(Object object) {
        return isTeaching(object) && teachingsAreEquals((Teaching) object);
    }

    private boolean isTeaching(Object object) {
        return (object != null && getClass() == object.getClass());
    }

    private boolean teachingsAreEquals(Teaching teaching) {
        return ((group.equals(teaching.group)) && (schedule.equals(teaching.schedule)) && (subject.equals(teaching.subject)));
    }

    @Override
    public int hashCode() {
        int result = subject.hashCode();
        result = 31 * (31 * result + group.hashCode()) + schedule.hashCode();
        return result;
    }

    public boolean isSignedFor(Professor professor) {
        return signatures.get(professor) != null;
    }

    public Signature getProfessorSignature(Professor professor) {
        return signatures.get(professor);
    }

    public Map<Professor, Signature> getSignatures() {
        return signatures;
    }
}
