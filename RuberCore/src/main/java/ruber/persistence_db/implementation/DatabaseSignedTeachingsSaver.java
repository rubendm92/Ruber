package ruber.persistence_db.implementation;

import ruber.model.Professor;
import ruber.model.Teaching;
import ruber.model.TeachingList;
import ruber.persistence.SignedTeachingsSaver;
import ruber.persistence_db.util.DataSaver;
import ruber.persistence_db.util.Database;

import java.io.ByteArrayInputStream;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class DatabaseSignedTeachingsSaver extends DataSaver implements SignedTeachingsSaver {

    private Professor professor;
    private Teaching currentTeaching;

    public DatabaseSignedTeachingsSaver(Database database) {
        super(database);
    }

    @Override
    public void save(Professor professor, TeachingList teachings) {
        this.professor = professor;
        teachings.stream().forEach((teaching) -> saveTeaching(teaching));
    }

    private void saveTeaching(Teaching teaching) {
        this.currentTeaching = teaching;
        abstractSave();
    }

    @Override
    protected String statement() {
        return "INSERT INTO `control_de_firmas`(`IP`,`Fecha`, `Horario`, `Profesor`, `Titulacion`, `DescAsignatura`, `DescGrupo`, `DescLocal`, `HoraFirma`, `Firmado`, `Firma`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected void setStatementValues() throws SQLException {
        preparedStatement().setString(1, getIp());
        preparedStatement().setDate(2, Date.valueOf(LocalDate.now()));
        preparedStatement().setString(3, currentTeaching.getSchedule().toString());
        preparedStatement().setString(4, currentTeaching.getProfessorWhoSignedFor(professor).getName());
        preparedStatement().setString(5, currentTeaching.getDegree());
        preparedStatement().setString(6, currentTeaching.getSubjectName());
        preparedStatement().setString(7, currentTeaching.getGroup());
        preparedStatement().setString(8, currentTeaching.getClassroom());
        preparedStatement().setTime(9, Time.valueOf(LocalTime.now()));
        preparedStatement().setString(10, professor.getName());
        preparedStatement().setBinaryStream(11, new ByteArrayInputStream(currentTeaching.getSignatureBytes(professor)));
    }

    private static String getIp() {
        try {
            return Inet4Address.getLocalHost().getHostAddress();
        } catch (UnknownHostException ex) {
            return "IPException";
        }
    }
}