package ruber.viewmodels;

import java.util.stream.Collectors;

public class Selection {

    private TeachingList teachings;

    public void setTeachings(TeachingList teachings) {
        this.teachings = teachings;
    }

    public TeachingList getTeachings() {
        return teachings;
    }

    public TeachingList getSelectedTeachings() {
        return teachings.stream().filter(teaching -> teaching.isSelected()).collect(Collectors.toCollection(() -> new TeachingList()));
    }
}
