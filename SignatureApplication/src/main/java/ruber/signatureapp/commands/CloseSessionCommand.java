package ruber.signatureapp.commands;

import ruber.signatureapp.viewmodels.FrameViewModel;
import ruber.signatureapp.views.Command;

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
