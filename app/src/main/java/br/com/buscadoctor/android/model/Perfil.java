package br.com.buscadoctor.android.model;

/**
 * @author Guilherme Mendes
 * @version 1.0.0
 * @since 1.0.0
 */
public class Perfil {

    private int id;
    private String nome;
    private String alias;
    private String user;
    private String senha;
    private String status;
    private Consultorio consultorio;

    public Perfil() {
    }

    public Perfil(int id, String nome, String alias, String user, String senha, String status, Consultorio consultorio) {
        this.id = id;
        this.nome = nome;
        this.alias = alias;
        this.user = user;
        this.senha = senha;
        this.status = status;
        this.consultorio = consultorio;
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

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Consultorio getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(Consultorio consultorio) {
        this.consultorio = consultorio;
    }
}