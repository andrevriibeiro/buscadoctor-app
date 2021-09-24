package br.com.buscadoctor.android.model;

import java.util.Date;

/**
 * Classe que representa o Paciente
 *
 * @author Guilherme Mendes
 * @version 1.0.0
 * @since 1.0.0
 */
public class Paciente {

    private int id;
    private Integer numeroregistro;
    private Usuario usuario;
    private Consultorio consultorio;
    private String observacao;
    private Date createdAt;

    public Paciente(int id, Integer numeroregistro, Usuario usuario, Consultorio consultorio, String observacao, Date createdAt) {
        this.id = id;
        this.numeroregistro = numeroregistro;
        this.usuario = usuario;
        this.consultorio = consultorio;
        this.observacao = observacao;
        this.createdAt = createdAt;
    }

    public Paciente() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getNumeroregistro() {
        return numeroregistro;
    }

    public void setNumeroregistro(Integer numeroregistro) {
        this.numeroregistro = numeroregistro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Consultorio getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(Consultorio consultorio) {
        this.consultorio = consultorio;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}