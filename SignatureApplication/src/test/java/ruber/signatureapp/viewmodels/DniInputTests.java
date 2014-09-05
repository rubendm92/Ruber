package ruber.signatureapp.viewmodels;

import org.junit.Before;
import org.junit.Test;
import ruber.core.model.Observer;
import ruber.signatureapp.viewmodels.session.DniInputViewModel;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class DniInputTests {

    private DniInputViewModel dniInput;

    @Before
    public void setUp() {
        dniInput = new DniInputViewModel();
    }

    @Test
    public void whenANumberIsTypedItShouldNotifyToAnObserverAndBeAbleToGetInput() {
        Observer observer = mock(Observer.class);
        dniInput.addObserver(observer);

        dniInput.type('1');

        verify(observer).changed();
        assertThat(dniInput.getInput(), is("1"));
    }

    @Test
    public void whenDeleteIsPressedItShouldRemoveLastCharacterFromDisplay() {
        for (char character : "123".toCharArray())
            dniInput.type(character);

        dniInput.delete();

        assertThat(dniInput.getInput(), is("12"));
    }

    @Test
    public void whenClearIsPressedItShouldRemoveAllFromDisplay() {
        for (char character : "123".toCharArray())
            dniInput.type(character);

        dniInput.clear();

        assertThat(dniInput.getInput(), is(""));
    }

    @Test
    public void whenDniHas8CharactersItIsCompleted() {
        for (char character : "12312323".toCharArray())
            dniInput.type(character);

        assertTrue(dniInput.isCompleted());
    }
}
