package ruber.viewmodels;

import ruber.model.Observable;

public class DniInput extends Observable {

    private static final int DNI_LENGTH = 8;
    private String input;

    public DniInput() {
        this.input = "";
    }

    public void type(char character) {
        input += character;
        if (input.length() == DNI_LENGTH)
            notifyChanges();
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
