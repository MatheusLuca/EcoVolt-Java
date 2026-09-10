package br.com.EcoVolt.service;

import br.com.EcoVolt.dao.UsuarioDao;
import br.com.EcoVolt.model.Usuario;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RankingService {

    private final UsuarioDao usuarioDao = new UsuarioDao();

    public List<Usuario> gerarRankingUsuarios() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>(usuarioDao.listar());
        usuarios.sort(Comparator.comparingInt(Usuario::getPontos).reversed());
        return usuarios;
    }
}
