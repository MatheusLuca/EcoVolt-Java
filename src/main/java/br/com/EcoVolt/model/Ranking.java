package br.com.EcoVolt.model;

import java.time.LocalDate;

public class Ranking {

    private int id;
    private String nome;
    private int posicao;
    private LocalDate dataReferencia;

    public Ranking() {
    }

    public Ranking(String nome, int posicao, LocalDate dataReferencia) {
        this.nome = nome;
        this.posicao = posicao;
        this.dataReferencia = dataReferencia;
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

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public LocalDate getDataReferencia() {
        return dataReferencia;
    }

    public void setDataReferencia(LocalDate dataReferencia) {
        this.dataReferencia = dataReferencia;
    }

    @Override
    public String toString() {
        return "Ranking{id=" + id + ", nome='" + nome + "', posicao=" + posicao + ", dataReferencia=" + dataReferencia + '}';
    }
}
