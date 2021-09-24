package br.com.buscadoctor.android.model;

/**
 * Esta classe representa o Configuracao do usuario, contem suas caracteristicas e acoes.
 * <p>
 * Utilizar somente nas operacoes especificas.
 *
 * @author Guilherme Mendes
 * @version 1.0.0
 * @since 1.0.0
 */
public class Configuracao {

    private int id;
    private int notificacaoconsulta;

    public Configuracao() {
    }

    public Configuracao(int id, int notificacaoconsulta) {
        this.id = id;
        this.notificacaoconsulta = notificacaoconsulta;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the notificacaoconsulta
     */
    public int getNotificacaoconsulta() {
        return notificacaoconsulta;
    }

    /**
     * @param notificacaoconsulta the notificacaoconsulta to set
     */
    public void setNotificacaoconsulta(int notificacaoconsulta) {
        this.notificacaoconsulta = notificacaoconsulta;
    }
}