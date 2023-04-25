package main.java.dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;
import java.sql.SQLException;

public class DataAccessManager {
    private SQLServerDataSource dataSource;

    public DataAccessManager(){
        dataSource = new SQLServerDataSource();
        dataSource.setServerName("10.176.111.34");
        dataSource.setDatabaseName("WUAV_Documentation_System");
        dataSource.setUser("CSe2022B_e_7");
        dataSource.setPassword("CSe2022BE7#");
        dataSource.setPortNumber(1433);
        dataSource.setTrustServerCertificate(true);

    }
    public Connection getConnection() throws SQLServerException {
        return dataSource.getConnection();
    }


    //checks if connection is closed or not
    public static void main(String[] args) throws SQLException {
        DataAccessManager dataAccessManager = new DataAccessManager();
        Connection connection = dataAccessManager.getConnection();

        System.out.println("Is it open? " + !connection.isClosed());
        connection.close();
    }
}
