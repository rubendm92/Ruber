package ruber.core.persistence;

public interface PersistenceProvider {

    public TeachingsLoader getTeachingsLoader();

    public ProfessorsLoader getProfessorsLoader();

    public SignedTeachingsSaver getSignedTeachingsSaver();

    public NotificationSaver getNotificationSaver();
}
