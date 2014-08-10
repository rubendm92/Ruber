package ruber.viewmodels;

import org.junit.Before;
import org.junit.Test;
import ruber.model.Observer;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class DniInputTests {

    private DniInputViewModel dniInput;

    @Before
    public void setUp() {
        dniInput = new DniInputViewModel();
    }

    @Test
    public void whenANumberIsTypedItShouldBeShownOnTheDisplay() {
        dniInput.type('1');

        assertThat(dniInput.getInput(), is("1"));
    }

    @Test
    public void whenDeleteIsPressedItShouldRemoveLastCharacterFromDisplay() {
        dniInput.type('1');
        dniInput.type('2');
        dniInput.type('3');

        dniInput.delete();

        assertThat(dniInput.getInput(), is("12"));
    }

    @Test
    public void whenClearIsPressedItShouldRemoveAllFromDisplay() {
        dniInput.type('1');
        dniInput.type('2');
        dniInput.type('3');

        dniInput.clear();

        assertThat(dniInput.getInput(), is(""));
    }

    @Test
    public void whenDniIsCompletedItShouldNotifyToAnObserver() {
        Observer observer = mock(Observer.class);
        dniInput.addObserver(observer);

        dniInput.type('1');
        dniInput.type('2');
        dniInput.type('3');
        dniInput.type('1');
        dniInput.type('2');
        dniInput.type('3');
        dniInput.type('2');
        dniInput.type('3');

        verify(observer).changed();
    }
}
