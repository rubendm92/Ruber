package ruber.core.viewmodels;

import org.junit.Before;
import org.junit.Test;
import ruber.core.listeners.OnDniCompletedListener;
import ruber.core.model.ProfessorNotFoundException;
import ruber.core.model.fake.Professors;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SessionTests {

    private DniInputViewModel dniInput;
    private SessionViewModel session;

    @Before
    public void setUp() {
        dniInput = new DniInputViewModel();
        session = new SessionViewModel(dniInput, Professors.list());
    }

    @Test
    public void whenDniIsCompletedSessionShouldBeStartedForProfessorWithThatDni() {
        initSessionForProfessor();
        assertThat(session.getProfessor(), is(Professors.ruben()));
    }

    @Test(expected = ProfessorNotFoundException.class)
    public void whenThereIsNoProfessorForDniItShouldThrowAnException() {
        for (char character : "12312322".toCharArray())
            dniInput.type(character);
    }

    @Test
    public void whenCloseSessionIsPressedProfessorAndDniShouldBeRemoved() {
        initSessionForProfessor();
        session.close();
        assertThat(session.getProfessor(), nullValue());
        assertThat(dniInput.getInput(), is(""));
    }

    private void initSessionForProfessor() {
        for (char character : "12312323".toCharArray())
            dniInput.type(character);
    }

    @Test
    public void sessionNofityWhenDniIsCompleted() {
        OnDniCompletedListener listener = mock(OnDniCompletedListener.class);
        session.addOnDniCompletedListener(listener);
        initSessionForProfessor();
        verify(listener).onDniCompleted();
    }
}
