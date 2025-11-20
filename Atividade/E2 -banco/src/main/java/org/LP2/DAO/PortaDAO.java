package org.LP2.DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.LP2.BD.Database;
import org.LP2.Porta;

public class PortaDAO {

    public void salvar(Porta porta) {
        if (porta.getId() == null) {
            inserir(porta);
        } else {
            atualizar(porta);
        }
    }

    private void inserir(Porta porta) {
        String sql = "INSERT INTO porta (cor, estado, destino) VALUES (?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, porta.getCor());
            ps.setString(2, porta.getEstado());
            ps.setString(3, porta.getDestino());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    porta.setId(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void atualizar(Porta porta) {
        String sql = "UPDATE porta SET cor = ?, estado = ?, destino = ? WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, porta.getCor());
            ps.setString(2, porta.getEstado());
            ps.setString(3, porta.getDestino());
            ps.setInt(4, porta.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletar(Porta porta) {
        if (porta.getId() == null) return;

        String sql = "DELETE FROM porta WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, porta.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Porta> listarTodos() {
        List<Porta> lista = new ArrayList<>();
        String sql = "SELECT id, cor, estado, destino FROM porta";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Porta p = new Porta(
                        rs.getInt("id"),
                        rs.getString("cor"),
                        rs.getString("estado"),
                        rs.getString("destino")
                );
                lista.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
