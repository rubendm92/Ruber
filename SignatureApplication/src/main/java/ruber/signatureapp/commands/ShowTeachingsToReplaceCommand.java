package ruber.signatureapp.commands;

import ruber.core.model.TeachingList;
import ruber.signatureapp.viewmodels.FrameViewModel;
import ruber.signatureapp.views.Command;

public class ShowTeachingsToReplaceCommand implements Command {

    private final FrameViewModel frame;
    private final TeachingList teachings;

    public ShowTeachingsToReplaceCommand(FrameViewModel frame, TeachingList teachings) {
        this.frame = frame;
        this.teachings = teachings;
    }

    @Override
    public void execute() {
        frame.showProfessorToReplace();
        frame.showTeachings(teachings.getTeachingsForProfessor(frame.getProfessorToReplace()));
    }
}
