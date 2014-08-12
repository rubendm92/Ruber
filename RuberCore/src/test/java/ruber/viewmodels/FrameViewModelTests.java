package ruber.viewmodels;

import org.junit.Before;
import org.junit.Test;
import ruber.model.fake.Teachings;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class FrameViewModelTests {

    private SessionViewModel session;
    private SelectionViewModel selection;
    private FrameViewModel frame;

    @Before
    public void setUp() {
        session = mock(SessionViewModel.class);
        selection = mock(SelectionViewModel.class);
        frame = new FrameViewModel(session, selection);
    }

    @Test
    public void callingMethodClearShouldClearContentFromSelection() {
        frame.clear();

        verify(selection).clear();
    }

    @Test
    public void callingMethodShowTeachingsShouldShowTeachingsInSelection() {
        frame.showTeachings(Teachings.longList());

        verify(selection).showTeachings(Teachings.longList());
    }
}
