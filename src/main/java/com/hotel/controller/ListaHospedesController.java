package com.hotel.controller;

import com.hotel.DAO.HospedesDAO;
import com.hotel.ListaHospedes;
import com.hotel.model.Hospedes;
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

public class ListaHospedesController implements Initializable {

    @FXML    private TableView<Hospedes> tabelaMostraHospedes;
    @FXML    private TableColumn<Hospedes, Integer> idColTabHospedes;
    @FXML    private TableColumn<Hospedes, String> nomeColTabHospedes;
    @FXML    private TableColumn<Hospedes, String> sobrenomeColTabHospedesaida;
    @FXML    private TableColumn<Hospedes, String> nascimentoColTabHospedes;
    @FXML    private TableColumn<Hospedes, String> nacionalidadeColTabHospedes;
    @FXML    private TableColumn<Hospedes, String> telefoneColTabHospedes;
    @FXML    private TableColumn<Hospedes, Integer> idReserva;
    @FXML    private TextField campoPesquisa;
    @FXML    private TextField edidNacao;
    @FXML    private TextField editNascimento;
    @FXML    private TextField editNome;
    @FXML    private TextField editSobrenome;
    @FXML    private TextField editTelefone;
    @FXML private Label labId;
    @FXML private Button btEditar;
    @FXML private Button btDel;
    @FXML private Button btOptions;

    private Hospedes select;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        initTable();

        tabelaMostraHospedes.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                select = (Hospedes) t1;
                mostraDetalhes();
            }
        });
        btEditar.setOnMouseClicked((MouseEvent e) -> {
            if (select != null) {
                confirmaEdicao();
            }
        });
        btDel.setOnMouseClicked((MouseEvent e) -> {
            if (select != null) {
                confirmaExclusao();
            }
        });
        btOptions.setOnMouseClicked((MouseEvent e) -> {
            Nav n = new Nav();
            n.options();
            ListaHospedes.getStage().close();
        });
    }
    public void initTable(){

        idColTabHospedes.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomeColTabHospedes.setCellValueFactory(new PropertyValueFactory<>("nome"));
        sobrenomeColTabHospedesaida.setCellValueFactory(new PropertyValueFactory<>("sobrenome"));
        nascimentoColTabHospedes.setCellValueFactory(new PropertyValueFactory<>("dataNascimento"));
        nacionalidadeColTabHospedes.setCellValueFactory(new PropertyValueFactory<>("nacionalidade"));
        telefoneColTabHospedes.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        idReserva.setCellValueFactory(new PropertyValueFactory<>("idReserva"));

        tabelaMostraHospedes.setItems(atualizaTabela());
    }

    public ObservableList<Hospedes> atualizaTabela() {

        HospedesDAO dao = null;
        try {
            dao = new HospedesDAO();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return FXCollections.observableArrayList(dao.getList());
    }
    public void pesquisa(){
        HospedesDAO dao = null;
        try {
            dao = new HospedesDAO();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ObservableList<Hospedes> mostraItem = FXCollections.observableArrayList(dao.getList());

        FilteredList<Hospedes> filteredData = new FilteredList<>(mostraItem, b -> true);

        campoPesquisa.textProperty().addListener((observable, oldValue, newValue) -> {

            filteredData.setPredicate(searchModel -> {
                if (newValue.isEmpty() || newValue.isBlank()) {
                    return true;
                }
                String searchKeyword = newValue.toLowerCase();

                if (searchModel.getId().toString().contains(searchKeyword)) {
                    return true;
                } else if (searchModel.getNome().toLowerCase().contains(searchKeyword))  {
                    return true;
                } else if (searchModel.getSobrenome().toLowerCase().contains(searchKeyword)) {
                    return true;
                } else if (searchModel.getDataNascimento().toLowerCase().contains(searchKeyword)) {
                    return true;
                } else if (searchModel.getNacionalidade().toLowerCase().contains(searchKeyword)) {
                    return true;
                } else if (searchModel.getTelefone().toLowerCase().contains(searchKeyword)) {
                    return true;
                } else return searchModel.getIdReserva().toString().contains(searchKeyword);
            });
        });

        SortedList<Hospedes> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(tabelaMostraHospedes.comparatorProperty());

        tabelaMostraHospedes.setItems(sortedData);
    }
    public void confirmaEdicao(){
        Alert al = new Alert(Alert.AlertType.CONFIRMATION);
        al.setTitle("Alteração de Hospede.");
        al.setHeaderText("Hospede\n" + editNome.getText() + " Id: " + labId.getText() + "\nSerá Alterado...");
        al.setContentText("Gostaria Realmente de Alterar?");

        Optional<ButtonType> result = al.showAndWait();

        if (result.get() == ButtonType.OK) {
            editaHospede();
        }
    }
    public void confirmaExclusao(){

        Alert al = new Alert(Alert.AlertType.CONFIRMATION);
        al.setTitle("Exclusão de Hospede.");
        al.setHeaderText("Hospede\n" + editNome.getText() + " Id: " + labId.getText() + "\nSerá Excluído...");
        al.setContentText("Gostaria Realmente de Excluir?");

        Optional<ButtonType> result = al.showAndWait();

        if (result.get() == ButtonType.OK) {
            deletaHospedes();
        }
    }

    public void deletaHospedes(){

        if (select != null) {
            HospedesDAO dao = null;
            try {
                dao = new HospedesDAO();
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
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setHeaderText("Erro Ao Excluir");
            al.show();
        }

    }
    public void editaHospede(){



        int id = Integer.parseInt(labId.getText());
        String nome = editNome.getText();
        String  sobrenome = editSobrenome.getText();
        String nascimento = editNascimento.getText();
        String  nacionalidade = edidNacao.getText();
        String  telefone = editTelefone.getText();

        HospedesDAO dao = null;
        try {
            dao = new HospedesDAO();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Hospedes h = new Hospedes(id, nome, sobrenome, nascimento, nacionalidade, telefone);

        if (dao.update(h)) {
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

    public void limpaCampos(){
        labId.setText("");
        editNome.setText("");
        editSobrenome.setText("");
        editNascimento.setText("");
        edidNacao.setText("");
        editTelefone.setText("");
        campoPesquisa.setText("");
    }
    public void mostraDetalhes(){
        if (select != null){
            labId.setText(select.getId().toString());
            editNome.setText(select.getNome());
            editSobrenome.setText(select.getSobrenome());
            editNascimento.setText(select.getDataNascimento());
            edidNacao.setText(select.getNacionalidade());
            editTelefone.setText(select.getTelefone());
        }
        else{
            limpaCampos();
        }
    }
}
