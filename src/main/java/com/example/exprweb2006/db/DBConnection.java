package com.example.exprweb2006.db;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection instance;

    private Connection connection;

    private DBConnection(){}

    public static DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    public void initConnection(DataSource ds) throws SQLException {
        connection = ds.getConnection();
    }

    public Connection getConnection() {
        return connection;
    }
}
