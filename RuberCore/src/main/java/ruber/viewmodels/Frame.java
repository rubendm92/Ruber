package ruber.viewmodels;

import ruber.model.Professor;
import ruber.model.ProfessorList;
import ruber.model.TeachingList;

public class Frame {

    private final Session session;
    private final Selection selection;
    private String dni;
    private Professor professorToReplace;
    private Professor professor;
    private byte[] signature;

    public Frame(Session session, Selection selection) {
        this.session = session;
        this.selection = selection;
    }

    public void clear() {
    }

    public void initSession(Professor professor) {
    }

    public void showTeachings(TeachingList teachings) {
    }

    public void showProfessors(ProfessorList professors) {
    }

    public void showProfessorToReplace() {
    }

    public Professor getProfessorToReplace() {
        return professorToReplace;
    }

    public Professor getProfessorFromSession() {
        return session.getProfessor();
    }

    public TeachingList getSelectedTeachings() {
        return selection.getSelectedTeachings();
    }

    public byte[] getSignature() {
        return signature;
    }

    public Professor getSelectedProfessor() {
        return selection.getSelectedProfessor();
    }
}
