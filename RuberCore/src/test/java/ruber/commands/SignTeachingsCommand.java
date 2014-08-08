package ruber.commands;

import ruber.model.Signature;
import ruber.persistence.SignedTeachingsSaver;
import ruber.view.Command;
import ruber.viewmodels.FrameViewModel;

import java.time.LocalTime;

public class SignTeachingsCommand implements Command {

    private final FrameViewModel frame;
    private final SignedTeachingsSaver saver;

    public SignTeachingsCommand(FrameViewModel frame, SignedTeachingsSaver saver) {
        this.frame = frame;
        this.saver = saver;
    }

    @Override
    public void execute() {
        frame.getSelectedTeachings().forEach(teaching -> teaching.sign(frame.getProfessorSelected(), signature()));
        saver.save(frame.getProfessorFromSession(), frame.getSelectedTeachings());
    }

    private Signature signature() {
        return new Signature(frame.getProfessorFromSession(), LocalTime.now(), frame.getSignature());
    }
}
