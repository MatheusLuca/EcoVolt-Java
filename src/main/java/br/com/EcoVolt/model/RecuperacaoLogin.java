package br.com.EcoVolt.model;

import java.time.LocalDate;

public class RecuperacaoLogin {

    private int id;
    private int idUsuario;
    private String codigo;
    private LocalDate dataSolicitacao;
    private String statusUtilizado;

    public RecuperacaoLogin() {
        this.dataSolicitacao = LocalDate.now();
        this.statusUtilizado = "N";
    }

    public RecuperacaoLogin(int idUsuario, String codigo) {
        this.idUsuario = idUsuario;
        this.codigo = codigo;
        this.dataSolicitacao = LocalDate.now();
        this.statusUtilizado = "N";
    }

    public boolean jaUtilizado() {
        return "S".equalsIgnoreCase(statusUtilizado);
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public LocalDate getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(LocalDate dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public String getStatusUtilizado() {
        return statusUtilizado;
    }

    public void setStatusUtilizado(String statusUtilizado) {
        this.statusUtilizado = statusUtilizado;
    }

    @Override
    public String toString() {
        return "RecuperacaoLogin{id=" + id + ", idUsuario=" + idUsuario + ", codigo='" + codigo + "', utilizado=" + jaUtilizado() + '}';
    }
}
