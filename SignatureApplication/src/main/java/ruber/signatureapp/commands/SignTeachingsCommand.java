package ruber.signatureapp.commands;

import javafx.application.Platform;
import ruber.core.mail.TeachingsReplacedMail;
import ruber.core.mail.TeachingsSignedMail;
import ruber.core.model.Professor;
import ruber.core.model.Signature;
import ruber.core.model.TeachingList;
import ruber.core.persistence.SignedTeachingsSaver;
import ruber.core.persistence_file.FileSignedTeachingsSaver;
import ruber.signatureapp.model.Timer;
import ruber.signatureapp.viewcontrollers.RuberFrameViewController;
import ruber.signatureapp.views.Command;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class SignTeachingsCommand implements Command {

    private static final int ONE_HOUR = 60 * 60 * 1000;

    private final RuberFrameViewController frame;
    private final SignedTeachingsSaver saver;
    private final TeachingsSignedMail teachingsSigned;
    private final TeachingsReplacedMail teachingsReplaced;
    private Timer timer;

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
        FileSignedTeachingsSaver.getInstance().save(professor, teachings);
        if (connectionIsAvailable())
            remoteSave(teachings, professor);
        else
            tryLater(teachings, professor);

    }

    private void remoteSave(TeachingList teachings, Professor professor) {
        saver.save(professor, teachings);
        teachingsSigned.send(teachings, professor);
        teachingsReplaced.send(teachings, professor);
    }

    private void tryLater(TeachingList teachings, Professor professor) {
        timer = new Timer(ONE_HOUR, () -> tryToSaveIntoDatabase(timer, teachings, professor));
        timer.start();
    }

    private void tryToSaveIntoDatabase(Timer timer, TeachingList teachings, Professor professor) {
        if (!connectionIsAvailable()) return;
        remoteSave(teachings, professor);
        timer.stop();
    }

    private boolean connectionIsAvailable() {
        try {
            URLConnection connection = new URL("http://www.google.es").openConnection();
            connection.getInputStream();
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
