package br.com.EcoVolt.dao;

import br.com.EcoVolt.conexao.ConnectionFactory;
import br.com.EcoVolt.exception.EntidadeNaoEncontradaException;
import br.com.EcoVolt.model.Pontuacao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PontuacaoDao {

    public Pontuacao cadastrar(Pontuacao pontuacao) throws SQLException {
        String sql = "INSERT INTO T_EV_PONTUACAO (ID_PONTUACAO, ID_USUARIO, DT_GANHO, QT_PONTOS, DS_MOTIVO) " +
                "VALUES (SQ_EV_PONTUACAO.NEXTVAL, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, new String[]{"ID_PONTUACAO"})) {
            stmt.setInt(1, pontuacao.getIdUsuario());
            stmt.setDate(2, Date.valueOf(pontuacao.getDataGanho()));
            stmt.setInt(3, pontuacao.getPontos());
            stmt.setString(4, pontuacao.getMotivo());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    pontuacao.setId(rs.getInt(1));
                }
            }
            return pontuacao;
        }
    }

    public List<Pontuacao> listar() throws SQLException {
        List<Pontuacao> lista = new ArrayList<>();
        String sql = "SELECT * FROM T_EV_PONTUACAO ORDER BY DT_GANHO DESC";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(montar(rs));
            }
        }
        return lista;
    }

    public List<Pontuacao> listarPorUsuario(int idUsuario) throws SQLException {
        List<Pontuacao> lista = new ArrayList<>();
        String sql = "SELECT * FROM T_EV_PONTUACAO WHERE ID_USUARIO = ? ORDER BY DT_GANHO DESC";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(montar(rs));
                }
            }
        }
        return lista;
    }

    public Pontuacao buscarPorId(int id) throws SQLException, EntidadeNaoEncontradaException {
        String sql = "SELECT * FROM T_EV_PONTUACAO WHERE ID_PONTUACAO = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (!rs.next()) {
                    throw new EntidadeNaoEncontradaException("Pontuacao nao encontrada.");
                }
                return montar(rs);
            }
        }
    }

    public void atualizar(Pontuacao pontuacao) throws SQLException, EntidadeNaoEncontradaException {
        String sql = "UPDATE T_EV_PONTUACAO SET ID_USUARIO = ?, DT_GANHO = ?, QT_PONTOS = ?, DS_MOTIVO = ? WHERE ID_PONTUACAO = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, pontuacao.getIdUsuario());
            stmt.setDate(2, Date.valueOf(pontuacao.getDataGanho()));
            stmt.setInt(3, pontuacao.getPontos());
            stmt.setString(4, pontuacao.getMotivo());
            stmt.setInt(5, pontuacao.getId());
            if (stmt.executeUpdate() == 0) {
                throw new EntidadeNaoEncontradaException("Pontuacao nao encontrada para atualizacao.");
            }
        }
    }

    public void remover(int id) throws SQLException, EntidadeNaoEncontradaException {
        String sql = "DELETE FROM T_EV_PONTUACAO WHERE ID_PONTUACAO = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            if (stmt.executeUpdate() == 0) {
                throw new EntidadeNaoEncontradaException("Pontuacao nao encontrada para remocao.");
            }
        }
    }

    public void removerPorUsuario(int idUsuario) throws SQLException {
        String sql = "DELETE FROM T_EV_PONTUACAO WHERE ID_USUARIO = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            stmt.executeUpdate();
        }
    }

    private Pontuacao montar(ResultSet rs) throws SQLException {
        Pontuacao pontuacao = new Pontuacao();
        pontuacao.setId(rs.getInt("ID_PONTUACAO"));
        pontuacao.setIdUsuario(rs.getInt("ID_USUARIO"));
        pontuacao.setDataGanho(rs.getDate("DT_GANHO").toLocalDate());
        pontuacao.setPontos(rs.getInt("QT_PONTOS"));
        pontuacao.setMotivo(rs.getString("DS_MOTIVO"));
        return pontuacao;
    }
}
