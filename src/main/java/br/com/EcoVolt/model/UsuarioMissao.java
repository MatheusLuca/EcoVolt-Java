package br.com.EcoVolt.model;

public class UsuarioMissao {

    private int id;
    private int idUsuario;
    private int idMissao;
    private String status;

    public UsuarioMissao() {
        this.status = "EM_ANDAMENTO";
    }

    public UsuarioMissao(int idUsuario, int idMissao) {
        this.idUsuario = idUsuario;
        this.idMissao = idMissao;
        this.status = "EM_ANDAMENTO";
    }

    public boolean concluida() {
        return "CONCLUIDA".equalsIgnoreCase(status);
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

    public int getIdMissao() {
        return idMissao;
    }

    public void setIdMissao(int idMissao) {
        this.idMissao = idMissao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UsuarioMissao{id=" + id + ", idUsuario=" + idUsuario + ", idMissao=" + idMissao + ", status='" + status + "'}";
    }
}
