package com.hotel.controller;

import com.hotel.DAO.HospedesDAO;
import com.hotel.DAO.ReservasDAO;
import com.hotel.RegistroHospedes;
import com.hotel.model.Hospedes;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

public class RegistroHospedesController {
    @FXML    private TextField nomeHospede;
    @FXML    private TextField sobrenomeHospede;
    @FXML    private TextField telefoneHospede;
    @FXML    private TextField numeroReserva;
    @FXML    private DatePicker dataNascimentoHospede;
    @FXML    private ComboBox<String> nacaoHospede;
    @FXML    private Button btFinalizar;

    public void initialize() {

        Pais.Country[] items = Pais.Country.values();
        for (Pais.Country item : items) {
            nacaoHospede.getItems().add(String.valueOf(item.getName()));
        }
        btFinalizar.setOnMouseClicked((MouseEvent e) -> {
            validaHospede();
        });

        numeroReserva.setText(String.valueOf(idReserva));
    }
    public void validaHospede() {
        if (!nomeHospede.getText().isBlank() && !sobrenomeHospede.getText().isBlank() &&
                !telefoneHospede.getText().isBlank() && !nacaoHospede.getSelectionModel().isEmpty() &&
                dataNascimentoHospede.getValue() != null) {
            registraHospede();
        } else {
            System.out.println("ERRO");
        }
    }
    public void registraHospede(){

        String nascimento = dataNascimentoHospede.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                nome = nomeHospede.getText(),
                sobrenome = sobrenomeHospede.getText(),
                nacionalidade = nacaoHospede.getValue(),
                telefone = telefoneHospede.getText();

        Hospedes h = new Hospedes(nome,sobrenome, nascimento, nacionalidade, telefone, idReserva);
        HospedesDAO dao = null;
        try {
            dao = new HospedesDAO();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        dao.add(h);
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setHeaderText("Reserva feita com Sucesso");
        Nav n = new Nav();
        n.options();
        a.show();
        RegistroHospedes.getStage().close();
    }
    int idReserva;
    ReservasDAO dao;
    {
        try {
            dao = new ReservasDAO();
            idReserva = dao.buscaId();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
