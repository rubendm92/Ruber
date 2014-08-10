package ruber.commands;

import ruber.model.Professor;
import ruber.model.TeachingList;
import ruber.view.Command;
import ruber.viewmodels.Frame;

public class ShowTeachingsCommand implements Command {

    private final Frame frame;
    private final TeachingList teachings;

    public ShowTeachingsCommand(Frame frame, TeachingList teachings) {
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
