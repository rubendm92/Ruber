package ruber.core.commands;

import ruber.core.model.TeachingList;
import ruber.core.view.Command;
import ruber.core.viewmodels.FrameViewModel;

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
