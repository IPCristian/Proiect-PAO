package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionManager {

    private final Connection conn;

    public ConnectionManager(String url, String user, String pass) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(url,user,pass);
    }

    public void close() throws SQLException {
        conn.close();
    }
    public PreparedStatement prepareStatement(String statement) throws SQLException {
        return conn.prepareStatement(statement);
    }
}
