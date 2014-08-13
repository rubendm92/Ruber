package ruber.core.viewmodels;

import org.junit.Before;
import org.junit.Test;
import ruber.core.model.fake.Teachings;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TeachingTests {

    private TeachingViewModel teaching;

    @Before
    public void setUp() {
        teaching = new TeachingViewModel(Teachings.fso());
    }

    @Test
    public void byDefaultTeachingIsSelected() {
        assertTrue(teaching.isSelected());
    }

    @Test
    public void unselectTeaching() {
        teaching.unselect();
        assertFalse(teaching.isSelected());
    }
}
