package ruber.viewmodels;

public class Teaching {

    private final ruber.model.Teaching teaching;
    private boolean isSelected;

    public Teaching(ruber.model.Teaching teaching) {
        this.teaching = teaching;
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
