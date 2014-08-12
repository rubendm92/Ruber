package ruber.viewmodels;

public class SelectableItemViewModel {

    private boolean isSelected;

    public SelectableItemViewModel() {
        this.isSelected = false;
    }

    public void select() {
        isSelected = true;
    }

    public void unselect() {
        isSelected = false;
    }

    public boolean isSelected() {
        return isSelected;
    }
}
