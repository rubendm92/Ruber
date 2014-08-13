package ruber.core.persistence;

import ruber.core.model.Notification;
import ruber.core.model.Professor;

public interface NotificationSaver {

    public void save(Professor professor, Notification notification);
}
