package ruber.viewmodels;

public class SelectableItem {

    protected boolean isSelected;

    public SelectableItem() {
        this.isSelected = true;
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
