package ruber.signatureapp.viewmodels;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ruber.core.model.Observer;

public class DniInputTests {

    private DniInputViewModel dniInput;

    @Before
    public void setUp() {
        dniInput = new DniInputViewModel();
    }

    @Test
    public void whenANumberIsTypedItShouldBeShownOnTheDisplay() {
        dniInput.type('1');

        Assert.assertThat(dniInput.getInput(), Is.is("1"));
    }

    @Test
    public void whenDeleteIsPressedItShouldRemoveLastCharacterFromDisplay() {
        for (char character : "123".toCharArray())
            dniInput.type(character);

        dniInput.delete();

        Assert.assertThat(dniInput.getInput(), Is.is("12"));
    }

    @Test
    public void whenClearIsPressedItShouldRemoveAllFromDisplay() {
        for (char character : "123".toCharArray())
            dniInput.type(character);

        dniInput.clear();

        Assert.assertThat(dniInput.getInput(), Is.is(""));
    }

    @Test
    public void whenDniIsCompletedItShouldNotifyToAnObserver() {
        Observer observer = Mockito.mock(Observer.class);
        dniInput.addObserver(observer);

        for (char character : "12312323".toCharArray())
            dniInput.type(character);

        Mockito.verify(observer).changed();
    }
}
