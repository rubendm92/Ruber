package ruber.viewmodels;

import java.util.stream.Collectors;

public class Selection {

    private TeachingList teachings;
    private ProfessorList professorsToReplace;

    public void showTeachings(TeachingList teachings) {
        this.teachings = teachings;
    }

    public TeachingList getTeachings() {
        return teachings;
    }

    public TeachingList getSelectedTeachings() {
        return teachings.stream().filter(Teaching::isSelected).collect(Collectors.toCollection(() -> new TeachingList()));
    }

    public void showProfessorsToReplace(ProfessorList professors) {
        this.professorsToReplace = professors;
    }

    public Professor getSelectedProfessor() {
        return professorsToReplace.stream().filter(Professor::isSelected).findFirst().get();
    }
}
