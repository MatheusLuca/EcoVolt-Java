package br.com.EcoVolt.model;

public class Recompensa {

    private int id;
    private String nome;
    private String descricao;
    private int pontosCusto;

    public Recompensa() {
    }

    public Recompensa(String nome, String descricao, int pontosCusto) {
        this.nome = nome;
        this.descricao = descricao;
        this.pontosCusto = pontosCusto;
    }

    public boolean usuarioPodeResgatar(int pontosUsuario) {
        return pontosUsuario >= pontosCusto;
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

    public int getPontosCusto() {
        return pontosCusto;
    }

    public void setPontosCusto(int pontosCusto) {
        if (pontosCusto < 0) {
            throw new IllegalArgumentException("Custo em pontos nao pode ser negativo.");
        }
        this.pontosCusto = pontosCusto;
    }

    @Override
    public String toString() {
        return "Recompensa{id=" + id + ", nome='" + nome + "', pontosCusto=" + pontosCusto + '}';
    }
}
