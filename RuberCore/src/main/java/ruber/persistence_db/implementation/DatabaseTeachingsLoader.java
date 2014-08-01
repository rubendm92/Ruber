package ruber.persistence_db.implementation;

import ruber.model.Schedule;
import ruber.model.Subject;
import ruber.model.Teaching;
import ruber.model.TeachingList;
import ruber.persistence.TeachingsLoader;
import ruber.persistence_db.util.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class DatabaseTeachingsLoader extends DataLoader<TeachingList> implements TeachingsLoader {

    private static final int START_TIME = 0;
    private static final int END_TIME = 1;

    private LocalDate day;

    public DatabaseTeachingsLoader(Database database) {
        super(database);
    }

    @Override
    public TeachingList load(LocalDate day) {
        this.day = day;
        return abstractLoad();
    }

    @Override
    protected String statement() {
        return "SELECT DISTINCT Titulacion, DescAsignatura, DescGrupo, Horario, DescLocal " +
                "from `Horario-201314` " +
                "where Periodo = ? AND DiaSemana = ? AND `Titulacion` != '4801 - Doble Grado en ing. Inf. y Grado en Adm. y Direc.' " +
                "ORDER BY `Horario`";
    }

    @Override
    protected void setStatementValues() throws SQLException {
        preparedStatement().setString(1, "2C");
        preparedStatement().setString(2, DayOfWeekToStringTranslator.instance().dateToDayString(day));
    }

    @Override
    protected TeachingList doLoad(ResultSet set) {
        TeachingList teachingList = new TeachingList();
        try {
            while (set.next())
                teachingList.add(teaching(set));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        addProfessors(teachingList);
        addSignatures(teachingList);
        return teachingList;
    }

    private void addProfessors(TeachingList teachingList) {
        ProfessorSetter setter = new ProfessorSetter(database);
        teachingList.forEach((teaching) -> setter.setProfessors(teaching, day));
    }

    private void addSignatures(TeachingList teachingList) {
        SignatureSetter setter = new SignatureSetter(database);
        teachingList.forEach((teaching) -> setter.setSignatures(teaching, day));
    }

    private Teaching teaching(ResultSet set) throws SQLException {
        return new Teaching(subject(set.getString("DescAsignatura"), set.getString("Titulacion")),
                schedule(set.getString("Horario"), set.getString("DescLocal")),
                set.getString("DescGrupo"));
    }

    private Subject subject(String name, String degree) {
        return new Subject(name, degree);
    }

    private Schedule schedule(String schedule, String classroom) {
        return new Schedule(time(schedule, START_TIME), time(schedule, END_TIME), classroom);
    }

    private LocalTime time(String schedule, int selection) {
        return LocalTime.of(Integer.valueOf(hour(schedule.split("-")[selection])), Integer.valueOf(minute(schedule.split("-")[selection])));
    }

    private String hour(String time) {
        return time.split(":")[0];
    }

    private String minute(String time) {
        return time.split(":")[1];
    }
}
