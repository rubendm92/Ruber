package ruber.viewmodels;

import org.junit.Before;
import org.junit.Test;
import ruber.model.ProfessorNotFoundException;
import ruber.model.fake.Professors;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

public class SessionTests {

    private DniInput dniInput;
    private Session session;

    @Before
    public void setUp() {
        dniInput = new DniInput();
        session = new Session(dniInput, Professors.list());
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
}