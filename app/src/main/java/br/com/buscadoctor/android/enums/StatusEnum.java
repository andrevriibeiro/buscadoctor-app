package br.com.buscadoctor.android.enums;

/**
 * @author Guilherme Mendes
 * @version 1.0.0
 * @since 1.0.0
 */
public enum StatusEnum {

    PENDENTE_CONFIRMACAO(2);

    private int id;

    StatusEnum(int id) {
        this.id = id;
    }

    public int getID() {
        return id;
    }
}