package br.com.EcoVolt;

import br.com.EcoVolt.conexao.ConnectionFactory;
import br.com.EcoVolt.model.AcaoSustentavel;
import br.com.EcoVolt.model.ContaEnergia;
import br.com.EcoVolt.model.Dica;
import br.com.EcoVolt.model.Missao;
import br.com.EcoVolt.model.Usuario;
import br.com.EcoVolt.service.AcaoSustentavelService;
import br.com.EcoVolt.service.ContaEnergiaService;
import br.com.EcoVolt.service.DicaService;
import br.com.EcoVolt.service.MissaoService;
import br.com.EcoVolt.service.NivelService;
import br.com.EcoVolt.service.PontuacaoService;
import br.com.EcoVolt.service.RankingService;
import br.com.EcoVolt.service.UsuarioService;

import java.sql.Connection;
import java.time.LocalDate;

public class ValidacaoSistema {

    public static void main(String[] args) {
        int erros = 0;

        System.out.println("=== 1) Teste de conexao Oracle ===");
        try (Connection conn = ConnectionFactory.getConnection()) {
            System.out.println("OK - Conexao aberta: " + !conn.isClosed());
        } catch (Exception e) {
            erros++;
            System.out.println("ERRO conexao: " + e.getMessage());
            System.out.println("Encerrando validacao com banco (sem conexao).");
            System.out.println("Total de erros: " + erros);
            return;
        }

        UsuarioService usuarioService = new UsuarioService();
        ContaEnergiaService contaService = new ContaEnergiaService();
        MissaoService missaoService = new MissaoService();
        DicaService dicaService = new DicaService();
        AcaoSustentavelService acaoService = new AcaoSustentavelService();
        PontuacaoService pontuacaoService = new PontuacaoService();
        RankingService rankingService = new RankingService();
        NivelService nivelService = new NivelService();

        try {
            System.out.println("=== 2) CRUD Usuario ===");
            Usuario usuario = new Usuario("Validacao Eco", "validacao" + System.currentTimeMillis() + "@ecovolt.com", "123456");
            usuario.setCidade("Sao Paulo");
            usuario.setEstado("SP");
            usuario.setPais("Brasil");
            usuario = usuarioService.cadastrar(usuario);
            System.out.println("OK - Usuario cadastrado id=" + usuario.getId());

            usuario.setCidade("Campinas");
            usuarioService.atualizar(usuario);
            Usuario buscado = usuarioService.buscarPorId(usuario.getId());
            if (!"Campinas".equals(buscado.getCidade())) {
                throw new RuntimeException("Atualizacao de usuario falhou.");
            }
            System.out.println("OK - Usuario atualizado/buscado");

            System.out.println("=== 3) CRUD Conta Energia ===");
            ContaEnergia conta1 = new ContaEnergia(usuario.getId(), LocalDate.now().minusMonths(1), 220, 180);
            conta1.setComprovante("conta-anterior.pdf");
            conta1.setStatusPagamento("S");
            conta1 = contaService.cadastrar(conta1);

            ContaEnergia conta2 = new ContaEnergia(usuario.getId(), LocalDate.now(), 176, 145);
            conta2.setComprovante("conta-atual.pdf");
            conta2.setStatusPagamento("N");
            conta2 = contaService.cadastrar(conta2);
            System.out.println("OK - Contas cadastradas ids=" + conta1.getId() + "," + conta2.getId());

            System.out.println("=== 4) Pontuacao por economia ===");
            int pontos = pontuacaoService.pontuarUsuarioPorEconomia(usuario.getId());
            System.out.println("OK - Pontos gerados=" + pontos);
            if (pontos <= 0) {
                throw new RuntimeException("Esperava pontos > 0 na economia.");
            }

            System.out.println("=== 5) Resumo / Ranking / Niveis ===");
            System.out.println("OK - Resumo: " + contaService.gerarResumoConsumoUsuario(usuario.getId()));
            System.out.println("OK - Ranking size=" + rankingService.gerarRankingUsuarios().size());
            System.out.println("OK - Niveis size=" + nivelService.listar().size());

            System.out.println("=== 6) Missao / Dica / Acao ===");
            Missao missao = new Missao("Missao Validacao", "Teste", 30);
            missao = missaoService.cadastrar(missao);
            System.out.println("OK - Missao id=" + missao.getId());

            Dica dica = new Dica("Dica Validacao", "Desligue luzes", usuario.getId());
            dica = dicaService.cadastrar(dica);
            System.out.println("OK - Dica id=" + dica.getId());

            AcaoSustentavel acao = new AcaoSustentavel("Acao Validacao", "Separar lixo", 10);
            acao = acaoService.cadastrar(acao);
            System.out.println("OK - Acao id=" + acao.getId());

            System.out.println("=== 7) Remocoes ===");
            acaoService.remover(acao.getId());
            dicaService.remover(dica.getId());
            missaoService.remover(missao.getId());
            contaService.remover(conta1.getId());
            contaService.remover(conta2.getId());
            usuarioService.remover(usuario.getId());
            System.out.println("OK - Remocoes concluidas");

        } catch (Exception e) {
            erros++;
            System.out.println("ERRO funcional: " + e.getClass().getSimpleName() + " - " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println();
        if (erros == 0) {
            System.out.println("VALIDACAO CONCLUIDA: nenhum erro encontrado.");
        } else {
            System.out.println("VALIDACAO CONCLUIDA: " + erros + " erro(s) encontrado(s).");
        }
    }
}
