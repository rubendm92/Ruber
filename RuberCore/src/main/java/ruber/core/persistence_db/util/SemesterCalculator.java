package ruber.core.persistence_db.util;

import ruber.core.log.Log;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class SemesterCalculator extends DataLoader<String>{

    private LocalDate date;

    public SemesterCalculator(Database database) {
        super(database);
    }

    public String getSemester(LocalDate date) {
        this.date = date;
        return abstractLoad();
    }

    @Override
    protected String statement() {
        return "SELECT Semestre FROM `semestres` WHERE Inicio < ? AND Fin > ?";
    }

    @Override
    protected void setStatementValues() throws SQLException {
        preparedStatement().setDate(1, Date.valueOf(date));
        preparedStatement().setDate(2, Date.valueOf(date));
    }

    @Override
    protected String doLoad(ResultSet set) {
        try {
            while (set.next())
                return set.getString("semestre");
        } catch (SQLException ex) {
            Log.getInstance().add(ex);
        }
        return "";
    }
}
