package ruber.core.persistence_db.implementation;

import ruber.core.model.Notification;
import ruber.core.model.Professor;
import ruber.core.persistence.NotificationSaver;
import ruber.core.persistence_db.util.DataSaver;
import ruber.core.persistence_db.util.Database;

import java.io.ByteArrayInputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class DatabaseNotificationSaver extends DataSaver implements NotificationSaver {

    private Professor professor;
    private Notification notification;

    public DatabaseNotificationSaver(Database database) {
        super(database);
    }

    @Override
    public void save(Professor professor, Notification notification) {
        this.professor = professor;
        this.notification = notification;
        abstractSave();
    }

    @Override
    protected String statement() {
        return "INSERT INTO `Notificaciones`(`Fecha`, `Hora`, `Profesor`, `Tipo`, `Descripcion`) VALUES (?, ?, ?, ?, ?)";
    }

    @Override
    protected void setStatementValues() throws SQLException {
        preparedStatement().setDate(1, Date.valueOf(LocalDate.now()));
        preparedStatement().setTime(2, Time.valueOf(LocalTime.now()));
        preparedStatement().setString(3, professor.getName());
        preparedStatement().setString(4, notification.getType());
        preparedStatement().setBinaryStream(5, new ByteArrayInputStream(notification.getDescription()));
    }
}
