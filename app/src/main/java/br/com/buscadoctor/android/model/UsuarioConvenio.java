package br.com.buscadoctor.android.model;

/**
 * Created by andreribeiro on 04/07/17.
 */

public class UsuarioConvenio {

    private int id;
    private Convenio convenio;
    private Usuario usuario;
    private String tipo;
    private String numero;

    public UsuarioConvenio() {
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public Convenio getConvenio() {
        return convenio;
    }

    public void setConvenio(Convenio convenio) {
        this.convenio = convenio;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
