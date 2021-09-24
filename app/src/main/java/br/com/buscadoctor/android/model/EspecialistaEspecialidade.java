package br.com.buscadoctor.android.model;

/**
 * @author Guilherme Mendes <gmendes92@gmail.com>
 * @version 1.0.0
 * @since 1.0.0
 */
public class EspecialistaEspecialidade {

    private int id;
    private Especialista especialista;
    private Especialidade especialidade;
    private boolean principal;

    public EspecialistaEspecialidade() {
    }

    public EspecialistaEspecialidade(int id, Especialista especialista, Especialidade especialidade, boolean principal) {
        this.id = id;
        this.especialista = especialista;
        this.especialidade = especialidade;
        this.principal = principal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Especialista getEspecialista() {
        return especialista;
    }

    public void setEspecialista(Especialista especialista) {
        this.especialista = especialista;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }

    public boolean isPrincipal() {
        return principal;
    }

    public void setPrincipal(boolean principal) {
        this.principal = principal;
    }
}