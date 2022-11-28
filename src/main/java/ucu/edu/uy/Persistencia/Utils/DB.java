package ucu.edu.uy.Persistencia.Utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import lombok.Getter;
import ucu.edu.uy.Jade.Utils.Executor;

public class DB {

    private static final String dbName = "test";

    @Getter
    private Connection connection;

    @Getter
    private static final DB SINGLE_INSTANCE = new DB();

    @Getter
    private String url = "jdbc:postgresql://";

    private DB() {
        Executor e = new Executor();
        try {
            e.loadProperties();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        this.url = this.url + e.prop.getProperty("host") + ":" + e.prop.getProperty("port") + "/";
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

    public ResultSet executeAction(PreparedStatement statement) throws SQLException {
        statement.execute();
        ResultSet result = statement.getResultSet();
        result.next();
        return result;
    }

    public boolean executeQuery(PreparedStatement statement) throws SQLException {
        boolean result = statement.execute();
        return result;
    }

}
