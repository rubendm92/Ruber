package ruber.core.commands;

import ruber.core.view.Command;
import ruber.core.viewmodels.FrameViewModel;

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
