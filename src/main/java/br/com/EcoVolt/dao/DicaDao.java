package br.com.EcoVolt.dao;

import br.com.EcoVolt.conexao.ConnectionFactory;
import br.com.EcoVolt.exception.EntidadeNaoEncontradaException;
import br.com.EcoVolt.model.Dica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DicaDao {

    public Dica cadastrar(Dica dica) throws SQLException {
        String sql = "INSERT INTO T_EV_DICA (ID_DICA, NM_DICA, DS_DICA, ID_USUARIO_AUTOR) " +
                "VALUES (SQ_EV_DICA.NEXTVAL, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, new String[]{"ID_DICA"})) {
            stmt.setString(1, dica.getNome());
            stmt.setString(2, dica.getDescricao());
            stmt.setInt(3, dica.getIdUsuarioAutor());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    dica.setId(rs.getInt(1));
                }
            }
            return dica;
        }
    }

    public List<Dica> listar() throws SQLException {
        List<Dica> lista = new ArrayList<>();
        String sql = "SELECT * FROM T_EV_DICA ORDER BY NM_DICA";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(montar(rs));
            }
        }
        return lista;
    }

    public Dica buscarPorId(int id) throws SQLException, EntidadeNaoEncontradaException {
        String sql = "SELECT * FROM T_EV_DICA WHERE ID_DICA = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (!rs.next()) {
                    throw new EntidadeNaoEncontradaException("Dica nao encontrada.");
                }
                return montar(rs);
            }
        }
    }

    public void atualizar(Dica dica) throws SQLException, EntidadeNaoEncontradaException {
        String sql = "UPDATE T_EV_DICA SET NM_DICA = ?, DS_DICA = ?, ID_USUARIO_AUTOR = ? WHERE ID_DICA = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, dica.getNome());
            stmt.setString(2, dica.getDescricao());
            stmt.setInt(3, dica.getIdUsuarioAutor());
            stmt.setInt(4, dica.getId());
            if (stmt.executeUpdate() == 0) {
                throw new EntidadeNaoEncontradaException("Dica nao encontrada para atualizacao.");
            }
        }
    }

    public void remover(int id) throws SQLException, EntidadeNaoEncontradaException {
        String sql = "DELETE FROM T_EV_DICA WHERE ID_DICA = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            if (stmt.executeUpdate() == 0) {
                throw new EntidadeNaoEncontradaException("Dica nao encontrada para remocao.");
            }
        }
    }

    private Dica montar(ResultSet rs) throws SQLException {
        Dica dica = new Dica();
        dica.setId(rs.getInt("ID_DICA"));
        dica.setNome(rs.getString("NM_DICA"));
        dica.setDescricao(rs.getString("DS_DICA"));
        dica.setIdUsuarioAutor(rs.getInt("ID_USUARIO_AUTOR"));
        return dica;
    }
}
