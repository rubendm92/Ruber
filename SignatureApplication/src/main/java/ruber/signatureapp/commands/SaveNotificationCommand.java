package ruber.signatureapp.commands;

import javafx.application.Platform;
import ruber.core.model.Notification;
import ruber.core.model.Professor;
import ruber.core.persistence.NotificationSaver;
import ruber.signatureapp.viewcontrollers.RuberFrameViewController;
import ruber.signatureapp.viewmodels.RuberFrameViewModel;
import ruber.signatureapp.views.Command;

public class SaveNotificationCommand implements Command {

    private final RuberFrameViewController frame;
    private final NotificationSaver saver;

    public SaveNotificationCommand(RuberFrameViewController frame, NotificationSaver saver) {
        this.frame = frame;
        this.saver = saver;
    }

    @Override
    public void execute() {
        Platform.runLater(this::saveNotification);
    }

    private void saveNotification() {
        Professor professor = frame.getProfessorFromSession();
        Notification notification = frame.getNotification();
        new Thread(() -> saver.save(professor, notification)).start();
        frame.notificationWritten();
    }
}
