package ruber.core.viewmodels;

import org.junit.Before;
import org.junit.Test;
import ruber.core.model.fake.Professors;
import ruber.core.model.fake.Teachings;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class RuberFrameViewModelTests {

    private SessionViewModel session;
    private SelectionViewModel selection;
    private RuberFrameViewModel frame;

    @Before
    public void setUp() {
        session = mock(SessionViewModel.class);
        selection = mock(SelectionViewModel.class);
        frame = new RuberFrameViewModel(session, selection);
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

    @Test
    public void callingMethodShowProfessorsShouldCallToMethodInSelection() {
        frame.showProfessors(Professors.list());

        verify(selection).showProfessorsToReplace(Professors.list());
    }
}
