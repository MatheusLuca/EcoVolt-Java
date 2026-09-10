package br.com.EcoVolt.model;

public class Dica {

    private int id;
    private String nome;
    private String descricao;
    private int idUsuarioAutor;

    public Dica() {
    }

    public Dica(String nome, String descricao, int idUsuarioAutor) {
        this.nome = nome;
        this.descricao = descricao;
        this.idUsuarioAutor = idUsuarioAutor;
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

    public int getIdUsuarioAutor() {
        return idUsuarioAutor;
    }

    public void setIdUsuarioAutor(int idUsuarioAutor) {
        this.idUsuarioAutor = idUsuarioAutor;
    }

    @Override
    public String toString() {
        return "Dica{id=" + id + ", nome='" + nome + "', idUsuarioAutor=" + idUsuarioAutor + '}';
    }
}
