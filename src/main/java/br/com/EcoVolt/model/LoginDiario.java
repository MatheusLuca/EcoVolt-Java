package br.com.EcoVolt.model;

import java.time.LocalDate;

public class LoginDiario {

    private int id;
    private int idUsuario;
    private LocalDate dataLogin;

    public LoginDiario() {
        this.dataLogin = LocalDate.now();
    }

    public LoginDiario(int idUsuario, LocalDate dataLogin) {
        this.idUsuario = idUsuario;
        this.dataLogin = dataLogin;
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

    public LocalDate getDataLogin() {
        return dataLogin;
    }

    public void setDataLogin(LocalDate dataLogin) {
        this.dataLogin = dataLogin;
    }

    @Override
    public String toString() {
        return "LoginDiario{id=" + id + ", idUsuario=" + idUsuario + ", dataLogin=" + dataLogin + '}';
    }
}
