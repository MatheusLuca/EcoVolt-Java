package br.com.EcoVolt.model;

import java.time.LocalDate;

public class Usuario {

    private int id;
    private String nome;
    private String email;
    private String senha;
    private LocalDate dataNascimento;
    private String genero;
    private String cidade;
    private String estado;
    private String pais;
    private int pontos;
    private Integer idNivel;
    private Integer idRank;

    public Usuario() {
    }

    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.pontos = 0;
    }

    public void adicionarPontos(int pontosGanhos) {
        if (pontosGanhos < 0) {
            throw new IllegalArgumentException("Os pontos ganhos nao podem ser negativos.");
        }
        this.pontos += pontosGanhos;
    }

    public boolean possuiPerfilCompleto() {
        return textoPreenchido(nome) && textoPreenchido(email) && textoPreenchido(senha);
    }

    public String mascararEmail() {
        if (!textoPreenchido(email) || !email.contains("@")) {
            return "E-mail invalido";
        }
        String[] partes = email.split("@", 2);
        String usuario = partes[0];
        String prefixo = usuario.length() <= 2 ? usuario : usuario.substring(0, 2);
        return prefixo + "***@" + partes[1];
    }

    private boolean textoPreenchido(String texto) {
        return texto != null && !texto.trim().isEmpty();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        if (pontos < 0) {
            throw new IllegalArgumentException("Os pontos nao podem ser negativos.");
        }
        this.pontos = pontos;
    }

    public Integer getIdNivel() {
        return idNivel;
    }

    public void setIdNivel(Integer idNivel) {
        this.idNivel = idNivel;
    }

    public Integer getIdRank() {
        return idRank;
    }

    public void setIdRank(Integer idRank) {
        this.idRank = idRank;
    }

    @Override
    public String toString() {
        return "Usuario{id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + mascararEmail() + '\'' +
                ", cidade='" + cidade + '\'' +
                ", estado='" + estado + '\'' +
                ", pontos=" + pontos +
                ", idNivel=" + idNivel +
                ", idRank=" + idRank + '}';
    }
}
