package br.com.EcoVolt.dao;

import br.com.EcoVolt.conexao.ConnectionFactory;
import br.com.EcoVolt.exception.EntidadeNaoEncontradaException;
import br.com.EcoVolt.model.Missao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class MissaoDao {

    public Missao cadastrar(Missao missao) throws SQLException {
        String sql = "INSERT INTO T_EV_MISSAO (ID_MISSAO, NM_MISSAO, DS_MISSAO, QT_PONTOS_PREMIO, DT_INICIO, DT_FIM) " +
                "VALUES (SQ_EV_MISSAO.NEXTVAL, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, new String[]{"ID_MISSAO"})) {
            stmt.setString(1, missao.getNome());
            stmt.setString(2, missao.getDescricao());
            stmt.setInt(3, missao.getPontosPremio());
            if (missao.getDataInicio() != null) {
                stmt.setDate(4, Date.valueOf(missao.getDataInicio()));
            } else {
                stmt.setNull(4, Types.DATE);
            }
            if (missao.getDataFim() != null) {
                stmt.setDate(5, Date.valueOf(missao.getDataFim()));
            } else {
                stmt.setNull(5, Types.DATE);
            }
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    missao.setId(rs.getInt(1));
                }
            }
            return missao;
        }
    }

    public List<Missao> listar() throws SQLException {
        List<Missao> lista = new ArrayList<>();
        String sql = "SELECT * FROM T_EV_MISSAO ORDER BY NM_MISSAO";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(montar(rs));
            }
        }
        return lista;
    }

    public Missao buscarPorId(int id) throws SQLException, EntidadeNaoEncontradaException {
        String sql = "SELECT * FROM T_EV_MISSAO WHERE ID_MISSAO = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (!rs.next()) {
                    throw new EntidadeNaoEncontradaException("Missao nao encontrada.");
                }
                return montar(rs);
            }
        }
    }

    public void atualizar(Missao missao) throws SQLException, EntidadeNaoEncontradaException {
        String sql = "UPDATE T_EV_MISSAO SET NM_MISSAO = ?, DS_MISSAO = ?, QT_PONTOS_PREMIO = ?, DT_INICIO = ?, DT_FIM = ? " +
                "WHERE ID_MISSAO = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, missao.getNome());
            stmt.setString(2, missao.getDescricao());
            stmt.setInt(3, missao.getPontosPremio());
            if (missao.getDataInicio() != null) {
                stmt.setDate(4, Date.valueOf(missao.getDataInicio()));
            } else {
                stmt.setNull(4, Types.DATE);
            }
            if (missao.getDataFim() != null) {
                stmt.setDate(5, Date.valueOf(missao.getDataFim()));
            } else {
                stmt.setNull(5, Types.DATE);
            }
            stmt.setInt(6, missao.getId());
            if (stmt.executeUpdate() == 0) {
                throw new EntidadeNaoEncontradaException("Missao nao encontrada para atualizacao.");
            }
        }
    }

    public void remover(int id) throws SQLException, EntidadeNaoEncontradaException {
        String sql = "DELETE FROM T_EV_MISSAO WHERE ID_MISSAO = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            if (stmt.executeUpdate() == 0) {
                throw new EntidadeNaoEncontradaException("Missao nao encontrada para remocao.");
            }
        }
    }

    private Missao montar(ResultSet rs) throws SQLException {
        Missao missao = new Missao();
        missao.setId(rs.getInt("ID_MISSAO"));
        missao.setNome(rs.getString("NM_MISSAO"));
        missao.setDescricao(rs.getString("DS_MISSAO"));
        missao.setPontosPremio(rs.getInt("QT_PONTOS_PREMIO"));
        Date inicio = rs.getDate("DT_INICIO");
        if (inicio != null) {
            missao.setDataInicio(inicio.toLocalDate());
        }
        Date fim = rs.getDate("DT_FIM");
        if (fim != null) {
            missao.setDataFim(fim.toLocalDate());
        }
        return missao;
    }
}
