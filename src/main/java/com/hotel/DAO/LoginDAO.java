package com.hotel.DAO;

import com.hotel.JDBC.ConnectaDb;
import com.hotel.model.LoginModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginDAO {

    private Connection conn;

    public LoginDAO() throws SQLException {
        this.conn = new ConnectaDb().getDBConnection();
    }

    public List<LoginModel> getList() {

        List<LoginModel> logins = new ArrayList<>();
        String sql = "SELECT * FROM Usuarios;";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                LoginModel l = new LoginModel();
                l.setId(rs.getInt("id"));
                l.setUsuario(rs.getString("username"));
                l.setSenha(rs.getString("password"));

                logins.add(l);
            }
            ps.close();
            rs.close();
            conn.close();

            return logins;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("erro na lista");
            return null;
        }
    }
}
