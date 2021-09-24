package br.com.buscadoctor.android.model.dto;

import java.util.Date;

import br.com.buscadoctor.android.model.Agenda;
import br.com.buscadoctor.android.model.Consultorio;
import br.com.buscadoctor.android.model.Especialista;
import br.com.buscadoctor.android.model.Tipo;

/**
 * @author Guilherme Mendes
 * @version 1.0.0
 * @since 1.0.0
 */
public class HourDTO {

    private Tipo tipo;
    private Especialista especialista;
    private Consultorio consultorio;
    private int diasemana;
    private boolean disponivel;
    private Agenda agenda;
    private Date diafim;
    private Date diainicio;

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Especialista getEspecialista() {
        return especialista;
    }

    public void setEspecialista(Especialista especialista) {
        this.especialista = especialista;
    }

    public Consultorio getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(Consultorio consultorio) {
        this.consultorio = consultorio;
    }

    public int getDiasemana() {
        return diasemana;
    }

    public void setDiasemana(int diasemana) {
        this.diasemana = diasemana;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    public Date getDiafim() {
        return diafim;
    }

    public void setDiafim(Date diafim) {
        this.diafim = diafim;
    }

    public Date getDiainicio() {
        return diainicio;
    }

    public void setDiainicio(Date diainicio) {
        this.diainicio = diainicio;
    }
}