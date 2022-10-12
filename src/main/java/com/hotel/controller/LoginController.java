package com.hotel.controller;

import com.hotel.DAO.LoginDAO;
import com.hotel.Login;
import com.hotel.model.LoginModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class LoginController {
    @FXML    private Button btEntrarLogin;
    @FXML    private Button btFecharLogin;
    @FXML    private Label labelLogin;
    @FXML    private PasswordField txtSenhaLogin;
    @FXML    private TextField txtUsuarioLogin;

    public void initialize() {
        btEntrarLogin.setOnMouseClicked((MouseEvent e) -> {
            logar();
        });

        btFecharLogin.setOnMouseClicked((MouseEvent e) -> {
            Login.getStage().close();
        });
    }
    public void logar() {

        LoginDAO dao = null;
        try {
            dao = new LoginDAO();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        List<LoginModel> logins = dao.getList();

        for (int i = 0; i < logins.size(); i++) {
            if (txtUsuarioLogin.getText().equals(logins.get(i).getUsuario()) && txtSenhaLogin.getText().equals(logins.get(i).getSenha())) {
                Nav n = new Nav();
                n.options();

                Login.getStage().close();
            }
            else{
                labelLogin.setText("Login Falhou. Tente: admin admin");
            }


        }
    }
}
