package ruber.commands;

import ruber.view.Command;
import ruber.viewmodels.FrameViewModel;

public class ClearFrameCommand implements Command {

    private final FrameViewModel frame;

    public ClearFrameCommand(FrameViewModel frame) {
        this.frame = frame;
    }

    @Override
    public void execute() {
        frame.clear();
    }
}
