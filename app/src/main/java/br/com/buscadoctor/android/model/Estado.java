package br.com.buscadoctor.android.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Esta classe representa o Estado, contem suas caracteristicas e acoes.
 * <p>
 * Utilizar somente nas operacoes especificas.
 *
 * @author Guilherme Mendes
 * @version 1.0.0
 * @since 1.0.0
 */
public class Estado implements Parcelable {

    private int id;
    private String nome;
    private String acronimo;

    public Estado() {
    }

    public Estado(Parcel in) {
        readFromParcel(in);
    }

    public Estado(int id, String nome, String acronimo) {
        this.id = id;
        this.nome = nome;
        this.acronimo = acronimo;
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

    public String getAcronimo() {
        return acronimo;
    }

    public void setAcronimo(String acronimo) {
        this.acronimo = acronimo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nome);
        dest.writeString(acronimo);
    }

    public static final Creator CREATOR = new Creator<Estado>() {
        @Override
        public Estado createFromParcel(Parcel source) {
            return new Estado(source);
        }

        @Override
        public Estado[] newArray(int size) {return new Estado[size];}
    };

    public void readFromParcel(Parcel in) {
        id = in.readInt();
        nome = in.readString();
        acronimo = in.readString();
    }
}