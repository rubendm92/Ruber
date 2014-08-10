package ruber.viewmodels;

public class DniInputViewModel {

    private String input;

    public DniInputViewModel() {
        this.input = "";
    }

    public void type(char character) {
        input += character;
    }

    public String getInput() {
        return input;
    }
}
