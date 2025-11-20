package org.LP2.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.LP2.Alma;
import org.LP2.BD.Database;

public class AlmaDAO {

    public void salvar(Alma alma) {
        if (alma.getId() == null) {
            inserir(alma);
        } else {
            atualizar(alma);
        }
    }

    private void inserir(Alma alma) {
        String sql = "INSERT INTO alma (estado, temperatura, conexao) VALUES (?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, alma.getEstado());
            ps.setInt(2, alma.getTemperatura());
            ps.setInt(3, alma.getConexao());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    alma.setId(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void atualizar(Alma alma) {
        String sql = "UPDATE alma SET estado = ?, temperatura = ?, conexao = ? WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, alma.getEstado());
            ps.setInt(2, alma.getTemperatura());
            ps.setInt(3, alma.getConexao());
            ps.setInt(4, alma.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletar(Alma alma) {
        if (alma.getId() == null) return;

        String sql = "DELETE FROM alma WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, alma.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Alma> listarTodos() {
        List<Alma> lista = new ArrayList<>();
        String sql = "SELECT id, estado, temperatura, conexao FROM alma";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Alma a = new Alma(
                        rs.getInt("id"),
                        rs.getString("estado"),
                        rs.getInt("temperatura"),
                        rs.getInt("conexao")
                );
                lista.add(a);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
