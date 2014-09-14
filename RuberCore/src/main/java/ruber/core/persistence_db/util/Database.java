package ruber.core.persistence_db.util;

import ruber.core.log.Log;

import java.sql.*;

public class Database {

    private final String database;
    private final String username;
    private final String password;

    private Connection connection;

    Database(String database, String username, String password) {
        this.database = database;
        this.username = username;
        this.password = password;
        loadDriver();
    }

    private void loadDriver() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Log.getInstance().add(ex);
        }
    }

    public void openConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + database, username, password);
        } catch (SQLException ex) {
            Log.getInstance().add(ex);
        }
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }

    public PreparedStatement prepareStatement(String statement) throws SQLException {
        return connection.prepareStatement(statement);
    }

    public ResultSet executeSelectStatement(PreparedStatement statement) throws SQLException {
        return statement.executeQuery();
    }

    public void executeUpdateStatement(PreparedStatement statement) throws SQLException {
        statement.executeUpdate();
    }
}
