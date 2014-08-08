package ruber.commands;

import ruber.view.Command;
import ruber.viewmodels.FrameViewModel;

public class CloseSessionCommand implements Command {

    private final FrameViewModel frame;

    public CloseSessionCommand(FrameViewModel frame) {
        this.frame = frame;
    }

    @Override
    public void execute() {
        frame.clear();
    }
}
