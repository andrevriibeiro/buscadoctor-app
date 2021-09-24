package br.com.buscadoctor.android.model;

/**
 * @author Guilherme Mendes
 * @version 1.0.0
 * @since 1.0.0
 */
public class Origem {

    private int id;
    private String origem;

    public Origem() {
    }

    public Origem(int id, String origem) {
        this.id = id;
        this.origem = origem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }
}