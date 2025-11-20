package org.LP2.DAO;    

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.LP2.BD.Database;
import org.LP2.Olhos;

public class OlhosDAO {

    public void salvar(Olhos olhos) {
        if (olhos.getId() == null) {
            inserir(olhos);
        } else {
            atualizar(olhos);
        }
    }

    private void inserir(Olhos olhos) {
        String sql = "INSERT INTO olhos (cor, aberto, nitidez) VALUES (?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, olhos.getCor());
            ps.setInt(2, olhos.isAberto() ? 1 : 0);
            ps.setInt(3, olhos.getNitidez());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    olhos.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void atualizar(Olhos olhos) {
        String sql = "UPDATE olhos SET cor = ?, aberto = ?, nitidez = ? WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, olhos.getCor());
            ps.setInt(2, olhos.isAberto() ? 1 : 0);
            ps.setInt(3, olhos.getNitidez());
            ps.setInt(4, olhos.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletar(Olhos olhos) {
        if (olhos.getId() == null) return;

        String sql = "DELETE FROM olhos WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, olhos.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Olhos> listarTodos() {
        List<Olhos> lista = new ArrayList<>();
        String sql = "SELECT id, cor, aberto, nitidez FROM olhos";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Olhos o = new Olhos(
                        rs.getInt("id"),
                        rs.getString("cor"),
                        rs.getInt("aberto") == 1,
                        rs.getInt("nitidez")
                );
                lista.add(o);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
