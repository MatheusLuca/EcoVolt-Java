package br.com.EcoVolt.model;

public class Nivel {

    private int id;
    private String nome;
    private int xpMinima;
    private int xpMaxima;

    public Nivel() {
    }

    public Nivel(String nome, int xpMinima, int xpMaxima) {
        this.nome = nome;
        this.xpMinima = xpMinima;
        this.xpMaxima = xpMaxima;
    }

    public boolean contemPontos(int pontos) {
        return pontos >= xpMinima && pontos <= xpMaxima;
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

    public int getXpMinima() {
        return xpMinima;
    }

    public void setXpMinima(int xpMinima) {
        this.xpMinima = xpMinima;
    }

    public int getXpMaxima() {
        return xpMaxima;
    }

    public void setXpMaxima(int xpMaxima) {
        this.xpMaxima = xpMaxima;
    }

    @Override
    public String toString() {
        return "Nivel{id=" + id + ", nome='" + nome + "', xpMinima=" + xpMinima + ", xpMaxima=" + xpMaxima + '}';
    }
}
