package br.com.EcoVolt.service;

import br.com.EcoVolt.dao.ContaEnergiaDao;
import br.com.EcoVolt.dao.PontuacaoDao;
import br.com.EcoVolt.dao.UsuarioDao;
import br.com.EcoVolt.exception.EcoVoltException;
import br.com.EcoVolt.model.Usuario;

import java.sql.SQLException;
import java.util.List;

public class UsuarioService {

    private final UsuarioDao usuarioDao = new UsuarioDao();
    private final PontuacaoDao pontuacaoDao = new PontuacaoDao();
    private final ContaEnergiaDao contaEnergiaDao = new ContaEnergiaDao();

    public Usuario cadastrar(Usuario usuario) throws EcoVoltException, SQLException {
        validarUsuario(usuario);
        return usuarioDao.cadastrar(usuario);
    }

    public List<Usuario> listar() throws SQLException {
        return usuarioDao.listar();
    }

    public Usuario buscarPorId(int id) throws EcoVoltException, SQLException {
        return usuarioDao.buscarPorId(id);
    }

    public void atualizar(Usuario usuario) throws EcoVoltException, SQLException {
        validarUsuario(usuario);
        usuarioDao.atualizar(usuario);
    }

    public void remover(int id) throws EcoVoltException, SQLException {
        // Remove vinculos antes do usuario para nao violar FK do Oracle
        pontuacaoDao.removerPorUsuario(id);
        contaEnergiaDao.removerPorUsuario(id);
        usuarioDao.remover(id);
    }

    private void validarUsuario(Usuario usuario) throws EcoVoltException {
        if (usuario == null || !usuario.possuiPerfilCompleto()) {
            throw new EcoVoltException("Nome, e-mail e senha sao obrigatorios.");
        }
        if (!usuario.getEmail().contains("@")) {
            throw new EcoVoltException("E-mail invalido.");
        }
        if (usuario.getSenha().length() < 6) {
            throw new EcoVoltException("A senha deve possuir ao menos 6 caracteres.");
        }
    }
}
