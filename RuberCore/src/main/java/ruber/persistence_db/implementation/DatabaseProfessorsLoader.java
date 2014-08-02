package ruber.persistence_db.implementation;

import ruber.model.Professor;
import ruber.persistence.ProfessorsLoader;
import ruber.persistence_db.util.DataLoader;
import ruber.persistence_db.util.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseProfessorsLoader extends DataLoader<List<Professor>> implements ProfessorsLoader {

    public DatabaseProfessorsLoader(Database database) {
        super(database);
    }

    @Override
    public List<Professor> load() {
        return abstractLoad();
    }

    @Override
    protected String statement() {
        return "select * from `Profesores`";
    }

    @Override
    protected void setStatementValues() throws SQLException {
    }

    @Override
    protected List<Professor> doLoad(ResultSet set) {
        List<Professor> professorList = new ArrayList<>();
        try {
            while (set.next())
                professorList.add(new Professor(set.getString("DNI"), set.getString("Profesor"), set.getString("Correo")));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return professorList;
    }
}
