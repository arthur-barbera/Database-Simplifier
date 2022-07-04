package fr.arthurbarbera.lib.dbsimplifier.connection;

import fr.arthurbarbera.lib.dbsimplifier.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBConnection {

    private final DBCredentials credentials;
    private final Connection connection;

    public DBConnection(DBCredentials credentials) {
        this.credentials = credentials;

        try {
            this.connection = DriverManager.getConnection(
                    this.credentials.toURI(),
                    this.credentials.user(),
                    this.credentials.pass()
            );

            Logger.log("Successfully connected to database \"" + this.credentials.databaseName());
        } catch (SQLException e) {
            throw new RuntimeException("[DATABASE SIMPLIFIER] An error has occurred while connecting to database \""
                    + this.credentials.databaseName() + "\" with uri: \"" + this.credentials.toURI() + "\": " + e);
        }
    }

    public PreparedStatement newRequest(String request) {
        try {
            return connection.prepareStatement(request);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {
        try {
            if (!this.connection.isClosed()) {
                this.close();
                Logger.log("The connection to database \"" + this.credentials.databaseName() + "\" with uri \""
                        + this.credentials.toURI() + "\" has successfully been disconnected.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("[DATABASE SIMPLIFIER] An error has occurred while closing connection " +
                    "to database \"" + this.credentials.databaseName() + "\": " + e);
        }
    }

}
