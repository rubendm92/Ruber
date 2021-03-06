package ruber.signatureapp.viewmodels.notification;

import ruber.signatureapp.signaturedevices.SignatureViewModel;

import java.util.List;

public class NotificationsViewModel {

    private final List<String> notificationTypes;
    private final SignatureViewModel signatureViewModel;

    public NotificationsViewModel(List<String> notificationTypes, SignatureViewModel signatureViewModel) {
        this.notificationTypes = notificationTypes;
        this.signatureViewModel = signatureViewModel;
    }

    public SignatureViewModel getSignatureViewModel() {
        return signatureViewModel;
    }

    public List<String> getNotificationTypes() {
        return notificationTypes;
    }
}
