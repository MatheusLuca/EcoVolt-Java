package br.com.EcoVolt.service;

import br.com.EcoVolt.dao.NivelDao;
import br.com.EcoVolt.model.Nivel;

import java.sql.SQLException;
import java.util.List;

public class NivelService {

    private final NivelDao nivelDao = new NivelDao();

    public List<Nivel> listar() throws SQLException {
        return nivelDao.listar();
    }
}
