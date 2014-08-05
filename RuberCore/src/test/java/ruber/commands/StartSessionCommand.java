package ruber.commands;

import ruber.model.Professor;
import ruber.model.ProfessorList;
import ruber.model.TeachingList;
import ruber.view.Command;
import ruber.viewmodels.FrameViewModel;

public class StartSessionCommand implements Command {

    private final FrameViewModel frame;
    private final ProfessorList professors;
    private final TeachingList teachings;

    public StartSessionCommand(FrameViewModel frame, ProfessorList professors, TeachingList teachings) {
        this.frame = frame;
        this.professors = professors;
        this.teachings = teachings;
    }

    @Override
    public void execute() {
        frame.initSession(professor());
        frame.showTeachings(teachings());
    }

    private Professor professor() {
        return professors.getByDni(frame.getDni());
    }

    private TeachingList teachings() {
        return teachings.getTeachingsForProfessor(professor());
    }
}
