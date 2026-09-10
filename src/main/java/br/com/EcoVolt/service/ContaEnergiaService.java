package br.com.EcoVolt.service;

import br.com.EcoVolt.dao.ContaEnergiaDao;
import br.com.EcoVolt.dao.UsuarioDao;
import br.com.EcoVolt.exception.EcoVoltException;
import br.com.EcoVolt.model.ContaEnergia;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContaEnergiaService {

    private final ContaEnergiaDao contaEnergiaDao = new ContaEnergiaDao();
    private final UsuarioDao usuarioDao = new UsuarioDao();

    public ContaEnergia cadastrar(ContaEnergia conta) throws EcoVoltException, SQLException {
        if (conta.getIdUsuario() <= 0) {
            throw new EcoVoltException("Informe um usuario valido.");
        }
        usuarioDao.buscarPorId(conta.getIdUsuario());
        return contaEnergiaDao.cadastrar(conta);
    }

    public List<ContaEnergia> listar() throws SQLException {
        return contaEnergiaDao.listar();
    }

    public ContaEnergia buscarPorId(int id) throws EcoVoltException, SQLException {
        return contaEnergiaDao.buscarPorId(id);
    }

    public void atualizar(ContaEnergia conta) throws EcoVoltException, SQLException {
        contaEnergiaDao.atualizar(conta);
    }

    public void remover(int id) throws EcoVoltException, SQLException {
        contaEnergiaDao.remover(id);
    }

    public Map<String, Double> gerarResumoConsumoUsuario(int idUsuario) throws EcoVoltException, SQLException {
        List<ContaEnergia> contas = contaEnergiaDao.listarPorUsuario(idUsuario);
        if (contas.isEmpty()) {
            throw new EcoVoltException("Nao existem contas cadastradas para este usuario.");
        }

        double totalKwh = 0;
        double totalConta = 0;
        for (ContaEnergia conta : contas) {
            totalKwh += conta.getConsumoKwh();
            totalConta += conta.getValorTotal();
        }

        Map<String, Double> resumo = new HashMap<>();
        resumo.put("totalKwh", totalKwh);
        resumo.put("totalConta", totalConta);
        resumo.put("mediaKwh", totalKwh / contas.size());
        resumo.put("mediaConta", totalConta / contas.size());
        return resumo;
    }
}
