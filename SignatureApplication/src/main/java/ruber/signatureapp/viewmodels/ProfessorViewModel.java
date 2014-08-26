package ruber.signatureapp.viewmodels;

public class ProfessorViewModel extends SelectableItemViewModel {

    private final ruber.core.model.Professor professor;

    public ProfessorViewModel(ruber.core.model.Professor professor) {
        super();
        unselect();
        this.professor = professor;
    }

    public ruber.core.model.Professor getModel() {
        return professor;
    }
}
