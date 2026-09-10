package br.com.EcoVolt.model;

import java.time.LocalDate;

public class Interacao {

    private int id;
    private int idUsuario;
    private int idDica;
    private String tipoInteracao;
    private String comentario;
    private LocalDate dataInteracao;

    public Interacao() {
        this.dataInteracao = LocalDate.now();
    }

    public Interacao(int idUsuario, int idDica, String tipoInteracao, String comentario) {
        this.idUsuario = idUsuario;
        this.idDica = idDica;
        this.tipoInteracao = tipoInteracao;
        this.comentario = comentario;
        this.dataInteracao = LocalDate.now();
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

    public int getIdDica() {
        return idDica;
    }

    public void setIdDica(int idDica) {
        this.idDica = idDica;
    }

    public String getTipoInteracao() {
        return tipoInteracao;
    }

    public void setTipoInteracao(String tipoInteracao) {
        this.tipoInteracao = tipoInteracao;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDate getDataInteracao() {
        return dataInteracao;
    }

    public void setDataInteracao(LocalDate dataInteracao) {
        this.dataInteracao = dataInteracao;
    }

    @Override
    public String toString() {
        return "Interacao{id=" + id + ", idUsuario=" + idUsuario + ", idDica=" + idDica + ", tipo='" + tipoInteracao + "'}";
    }
}
