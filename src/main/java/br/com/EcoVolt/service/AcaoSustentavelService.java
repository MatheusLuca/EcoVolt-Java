package br.com.EcoVolt.service;

import br.com.EcoVolt.dao.AcaoSustentavelDao;
import br.com.EcoVolt.exception.EcoVoltException;
import br.com.EcoVolt.model.AcaoSustentavel;

import java.sql.SQLException;
import java.util.List;

public class AcaoSustentavelService {

    private final AcaoSustentavelDao acaoSustentavelDao = new AcaoSustentavelDao();

    public AcaoSustentavel cadastrar(AcaoSustentavel acao) throws EcoVoltException, SQLException {
        if (acao.getNome() == null || acao.getNome().trim().isEmpty()) {
            throw new EcoVoltException("Nome da acao e obrigatorio.");
        }
        return acaoSustentavelDao.cadastrar(acao);
    }

    public List<AcaoSustentavel> listar() throws SQLException {
        return acaoSustentavelDao.listar();
    }

    public void remover(int id) throws EcoVoltException, SQLException {
        acaoSustentavelDao.remover(id);
    }
}
