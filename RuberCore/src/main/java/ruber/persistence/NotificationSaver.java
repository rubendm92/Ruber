package ruber.persistence;

import ruber.model.Notification;
import ruber.model.Professor;

public interface NotificationSaver {

    public void save(Professor professor, Notification notification);
}
