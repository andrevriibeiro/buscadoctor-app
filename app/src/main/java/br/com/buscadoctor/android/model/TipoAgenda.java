package br.com.buscadoctor.android.model;

/**
 * @author Guilherme Mendes
 * @version 1.0.0
 * @since 1.0.0
 */
public class TipoAgenda {

    private int id;
    private String tipo;
    private String configuracao;

    public TipoAgenda() {
    }

    public TipoAgenda(int id, String tipo, String configuracao) {
        this.id = id;
        this.tipo = tipo;
        this.configuracao = configuracao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getConfiguracao() {
        return configuracao;
    }

    public void setConfiguracao(String configuracao) {
        this.configuracao = configuracao;
    }
}