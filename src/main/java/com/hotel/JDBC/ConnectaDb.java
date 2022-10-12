package com.hotel.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectaDb {
    public Connection databaseLink;

    public Connection getDBConnection(){
        String databaseName = "db_hotel";
        String databaseUser = "admin";
        String databasePass = "8@Th6$Ui";
        String url = "jdbc:mysql://localhost/" + databaseName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePass);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return databaseLink;
    }
}
