package ruber.persistence_db;

import ruber.persistence.*;
import ruber.persistence_db.implementation.DatabaseTeachingsLoader;
import ruber.persistence_db.util.Database;
import ruber.persistence_db.util.DatabaseProvider;

public class DatabasePersistenceProvider implements PersistenceProvider {

    private final Database database;
    private TeachingsLoader teachingsLoader;
    private ProfessorsLoader professorsLoader;
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
