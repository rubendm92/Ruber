package ruber.signatureapp.commands;

import ruber.core.model.Professor;
import ruber.core.model.TeachingList;
import ruber.signatureapp.viewmodels.FrameViewModel;
import ruber.signatureapp.views.Command;

public class ShowTeachingsCommand implements Command {

    private final FrameViewModel frame;
    private final TeachingList teachings;

    public ShowTeachingsCommand(FrameViewModel frame, TeachingList teachings) {
        this.frame = frame;
        this.teachings = teachings;
    }

    @Override
    public void execute() {
        frame.showTeachings(teachings());
    }

    private TeachingList teachings() {
        return teachings.getTeachingsForProfessor(professor());
    }

    private Professor professor() {
        return frame.getProfessorFromSession();
    }
}
