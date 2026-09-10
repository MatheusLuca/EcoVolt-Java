package br.com.EcoVolt.model;

import java.time.LocalDate;

public class ContaEnergia {

    private int id;
    private int idUsuario;
    private LocalDate dataReferencia;
    private Integer numeroLeitura;
    private double consumoKwh;
    private double valorTotal;
    private String comprovante;
    private String statusPagamento;

    public ContaEnergia() {
        this.statusPagamento = "N";
        this.dataReferencia = LocalDate.now();
    }

    public ContaEnergia(int idUsuario, LocalDate dataReferencia, double consumoKwh, double valorTotal) {
        this.idUsuario = idUsuario;
        this.dataReferencia = dataReferencia;
        setConsumoKwh(consumoKwh);
        setValorTotal(valorTotal);
        this.statusPagamento = "N";
    }

    public double calcularValorMedioKwh() {
        if (consumoKwh == 0) {
            return 0;
        }
        return valorTotal / consumoKwh;
    }

    public double calcularEconomiaPercentual(ContaEnergia contaAnterior) {
        if (contaAnterior == null || contaAnterior.getConsumoKwh() == 0) {
            return 0;
        }
        double economia = contaAnterior.getConsumoKwh() - consumoKwh;
        return (economia / contaAnterior.getConsumoKwh()) * 100;
    }

    public int calcularPontosSustentaveis(ContaEnergia contaAnterior) {
        double economia = calcularEconomiaPercentual(contaAnterior);
        if (economia <= 0) {
            return 0;
        }
        return (int) Math.round(economia * 10);
    }

    public String classificarConsumo() {
        if (consumoKwh <= 120) {
            return "Baixo consumo";
        }
        if (consumoKwh <= 220) {
            return "Consumo moderado";
        }
        return "Alto consumo";
    }

    public boolean estaPaga() {
        return "S".equalsIgnoreCase(statusPagamento);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public LocalDate getDataReferencia() {
        return dataReferencia;
    }

    public void setDataReferencia(LocalDate dataReferencia) {
        this.dataReferencia = dataReferencia;
    }

    public Integer getNumeroLeitura() {
        return numeroLeitura;
    }

    public void setNumeroLeitura(Integer numeroLeitura) {
        this.numeroLeitura = numeroLeitura;
    }

    public double getConsumoKwh() {
        return consumoKwh;
    }

    public void setConsumoKwh(double consumoKwh) {
        if (consumoKwh < 0) {
            throw new IllegalArgumentException("O consumo em kWh nao pode ser negativo.");
        }
        this.consumoKwh = consumoKwh;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        if (valorTotal < 0) {
            throw new IllegalArgumentException("O valor da conta nao pode ser negativo.");
        }
        this.valorTotal = valorTotal;
    }

    public String getComprovante() {
        return comprovante;
    }

    public void setComprovante(String comprovante) {
        this.comprovante = comprovante;
    }

    public String getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(String statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    @Override
    public String toString() {
        return "ContaEnergia{id=" + id +
                ", idUsuario=" + idUsuario +
                ", dataReferencia=" + dataReferencia +
                ", consumoKwh=" + consumoKwh +
                ", valorTotal=" + valorTotal +
                ", statusPagamento='" + statusPagamento + '\'' +
                ", classificacao='" + classificarConsumo() + '\'' + '}';
    }
}
