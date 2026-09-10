package br.com.EcoVolt.dao;

import br.com.EcoVolt.conexao.ConnectionFactory;
import br.com.EcoVolt.exception.EntidadeNaoEncontradaException;
import br.com.EcoVolt.model.Usuario;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao {

    public Usuario cadastrar(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO T_EV_USUARIO (ID_USUARIO, NM_USUARIO, DS_EMAIL, DS_SENHA, DT_NASCIMENTO, " +
                "DS_GENERO, DS_CIDADE, DS_ESTADO, DS_PAIS, QT_PONTOS, ID_NIVEL, ID_RANK) " +
                "VALUES (SQ_EV_USUARIO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, new String[]{"ID_USUARIO"})) {
            preencherStatement(stmt, usuario);
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    usuario.setId(rs.getInt(1));
                }
            }
            return usuario;
        }
    }

    public List<Usuario> listar() throws SQLException {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM T_EV_USUARIO ORDER BY NM_USUARIO";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(montar(rs));
            }
        }
        return lista;
    }

    public Usuario buscarPorId(int id) throws SQLException, EntidadeNaoEncontradaException {
        String sql = "SELECT * FROM T_EV_USUARIO WHERE ID_USUARIO = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (!rs.next()) {
                    throw new EntidadeNaoEncontradaException("Usuario nao encontrado.");
                }
                return montar(rs);
            }
        }
    }

    public void atualizar(Usuario usuario) throws SQLException, EntidadeNaoEncontradaException {
        String sql = "UPDATE T_EV_USUARIO SET NM_USUARIO = ?, DS_EMAIL = ?, DS_SENHA = ?, DT_NASCIMENTO = ?, " +
                "DS_GENERO = ?, DS_CIDADE = ?, DS_ESTADO = ?, DS_PAIS = ?, QT_PONTOS = ?, ID_NIVEL = ?, ID_RANK = ? " +
                "WHERE ID_USUARIO = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            preencherStatement(stmt, usuario);
            stmt.setInt(12, usuario.getId());
            if (stmt.executeUpdate() == 0) {
                throw new EntidadeNaoEncontradaException("Usuario nao encontrado para atualizacao.");
            }
        }
    }

    public void remover(int id) throws SQLException, EntidadeNaoEncontradaException {
        String sql = "DELETE FROM T_EV_USUARIO WHERE ID_USUARIO = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            if (stmt.executeUpdate() == 0) {
                throw new EntidadeNaoEncontradaException("Usuario nao encontrado para remocao.");
            }
        }
    }

    private void preencherStatement(PreparedStatement stmt, Usuario usuario) throws SQLException {
        stmt.setString(1, usuario.getNome());
        stmt.setString(2, usuario.getEmail());
        stmt.setString(3, usuario.getSenha());
        if (usuario.getDataNascimento() != null) {
            stmt.setDate(4, Date.valueOf(usuario.getDataNascimento()));
        } else {
            stmt.setNull(4, Types.DATE);
        }
        stmt.setString(5, usuario.getGenero());
        stmt.setString(6, usuario.getCidade());
        stmt.setString(7, usuario.getEstado());
        stmt.setString(8, usuario.getPais());
        stmt.setInt(9, usuario.getPontos());
        if (usuario.getIdNivel() != null) {
            stmt.setInt(10, usuario.getIdNivel());
        } else {
            stmt.setNull(10, Types.NUMERIC);
        }
        if (usuario.getIdRank() != null) {
            stmt.setInt(11, usuario.getIdRank());
        } else {
            stmt.setNull(11, Types.NUMERIC);
        }
    }

    private Usuario montar(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setId(rs.getInt("ID_USUARIO"));
        usuario.setNome(rs.getString("NM_USUARIO"));
        usuario.setEmail(rs.getString("DS_EMAIL"));
        usuario.setSenha(rs.getString("DS_SENHA"));
        Date nascimento = rs.getDate("DT_NASCIMENTO");
        if (nascimento != null) {
            usuario.setDataNascimento(nascimento.toLocalDate());
        }
        usuario.setGenero(rs.getString("DS_GENERO"));
        usuario.setCidade(rs.getString("DS_CIDADE"));
        usuario.setEstado(rs.getString("DS_ESTADO"));
        usuario.setPais(rs.getString("DS_PAIS"));
        usuario.setPontos(rs.getInt("QT_PONTOS"));
        int idNivel = rs.getInt("ID_NIVEL");
        if (!rs.wasNull()) {
            usuario.setIdNivel(idNivel);
        }
        int idRank = rs.getInt("ID_RANK");
        if (!rs.wasNull()) {
            usuario.setIdRank(idRank);
        }
        return usuario;
    }
}
