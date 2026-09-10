package br.com.EcoVolt.dao;

import br.com.EcoVolt.conexao.ConnectionFactory;
import br.com.EcoVolt.exception.EntidadeNaoEncontradaException;
import br.com.EcoVolt.model.ContaEnergia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class ContaEnergiaDao {

    public ContaEnergia cadastrar(ContaEnergia conta) throws SQLException {
        String sql = "INSERT INTO T_EV_CONTA_ENERGIA (ID_CONTA_ENERGIA, ID_USUARIO, DT_REFERENCIA, NR_LEITURA, " +
                "VL_CONSUMO_KWH, VL_TOTAL_CONTA, DS_COMPROVANTE, ST_PAGAMENTO) " +
                "VALUES (SQ_EV_CONTA_ENERGIA.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, new String[]{"ID_CONTA_ENERGIA"})) {
            stmt.setInt(1, conta.getIdUsuario());
            stmt.setDate(2, Date.valueOf(conta.getDataReferencia()));
            if (conta.getNumeroLeitura() != null) {
                stmt.setInt(3, conta.getNumeroLeitura());
            } else {
                stmt.setNull(3, Types.NUMERIC);
            }
            stmt.setDouble(4, conta.getConsumoKwh());
            stmt.setDouble(5, conta.getValorTotal());
            stmt.setString(6, conta.getComprovante());
            stmt.setString(7, conta.getStatusPagamento());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    conta.setId(rs.getInt(1));
                }
            }
            return conta;
        }
    }

    public List<ContaEnergia> listar() throws SQLException {
        List<ContaEnergia> lista = new ArrayList<>();
        String sql = "SELECT * FROM T_EV_CONTA_ENERGIA ORDER BY DT_REFERENCIA DESC";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(montar(rs));
            }
        }
        return lista;
    }

    public List<ContaEnergia> listarPorUsuario(int idUsuario) throws SQLException {
        List<ContaEnergia> lista = new ArrayList<>();
        String sql = "SELECT * FROM T_EV_CONTA_ENERGIA WHERE ID_USUARIO = ? ORDER BY DT_REFERENCIA DESC";

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

    public ContaEnergia buscarPorId(int id) throws SQLException, EntidadeNaoEncontradaException {
        String sql = "SELECT * FROM T_EV_CONTA_ENERGIA WHERE ID_CONTA_ENERGIA = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (!rs.next()) {
                    throw new EntidadeNaoEncontradaException("Conta de energia nao encontrada.");
                }
                return montar(rs);
            }
        }
    }

    public void atualizar(ContaEnergia conta) throws SQLException, EntidadeNaoEncontradaException {
        String sql = "UPDATE T_EV_CONTA_ENERGIA SET ID_USUARIO = ?, DT_REFERENCIA = ?, NR_LEITURA = ?, " +
                "VL_CONSUMO_KWH = ?, VL_TOTAL_CONTA = ?, DS_COMPROVANTE = ?, ST_PAGAMENTO = ? " +
                "WHERE ID_CONTA_ENERGIA = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, conta.getIdUsuario());
            stmt.setDate(2, Date.valueOf(conta.getDataReferencia()));
            if (conta.getNumeroLeitura() != null) {
                stmt.setInt(3, conta.getNumeroLeitura());
            } else {
                stmt.setNull(3, Types.NUMERIC);
            }
            stmt.setDouble(4, conta.getConsumoKwh());
            stmt.setDouble(5, conta.getValorTotal());
            stmt.setString(6, conta.getComprovante());
            stmt.setString(7, conta.getStatusPagamento());
            stmt.setInt(8, conta.getId());
            if (stmt.executeUpdate() == 0) {
                throw new EntidadeNaoEncontradaException("Conta de energia nao encontrada para atualizacao.");
            }
        }
    }

    public void remover(int id) throws SQLException, EntidadeNaoEncontradaException {
        String sql = "DELETE FROM T_EV_CONTA_ENERGIA WHERE ID_CONTA_ENERGIA = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            if (stmt.executeUpdate() == 0) {
                throw new EntidadeNaoEncontradaException("Conta de energia nao encontrada para remocao.");
            }
        }
    }

    public void removerPorUsuario(int idUsuario) throws SQLException {
        String sql = "DELETE FROM T_EV_CONTA_ENERGIA WHERE ID_USUARIO = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            stmt.executeUpdate();
        }
    }

    private ContaEnergia montar(ResultSet rs) throws SQLException {
        ContaEnergia conta = new ContaEnergia();
        conta.setId(rs.getInt("ID_CONTA_ENERGIA"));
        conta.setIdUsuario(rs.getInt("ID_USUARIO"));
        conta.setDataReferencia(rs.getDate("DT_REFERENCIA").toLocalDate());
        int leitura = rs.getInt("NR_LEITURA");
        if (!rs.wasNull()) {
            conta.setNumeroLeitura(leitura);
        }
        conta.setConsumoKwh(rs.getDouble("VL_CONSUMO_KWH"));
        conta.setValorTotal(rs.getDouble("VL_TOTAL_CONTA"));
        conta.setComprovante(rs.getString("DS_COMPROVANTE"));
        conta.setStatusPagamento(rs.getString("ST_PAGAMENTO"));
        return conta;
    }
}
