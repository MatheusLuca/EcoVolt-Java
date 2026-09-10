package br.com.EcoVolt.model;

import java.time.LocalDate;

public class Missao {

    private int id;
    private String nome;
    private String descricao;
    private int pontosPremio;
    private LocalDate dataInicio;
    private LocalDate dataFim;

    public Missao() {
    }

    public Missao(String nome, String descricao, int pontosPremio) {
        this.nome = nome;
        this.descricao = descricao;
        this.pontosPremio = pontosPremio;
    }

    public boolean estaAtiva() {
        LocalDate hoje = LocalDate.now();
        if (dataInicio != null && hoje.isBefore(dataInicio)) {
            return false;
        }
        if (dataFim != null && hoje.isAfter(dataFim)) {
            return false;
        }
        return true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getPontosPremio() {
        return pontosPremio;
    }

    public void setPontosPremio(int pontosPremio) {
        if (pontosPremio < 0) {
            throw new IllegalArgumentException("Pontos premio nao podem ser negativos.");
        }
        this.pontosPremio = pontosPremio;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    @Override
    public String toString() {
        return "Missao{id=" + id +
                ", nome='" + nome + '\'' +
                ", pontosPremio=" + pontosPremio +
                ", ativa=" + estaAtiva() + '}';
    }
}
