package ruber.commands;

import ruber.view.Command;
import ruber.viewmodels.Frame;

public class CloseSessionCommand implements Command {

    private final Frame frame;

    public CloseSessionCommand(Frame frame) {
        this.frame = frame;
    }

    @Override
    public void execute() {
        frame.clear();
    }
}
