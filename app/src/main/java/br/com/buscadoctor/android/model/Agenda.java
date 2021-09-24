package br.com.buscadoctor.android.model;

import java.util.Date;

/**
 * Classe da que representa a Agenda
 *
 * @author Guilherme Mendes
 * @version 1.0.0
 * @since 1.0.0
 */
public class Agenda {

    private int id;
    private Consultorio consultorio;
    private Especialista especialista;
    private Date datainicio;
    private Date datafinal;
    private Integer tempoconsulta;
    private Integer diasemana;
    private Boolean liberado;
    private Boolean visivelapp;
    private TipoAgenda tipoagenda;
    private String observacao;
    private Date createdAt;

    public Agenda() {
    }

    public Agenda(int id, Consultorio consultorio, Especialista especialista, Date datainicio, Date datafinal,
                  Integer tempoconsulta, Integer diasemana, Boolean liberado, Boolean visivelapp, TipoAgenda tipoagenda,
                  String observacao, Date createdAt) {
        this.id = id;
        this.consultorio = consultorio;
        this.especialista = especialista;
        this.datainicio = datainicio;
        this.datafinal = datafinal;
        this.tempoconsulta = tempoconsulta;
        this.diasemana = diasemana;
        this.liberado = liberado;
        this.visivelapp = visivelapp;
        this.tipoagenda = tipoagenda;
        this.observacao = observacao;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Consultorio getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(Consultorio consultorio) {
        this.consultorio = consultorio;
    }

    public Especialista getEspecialista() {
        return especialista;
    }

    public void setEspecialista(Especialista especialista) {
        this.especialista = especialista;
    }

    public Date getDatainicio() {
        return datainicio;
    }

    public void setDatainicio(Date datainicio) {
        this.datainicio = datainicio;
    }

    public Date getDatafinal() {
        return datafinal;
    }

    public void setDatafinal(Date datafinal) {
        this.datafinal = datafinal;
    }

    public Integer getTempoconsulta() {
        return tempoconsulta;
    }

    public void setTempoconsulta(Integer tempoconsulta) {
        this.tempoconsulta = tempoconsulta;
    }

    public Integer getDiasemana() {
        return diasemana;
    }

    public void setDiasemana(Integer diasemana) {
        this.diasemana = diasemana;
    }

    public Boolean getLiberado() {
        return liberado;
    }

    public void setLiberado(Boolean liberado) {
        this.liberado = liberado;
    }

    public Boolean getVisivelapp() {
        return visivelapp;
    }

    public void setVisivelapp(Boolean visivelapp) {
        this.visivelapp = visivelapp;
    }

    public TipoAgenda getTipoagenda() {
        return tipoagenda;
    }

    public void setTipoagenda(TipoAgenda tipoagenda) {
        this.tipoagenda = tipoagenda;
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