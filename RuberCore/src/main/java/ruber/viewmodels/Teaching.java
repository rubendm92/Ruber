package ruber.viewmodels;

public class Teaching extends SelectableItem {

    private final ruber.model.Teaching teaching;

    public Teaching(ruber.model.Teaching teaching) {
        super();
        select();
        this.teaching = teaching;
    }

    public ruber.model.Teaching getModel() {
        return teaching;
    }
}
