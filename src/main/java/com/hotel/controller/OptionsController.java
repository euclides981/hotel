package com.hotel.controller;

import com.hotel.Options;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class OptionsController {
    @FXML    private Button btRegistrar;
    @FXML    private Button btHospedes;
    @FXML    private Button btReservas;
    @FXML    private Button btLogout;

    public void initialize(){
        btRegistrar.setOnMouseClicked((MouseEvent e) -> {
            Nav n = new Nav();
            n.registro();
            Options.getStage().close();
        });
        btHospedes.setOnMouseClicked((MouseEvent e) -> {
            Nav n = new Nav();
            n.hospedes();
            Options.getStage().close();
        });
        btReservas.setOnMouseClicked((MouseEvent e) -> {
            Nav n = new Nav();
            n.reservas();
            Options.getStage().close();
        });
        btLogout.setOnMouseClicked((MouseEvent e) -> {
            Nav n = new Nav();
            n.login();
            Options.getStage().close();
        });
    }

}
