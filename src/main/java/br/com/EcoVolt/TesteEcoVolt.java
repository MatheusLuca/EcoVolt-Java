package br.com.EcoVolt;

import br.com.EcoVolt.model.AcaoSustentavel;
import br.com.EcoVolt.model.ContaEnergia;
import br.com.EcoVolt.model.Dica;
import br.com.EcoVolt.model.Missao;
import br.com.EcoVolt.model.Nivel;
import br.com.EcoVolt.model.Pontuacao;
import br.com.EcoVolt.model.Recompensa;
import br.com.EcoVolt.model.Usuario;
import br.com.EcoVolt.service.AcaoSustentavelService;
import br.com.EcoVolt.service.ContaEnergiaService;
import br.com.EcoVolt.service.DicaService;
import br.com.EcoVolt.service.MissaoService;
import br.com.EcoVolt.service.NivelService;
import br.com.EcoVolt.service.PontuacaoService;
import br.com.EcoVolt.service.RankingService;
import br.com.EcoVolt.service.UsuarioService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class TesteEcoVolt {

    public static void main(String[] args) {
        try {
            System.out.println("===== TESTE ECOVOLT - SISTEMA ATUAL =====");
            System.out.println();

            // 1) Teste dos metodos de negocio nas classes model
            System.out.println("--- 1) Teste das regras de negocio (model) ---");
            Usuario usuarioModel = new Usuario("Matheus", "matheus@ecovolt.com", "123456");
            usuarioModel.setCidade("Sao Paulo");
            usuarioModel.setEstado("SP");
            usuarioModel.setPais("Brasil");

            ContaEnergia contaAnterior = new ContaEnergia(1, LocalDate.now().minusMonths(1), 220, 180);
            ContaEnergia contaAtual = new ContaEnergia(1, LocalDate.now(), 176, 145);
            Missao missaoModel = new Missao("Economizar 15%", "Reduzir o consumo mensal", 50);
            missaoModel.setDataInicio(LocalDate.now().minusDays(5));
            missaoModel.setDataFim(LocalDate.now().plusDays(25));
            Recompensa recompensa = new Recompensa("Voucher Eco", "Desconto sustentavel", 100);

            int pontosModel = contaAtual.calcularPontosSustentaveis(contaAnterior);
            usuarioModel.adicionarPontos(pontosModel);

            System.out.println(usuarioModel);
            System.out.println("Valor medio kWh: R$ " + contaAtual.calcularValorMedioKwh());
            System.out.println("Economia percentual: " + contaAtual.calcularEconomiaPercentual(contaAnterior) + "%");
            System.out.println("Classificacao: " + contaAtual.classificarConsumo());
            System.out.println("Missao ativa: " + missaoModel.estaAtiva());
            System.out.println("Pode resgatar recompensa: " + recompensa.usuarioPodeResgatar(usuarioModel.getPontos()));
            System.out.println();

            // 2) Teste com services e banco de dados
            System.out.println("--- 2) Teste dos services com banco ---");
            UsuarioService usuarioService = new UsuarioService();
            ContaEnergiaService contaService = new ContaEnergiaService();
            MissaoService missaoService = new MissaoService();
            DicaService dicaService = new DicaService();
            AcaoSustentavelService acaoService = new AcaoSustentavelService();
            PontuacaoService pontuacaoService = new PontuacaoService();
            RankingService rankingService = new RankingService();
            NivelService nivelService = new NivelService();

            Usuario usuario = new Usuario(
                    "Usuario Teste",
                    "teste" + System.currentTimeMillis() + "@ecovolt.com",
                    "123456"
            );
            usuario.setGenero("Nao informado");
            usuario.setCidade("Sao Paulo");
            usuario.setEstado("SP");
            usuario.setPais("Brasil");
            usuario = usuarioService.cadastrar(usuario);
            System.out.println("Usuario cadastrado: " + usuario);

            ContaEnergia conta1 = new ContaEnergia(usuario.getId(), LocalDate.now().minusMonths(1), 220, 180);
            conta1.setNumeroLeitura(1001);
            conta1.setComprovante("conta-mes-anterior.pdf");
            conta1.setStatusPagamento("S");
            conta1 = contaService.cadastrar(conta1);

            ContaEnergia conta2 = new ContaEnergia(usuario.getId(), LocalDate.now(), 176, 145);
            conta2.setNumeroLeitura(1002);
            conta2.setComprovante("conta-mes-atual.pdf");
            conta2.setStatusPagamento("N");
            conta2 = contaService.cadastrar(conta2);
            System.out.println("Contas cadastradas: " + conta1.getId() + " e " + conta2.getId());

            int pontosGerados = pontuacaoService.pontuarUsuarioPorEconomia(usuario.getId());
            System.out.println("Pontos gerados por economia: " + pontosGerados);

            Map<String, Double> resumo = contaService.gerarResumoConsumoUsuario(usuario.getId());
            System.out.println("Resumo consumo: " + resumo);

            List<Usuario> ranking = rankingService.gerarRankingUsuarios();
            System.out.println("Ranking (top):");
            for (int i = 0; i < ranking.size() && i < 3; i++) {
                Usuario u = ranking.get(i);
                System.out.println((i + 1) + "o - " + u.getNome() + " | " + u.getPontos() + " pontos");
            }

            Missao missao = new Missao("Missao Teste", "Testar cadastro de missao", 40);
            missao.setDataInicio(LocalDate.now());
            missao.setDataFim(LocalDate.now().plusDays(30));
            missao = missaoService.cadastrar(missao);
            System.out.println("Missao cadastrada: " + missao);

            Dica dica = new Dica("Dica Teste", "Desligue aparelhos da tomada", usuario.getId());
            dica = dicaService.cadastrar(dica);
            System.out.println("Dica cadastrada: " + dica);

            AcaoSustentavel acao = new AcaoSustentavel("Acao Teste", "Separar reciclaveis", 15);
            acao = acaoService.cadastrar(acao);
            System.out.println("Acao cadastrada: " + acao);

            List<Pontuacao> pontuacoes = pontuacaoService.listar();
            System.out.println("Total de pontuacoes no banco: " + pontuacoes.size());

            List<Nivel> niveis = nivelService.listar();
            System.out.println("Niveis cadastrados:");
            for (Nivel nivel : niveis) {
                System.out.println(nivel);
            }

            // 3) Limpeza dos dados de teste
            System.out.println();
            System.out.println("--- 3) Limpeza dos dados de teste ---");
            acaoService.remover(acao.getId());
            dicaService.remover(dica.getId());
            missaoService.remover(missao.getId());
            usuarioService.remover(usuario.getId());
            System.out.println("Dados de teste removidos com sucesso.");

            System.out.println();
            System.out.println("===== TESTE FINALIZADO COM SUCESSO =====");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro de validacao: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getClass().getSimpleName() + " - " + e.getMessage());
            e.printStackTrace();
        }
    }
}
