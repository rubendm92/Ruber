package ruber.core.persistence_db.implementation;

import ruber.core.persistence.NotificationTypesLoader;
import ruber.core.persistence_db.util.DataLoader;
import ruber.core.persistence_db.util.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseNotificationTypesLoader extends DataLoader<List<String>> implements NotificationTypesLoader {

    public DatabaseNotificationTypesLoader(Database database) {
        super(database);
    }

    @Override
    protected String statement() {
        return "select * from `tipos_notificaciones`";
    }

    @Override
    protected void setStatementValues() throws SQLException {
    }

    @Override
    protected List<String> doLoad(ResultSet set) {
        List<String> types = new ArrayList<>();
        try {
            while (set.next())
                types.add(set.getString("tipo"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return types;
    }

    @Override
    public List<String> load() {
        return abstractLoad();
    }
}
