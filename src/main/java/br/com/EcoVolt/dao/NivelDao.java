package br.com.EcoVolt.dao;

import br.com.EcoVolt.conexao.ConnectionFactory;
import br.com.EcoVolt.exception.EntidadeNaoEncontradaException;
import br.com.EcoVolt.model.Nivel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NivelDao {

    public List<Nivel> listar() throws SQLException {
        List<Nivel> lista = new ArrayList<>();
        String sql = "SELECT * FROM T_EV_NIVEL ORDER BY NR_XP_MINIMA";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(montar(rs));
            }
        }
        return lista;
    }

    public Nivel buscarPorId(int id) throws SQLException, EntidadeNaoEncontradaException {
        String sql = "SELECT * FROM T_EV_NIVEL WHERE ID_NIVEL = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (!rs.next()) {
                    throw new EntidadeNaoEncontradaException("Nivel nao encontrado.");
                }
                return montar(rs);
            }
        }
    }

    public Nivel buscarPorPontos(int pontos) throws SQLException {
        String sql = "SELECT * FROM T_EV_NIVEL WHERE ? BETWEEN NR_XP_MINIMA AND NR_XP_MAXIMA";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, pontos);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return montar(rs);
                }
            }
        }
        return null;
    }

    private Nivel montar(ResultSet rs) throws SQLException {
        Nivel nivel = new Nivel();
        nivel.setId(rs.getInt("ID_NIVEL"));
        nivel.setNome(rs.getString("NM_NIVEL"));
        nivel.setXpMinima(rs.getInt("NR_XP_MINIMA"));
        nivel.setXpMaxima(rs.getInt("NR_XP_MAXIMA"));
        return nivel;
    }
}
