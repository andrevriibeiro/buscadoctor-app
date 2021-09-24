package br.com.buscadoctor.android.model;

/**
 * @author Guilherme Mendes <gmendes92@gmail.com>
 * @see 1.0.0
 * @since 1.0.0
 */
public class Status {

    private int id;
    private String nome;

    public Status() {
    }

    public Status(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}