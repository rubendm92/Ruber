package ruber.core.persistence;

public interface PersistenceProvider {

    public TeachingsLoader getTeachingsLoader();

    public ProfessorsLoader getProfessorsLoader();

    public NotificationTypesLoader getNotificationTypesLoader();

    public SignedTeachingsSaver getSignedTeachingsSaver();

    public NotificationSaver getNotificationSaver();
}
