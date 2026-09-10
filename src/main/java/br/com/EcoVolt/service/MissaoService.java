package br.com.EcoVolt.service;

import br.com.EcoVolt.dao.MissaoDao;
import br.com.EcoVolt.exception.EcoVoltException;
import br.com.EcoVolt.model.Missao;

import java.sql.SQLException;
import java.util.List;

public class MissaoService {

    private final MissaoDao missaoDao = new MissaoDao();

    public Missao cadastrar(Missao missao) throws EcoVoltException, SQLException {
        if (missao.getNome() == null || missao.getNome().trim().isEmpty()) {
            throw new EcoVoltException("Nome da missao e obrigatorio.");
        }
        return missaoDao.cadastrar(missao);
    }

    public List<Missao> listar() throws SQLException {
        return missaoDao.listar();
    }

    public Missao buscarPorId(int id) throws EcoVoltException, SQLException {
        return missaoDao.buscarPorId(id);
    }

    public void atualizar(Missao missao) throws EcoVoltException, SQLException {
        missaoDao.atualizar(missao);
    }

    public void remover(int id) throws EcoVoltException, SQLException {
        missaoDao.remover(id);
    }
}
