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
        Scanner sc = new Scanner(System.in);

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
                opcao = Integer.parseInt(sc.nextLine());

                switch (opcao) {
                    case 1: {
                        System.out.print("Nome: ");
                        String nome = sc.nextLine();

                        System.out.print("E-mail: ");
                        String email = sc.nextLine();

                        System.out.print("Senha: ");
                        String senha = sc.nextLine();

                        System.out.print("Data nascimento (AAAA-MM-DD) ou Enter: ");
                        String nasc = sc.nextLine();

                        System.out.print("Genero: ");
                        String genero = sc.nextLine();

                        System.out.print("Cidade: ");
                        String cidade = sc.nextLine();

                        System.out.print("Estado (UF): ");
                        String estado = sc.nextLine();

                        System.out.print("Pais: ");
                        String pais = sc.nextLine();

                        Usuario usuario = new Usuario();
                        usuario.setNome(nome);
                        usuario.setEmail(email);
                        usuario.setSenha(senha);
                        if (!nasc.trim().isEmpty()) {
                            usuario.setDataNascimento(LocalDate.parse(nasc));
                        }
                        usuario.setGenero(genero);
                        usuario.setCidade(cidade);
                        usuario.setEstado(estado);
                        usuario.setPais(pais);

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
                        int idUsuario = Integer.parseInt(sc.nextLine());
                        Usuario usuario = usuarioService.buscarPorId(idUsuario);

                        System.out.print("Novo nome: ");
                        String nome = sc.nextLine();

                        System.out.print("Novo e-mail: ");
                        String email = sc.nextLine();

                        System.out.print("Nova senha: ");
                        String senha = sc.nextLine();

                        System.out.print("Cidade: ");
                        String cidade = sc.nextLine();

                        System.out.print("Estado (UF): ");
                        String estado = sc.nextLine();

                        System.out.print("Pais: ");
                        String pais = sc.nextLine();

                        usuario.setNome(nome);
                        usuario.setEmail(email);
                        usuario.setSenha(senha);
                        usuario.setCidade(cidade);
                        usuario.setEstado(estado);
                        usuario.setPais(pais);

                        usuarioService.atualizar(usuario);
                        System.out.println("Usuario atualizado.");
                        break;
                    }
                    case 4: {
                        System.out.print("ID do usuario: ");
                        int idUsuario = Integer.parseInt(sc.nextLine());
                        usuarioService.remover(idUsuario);
                        System.out.println("Usuario removido.");
                        break;
                    }
                    case 5: {
                        System.out.print("ID do usuario: ");
                        int idUsuario = Integer.parseInt(sc.nextLine());

                        System.out.print("Data referencia (AAAA-MM-DD): ");
                        String dataTexto = sc.nextLine();

                        System.out.print("Numero da leitura: ");
                        String leitura = sc.nextLine();

                        System.out.print("Consumo kWh: ");
                        String kwhTexto = sc.nextLine();

                        System.out.print("Valor total da conta: ");
                        String valorTexto = sc.nextLine();

                        System.out.print("Comprovante: ");
                        String comprovante = sc.nextLine();

                        System.out.print("Pago? (S/N): ");
                        String pago = sc.nextLine();

                        ContaEnergia conta = new ContaEnergia();
                        conta.setIdUsuario(idUsuario);
                        conta.setDataReferencia(LocalDate.parse(dataTexto));
                        if (!leitura.trim().isEmpty()) {
                            conta.setNumeroLeitura(Integer.parseInt(leitura));
                        }
                        conta.setConsumoKwh(Double.parseDouble(kwhTexto.replace(',', '.')));
                        conta.setValorTotal(Double.parseDouble(valorTexto.replace(',', '.')));
                        conta.setComprovante(comprovante);
                        if (pago.equalsIgnoreCase("S")) {
                            conta.setStatusPagamento("S");
                        } else {
                            conta.setStatusPagamento("N");
                        }

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
                        int idConta = Integer.parseInt(sc.nextLine());
                        ContaEnergia conta = contaEnergiaService.buscarPorId(idConta);

                        System.out.print("ID do usuario: ");
                        int idUsuario = Integer.parseInt(sc.nextLine());

                        System.out.print("Data referencia (AAAA-MM-DD): ");
                        String dataTexto = sc.nextLine();

                        System.out.print("Consumo kWh: ");
                        String kwhTexto = sc.nextLine();

                        System.out.print("Valor total: ");
                        String valorTexto = sc.nextLine();

                        System.out.print("Comprovante: ");
                        String comprovante = sc.nextLine();

                        System.out.print("Pago? (S/N): ");
                        String pago = sc.nextLine();

                        conta.setIdUsuario(idUsuario);
                        conta.setDataReferencia(LocalDate.parse(dataTexto));
                        conta.setConsumoKwh(Double.parseDouble(kwhTexto.replace(',', '.')));
                        conta.setValorTotal(Double.parseDouble(valorTexto.replace(',', '.')));
                        conta.setComprovante(comprovante);
                        if (pago.equalsIgnoreCase("S")) {
                            conta.setStatusPagamento("S");
                        } else {
                            conta.setStatusPagamento("N");
                        }

                        contaEnergiaService.atualizar(conta);
                        System.out.println("Conta atualizada.");
                        break;
                    }
                    case 8: {
                        System.out.print("ID da conta: ");
                        int idConta = Integer.parseInt(sc.nextLine());
                        contaEnergiaService.remover(idConta);
                        System.out.println("Conta removida.");
                        break;
                    }
                    case 9: {
                        System.out.print("Nome da missao: ");
                        String nome = sc.nextLine();

                        System.out.print("Descricao: ");
                        String descricao = sc.nextLine();

                        System.out.print("Pontos premio: ");
                        int pontosPremio = Integer.parseInt(sc.nextLine());

                        System.out.print("Data inicio (AAAA-MM-DD) ou Enter: ");
                        String dataInicio = sc.nextLine();

                        System.out.print("Data fim (AAAA-MM-DD) ou Enter: ");
                        String dataFim = sc.nextLine();

                        Missao missao = new Missao();
                        missao.setNome(nome);
                        missao.setDescricao(descricao);
                        missao.setPontosPremio(pontosPremio);
                        if (!dataInicio.trim().isEmpty()) {
                            missao.setDataInicio(LocalDate.parse(dataInicio));
                        }
                        if (!dataFim.trim().isEmpty()) {
                            missao.setDataFim(LocalDate.parse(dataFim));
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
                        int idMissao = Integer.parseInt(sc.nextLine());
                        missaoService.remover(idMissao);
                        System.out.println("Missao removida.");
                        break;
                    }
                    case 12: {
                        System.out.print("Nome da dica: ");
                        String nome = sc.nextLine();

                        System.out.print("Descricao: ");
                        String descricao = sc.nextLine();

                        System.out.print("ID do usuario autor: ");
                        int idAutor = Integer.parseInt(sc.nextLine());

                        Dica dica = new Dica();
                        dica.setNome(nome);
                        dica.setDescricao(descricao);
                        dica.setIdUsuarioAutor(idAutor);

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
                        System.out.print("Nome da acao: ");
                        String nome = sc.nextLine();

                        System.out.print("Descricao: ");
                        String descricao = sc.nextLine();

                        System.out.print("Pontos base: ");
                        int pontosBase = Integer.parseInt(sc.nextLine());

                        AcaoSustentavel acao = new AcaoSustentavel();
                        acao.setNome(nome);
                        acao.setDescricao(descricao);
                        acao.setPontosBase(pontosBase);

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
                        int idUsuario = Integer.parseInt(sc.nextLine());
                        int pontos = pontuacaoService.pontuarUsuarioPorEconomia(idUsuario);
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
                        int idUsuario = Integer.parseInt(sc.nextLine());
                        Map<String, Double> resumo = contaEnergiaService.gerarResumoConsumoUsuario(idUsuario);
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

        sc.close();
    }
}
