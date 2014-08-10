package ruber.viewmodels;

public class SelectableItem {

    private boolean isSelected;

    public SelectableItem() {
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
