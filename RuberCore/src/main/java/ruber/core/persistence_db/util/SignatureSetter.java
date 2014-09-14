package ruber.core.persistence_db.util;

import ruber.core.log.Log;
import ruber.core.model.Professor;
import ruber.core.model.Signature;
import ruber.core.model.Teaching;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class SignatureSetter extends DataLoader<Void> {

    private Teaching teaching;
    private LocalDate day;
    private Professor professor;

    public SignatureSetter(Database database) {
        super(database);
    }

    public void setSignatures(Teaching teaching, LocalDate day) {
        this.teaching = teaching;
        this.day = day;
        teaching.getProfessors().forEach(professor -> {
            this.professor = professor;
            abstractLoad();
        });
    }

    @Override
    protected String statement() {
        return "SELECT Profesores.Profesor, Profesores.DNI, Profesores.Correo, control_de_firmas.Profesor, HoraFirma, Firmado, Firma " +
                "FROM `control_de_firmas`, `Profesores` " +
                "WHERE `Fecha`= ? AND `Titulacion` = ? AND `DescAsignatura` = ? " +
                "AND `DescGrupo` = ? AND `Horario` = ? AND `DescLocal` = ? AND control_de_firmas.Profesor = ? " +
                "AND Profesores.Profesor = control_de_firmas.Firmado";
    }

    @Override
    protected void setStatementValues() throws SQLException {
        preparedStatement().setDate(1, Date.valueOf(day));
        preparedStatement().setString(2, teaching.getDegree());
        preparedStatement().setString(3, teaching.getSubjectName());
        preparedStatement().setString(4, teaching.getGroup());
        preparedStatement().setString(5, teaching.getStringSchedule());
        preparedStatement().setString(6, teaching.getClassroom());
        preparedStatement().setString(7, professor.getName());
    }

    @Override
    protected Void doLoad(ResultSet set) {
        try {
            while (set.next())
                teaching.sign(professor, signature(set));
        } catch (SQLException ex) {
            Log.getInstance().add(ex);
        }
        return null;
    }

    private Signature signature(ResultSet set) throws SQLException {
        return new Signature(professor(set), set.getTime("HoraFirma").toLocalTime(), set.getBytes("Firma"));
    }

    private Professor professor(ResultSet set) throws SQLException {
        return new Professor(set.getString("DNI"), set.getString("Profesor"), set.getString("Correo"));
    }
}