package ruber.signatureapp.commands;

import ruber.core.model.Signature;
import ruber.core.persistence.SignedTeachingsSaver;
import ruber.signatureapp.viewmodels.FrameViewModel;
import ruber.signatureapp.views.Command;

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
        frame.getSelectedTeachings().forEach(teaching -> teaching.sign(frame.getSelectedProfessor(), signature()));
        saver.save(frame.getProfessorFromSession(), frame.getSelectedTeachings());
    }

    private Signature signature() {
        return new Signature(frame.getProfessorFromSession(), LocalTime.now(), frame.getSignature());
    }
}
