package ruber.signatureapp.viewmodels.session;

import ruber.core.model.Observable;

public class DniInputViewModel extends Observable {

    private static final int DNI_LENGTH = 8;
    private String input;

    public DniInputViewModel() {
        this.input = "DNI";
    }

    public void type(char character) {
        if (isCompleted())
            return;
        if (input.equals("DNI"))
            input = "";
        input += character;
        notifyChanges();
    }

    public String getInput() {
        return input;
    }

    public void delete() {
        if (isEmpty())
            return;
        input = input.substring(0, input.length() - 1);
        if (input.equals(""))
            input = "DNI";
        notifyChanges();
    }

    public void clear() {
        input = "DNI";
        notifyChanges();
    }

    public boolean isCompleted() {
        return DNI_LENGTH == input.length();
    }

    public boolean isEmpty() {
        return input.equals("DNI");
    }
}
