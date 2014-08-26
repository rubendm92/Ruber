package ruber.signatureapp.viewmodels;

import org.junit.Before;
import org.junit.Test;
import ruber.signatureapp.fake.Professors;
import ruber.signatureapp.fake.Teachings;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class RuberFrameViewModelTests {

    private HeaderViewModel header;
    private SessionViewModel session;
    private SelectionViewModel selection;
    private RuberFrameViewModel frame;

    @Before
    public void setUp() {
        header = mock(HeaderViewModel.class);
        session = mock(SessionViewModel.class);
        selection = mock(SelectionViewModel.class);
        frame = new RuberFrameViewModel(header, session, selection);
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
