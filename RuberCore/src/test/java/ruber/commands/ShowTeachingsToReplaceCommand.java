package ruber.commands;

import ruber.model.TeachingList;
import ruber.view.Command;
import ruber.viewmodels.FrameViewModel;

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
