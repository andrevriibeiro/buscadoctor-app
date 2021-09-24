package br.com.buscadoctor.android.enums;

/**
 * @author Guilherme Mendes
 * @version 1.0.0
 * @since 1.0.0
 */
public enum OrigemEnum {

    ANDROID(2);

    private int id;

    OrigemEnum(int id) {
        this.id = id;
    }

    public int getID() {
        return id;
    }
}
