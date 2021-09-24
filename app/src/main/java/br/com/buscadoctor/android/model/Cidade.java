package br.com.buscadoctor.android.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Esta classe representa a Cidade, contem suas caracteristicas e acoes.
 * <p>
 * Utilizar somente nas operacoes especificas.
 *
 * @author Guilherme Mendes
 * @version 1.1.0
 * @since 1.0.0
 */
public class Cidade implements Parcelable{

    private int id;
    private String nome;
    private Estado estado;

    public Cidade() {
    }

    public Cidade(Parcel in) {
        readFromParcel(in);
    }

    public Cidade(int id, String nome, Estado estado) {
        this.id = id;
        this.nome = nome;
        this.estado = estado;
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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nome);
        dest.writeValue(estado);
    }

    public static final Creator CREATOR = new Creator<Cidade>() {
        @Override
        public Cidade createFromParcel(Parcel source) {
            return new Cidade(source);
        }

        @Override
        public Cidade[] newArray(int size) {
            return new Cidade[size];
        }
    };

    public void readFromParcel(Parcel in) {
        id = in.readInt();
        nome = in.readString();
        estado = (Estado) in.readValue(Estado.class.getClassLoader());
    }
}