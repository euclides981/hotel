package com.hotel.controller;

import com.hotel.*;
import javafx.stage.Stage;

import java.io.IOException;

public class Nav {

    public void login(){
        Login tela = new Login();
        try {
            tela.start(new Stage());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    public void options(){
        Options tela = new Options();
        try {
            tela.start(new Stage());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    public void registro(){
        RegistroReservas tela = new RegistroReservas();
        try {
            tela.start(new Stage());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    public void hospedes(){
        ListaHospedes tela = new ListaHospedes();
        try {
            tela.start(new Stage());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    public void reservas(){
        ListaReservas tela = new ListaReservas();
        try {
            tela.start(new Stage());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void registroHospede(){
        RegistroHospedes tela = new RegistroHospedes();
        try {
            tela.start(new Stage());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }


}
