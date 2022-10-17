package com.hotel.controller;

import com.hotel.DAO.ReservasDAO;
import com.hotel.ListaReservas;
import com.hotel.model.Reservas;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class ListaReservasController implements Initializable {
    @FXML    private TableView<Reservas> tabReservas;
    @FXML    private TableColumn<Reservas, Integer> tabId;
    @FXML    private TableColumn<Reservas, String> tabEntrada;
    @FXML    private TableColumn<Reservas, String> tabSaida;
    @FXML    private TableColumn<Reservas, Integer> tabDias;
    @FXML    private TableColumn<Reservas, Integer> tabValor;
    @FXML    private TableColumn<Reservas, String> tabSuite;
    @FXML    private TableColumn<Reservas, String> tabPag;
    @FXML    private TextField editEntrada;
    @FXML    private TextField editSaida;
    @FXML    private TextField editDias;
    @FXML    private TextField editValor;
    @FXML    private TextField editSuite;
    @FXML    private TextField editFormPag;
    @FXML    private Label labIdReserva;
    @FXML    private TextField campoPesquisa;
    @FXML    private Button btDeletar;
    @FXML    private Button btEditar;
    @FXML    private Button btOptions;
    private Reservas select;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initTable();
        tabReservas.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                select = (Reservas) t1;
                mostraDetalhes();
            }
        });
        btDeletar.setOnMouseClicked((MouseEvent e) -> {
            if (select != null) {
                confirmaExclusao();
            }
        });
        btEditar.setOnMouseClicked((MouseEvent e) -> {
            if (select != null) {
                confirmaEdicao();
            }
        });
        btOptions.setOnMouseClicked((MouseEvent e) -> {
            Nav n = new Nav();
            n.options();
            ListaReservas.getStage().close();
        });
    }
    public void initTable(){
        tabId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tabEntrada.setCellValueFactory(new PropertyValueFactory<>("entrada"));
        tabSaida.setCellValueFactory(new PropertyValueFactory<>("saida"));
        tabDias.setCellValueFactory(new PropertyValueFactory<>("dias"));
        tabValor.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));
        tabSuite.setCellValueFactory(new PropertyValueFactory<>("suite"));
        tabPag.setCellValueFactory(new PropertyValueFactory<>("formPag"));
        tabReservas.setItems(atualizaTabela());
    }
    public void editaReserva(){

        int id = Integer.parseInt(labIdReserva.getText());
        String entrada = editEntrada.getText();
        String saida = editSaida.getText();
        int  dias = Integer.parseInt(editDias.getText());
        int  valorTotal = Integer.parseInt(editValor.getText());
        String  suite = editSuite.getText();
        String  formPag = editFormPag.getText();

        ReservasDAO dao = null;
        try {
            dao = new ReservasDAO();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Reservas r = new Reservas(id, entrada, saida, dias, valorTotal, suite, formPag);

        if (dao.update(r)) {
            Alert al = new Alert(Alert.AlertType.INFORMATION);
            al.setHeaderText("Alterado com Sucesso");
            al.show();
        }
        else{
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setHeaderText("Erro ao Cadastrar");
            al.show();
        }
        initTable();
    }
    public void deletaReserva(){
        if (select != null) {
            ReservasDAO dao = null;
            try {
                dao = new ReservasDAO();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            dao.delete(select);
            limpaCampos();
            initTable();
            Alert al = new Alert(Alert.AlertType.INFORMATION);
            al.setHeaderText("Excluído com Sucesso");
            al.show();
        }
        else{
            Alert al = new Alert(Alert.AlertType.INFORMATION);
            al.setHeaderText("Erro ao Deletar");
            al.show();
        }

    }
    public ObservableList<Reservas> atualizaTabela() {

        ReservasDAO dao = null;
        try {
            dao = new ReservasDAO();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return FXCollections.observableArrayList(dao.getList());
    }
    public void pesquisa(){
        ReservasDAO dao = null;
        try {
            dao = new ReservasDAO();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ObservableList<Reservas> mostraItem = FXCollections.observableArrayList(dao.getList());

        FilteredList<Reservas> filteredData = new FilteredList<>(mostraItem, b -> true);

        campoPesquisa.textProperty().addListener((observable, oldValue, newValue) -> {

            filteredData.setPredicate(searchModel -> {
                if (newValue.isEmpty() || newValue.isBlank()) {
                    return true;
                }
                String searchKeyword = newValue.toLowerCase();

                if (searchModel.getId().toString().contains(searchKeyword)) {
                    return true;
                } else if (searchModel.getEntrada().toLowerCase().contains(searchKeyword))  {
                    return true;
                } else if (searchModel.getSaida().toLowerCase().contains(searchKeyword)) {
                    return true;
                } else if (searchModel.getDias().toString().contains(searchKeyword)) {
                    return true;
                } else if (searchModel.getValorTotal().toString().contains(searchKeyword)) {
                    return true;
                } else if (searchModel.getSuite().toLowerCase().contains(searchKeyword)) {
                    return true;
                } else if (searchModel.getFormPag().toLowerCase().contains(searchKeyword)) {
                    return true;
                }else {
                    return false;
                }

            });
        });

        SortedList<Reservas> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(tabReservas.comparatorProperty());

        tabReservas.setItems(sortedData);
    }
    public void mostraDetalhes(){
        if (select != null){
            labIdReserva.setText(select.getId().toString());
            editEntrada.setText(select.getEntrada());
            editSaida.setText(select.getSaida());
            editDias.setText(select.getDias().toString());
            editValor.setText(select.getValorTotal().toString());
            editSuite.setText(select.getSuite());
            editFormPag.setText(select.getFormPag());
        }
        else{
            limpaCampos();
        }
    }
    public void limpaCampos(){
        labIdReserva.setText("");
        editEntrada.setText("");
        editSaida.setText("");
        editDias.setText("");
        editValor.setText("");
        editSuite.setText("");
        editFormPag.setText("");
        campoPesquisa.setText("");
    }

    public void confirmaEdicao(){
        Alert al = new Alert(Alert.AlertType.CONFIRMATION);
        al.setTitle("Alteração de Reserva.");
        al.setHeaderText("A reserva com Id\n" + labIdReserva.getText() + "\nSerá Alterada...");
        al.setContentText("Gostaria Realmente de Alterar?");

        Optional<ButtonType> result = al.showAndWait();

        if (result.get() == ButtonType.OK) {
            editaReserva();
        }
    }
    public void confirmaExclusao(){

        Alert al = new Alert(Alert.AlertType.CONFIRMATION);
        al.setTitle("Exclusão de Hospede.");
        al.setHeaderText("Reserva\n" + labIdReserva.getText() + "\nSerá Excluída...");
        al.setContentText("Gostaria Realmente de Excluir?");

        Optional<ButtonType> result = al.showAndWait();

        if (result.get() == ButtonType.OK) {
            deletaReserva();
        }
    }
}