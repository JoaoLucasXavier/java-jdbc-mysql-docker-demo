package com.jl.lab.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import io.github.cdimascio.dotenv.Dotenv;

public class ConnectionFactory {

    private String database;
    private String host;
    private String port;
    private String username;
    private String password;

    public ConnectionFactory() {
        Dotenv dotenv = Dotenv.load();

        this.database = dotenv.get("DB_DATABASE");
        this.host = dotenv.get("DB_HOST");
        this.port = dotenv.get("DB_PORT");
        this.username = dotenv.get("DB_USERNAME");
        this.password = dotenv.get("DB_PASSWORD");
    }

    private String getConnectionString() {
        return "jdbc:" + this.database + "://" + this.host + ":" + this.port + "/java_demo?serverTimeZone=UTC";
    }

    private Connection createConnection() throws Exception {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    this.getConnectionString(),
                    this.username,
                    this.password);

            if (connection == null) {
                throw new Exception("Connect failed.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    public Connection getConnection() {
        try {
            return this.createConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
