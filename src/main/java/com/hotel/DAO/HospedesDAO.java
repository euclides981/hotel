package com.hotel.DAO;

import com.hotel.JDBC.ConnectaDb;
import com.hotel.model.Hospedes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HospedesDAO {
    private Connection conn;

    public HospedesDAO() throws SQLException {
        this.conn = new ConnectaDb().getDBConnection();
    }
    public boolean add(Hospedes ho){
        String sql = "insert into Hospedes (Nome, Sobrenome, Nascimento, Nacionalidade, telefone, idReserva) values (?,?,?,?,?,?);";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, ho.getNome());
            stmt.setString(2, ho.getSobrenome());
            stmt.setString(3, ho.getDataNascimento());
            stmt.setString(4, ho.getNacionalidade());
            stmt.setString(5, ho.getTelefone());
            stmt.setInt(6,ho.getIdReserva());

            stmt.execute();

            stmt.close();
            conn.close();
            return true;

        } catch (SQLException e) {
            Logger.getLogger(HospedesDAO.class.getName()).log(Level.SEVERE,null,e);
            e.printStackTrace();
            return false;
        }
    }

    public List<Hospedes> getList(){

        List<Hospedes> hospedes = new ArrayList<>();
        String sql = "select * from Hospedes;";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet res = stmt.executeQuery();

            while(res.next()){
                Hospedes h = new Hospedes();
                h.setId(res.getInt("idHospede"));
                h.setNome(res.getString("Nome"));
                h.setSobrenome(res.getString("Sobrenome"));
               h.setDataNascimento(res.getString("Nascimento"));
                h.setNacionalidade(res.getString("Nacionalidade"));
                h.setTelefone(res.getString("Telefone"));
                h.setIdReserva(res.getInt("idReserva"));

                hospedes.add(h);
            }

            stmt.close();
            res.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("erro na lista");
            return null;
        }
        return hospedes;
    }

    public boolean update(Hospedes h){
        String sql = "update Hospedes set nome = ?, sobrenome = ?, nascimento = ?, nacionalidade = ?, telefone = ? where idHospede = ?;";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, h.getNome());
            stmt.setString(2, h.getSobrenome());
            stmt.setString(3, h.getDataNascimento());
            stmt.setString(4, h.getNacionalidade());
            stmt.setString(5, h.getTelefone());
            stmt.setInt(6, h.getId());
            stmt.execute();
            stmt.close();
            conn.close();
            return true;

        } catch (SQLException e) {
            Logger.getLogger(HospedesDAO.class.getName()).log(Level.SEVERE,null,e);
            return false;
        }
    }
    public boolean delete(Hospedes h){
        String sql = "delete from Hospedes where idHospede = ?;";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, h.getId());
            stmt.execute();
            stmt.close();
            conn.close();
            return true;

        } catch (SQLException e) {
            Logger.getLogger(HospedesDAO.class.getName()).log(Level.SEVERE,null,e);
            return false;
        }
    }

}
