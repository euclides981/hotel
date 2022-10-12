module com.hotel {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.joda.time;

    opens com.hotel;
    opens com.hotel.controller ;
    opens com.hotel.DAO;
    opens com.hotel.JDBC;
    opens com.hotel.model;

    exports com.hotel;
    exports com.hotel.controller;
    exports com.hotel.DAO;
    exports com.hotel.JDBC;
    exports com.hotel.model;
}