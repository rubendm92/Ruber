package ruber.core.viewmodels;

import ruber.core.model.Professor;
import ruber.core.model.ProfessorList;
import ruber.core.model.TeachingList;

public class RuberFrameViewModel {

    private final SessionViewModel session;
    private final SelectionViewModel selection;

    public RuberFrameViewModel(SessionViewModel session, SelectionViewModel selection) {
        this.session = session;
        this.selection = selection;
    }

    public void clear() {
        selection.clear();
    }

    public void showTeachings(TeachingList teachings) {
        selection.showTeachings(teachings);
    }

    public void showProfessors(ProfessorList professors) {
        selection.showProfessorsToReplace(professors);
    }

    public void showProfessorToReplace() {
    }

    public Professor getProfessorToReplace() {
        return selection.getProfessorToReplace();
    }

    public Professor getProfessorFromSession() {
        return session.getProfessor();
    }

    public TeachingList getSelectedTeachings() {
        return selection.getSelectedTeachings();
    }

    public byte[] getSignature() {
        return selection.getSignature();
    }

    public Professor getSelectedProfessor() {
        return (selection.getProfessorToReplace() == null ? session.getProfessor() : selection.getProfessorToReplace());
    }
}