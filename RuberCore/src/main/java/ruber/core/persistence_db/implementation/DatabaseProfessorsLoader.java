package ruber.core.persistence_db.implementation;

import ruber.core.log.Log;
import ruber.core.model.Professor;
import ruber.core.model.ProfessorList;
import ruber.core.persistence.ProfessorsLoader;
import ruber.core.persistence_db.util.DataLoader;
import ruber.core.persistence_db.util.Database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseProfessorsLoader extends DataLoader<ProfessorList> implements ProfessorsLoader {

    public DatabaseProfessorsLoader(Database database) {
        super(database);
    }

    @Override
    public ProfessorList load() {
        return abstractLoad();
    }

    @Override
    protected String statement() {
        return "select * from `Profesores` ORDER BY `profesor`";
    }

    @Override
    protected void setStatementValues() throws SQLException {
    }

    @Override
    protected ProfessorList doLoad(ResultSet set) {
        ProfessorList professorList = new ProfessorList();
        try {
            while (set.next())
                professorList.add(new Professor(set.getString("DNI"), set.getString("Profesor"), set.getString("Correo")));
        } catch (SQLException ex) {
            Log.getInstance().add(ex);
        }
        return professorList;
    }
}
