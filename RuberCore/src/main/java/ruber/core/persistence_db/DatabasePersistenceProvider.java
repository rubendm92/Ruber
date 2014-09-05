package ruber.core.persistence_db;

import ruber.core.persistence.*;
import ruber.core.persistence_db.implementation.*;
import ruber.core.persistence_db.util.Database;
import ruber.core.persistence_db.util.DatabaseProvider;

public class DatabasePersistenceProvider implements PersistenceProvider {

    private final Database database;
    private TeachingsLoader teachingsLoader;
    private ProfessorsLoader professorsLoader;
    private NotificationTypesLoader notificationTypesLoader;
    private SignedTeachingsSaver signedTeachingsSaver;
    private NotificationSaver notificationSaver;

    public DatabasePersistenceProvider(String pathToConfigFile) {
        this.database = DatabaseProvider.getDatabase(pathToConfigFile);
    }

    @Override
    public TeachingsLoader getTeachingsLoader() {
        if (teachingsLoader == null)
            teachingsLoader = new DatabaseTeachingsLoader(database);
        return teachingsLoader;
    }

    @Override
    public ProfessorsLoader getProfessorsLoader() {
        if (professorsLoader == null)
            professorsLoader = new DatabaseProfessorsLoader(database);
        return professorsLoader;
    }

    @Override
    public NotificationTypesLoader getNotificationTypesLoader() {
        if (notificationTypesLoader == null)
            notificationTypesLoader = new DatabaseNotificationTypesLoader(database);
        return notificationTypesLoader;
    }

    @Override
    public SignedTeachingsSaver getSignedTeachingsSaver() {
        if (signedTeachingsSaver == null)
            signedTeachingsSaver = new DatabaseSignedTeachingsSaver(database);
        return signedTeachingsSaver;
    }

    @Override
    public NotificationSaver getNotificationSaver() {
        if (notificationSaver == null)
            notificationSaver = new DatabaseNotificationSaver(database);
        return notificationSaver;
    }
}
