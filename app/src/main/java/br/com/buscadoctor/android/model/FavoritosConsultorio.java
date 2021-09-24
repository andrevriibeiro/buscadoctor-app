package br.com.buscadoctor.android.model;

/**
 * Created by andreribeiro on 13/07/17.
 */

public class FavoritosConsultorio {

    private Usuario usuario;
    private Consultorio consultorio;

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
}
