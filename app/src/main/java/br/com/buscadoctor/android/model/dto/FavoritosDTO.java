package br.com.buscadoctor.android.model.dto;

import br.com.buscadoctor.android.model.Consultorio;
import br.com.buscadoctor.android.model.Especialidade;
import br.com.buscadoctor.android.model.Especialista;
import br.com.buscadoctor.android.model.EspecialistaEspecialidade;

/**
 * Created by andreribeiro on 10/07/17.
 */

public class FavoritosDTO {

    private Especialista especialista;
    private Consultorio consultorio;
    private Especialidade especialidadeprincipal;
    private String tipo;

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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Especialidade getEspecialidadeprincipal() {
        return especialidadeprincipal;
    }

    public void setEspecialidadeprincipal(Especialidade especialidadeprincipal) {
        this.especialidadeprincipal = especialidadeprincipal;
    }
}
