package br.com.buscadoctor.android.enums;

/**
 * @author Guilherme Mendes
 * @version 1.0.0
 * @since 1.0.0
 */
public enum ConvenioEnum {

    PARTICULAR(8);

    private int id;

    ConvenioEnum(int id) {
        this.id = id;
    }

    public int getID() {
        return id;
    }
}