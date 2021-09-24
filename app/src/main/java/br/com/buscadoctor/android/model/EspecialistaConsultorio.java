package br.com.buscadoctor.android.model;

/**
 * Esta classe representa o Consultorio do especialista, contem suas
 * caracteristicas e acoes.
 * <p>
 * Utilizar somente nas operacoes especificas.
 *
 * @author Guilherme Mendes <gmendes92@gmail.com>
 * @version 1.0.0
 * @since 1.0.0
 */
public class EspecialistaConsultorio {

    private int id;
    private Especialista especialista;
    private Consultorio consultorio;
    private Boolean trabalhaferiado;

    public EspecialistaConsultorio() {
    }

    public EspecialistaConsultorio(int id, Especialista especialista, Consultorio consultorio, Boolean trabalhaferiado) {
        this.id = id;
        this.especialista = especialista;
        this.consultorio = consultorio;
        this.trabalhaferiado = trabalhaferiado;
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

    public Consultorio getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(Consultorio consultorio) {
        this.consultorio = consultorio;
    }

    public Boolean getTrabalhaferiado() {
        return trabalhaferiado;
    }

    public void setTrabalhaferiado(Boolean trabalhaferiado) {
        this.trabalhaferiado = trabalhaferiado;
    }
}