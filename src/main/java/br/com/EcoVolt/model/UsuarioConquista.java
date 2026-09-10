package br.com.EcoVolt.model;

import java.time.LocalDate;

public class UsuarioConquista {

    private int id;
    private int idUsuario;
    private int idConquista;
    private LocalDate dataConquista;

    public UsuarioConquista() {
        this.dataConquista = LocalDate.now();
    }

    public UsuarioConquista(int idUsuario, int idConquista) {
        this.idUsuario = idUsuario;
        this.idConquista = idConquista;
        this.dataConquista = LocalDate.now();
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

    public int getIdConquista() {
        return idConquista;
    }

    public void setIdConquista(int idConquista) {
        this.idConquista = idConquista;
    }

    public LocalDate getDataConquista() {
        return dataConquista;
    }

    public void setDataConquista(LocalDate dataConquista) {
        this.dataConquista = dataConquista;
    }

    @Override
    public String toString() {
        return "UsuarioConquista{id=" + id + ", idUsuario=" + idUsuario + ", idConquista=" + idConquista + ", data=" + dataConquista + '}';
    }
}
