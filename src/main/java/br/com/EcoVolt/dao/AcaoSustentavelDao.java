package br.com.EcoVolt.dao;

import br.com.EcoVolt.conexao.ConnectionFactory;
import br.com.EcoVolt.exception.EntidadeNaoEncontradaException;
import br.com.EcoVolt.model.AcaoSustentavel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AcaoSustentavelDao {

    public AcaoSustentavel cadastrar(AcaoSustentavel acao) throws SQLException {
        String sql = "INSERT INTO T_EV_ACA_SUSTENTAVEIS (ID_ACAO, NM_ACAO, DS_ACAO, QT_PONTOS_BASE) " +
                "VALUES (SQ_EV_ACAO.NEXTVAL, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, new String[]{"ID_ACAO"})) {
            stmt.setString(1, acao.getNome());
            stmt.setString(2, acao.getDescricao());
            stmt.setInt(3, acao.getPontosBase());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    acao.setId(rs.getInt(1));
                }
            }
            return acao;
        }
    }

    public List<AcaoSustentavel> listar() throws SQLException {
        List<AcaoSustentavel> lista = new ArrayList<>();
        String sql = "SELECT * FROM T_EV_ACA_SUSTENTAVEIS ORDER BY NM_ACAO";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(montar(rs));
            }
        }
        return lista;
    }

    public AcaoSustentavel buscarPorId(int id) throws SQLException, EntidadeNaoEncontradaException {
        String sql = "SELECT * FROM T_EV_ACA_SUSTENTAVEIS WHERE ID_ACAO = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (!rs.next()) {
                    throw new EntidadeNaoEncontradaException("Acao sustentavel nao encontrada.");
                }
                return montar(rs);
            }
        }
    }

    public void atualizar(AcaoSustentavel acao) throws SQLException, EntidadeNaoEncontradaException {
        String sql = "UPDATE T_EV_ACA_SUSTENTAVEIS SET NM_ACAO = ?, DS_ACAO = ?, QT_PONTOS_BASE = ? WHERE ID_ACAO = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, acao.getNome());
            stmt.setString(2, acao.getDescricao());
            stmt.setInt(3, acao.getPontosBase());
            stmt.setInt(4, acao.getId());
            if (stmt.executeUpdate() == 0) {
                throw new EntidadeNaoEncontradaException("Acao sustentavel nao encontrada para atualizacao.");
            }
        }
    }

    public void remover(int id) throws SQLException, EntidadeNaoEncontradaException {
        String sql = "DELETE FROM T_EV_ACA_SUSTENTAVEIS WHERE ID_ACAO = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            if (stmt.executeUpdate() == 0) {
                throw new EntidadeNaoEncontradaException("Acao sustentavel nao encontrada para remocao.");
            }
        }
    }

    private AcaoSustentavel montar(ResultSet rs) throws SQLException {
        AcaoSustentavel acao = new AcaoSustentavel();
        acao.setId(rs.getInt("ID_ACAO"));
        acao.setNome(rs.getString("NM_ACAO"));
        acao.setDescricao(rs.getString("DS_ACAO"));
        acao.setPontosBase(rs.getInt("QT_PONTOS_BASE"));
        return acao;
    }
}
