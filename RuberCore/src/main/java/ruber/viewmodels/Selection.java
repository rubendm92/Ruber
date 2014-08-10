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

    public ruber.model.TeachingList getSelectedTeachings() {
        return teachings.stream().filter(Teaching::isSelected).map(Teaching::getModel).collect(Collectors.toCollection(ruber.model.TeachingList::new));
    }

    public void showProfessorsToReplace(ProfessorList professors) {
        this.professorsToReplace = professors;
    }

    public ruber.model.Professor getSelectedProfessor() {
        return professorsToReplace.stream().filter(Professor::isSelected).map(Professor::getModel).findFirst().get();
    }
}
