package br.com.EcoVolt.view;

import br.com.EcoVolt.exception.EcoVoltException;
import br.com.EcoVolt.model.AcaoSustentavel;
import br.com.EcoVolt.model.ContaEnergia;
import br.com.EcoVolt.model.Dica;
import br.com.EcoVolt.model.Missao;
import br.com.EcoVolt.model.Nivel;
import br.com.EcoVolt.model.Pontuacao;
import br.com.EcoVolt.model.Usuario;
import br.com.EcoVolt.service.AcaoSustentavelService;
import br.com.EcoVolt.service.ContaEnergiaService;
import br.com.EcoVolt.service.DicaService;
import br.com.EcoVolt.service.MissaoService;
import br.com.EcoVolt.service.NivelService;
import br.com.EcoVolt.service.PontuacaoService;
import br.com.EcoVolt.service.RankingService;
import br.com.EcoVolt.service.UsuarioService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class EcoVoltApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UsuarioService usuarioService = new UsuarioService();
        ContaEnergiaService contaEnergiaService = new ContaEnergiaService();
        MissaoService missaoService = new MissaoService();
        DicaService dicaService = new DicaService();
        AcaoSustentavelService acaoSustentavelService = new AcaoSustentavelService();
        PontuacaoService pontuacaoService = new PontuacaoService();
        RankingService rankingService = new RankingService();
        NivelService nivelService = new NivelService();
        int opcao = -1;

        while (opcao != 0) {
            System.out.println();
            System.out.println("===== ECOVOLT =====");
            System.out.println("1 - Cadastrar usuario");
            System.out.println("2 - Listar usuarios");
            System.out.println("3 - Atualizar usuario");
            System.out.println("4 - Remover usuario");
            System.out.println("5 - Cadastrar conta de energia");
            System.out.println("6 - Listar contas de energia");
            System.out.println("7 - Atualizar conta de energia");
            System.out.println("8 - Remover conta de energia");
            System.out.println("9 - Cadastrar missao");
            System.out.println("10 - Listar missoes");
            System.out.println("11 - Remover missao");
            System.out.println("12 - Cadastrar dica");
            System.out.println("13 - Listar dicas");
            System.out.println("14 - Cadastrar acao sustentavel");
            System.out.println("15 - Listar acoes sustentaveis");
            System.out.println("16 - Pontuar usuario por economia");
            System.out.println("17 - Ranking de usuarios");
            System.out.println("18 - Resumo de consumo do usuario");
            System.out.println("19 - Listar pontuacoes");
            System.out.println("20 - Listar niveis");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opcao: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1: {
                        Usuario usuario = new Usuario();
                        System.out.print("Nome: ");
                        usuario.setNome(scanner.nextLine());
                        System.out.print("E-mail: ");
                        usuario.setEmail(scanner.nextLine());
                        System.out.print("Senha: ");
                        usuario.setSenha(scanner.nextLine());
                        System.out.print("Data nascimento (AAAA-MM-DD) ou Enter: ");
                        String nasc = scanner.nextLine();
                        if (!nasc.trim().isEmpty()) {
                            usuario.setDataNascimento(LocalDate.parse(nasc));
                        }
                        System.out.print("Genero: ");
                        usuario.setGenero(scanner.nextLine());
                        System.out.print("Cidade: ");
                        usuario.setCidade(scanner.nextLine());
                        System.out.print("Estado (UF): ");
                        usuario.setEstado(scanner.nextLine());
                        System.out.print("Pais: ");
                        usuario.setPais(scanner.nextLine());
                        System.out.println(usuarioService.cadastrar(usuario));
                        break;
                    }
                    case 2: {
                        List<Usuario> usuarios = usuarioService.listar();
                        if (usuarios.isEmpty()) {
                            System.out.println("Nenhum usuario cadastrado.");
                        } else {
                            for (Usuario u : usuarios) {
                                System.out.println(u);
                            }
                        }
                        break;
                    }
                    case 3: {
                        System.out.print("ID do usuario: ");
                        Usuario usuario = usuarioService.buscarPorId(Integer.parseInt(scanner.nextLine()));
                        System.out.print("Novo nome: ");
                        usuario.setNome(scanner.nextLine());
                        System.out.print("Novo e-mail: ");
                        usuario.setEmail(scanner.nextLine());
                        System.out.print("Nova senha: ");
                        usuario.setSenha(scanner.nextLine());
                        System.out.print("Cidade: ");
                        usuario.setCidade(scanner.nextLine());
                        System.out.print("Estado (UF): ");
                        usuario.setEstado(scanner.nextLine());
                        System.out.print("Pais: ");
                        usuario.setPais(scanner.nextLine());
                        usuarioService.atualizar(usuario);
                        System.out.println("Usuario atualizado.");
                        break;
                    }
                    case 4: {
                        System.out.print("ID do usuario: ");
                        usuarioService.remover(Integer.parseInt(scanner.nextLine()));
                        System.out.println("Usuario removido.");
                        break;
                    }
                    case 5: {
                        ContaEnergia conta = new ContaEnergia();
                        System.out.print("ID do usuario: ");
                        conta.setIdUsuario(Integer.parseInt(scanner.nextLine()));
                        System.out.print("Data referencia (AAAA-MM-DD): ");
                        conta.setDataReferencia(LocalDate.parse(scanner.nextLine()));
                        System.out.print("Numero da leitura: ");
                        String leitura = scanner.nextLine();
                        if (!leitura.trim().isEmpty()) {
                            conta.setNumeroLeitura(Integer.parseInt(leitura));
                        }
                        System.out.print("Consumo kWh: ");
                        conta.setConsumoKwh(Double.parseDouble(scanner.nextLine().replace(',', '.')));
                        System.out.print("Valor total da conta: ");
                        conta.setValorTotal(Double.parseDouble(scanner.nextLine().replace(',', '.')));
                        System.out.print("Comprovante: ");
                        conta.setComprovante(scanner.nextLine());
                        System.out.print("Pago? (S/N): ");
                        conta.setStatusPagamento(scanner.nextLine().equalsIgnoreCase("S") ? "S" : "N");
                        System.out.println(contaEnergiaService.cadastrar(conta));
                        break;
                    }
                    case 6: {
                        List<ContaEnergia> contas = contaEnergiaService.listar();
                        if (contas.isEmpty()) {
                            System.out.println("Nenhuma conta cadastrada.");
                        } else {
                            for (ContaEnergia c : contas) {
                                System.out.println(c);
                            }
                        }
                        break;
                    }
                    case 7: {
                        System.out.print("ID da conta: ");
                        ContaEnergia conta = contaEnergiaService.buscarPorId(Integer.parseInt(scanner.nextLine()));
                        System.out.print("ID do usuario: ");
                        conta.setIdUsuario(Integer.parseInt(scanner.nextLine()));
                        System.out.print("Data referencia (AAAA-MM-DD): ");
                        conta.setDataReferencia(LocalDate.parse(scanner.nextLine()));
                        System.out.print("Consumo kWh: ");
                        conta.setConsumoKwh(Double.parseDouble(scanner.nextLine().replace(',', '.')));
                        System.out.print("Valor total: ");
                        conta.setValorTotal(Double.parseDouble(scanner.nextLine().replace(',', '.')));
                        System.out.print("Comprovante: ");
                        conta.setComprovante(scanner.nextLine());
                        System.out.print("Pago? (S/N): ");
                        conta.setStatusPagamento(scanner.nextLine().equalsIgnoreCase("S") ? "S" : "N");
                        contaEnergiaService.atualizar(conta);
                        System.out.println("Conta atualizada.");
                        break;
                    }
                    case 8: {
                        System.out.print("ID da conta: ");
                        contaEnergiaService.remover(Integer.parseInt(scanner.nextLine()));
                        System.out.println("Conta removida.");
                        break;
                    }
                    case 9: {
                        Missao missao = new Missao();
                        System.out.print("Nome da missao: ");
                        missao.setNome(scanner.nextLine());
                        System.out.print("Descricao: ");
                        missao.setDescricao(scanner.nextLine());
                        System.out.print("Pontos premio: ");
                        missao.setPontosPremio(Integer.parseInt(scanner.nextLine()));
                        System.out.print("Data inicio (AAAA-MM-DD) ou Enter: ");
                        String di = scanner.nextLine();
                        if (!di.trim().isEmpty()) {
                            missao.setDataInicio(LocalDate.parse(di));
                        }
                        System.out.print("Data fim (AAAA-MM-DD) ou Enter: ");
                        String df = scanner.nextLine();
                        if (!df.trim().isEmpty()) {
                            missao.setDataFim(LocalDate.parse(df));
                        }
                        System.out.println(missaoService.cadastrar(missao));
                        break;
                    }
                    case 10: {
                        List<Missao> missoes = missaoService.listar();
                        if (missoes.isEmpty()) {
                            System.out.println("Nenhuma missao cadastrada.");
                        } else {
                            for (Missao m : missoes) {
                                System.out.println(m);
                            }
                        }
                        break;
                    }
                    case 11: {
                        System.out.print("ID da missao: ");
                        missaoService.remover(Integer.parseInt(scanner.nextLine()));
                        System.out.println("Missao removida.");
                        break;
                    }
                    case 12: {
                        Dica dica = new Dica();
                        System.out.print("Nome da dica: ");
                        dica.setNome(scanner.nextLine());
                        System.out.print("Descricao: ");
                        dica.setDescricao(scanner.nextLine());
                        System.out.print("ID do usuario autor: ");
                        dica.setIdUsuarioAutor(Integer.parseInt(scanner.nextLine()));
                        System.out.println(dicaService.cadastrar(dica));
                        break;
                    }
                    case 13: {
                        List<Dica> dicas = dicaService.listar();
                        if (dicas.isEmpty()) {
                            System.out.println("Nenhuma dica cadastrada.");
                        } else {
                            for (Dica d : dicas) {
                                System.out.println(d);
                            }
                        }
                        break;
                    }
                    case 14: {
                        AcaoSustentavel acao = new AcaoSustentavel();
                        System.out.print("Nome da acao: ");
                        acao.setNome(scanner.nextLine());
                        System.out.print("Descricao: ");
                        acao.setDescricao(scanner.nextLine());
                        System.out.print("Pontos base: ");
                        acao.setPontosBase(Integer.parseInt(scanner.nextLine()));
                        System.out.println(acaoSustentavelService.cadastrar(acao));
                        break;
                    }
                    case 15: {
                        List<AcaoSustentavel> acoes = acaoSustentavelService.listar();
                        if (acoes.isEmpty()) {
                            System.out.println("Nenhuma acao cadastrada.");
                        } else {
                            for (AcaoSustentavel a : acoes) {
                                System.out.println(a);
                            }
                        }
                        break;
                    }
                    case 16: {
                        System.out.print("ID do usuario: ");
                        int pontos = pontuacaoService.pontuarUsuarioPorEconomia(Integer.parseInt(scanner.nextLine()));
                        System.out.println("Pontos gerados: " + pontos);
                        break;
                    }
                    case 17: {
                        List<Usuario> ranking = rankingService.gerarRankingUsuarios();
                        if (ranking.isEmpty()) {
                            System.out.println("Ranking vazio.");
                        } else {
                            for (int i = 0; i < ranking.size(); i++) {
                                Usuario u = ranking.get(i);
                                System.out.println((i + 1) + "o - " + u.getNome() + " - " + u.getPontos() + " pontos");
                            }
                        }
                        break;
                    }
                    case 18: {
                        System.out.print("ID do usuario: ");
                        Map<String, Double> resumo = contaEnergiaService.gerarResumoConsumoUsuario(Integer.parseInt(scanner.nextLine()));
                        System.out.println("Total kWh: " + resumo.get("totalKwh"));
                        System.out.println("Total pago: R$ " + resumo.get("totalConta"));
                        System.out.println("Media kWh: " + resumo.get("mediaKwh"));
                        System.out.println("Media da conta: R$ " + resumo.get("mediaConta"));
                        break;
                    }
                    case 19: {
                        List<Pontuacao> pontuacoes = pontuacaoService.listar();
                        if (pontuacoes.isEmpty()) {
                            System.out.println("Nenhuma pontuacao registrada.");
                        } else {
                            for (Pontuacao p : pontuacoes) {
                                System.out.println(p);
                            }
                        }
                        break;
                    }
                    case 20: {
                        List<Nivel> niveis = nivelService.listar();
                        for (Nivel n : niveis) {
                            System.out.println(n);
                        }
                        break;
                    }
                    case 0:
                        System.out.println("Sistema encerrado.");
                        break;
                    default:
                        System.out.println("Opcao invalida.");
                }
            } catch (EcoVoltException e) {
                System.out.println("Erro de regra de negocio: " + e.getMessage());
            } catch (SQLException e) {
                System.out.println("Erro ao acessar o banco de dados: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Digite apenas numeros validos.");
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
