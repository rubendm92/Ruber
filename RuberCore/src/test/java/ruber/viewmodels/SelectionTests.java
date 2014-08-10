package ruber.viewmodels;

import org.junit.Test;
import ruber.model.fake.Teachings;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SelectionTests {

    @Test
    public void getSelectedTeachings() {
        Selection selection = new Selection();
        final Teaching fso = new Teaching(Teachings.fso());
        final Teaching is2 = new Teaching(Teachings.is2());
        selection.setTeachings(teachings(fso, is2));
        is2.unselect();
        assertThat(selection.getSelectedTeachings(), is(fsoList(fso)));
    }

    private TeachingList teachings(Teaching fso, Teaching is2) {
        TeachingList teachings = new TeachingList();
        teachings.add(fso);
        teachings.add(is2);
        return teachings;
    }

    private TeachingList fsoList(Teaching fso) {
        final TeachingList teachings = new TeachingList();
        teachings.add(fso);
        return teachings;
    }
}
