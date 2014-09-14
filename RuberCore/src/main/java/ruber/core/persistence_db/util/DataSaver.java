package ruber.core.persistence_db.util;

import ruber.core.log.Log;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class DataSaver {
    
    private final Database database;
    
    private PreparedStatement statement;

    public DataSaver(Database database) {
        this.database = database;
    }
    
    protected PreparedStatement preparedStatement() {
        return statement;
    }
    
    protected void abstractSave() {
        database.openConnection();
        saveData();
        closeConnection();
    }
    
    private void saveData() {
        try {
            database.executeUpdateStatement(update());
        } catch (SQLException ex) {
            Log.getInstance().add(ex);
        }
    }
    
    private PreparedStatement update() throws SQLException {
        statement = database.prepareStatement(statement());
        setStatementValues();
        return statement;
    }
    
    private void closeConnection() {
        try {
            statement.close();
            database.closeConnection();
        } catch (SQLException ex) {
            Log.getInstance().add(ex);
        }
    }
    
    protected abstract String statement();
    
    protected abstract void setStatementValues() throws SQLException;
}
