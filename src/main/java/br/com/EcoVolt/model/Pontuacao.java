package br.com.EcoVolt.model;

import java.time.LocalDate;

public class Pontuacao {

    private int id;
    private int idUsuario;
    private LocalDate dataGanho;
    private int pontos;
    private String motivo;

    public Pontuacao() {
        this.dataGanho = LocalDate.now();
    }

    public Pontuacao(int idUsuario, int pontos, String motivo) {
        this.idUsuario = idUsuario;
        this.pontos = pontos;
        this.motivo = motivo;
        this.dataGanho = LocalDate.now();
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

    public LocalDate getDataGanho() {
        return dataGanho;
    }

    public void setDataGanho(LocalDate dataGanho) {
        this.dataGanho = dataGanho;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    @Override
    public String toString() {
        return "Pontuacao{id=" + id +
                ", idUsuario=" + idUsuario +
                ", dataGanho=" + dataGanho +
                ", pontos=" + pontos +
                ", motivo='" + motivo + '\'' + '}';
    }
}
