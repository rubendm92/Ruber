package ruber.viewmodels;

public class ProfessorViewModel extends SelectableItemViewModel {

    private final ruber.model.Professor professor;

    public ProfessorViewModel(ruber.model.Professor professor) {
        super();
        unselect();
        this.professor = professor;
    }

    public ruber.model.Professor getModel() {
        return professor;
    }
}
