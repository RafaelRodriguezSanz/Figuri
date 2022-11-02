package ucu.edu.uy.Persistencia.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import lombok.Getter;

public class DB {

    private static final String dbName = "test";

    @Getter
    private Connection connection;

    @Getter
    private static final DB SINGLE_INSTANCE = new DB();

    @Getter
    private String url = "jdbc:postgresql://192.168.9.133:5432/";

    private DB() {
    }

    public void connect(String dbName, String user, String password) throws SQLException {
        this.connection = DriverManager.getConnection(getUrl() + dbName, user, password);
        if (this.isConnected()) {
            System.out.println("Database was connected Successfully!");
        } else {
            System.out.println("Database has errors Connecting");
        }
    }

    public void connect(String dbName) throws SQLException {
        this.connection = DriverManager.getConnection(getUrl() + dbName, "postgres", "admin");
        if (this.isConnected()) {
            System.out.println("Database was connected Successfully!");
        } else {
            System.out.println("Database has errors Connecting");
        }
    }

    public void connect() throws SQLException {
        this.connection = DriverManager.getConnection(getUrl() + dbName, "postgres", "admin");
        if (this.isConnected()) {
            System.out.println("Database was connected Successfully!");
        } else {
            System.out.println("Database has errors Connecting");
        }
    }

    public void disconnect() throws SQLException {
        this.connection.close();
        if (!this.isConnected()) {
            System.out.println("Database was disconnected Successfully!");
        } else {
            System.out.println("Database has errors Disconnecting");
        }
    }

    public boolean isConnected() {
        try {
            return !getConnection().isClosed();
        } catch (SQLException e) {
            return false;
        }
    }

    public ResultSet executeAction(String action) throws SQLException {
        PreparedStatement statement = this.getConnection().prepareStatement(action);
        statement.execute();
        statement.close();
        return statement.getResultSet();
    }

    public boolean executeQuery(String action) throws SQLException {
        PreparedStatement statement = this.getConnection().prepareStatement(action);
        return statement.execute();
    }

}
