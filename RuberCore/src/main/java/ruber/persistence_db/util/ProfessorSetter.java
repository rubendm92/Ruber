package ruber.persistence_db.util;


import ruber.model.Professor;
import ruber.model.Teaching;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ProfessorSetter extends DataLoader<Void> {

    private Teaching teaching;
    private LocalDate day;

    public ProfessorSetter(Database database) {
        super(database);
    }
    
    public void setProfessors(Teaching teaching, LocalDate day) {
        this.teaching = teaching;
        this.day = day;
        abstractLoad();
    }

    @Override
    protected String statement() {
        return "SELECT Profesores.Profesor, DNI, Correo " +
                "FROM `Profesores`, `Horario-201314` " +
                "WHERE `Periodo` = ? AND `DiaSemana`= ? AND `Titulacion` = ? AND `DescAsignatura` = ? AND `DescGrupo` = ? AND `Horario` = ? AND `DescLocal` = ? AND `Profesores`.`Profesor` = `Horario-201314`.`Profesor` " +
                "ORDER BY `Horario`";
    }

    @Override
    protected void setStatementValues() throws SQLException {
        preparedStatement().setString(1, "2C");
        preparedStatement().setString(2, DayOfWeekToStringTranslator.instance().dateToDayString(day));
        preparedStatement().setString(3, teaching.getDegree());
        preparedStatement().setString(4, teaching.getSubjectName());
        preparedStatement().setString(5, teaching.getGroup());
        preparedStatement().setString(6, teaching.getSchedule().toString());
        preparedStatement().setString(7, teaching.getClassroom());
    }

    @Override
    protected Void doLoad(ResultSet set) {
        try {
            while (set.next())
                teaching.addProfessor(new Professor(set.getString("DNI"), set.getString("Profesor"), set.getString("Correo")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
