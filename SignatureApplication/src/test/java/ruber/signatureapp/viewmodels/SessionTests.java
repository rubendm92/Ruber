package ruber.signatureapp.viewmodels;

import org.hamcrest.core.Is;
import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;
import ruber.core.model.ProfessorNotFoundException;
import ruber.signatureapp.fake.Professors;

import static org.hamcrest.core.Is.is;
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
        assertThat(session.getProfessor(), IsNull.nullValue());
        assertThat(dniInput.getInput(), Is.is(""));
    }

    private void initSessionForProfessor() {
        for (char character : "12312323".toCharArray())
            dniInput.type(character);
    }

    @Test
    public void sessionNotifiesWhenDniIsCompleted() {
        Listener listener = mock(Listener.class);
        session.setOnDniCompletedListener(listener);
        initSessionForProfessor();
        verify(listener).execute();
    }

    @Test
    public void sessionNotifiesWhenSessionIsClosed() {
        Listener listener = mock(Listener.class);
        session.setOnSessionClosedListener(listener);
        initSessionForProfessor();
        session.close();
        verify(listener).execute();
    }
}
