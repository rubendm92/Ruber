package ruber.signatureapp.viewmodels;

import ruber.core.model.Professor;
import ruber.core.model.ProfessorList;
import ruber.core.model.TeachingList;

import java.util.stream.Collectors;

public class SelectionViewModel {

    private TeachingListViewModel teachings;
    private ProfessorListViewModel professorsToReplace;
    private byte[] signature;

    public void showTeachings(TeachingList teachings) {
        teachings.forEach(teaching -> this.teachings.add(new TeachingViewModel(teaching)));
    }

    public TeachingList getSelectedTeachings() {
        return teachings.stream().filter(TeachingViewModel::isSelected).map(TeachingViewModel::getModel).collect(Collectors.toCollection(ruber.core.model.TeachingList::new));
    }

    public void showProfessorsToReplace(ProfessorList professors) {
        professors.forEach(professor -> this.professorsToReplace.add(new ProfessorViewModel(professor)));
    }

    public Professor getProfessorToReplace() {
        return professorsToReplace.stream().filter(ProfessorViewModel::isSelected).map(ProfessorViewModel::getModel).findFirst().get();
    }

    public void clear() {
        teachings.clear();
        professorsToReplace.clear();
    }

    public byte[] getSignature() {
        return signature;
    }
}
