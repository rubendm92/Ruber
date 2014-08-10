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

    public void delete() {
        input = input.substring(0, input.length() - 1);
    }

    public void clear() {
        input = "";
    }
}
