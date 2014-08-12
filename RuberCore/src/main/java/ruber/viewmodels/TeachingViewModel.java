package ruber.viewmodels;

import ruber.model.Teaching;

public class TeachingViewModel extends SelectableItemViewModel {

    private final Teaching teaching;

    public TeachingViewModel(Teaching teaching) {
        super();
        select();
        this.teaching = teaching;
    }

    public Teaching getModel() {
        return teaching;
    }
}
