package ruber.signatureapp.viewmodels.teaching;

import ruber.core.model.Teaching;
import ruber.signatureapp.viewmodels.utils.SelectableItemViewModel;

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

    public String getFormattedSubjectName() {
        return teaching.getFormattedSubjectName();
    }

    public String getStringSchedule() {
        return teaching.getStringSchedule();
    }

    public String getClassroom() {
        return teaching.getClassroom();
    }

    public String getGroup() {
        return teaching.getGroup();
    }
}
