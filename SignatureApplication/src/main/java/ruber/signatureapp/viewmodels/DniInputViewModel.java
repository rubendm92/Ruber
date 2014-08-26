package ruber.signatureapp.viewmodels;

import ruber.core.model.Observable;

public class DniInputViewModel extends Observable {

    private static final int DNI_LENGTH = 8;
    private String input;

    public DniInputViewModel() {
        this.input = "";
    }

    public void type(char character) {
        input += character;
        notifyChanges();
    }

    public String getInput() {
        return input;
    }

    public void delete() {
        input = input.substring(0, input.length() - 1);
        notifyChanges();
    }

    public void clear() {
        input = "";
    }

    public boolean isCompleted() {
        return DNI_LENGTH == input.length();
    }
}
