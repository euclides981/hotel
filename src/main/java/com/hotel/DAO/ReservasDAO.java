package com.hotel.DAO;

import com.hotel.JDBC.ConnectaDb;
import com.hotel.model.Reservas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReservasDAO {
    private Connection conn;

    public ReservasDAO() throws SQLException {
        this.conn = new ConnectaDb().getDBConnection();
    }
    public boolean add(Reservas re){
        String sql = "insert into Reservas (data_entrada, data_saida, qtd_dias, valor, suite, forma_pagamento) values (?,?,?,?,?,?);";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, re.getEntrada());
            stmt.setString(2, re.getSaida());
            stmt.setInt(3, re.getDias());
            stmt.setInt(4, re.getValorTotal());
            stmt.setString(5, re.getSuite());
            stmt.setString(6, re.getFormPag());

            stmt.execute();

            stmt.close();
            conn.close();
            return true;

        } catch (SQLException e) {
            Logger.getLogger(ReservasDAO.class.getName()).log(Level.SEVERE,null,e);
            e.printStackTrace();
            return false;
        }
    }
    public List<Reservas> getList(){

        List<Reservas> reservas = new ArrayList<>();
        String sql = "select * from Reservas;";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet res = stmt.executeQuery();

            while(res.next()){

                Reservas r = new Reservas();
                r.setId(res.getInt("id_reserva"));
                r.setEntrada(res.getString("data_entrada"));
                r.setSaida(res.getString("data_saida"));
                r.setDias(res.getInt("qtd_dias"));
                r.setValorTotal(res.getInt("valor"));
                r.setSuite(res.getString("suite"));
                r.setFormPag(res.getString("forma_pagamento"));

                reservas.add(r);
            }

            stmt.close();
            res.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("erro na lista");
            return null;
        }
        return reservas;
    }

    public boolean update(Reservas r){
        String sql = "update Reservas set data_entrada = ?, data_saida = ?, qtd_dias = ?, valor = ?, suite = ?, forma_pagamento =? where id_reserva = ?;";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, r.getEntrada());
            stmt.setString(2, r.getSaida());
            stmt.setInt(3, r.getDias());
            stmt.setInt(4, r.getValorTotal());
            stmt.setString(5, r.getSuite());
            stmt.setString(6, r.getFormPag());
            stmt.setInt(7, r.getId());
            stmt.execute();
            stmt.close();
            conn.close();
            return true;

        } catch (SQLException e) {
            Logger.getLogger(HospedesDAO.class.getName()).log(Level.SEVERE,null,e);
            return false;
        }
    }
    public boolean delete(Reservas r){
        String sql = "delete from Reservas where id_reserva = ?;";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, r.getId());
            stmt.execute();
            stmt.close();
            conn.close();
            return true;

        } catch (SQLException e) {
            Logger.getLogger(HospedesDAO.class.getName()).log(Level.SEVERE,null,e);
            return false;
        }
    }

    public int buscaId() throws SQLException {
        String sql = "SELECT id_reserva FROM Reservas ORDER BY id_reserva DESC LIMIT 1";

        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet res = stmt.executeQuery();

        Integer IdReserva = null;
        while (res.next()) {
            IdReserva = res.getInt("id_reserva");
        }
        return IdReserva;
    }
}
