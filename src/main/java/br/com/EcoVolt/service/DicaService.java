package br.com.EcoVolt.service;

import br.com.EcoVolt.dao.DicaDao;
import br.com.EcoVolt.dao.UsuarioDao;
import br.com.EcoVolt.exception.EcoVoltException;
import br.com.EcoVolt.model.Dica;

import java.sql.SQLException;
import java.util.List;

public class DicaService {

    private final DicaDao dicaDao = new DicaDao();
    private final UsuarioDao usuarioDao = new UsuarioDao();

    public Dica cadastrar(Dica dica) throws EcoVoltException, SQLException {
        if (dica.getNome() == null || dica.getNome().trim().isEmpty()) {
            throw new EcoVoltException("Nome da dica e obrigatorio.");
        }
        usuarioDao.buscarPorId(dica.getIdUsuarioAutor());
        return dicaDao.cadastrar(dica);
    }

    public List<Dica> listar() throws SQLException {
        return dicaDao.listar();
    }

    public void remover(int id) throws EcoVoltException, SQLException {
        dicaDao.remover(id);
    }
}
