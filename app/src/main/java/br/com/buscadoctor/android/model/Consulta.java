package br.com.buscadoctor.android.model;

import java.util.Date;

/**
 * Esta classe representa a Consulta, contem suas caracteristicas e acoes.
 * <p>
 * Utilizar somente nas operacoes especificas.
 *
 * @author Guilherme Mendes <gmendes92@gmail.com>
 * @version 1.0.0
 * @since 1.0.0
 */
public class Consulta {

    private int id;
    private Paciente paciente;
    private Agenda agenda;
    private Status status;
    private Convenio convenio;
    private Especialidade especialidade;
    private Date horaInicio;
    private Date horaFinal;
    private String comentario;
    private Boolean notificado;
    private Perfil perfil;
    private Origem origem;
    private Date createdAt;

    public Consulta() {
    }

    public Consulta(int id, Paciente paciente, Agenda agenda, Status status, Convenio convenio, Especialidade especialidade,
                    Date horaInicio, Date horaFinal, String comentario, Boolean notificado, Perfil perfil, Origem origem, Date createdAt) {
        this.id = id;
        this.paciente = paciente;
        this.agenda = agenda;
        this.status = status;
        this.convenio = convenio;
        this.especialidade = especialidade;
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
        this.comentario = comentario;
        this.notificado = notificado;
        this.perfil = perfil;
        this.origem = origem;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Convenio getConvenio() {
        return convenio;
    }

    public void setConvenio(Convenio convenio) {
        this.convenio = convenio;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(Date horaFinal) {
        this.horaFinal = horaFinal;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Boolean getNotificado() {
        return notificado;
    }

    public void setNotificado(Boolean notificado) {
        this.notificado = notificado;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public Origem getOrigem() {
        return origem;
    }

    public void setOrigem(Origem origem) {
        this.origem = origem;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}