package sample.DAL;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnector {

    private static final String PROP_FILE = "config/config.settings";
    private SQLServerDataSource dataSource;

    public DatabaseConnector() throws IOException {
        Properties databaseProperties = new Properties();
        databaseProperties.load(new FileInputStream((PROP_FILE)));

        dataSource = new SQLServerDataSource();
        dataSource.setServerName(databaseProperties.getProperty("Server"));
        dataSource.setDatabaseName(databaseProperties.getProperty("Database"));
        dataSource.setUser(databaseProperties.getProperty("User"));
        dataSource.setPassword(databaseProperties.getProperty("Password"));
        dataSource.setPortNumber(1433);
        dataSource.setTrustServerCertificate(true);
        dataSource.setLoginTimeout(5);
    }

    public Connection getConnection() throws SQLServerException {
        return dataSource.getConnection();
    }

    public static void main(String[] args) throws SQLException, IOException {
        DatabaseConnector databaseConnector = new DatabaseConnector();

        try (Connection connection = databaseConnector.getConnection()) {

            System.out.println("Is it open? " + !connection.isClosed());

        }
    }
}
