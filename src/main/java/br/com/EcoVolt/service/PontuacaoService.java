package br.com.EcoVolt.service;

import br.com.EcoVolt.dao.ContaEnergiaDao;
import br.com.EcoVolt.dao.NivelDao;
import br.com.EcoVolt.dao.PontuacaoDao;
import br.com.EcoVolt.dao.UsuarioDao;
import br.com.EcoVolt.exception.EcoVoltException;
import br.com.EcoVolt.model.ContaEnergia;
import br.com.EcoVolt.model.Nivel;
import br.com.EcoVolt.model.Pontuacao;
import br.com.EcoVolt.model.Usuario;

import java.sql.SQLException;
import java.util.List;

public class PontuacaoService {

    private final PontuacaoDao pontuacaoDao = new PontuacaoDao();
    private final UsuarioDao usuarioDao = new UsuarioDao();
    private final ContaEnergiaDao contaEnergiaDao = new ContaEnergiaDao();
    private final NivelDao nivelDao = new NivelDao();

    public List<Pontuacao> listar() throws SQLException {
        return pontuacaoDao.listar();
    }

    public int pontuarUsuarioPorEconomia(int idUsuario) throws EcoVoltException, SQLException {
        Usuario usuario = usuarioDao.buscarPorId(idUsuario);
        List<ContaEnergia> contas = contaEnergiaDao.listarPorUsuario(idUsuario);

        if (contas.size() < 2) {
            throw new EcoVoltException("Cadastre ao menos duas contas de energia para calcular economia.");
        }

        ContaEnergia atual = contas.get(0);
        ContaEnergia anterior = contas.get(1);
        int pontos = atual.calcularPontosSustentaveis(anterior);

        usuario.adicionarPontos(pontos);
        Nivel nivel = nivelDao.buscarPorPontos(usuario.getPontos());
        if (nivel != null) {
            usuario.setIdNivel(nivel.getId());
        }
        usuarioDao.atualizar(usuario);

        Pontuacao registro = new Pontuacao(idUsuario, pontos, "Economia de energia entre contas");
        pontuacaoDao.cadastrar(registro);
        return pontos;
    }
}
