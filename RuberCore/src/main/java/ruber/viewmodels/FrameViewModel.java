package ruber.viewmodels;

import ruber.model.Professor;
import ruber.model.ProfessorList;
import ruber.model.TeachingList;

public class FrameViewModel {

    private String dni;
    private Professor professorToReplace;
    private Professor professor;
    private TeachingList selectedTeachings;
    private byte[] signature;
    private Professor professorSelected;

    public void clear() {
    }

    public void initSession(Professor professor) {
    }

    public void showTeachings(TeachingList teachings) {
    }

    public String getDni() {
        return dni;
    }

    public void showProfessors(ProfessorList professors) {
    }

    public void showProfessorToReplace() {
    }

    public Professor getProfessorToReplace() {
        return professorToReplace;
    }

    public Professor getProfessorFromSession() {
        return professor;
    }

    public TeachingList getSelectedTeachings() {
        return selectedTeachings;
    }

    public byte[] getSignature() {
        return signature;
    }

    public Professor getProfessorSelected() {
        return professorSelected;
    }
}
