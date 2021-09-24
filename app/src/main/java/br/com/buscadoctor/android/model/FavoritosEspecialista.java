package br.com.buscadoctor.android.model;

/**
 * Created by andreribeiro on 15/07/17.
 */

public class FavoritosEspecialista {

    private Usuario usuario;
    private Especialista especialista;


    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Especialista getEspecialista() {
        return especialista;
    }

    public void setEspecialista(Especialista especialista) {
        this.especialista = especialista;
    }
}
