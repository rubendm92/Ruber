package ruber.signatureapp.viewmodels.utils;

import ruber.core.model.Observable;

public class SelectableItemViewModel extends Observable {

    private boolean isSelected;

    public SelectableItemViewModel() {
        this.isSelected = false;
    }

    public void select() {
        isSelected = true;
        notifyChanges();
    }

    public void unselect() {
        isSelected = false;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void toggle() {
        isSelected = !isSelected;
        notifyChanges();
    }
}
