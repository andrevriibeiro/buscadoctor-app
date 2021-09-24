package br.com.buscadoctor.android.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Guilherme Mendes <gmendes92@gmail.com>
 * @version 1.0.0
 * @since 1.0.0
 */
public class Tipo implements Parcelable {

    private int id;
    private String nome;

    public Tipo() {
    }


    public Tipo(Parcel in) {
        readFromParcel(in);
    }

    public Tipo(int id, String nome) {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nome);
    }

    public static final Creator CREATOR = new Creator<Tipo>() {
        @Override
        public Tipo createFromParcel(Parcel source) {
            return new Tipo(source);
        }

        @Override
        public Tipo[] newArray(int size) {
            return new Tipo[size];
        }
    };

    public void readFromParcel(Parcel in) {
        id = in.readInt();
        nome = in.readString();
    }
}