package com.hotel;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ListaReservas extends Application {

    private static Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(ListaReservas.class.getResource("lista-reservas.fxml")));
        Scene scene = new Scene(root);
        stage.setTitle("Visualização de Reservas");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        setStage(stage);
    }

    public static void main(String[] args) {
        launch();
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        ListaReservas.stage = stage;
    }

}