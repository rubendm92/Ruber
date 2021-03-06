package ruber.core.persistence_db.util;

import ruber.core.log.Log;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DataLoader<Type> {

    protected final Database database;

    private PreparedStatement statement;
    private ResultSet set;

    protected DataLoader(Database database) {
        this.database = database;
    }

    protected PreparedStatement preparedStatement() {
        return statement;
    }

    protected Type abstractLoad() {
        database.openConnection();
        Type result = loadData();
        closeConnection();
        return result;
    }

    private Type loadData() {
        try {
            set = database.executeSelectStatement(select());
            return doLoad(set);
        } catch (SQLException ex) {
            Log.getInstance().add(ex);
        }
        return null;
    }

    private PreparedStatement select() throws SQLException {
        statement = database.prepareStatement(statement());
        setStatementValues();
        return statement;
    }

    private void closeConnection() {
        try {
            close();
        } catch (SQLException ex) {
            Log.getInstance().add(ex);
        }
    }

    private void close() throws SQLException {
        set.close();
        statement.close();
        database.closeConnection();
    }

    protected abstract String statement();

    protected abstract void setStatementValues() throws SQLException;

    protected abstract Type doLoad(ResultSet set);
}
