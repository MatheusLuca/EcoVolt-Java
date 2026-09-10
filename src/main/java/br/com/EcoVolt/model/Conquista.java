package br.com.EcoVolt.model;

public class Conquista {

    private int id;
    private String nome;
    private String descricao;
    private int pontosPremio;

    public Conquista() {
    }

    public Conquista(String nome, String descricao, int pontosPremio) {
        this.nome = nome;
        this.descricao = descricao;
        this.pontosPremio = pontosPremio;
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

    @Override
    public String toString() {
        return "Conquista{id=" + id + ", nome='" + nome + "', pontosPremio=" + pontosPremio + '}';
    }
}
