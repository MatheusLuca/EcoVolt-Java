package br.com.EcoVolt;

import br.com.EcoVolt.model.ContaEnergia;
import br.com.EcoVolt.model.Missao;
import br.com.EcoVolt.model.Recompensa;
import br.com.EcoVolt.model.Usuario;

import java.time.LocalDate;

public class TesteEcoVolt {

    public static void main(String[] args) {
        try {
            Usuario usuario = new Usuario("Matheus", "matheus@ecovolt.com", "123456");
            usuario.setCidade("Sao Paulo");
            usuario.setEstado("SP");
            usuario.setPais("Brasil");

            ContaEnergia anterior = new ContaEnergia(1, LocalDate.now().minusMonths(1), 220, 180);
            ContaEnergia atual = new ContaEnergia(1, LocalDate.now(), 176, 145);
            Missao missao = new Missao("Economizar 15%", "Reduzir o consumo mensal", 50);
            missao.setDataInicio(LocalDate.now().minusDays(5));
            missao.setDataFim(LocalDate.now().plusDays(25));
            Recompensa recompensa = new Recompensa("Voucher Eco", "Desconto sustentável", 100);

            int pontos = atual.calcularPontosSustentaveis(anterior);
            usuario.adicionarPontos(pontos);

            System.out.println("=== Teste dos metodos EcoVolt (MER T_EV) ===");
            System.out.println(usuario);
            System.out.println("Valor medio kWh: R$ " + atual.calcularValorMedioKwh());
            System.out.println("Economia percentual: " + atual.calcularEconomiaPercentual(anterior) + "%");
            System.out.println("Classificacao: " + atual.classificarConsumo());
            System.out.println("Missao ativa: " + missao.estaAtiva());
            System.out.println("Pode resgatar recompensa: " + recompensa.usuarioPodeResgatar(usuario.getPontos()));
        } catch (IllegalArgumentException e) {
            System.out.println("Erro de validacao: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
    }
}
