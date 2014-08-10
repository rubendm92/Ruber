package ruber.viewmodels;

public class Professor extends SelectableItem {

    private final ruber.model.Professor professor;

    public Professor(ruber.model.Professor professor) {
        super();
        unselect();
        this.professor = professor;
    }

    public ruber.model.Professor getModel() {
        return professor;
    }
}
