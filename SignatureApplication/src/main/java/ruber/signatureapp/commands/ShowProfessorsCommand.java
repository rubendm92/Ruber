package ruber.signatureapp.commands;

import ruber.core.model.TeachingList;
import ruber.signatureapp.viewmodels.FrameViewModel;
import ruber.signatureapp.views.Command;

import java.time.LocalTime;

public class ShowProfessorsCommand implements Command {

    private final FrameViewModel frame;
    private final TeachingList teachings;

    public ShowProfessorsCommand(FrameViewModel frame, TeachingList teachings) {
        this.frame = frame;
        this.teachings = teachings;
    }

    @Override
    public void execute() {
        frame.showProfessors(teachings.getProfessorsWithTeachingsForTime(LocalTime.now()));
    }
}
