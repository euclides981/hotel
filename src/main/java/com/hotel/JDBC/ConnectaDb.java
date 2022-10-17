package com.hotel.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectaDb {
    public Connection databaseLink;

    public Connection getDBConnection(){

        try {

            //Usar este caminho quando for fazer um novo Build.
            String url = "jdbc:sqlite:db/db_hotel.db";

            //Usar este caminho quando estiver trabalhando na IDE.
            //String url = "jdbc:sqlite:out/artifacts/hotel_jar/Hotel Arrays/db/db_hotel.db";
            this.databaseLink = DriverManager.getConnection(url);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return databaseLink;
    }
}