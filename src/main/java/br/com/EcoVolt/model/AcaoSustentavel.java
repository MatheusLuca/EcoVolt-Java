package br.com.EcoVolt.model;

public class AcaoSustentavel {

    private int id;
    private String nome;
    private String descricao;
    private int pontosBase;

    public AcaoSustentavel() {
    }

    public AcaoSustentavel(String nome, String descricao, int pontosBase) {
        this.nome = nome;
        this.descricao = descricao;
        this.pontosBase = pontosBase;
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

    public int getPontosBase() {
        return pontosBase;
    }

    public void setPontosBase(int pontosBase) {
        if (pontosBase < 0) {
            throw new IllegalArgumentException("Pontos base nao podem ser negativos.");
        }
        this.pontosBase = pontosBase;
    }

    @Override
    public String toString() {
        return "AcaoSustentavel{id=" + id + ", nome='" + nome + "', pontosBase=" + pontosBase + '}';
    }
}
