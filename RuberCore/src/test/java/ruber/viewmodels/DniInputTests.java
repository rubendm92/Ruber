package ruber.viewmodels;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DniInputTests {

    @Test
    public void whenANumberIsTypedItShouldBeShownOnTheDisplay() {
        DniInputViewModel dniInput = new DniInputViewModel();

        dniInput.type('1');

        assertThat(dniInput.getInput(), is("1"));
    }

    @Test
    public void whenDeleteIsPressedItShouldRemoveLastCharacterFromDisplay() {
        DniInputViewModel dniInput = new DniInputViewModel();
        dniInput.type('1');
        dniInput.type('2');
        dniInput.type('3');

        dniInput.delete();

        assertThat(dniInput.getInput(), is("12"));
    }
}
