package com.hotel.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectaDb {
    public Connection databaseLink;

    public Connection getDBConnection(){

        try {
            String url = "jdbc:sqlite:out/artifacts/hotel_jar/db/db_hotel.db";

            this.databaseLink = DriverManager.getConnection(url);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return databaseLink;
    }
}