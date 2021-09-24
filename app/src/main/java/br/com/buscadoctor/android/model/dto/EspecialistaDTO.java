package br.com.buscadoctor.android.model.dto;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import br.com.buscadoctor.android.model.Consultorio;
import br.com.buscadoctor.android.model.Especialidade;
import br.com.buscadoctor.android.model.Especialista;

/**
 * DTO Para manipulacao do especialista
 *
 * @author Guilherme Mendes
 * @version 1.0.0
 * @see Especialista
 * @since 1.0.0
 */
public class EspecialistaDTO {

    private Especialista especialista;
    private Consultorio consultorio;
    private ArrayList<Especialidade> especialidades;

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

    public ArrayList<Especialidade> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(ArrayList<Especialidade> especialidades) {
        this.especialidades = especialidades;
    }
}