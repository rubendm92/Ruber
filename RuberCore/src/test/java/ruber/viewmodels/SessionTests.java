package ruber.viewmodels;

import org.junit.Test;
import ruber.model.fake.Professors;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SessionTests {

    @Test
    public void whenDniIsCompletedSessionShouldBeStartedForProfessorWithThatDni() {
        DniInputViewModel dniInput = new DniInputViewModel();
        SessionViewModel session = new SessionViewModel(dniInput, Professors.list());

        dniInput.type('1');
        dniInput.type('2');
        dniInput.type('3');
        dniInput.type('1');
        dniInput.type('2');
        dniInput.type('3');
        dniInput.type('2');
        dniInput.type('3');

        assertThat(session.getProfessor(), is(Professors.ruben()));
    }
}
