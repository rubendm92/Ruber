package ruber.commands;

import ruber.model.TeachingList;
import ruber.view.Command;
import ruber.viewmodels.Frame;

public class ShowTeachingsToReplaceCommand implements Command {

    private final Frame frame;
    private final TeachingList teachings;

    public ShowTeachingsToReplaceCommand(Frame frame, TeachingList teachings) {
        this.frame = frame;
        this.teachings = teachings;
    }

    @Override
    public void execute() {
        frame.showProfessorToReplace();
        frame.showTeachings(teachings.getTeachingsForProfessor(frame.getProfessorToReplace()));
    }
}
