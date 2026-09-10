package br.com.EcoVolt.model;

public class ObjetivoMissao {

    private int id;
    private int idMissao;
    private String descricao;
    private Integer quantidadeMeta;

    public ObjetivoMissao() {
    }

    public ObjetivoMissao(int idMissao, String descricao, Integer quantidadeMeta) {
        this.idMissao = idMissao;
        this.descricao = descricao;
        this.quantidadeMeta = quantidadeMeta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdMissao() {
        return idMissao;
    }

    public void setIdMissao(int idMissao) {
        this.idMissao = idMissao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getQuantidadeMeta() {
        return quantidadeMeta;
    }

    public void setQuantidadeMeta(Integer quantidadeMeta) {
        this.quantidadeMeta = quantidadeMeta;
    }

    @Override
    public String toString() {
        return "ObjetivoMissao{id=" + id + ", idMissao=" + idMissao + ", descricao='" + descricao + "'}";
    }
}
