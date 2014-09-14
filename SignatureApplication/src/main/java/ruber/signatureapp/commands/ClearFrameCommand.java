package ruber.signatureapp.commands;

import ruber.core.persistence.NotificationSaver;
import ruber.core.persistence.PersistenceProvider;
import ruber.core.persistence.SignedTeachingsSaver;
import ruber.signatureapp.viewcontrollers.RuberFrameViewController;
import ruber.signatureapp.viewmodels.RuberFrameViewModel;
import ruber.signatureapp.views.Command;

public class ClearFrameCommand implements Command {

    private final RuberFrameViewController viewController;
    private final SignedTeachingsSaver teachingsSaver;
    private final NotificationSaver notificationSaver;

    public ClearFrameCommand(RuberFrameViewController viewController, PersistenceProvider provider) {
        this.viewController = viewController;
        this.teachingsSaver = provider.getSignedTeachingsSaver();
        this.notificationSaver = provider.getNotificationSaver();
    }

    @Override
    public void execute() {
        if (viewController.hasSignatureUnsaved())
            new SignTeachingsCommand(viewController, teachingsSaver).execute();
        else if (viewController.hasNotificationUnsaved())
            new SaveNotificationCommand(viewController, notificationSaver).execute();
        else
            viewController.clear();
    }
}
