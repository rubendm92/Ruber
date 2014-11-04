package ruber.signatureapp.commands;

import javafx.application.Platform;
import ruber.core.mail.TeachingsReplacedMail;
import ruber.core.mail.TeachingsSignedMail;
import ruber.core.model.Professor;
import ruber.core.model.Signature;
import ruber.core.model.TeachingList;
import ruber.core.persistence.SignedTeachingsSaver;
import ruber.signatureapp.viewcontrollers.RuberFrameViewController;
import ruber.signatureapp.views.Command;

public class SignTeachingsCommand implements Command {

    private final RuberFrameViewController frame;
    private final SignedTeachingsSaver saver;
    private final TeachingsSignedMail teachingsSigned;
    private final TeachingsReplacedMail teachingsReplaced;

    public SignTeachingsCommand(RuberFrameViewController frame, SignedTeachingsSaver saver) {
        this.frame = frame;
        this.saver = saver;
        this.teachingsSigned = new TeachingsSignedMail();
        this.teachingsReplaced = new TeachingsReplacedMail();
    }

    @Override
    public void execute() {
        Platform.runLater(this::sign);
    }

    private void sign() {
        final TeachingList teachings = frame.getSelectedTeachings();
        final Professor professor = frame.getProfessorFromSession();
        final Professor selectedProfessor = frame.getSelectedProfessor();
        final Signature signature = frame.getSignature();
        new Thread(() -> process(teachings, professor, selectedProfessor, signature)).start();
        frame.teachingsSigned();
    }

    private void process(TeachingList teachings, Professor professor, Professor selectedProfessor, Signature signature) {
        teachings.forEach(teaching -> teaching.sign(selectedProfessor, signature));
        remoteSave(teachings, professor);

    }

    private void remoteSave(TeachingList teachings, Professor professor) {
        saver.save(professor, teachings);
        teachingsSigned.send(teachings, professor);
        teachingsReplaced.send(teachings, professor);
    }
}
